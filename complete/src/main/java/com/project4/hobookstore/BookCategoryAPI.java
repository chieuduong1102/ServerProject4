/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.CategoryDTO;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.service.BookCategoryService;
import com.project4.hobookstore.service.BookService;
import com.project4.hobookstore.service.CategoryService;
import java.util.ArrayList;
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
    public List<NotifyMessage> createBookCategory(@RequestBody List<CategoryDTO> categoryName, @RequestParam("bid") Integer bid) {
        List<NotifyMessage> listMsg = new ArrayList<>();
        BookCategoryService bcSer = new BookCategoryService();
        BookService bSer = new BookService();
        CategoryService cSer = new CategoryService();
        Book book = bSer.findBookByBId(bid);
        for (int i = 0; i < categoryName.size(); i++) {
            Bookcategory newBC = new Bookcategory();
            newBC.setBid(book);
            newBC.setCid(cSer.findIdOfCategoryByName(categoryName.get(i).getCategoryName()));
            listMsg.add(bcSer.createBookCategory(newBC));
        }
        return listMsg;
    }

    @PostMapping(path = "update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NotifyMessage> updateCategory(@RequestBody List<CategoryDTO> categoryName, @RequestParam("bid") Integer bid) throws Exception {
        List<NotifyMessage> listMsg = new ArrayList<>();
        BookCategoryService bcSer = new BookCategoryService();
        BookService bSer = new BookService();
        CategoryService cSer = new CategoryService();
        Book book = bSer.findBookByBId(bid);
        if (book != null) {
            bcSer.deleteBookCategoryByBid(book.getBid());
            for (int i = 0; i < categoryName.size(); i++) {
                Bookcategory upBC = new Bookcategory();
                upBC.setBid(book);
                upBC.setCid(cSer.findIdOfCategoryByName(categoryName.get(i).getCategoryName()));
                listMsg.add(bcSer.updateBookCategory(upBC));
            }
        } else {
            NotifyMessage msg = new NotifyMessage();
            msg.setCode(Constant.UPDATE_CODE_FAIL);
            msg.setMsg("Update FAIL!");
        }
        return listMsg;
    }

}
