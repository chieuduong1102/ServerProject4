/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.dto;

import com.project4.hobookstore.model.Ratingfeedback;
import java.util.List;
import lombok.Data;

/**
 *
 * @author DuongPH
 */
@Data
public class BookDTO {
    private Integer bid;
    
    private String titleBook;
    private String author;
    private String manufacture;
    private String publishingCompany;
    private Integer yearPublish;
    private String dateSale;
    
    private Double price;
   
    private String description;
    private Integer status;
    private List<Ratingfeedback> ratingfeedbackList;
    private List<ImageDTO> imageList;
    private List<BookCategoryDTO> bookcategoryList;
//    private List<Orderdetail> orderdetailList;

    
}
