/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.controller.BookJpaController;
import com.project4.hobookstore.controller.ImageJpaController;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DuongPH
 */
public class ImageService {

    
    
    private final Path root = Paths.get("uploads");
    
    public ImageService() {
    }
    
    public List<Image> findAllImage(){
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        List<Image> listImage = new ArrayList<>();
        listImage = imgJpa.findImageEntities();
        return listImage;
    }
    
    public List<Image> findImagesByBid(Integer id){
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        List<Image> listImage = new ArrayList<>();
        listImage = imgJpa.findImageByBId(id);
        return listImage;
    }
    
    public void createImage(MultipartFile file, int bid) throws IOException {
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Book book = bookJpa.findBook(bid);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image img = new Image(fileName, book, file.getContentType(), file.getBytes());
        imgJpa.create(img);
      }
    
    public NotifyMessage uploadFile(MultipartFile file) {
        NotifyMessage msg = new NotifyMessage();
        try {
            Files.createDirectories(root);
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            msg.setCode(Constant.UPLOAD_IMG_SUCCESS);
            msg.setMsg("Upload success!");
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        return msg;
    }
}
