/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.CategoryDTO;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.service.BookCategoryService;
import com.project4.hobookstore.service.BookService;
import com.project4.hobookstore.service.CategoryService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DuongPH
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/bookcategory")
public class BookCategoryAPI {

    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NotifyMessage createBookCategory(@RequestBody List<CategoryDTO> categoryName, @RequestParam("bid") Integer bid) {
                NotifyMessage msg = new NotifyMessage();
                String str = "Category " + categoryName.get(0).getCategoryName() + " / " + categoryName.get(1).getCategoryName()+ " / " + bid.toString() ;
                System.out.println(""+ str);
        return msg;
    }
}