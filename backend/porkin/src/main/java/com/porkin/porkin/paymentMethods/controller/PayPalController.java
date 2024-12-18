package com.porkin.porkin.paymentMethods.controller;

import com.porkin.porkin.paymentMethods.dto.PayPalDTO;
import com.porkin.porkin.paymentMethods.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payPal")
public class PayPalController {

  @Autowired
  private PayPalService payPalService;

  @GetMapping
  public List<PayPalDTO> listAll() {
    return payPalService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody PayPalDTO payPalDTO) {
    payPalService.insert(payPalDTO);
  }

  @PutMapping
  public PayPalDTO update(@RequestBody PayPalDTO payPalDTO) {
    return payPalService.update(payPalDTO);
  }

  @DeleteMapping("/{id}/{idUser}")
  public void delete(@PathVariable("id") Long id, @PathVariable Long idUser) {
    payPalService.delete(id, idUser);
  }

}
