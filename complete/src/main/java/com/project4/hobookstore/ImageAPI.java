/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.BookDTO;
import com.project4.hobookstore.dto.ImageDTO;
import com.project4.hobookstore.model.Image;
import com.project4.hobookstore.service.ImageService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author DuongPH
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageAPI {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllImage")
    public ResponseEntity<List<ImageDTO>> getListFiles() {
        ImageService imgSer = new ImageService();
        List<Image> files = imgSer.findAllImage().stream().map(img -> {
            String fileUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/uploads/")
                    .path(img.getNameFile().toString()+"."+img.getType())
                    .toUriString();
            String type = img.getType();
            byte[] data = img.getData();
            return new Image(fileUri,
                    type,
                    data);
        }).collect(Collectors.toList());
        List<ImageDTO> listDTO = files.stream().map(image -> modelMapper.map(image, ImageDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    @GetMapping("/getByBid")
    public ResponseEntity<List<ImageDTO>> getImageByBid(@RequestParam("bid") Integer bid) {
        ImageService imgSer = new ImageService();
        List<Image> files = imgSer.findImagesByBid(bid).stream().map(img -> {
            String fileUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/uploads/")
                    .path(img.getNameFile().toString())
                    .toUriString();

            return new Image(fileUri,
                    img.getType(),
                    img.getData());
        }).collect(Collectors.toList());
        List<ImageDTO> listDTO = files.stream().map(image -> modelMapper.map(image, ImageDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    @PostMapping(path = "create")
    public NotifyMessage createImage(@RequestParam("file") MultipartFile img, @RequestParam("bid") Integer bid) {
        NotifyMessage msg = new NotifyMessage();
        ImageService imgSer = new ImageService();
        try {
            imgSer.createImage(img, bid);
            msg.setCode(Constant.CREATE_SUCCESS);
            msg.setMsg("Create Success!");
        } catch (Exception e) {
            msg.setCode(Constant.CREATE_FAIL);
            msg.setMsg("Create Fail!");
        }
        return msg;
    }

    @PostMapping("/upload")
    public NotifyMessage uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        ImageService imgSer = new ImageService();
        String message = "";
        try {
            Arrays.asList(files).stream().forEach(file -> {
                imgSer.uploadFile(file);
            });
            message = "Uploaded the files successfully!";
            return new NotifyMessage(message, Constant.UPLOAD_IMG_SUCCESS);
        } catch (Exception e) {
            message = "Fail to upload files!";
            return new NotifyMessage(message, Constant.UPLOAD_IMG_FAIL);
        }
    }
}
