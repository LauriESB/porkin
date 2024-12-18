package com.porkin.porkin.paymentMethods.controller;

import com.porkin.porkin.paymentMethods.dto.PixDTO;
import com.porkin.porkin.paymentMethods.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pix")
public class PixController {

  @Autowired
  private PixService pixService;

  @GetMapping
  public List<PixDTO> listAll() {
    return pixService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody PixDTO pixDTO) {
    pixService.addPix(pixDTO);
  }

  @PutMapping
  public PixDTO update (@RequestBody PixDTO pixDTO) {
    return pixService.updatePix(pixDTO);
  }

  @DeleteMapping("/{id}/{idUser}")
  public void delete(@PathVariable("id") Long id, @PathVariable Long idUser) {
    pixService.deletePix(id, idUser);
  }


}
