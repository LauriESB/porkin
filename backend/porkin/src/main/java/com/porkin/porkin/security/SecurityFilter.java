package com.porkin.porkin.security;

import com.porkin.porkin.repository.PersonRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;

  @Autowired
  PersonRepository personRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String requestURI = request.getRequestURI();

    // Pula o filtro para os endpoints públicos
    if (requestURI.contains("/auth/login") || requestURI.contains("/auth/register")) {
      filterChain.doFilter(request, response);
      return;
    }

    var token = this.recoverToken(request);
    if(token != null) {
      var login = tokenService.validateToken(token);
      UserDetails person = personRepository.findByUsername(login);

      var authentication = new UsernamePasswordAuthenticationToken(person, null, person.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    filterChain.doFilter(request, response);

  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if(authHeader == null) return null;
    return authHeader.replace("Bearer ", "");
  }

}
