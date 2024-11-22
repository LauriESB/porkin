package com.porkin.paymentMethods.controller;


import com.porkin.paymentMethods.dto.PixDTO;
import com.porkin.paymentMethods.service.PixService;
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

  @PutMapping("/{id}")
  public PixDTO update (@RequestBody PixDTO pixDTO, @PathVariable("id") Long id) {
    return pixService.updatePix(id, pixDTO);
  }

  @DeleteMapping("/{id}/{idUser}")
  public void delete(@PathVariable("id") Long id, @PathVariable Long idUser) {
    pixService.deletePix(id, idUser);
  }


}
