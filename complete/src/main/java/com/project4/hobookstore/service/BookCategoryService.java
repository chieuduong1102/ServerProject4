/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.controller.BookcategoryJpaController;
import com.project4.hobookstore.controller.exceptions.NonexistentEntityException;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author DuongPH
 */
public class BookCategoryService {

    public BookCategoryService() {
    }

    public List<Bookcategory> getAllBookCategory() {
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return bcJpa.findBookcategoryEntities();
    }

    public List<Bookcategory> getCategoryByBId(Integer bid) {
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return bcJpa.findBookCategoryByBId(bid);
    }

    public NotifyMessage createBookCategory(Bookcategory bc) {
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        if (bcJpa.findBookCategoryByBIdAndCId(bc.getBid().getBid(), bc.getCid().getCid()) == null) {
            bcJpa.create(bc);
            msg.setCode(Constant.CREATE_SUCCESS);
            msg.setMsg("Create BookCategory Success!");
        } else {
            msg.setCode(Constant.CREATE_FAIL);
            msg.setMsg("Category " + bc.getCid().getCategoryName() + " of this book is existed!");
        }
        return msg;
    }

    public NotifyMessage updateBookCategory(Bookcategory bc) throws Exception {
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        if (bcJpa.findBookCategoryByBIdAndCId(bc.getBid().getBid(), bc.getCid().getCid()) != null) {
            msg.setCode(Constant.UPDATE_CODE_SUSCCESS);
            msg.setMsg("Update BookCategory Success!");
        } else {

            bcJpa.create(bc);
            msg.setCode(Constant.UPDATE_CODE_SUSCCESS);
            msg.setMsg("Update new Category " + bc.getCid().getCategoryName() + " of this book!");
        }
        return msg;
    }

    public void deleteBookCategoryByBid(int bid) throws NonexistentEntityException {
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        List<Bookcategory> list = bcJpa.findBookCategoryByBId(bid);
        for (int i = 0; i < list.size(); i++) {
            bcJpa.destroy(list.get(i).getId());
        }
    }
}
