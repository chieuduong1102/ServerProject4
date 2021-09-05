/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.dto;

import lombok.Data;

/**
 *
 * @author DuongPH
 */
@Data
public class ImageDTO {
    private String nameFile;
    private String type;
    private long size;


    public ImageDTO() {
    }

    public ImageDTO(String nameFile, String type, long size) {
        this.nameFile = nameFile;
        this.type = type;
        this.size = size;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
