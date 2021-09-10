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
import com.project4.hobookstore.controller.exceptions.NonexistentEntityException;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
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

    public List<Image> findAllImage() {
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        List<Image> listImage = new ArrayList<>();
        listImage = imgJpa.findImageEntities();
        return listImage;
    }

    public List<Image> findImagesByBid(Integer id) {
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        List<Image> listImage = new ArrayList<>();
        listImage = imgJpa.findImageByBId(id);
        return listImage;
    }

    public boolean createImage(MultipartFile file, int bid) throws IOException {
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Book book = bookJpa.findBook(bid);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        List<Image> listImage = new ArrayList<>();
        listImage = imgJpa.findImageByBId(bid);
        int count = 0;
        for (int i = 0; i < listImage.size(); i++) {
            if(listImage.get(i).getNameFile().equals(fileName)){
                count++;
            }
        }
        if(count==0){
            Image img = new Image(fileName, book);
            imgJpa.create(img);
            return true;
        } else {
            return false;
        }
    }

    public void updateImage(MultipartFile file, int bid) throws IOException, NonexistentEntityException, Exception {
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Book book = bookJpa.findBook(bid);
        if (book != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image img = new Image(fileName, book);
            imgJpa.edit(img);
        }
    }
    
    public void deleteImageByBid(int bid) throws NonexistentEntityException {
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        List<Image> list = imgJpa.findImageByBId(bid);
        for (int i = 0; i < list.size(); i++) {
            imgJpa.destroy(list.get(i).getIid());
        }
    }

    public NotifyMessage uploadFile(MultipartFile file) {
        NotifyMessage msg = new NotifyMessage();
        String strPath = FileSystems.getDefault().getPath("").toAbsolutePath() + "\\uploads\\" + file.getOriginalFilename();
        Path path = Paths.get(strPath);
        if (Files.exists(path)) {
            msg.setCode(Constant.UPLOAD_IMG_FAIL);
            msg.setMsg("IMG exist!");
        } else {
            try {
                Files.createDirectories(root);
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                msg.setCode(Constant.UPLOAD_IMG_SUCCESS);
                msg.setMsg("Upload success!");
            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
        return msg;
    }
}
