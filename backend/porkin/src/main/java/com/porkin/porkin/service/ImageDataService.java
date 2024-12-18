package com.porkin.porkin.service;

import com.porkin.porkin.entity.ImageData;
import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.repository.ImageDataRepository;
import com.porkin.porkin.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

  @Autowired
  private ImageDataRepository imageDataRepository;

  @Autowired
  private PersonRepository personRepository;

  public String uploadImage(MultipartFile file, String username) throws IOException {

    PersonEntity person = (PersonEntity) personRepository.findByUsername(username);

    imageDataRepository.save(ImageData.builder().name(person.getUsername())
        .type(file.getContentType()).imageData(ImageUtil.compressImage(file.getBytes())).build());


    person.setUserProfilePicture(imageDataRepository.findByName(username).get());
    return "Imagem adicionada! : " + person.getUsername();

  }

  @Transactional
  public ImageData getInfoByImageByName(String name) {
    Optional<ImageData> dbImage = imageDataRepository.findByName(name);

    return ImageData.builder()
        .name(dbImage.get().getName())
        .type(dbImage.get().getType())
        .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();
  }

  @Transactional
  public byte[] getImage(String name) {
    Optional<ImageData> dbImage = imageDataRepository.findByName(name);
    byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
    return image;
  }

}
