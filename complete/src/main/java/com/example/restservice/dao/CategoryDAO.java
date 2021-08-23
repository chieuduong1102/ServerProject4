/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.dao;

import com.example.restservice.controller.CategoryJpaController;
import com.example.restservice.model.Category;
import com.example.restservice.model.Constant;
import com.example.restservice.model.NotifyMessage;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author T480s
 */
public class CategoryDAO implements Serializable {

    public CategoryDAO() {
    }

    public List<Category> findAll() {
        CategoryJpaController jpaController = new CategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.getCategoryList();
    }

    public Category checkCategoryExist(String cateName) {
        CategoryJpaController jpaController = new CategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.checkCategoryExist(cateName);
    }

    public NotifyMessage addCategory(Category cate) {
        CategoryJpaController categoryJpaController = new CategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if (checkCategoryExist(cate.getCategoryName()) != null) {
                msg.setMsg(Constant.CATEGORY_EXIST);
                msg.setCode(Constant.REGISTER_CODE_FAIL);
            } else if (checkCategoryExist(cate.getCategoryName()) == null) {
                categoryJpaController.create(cate);
                msg.setMsg(Constant.INSERT_CATEGORY_SUCCESS);
                msg.setCode(Constant.REGISTER_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    public NotifyMessage editCategory(Category cate) {
        CategoryJpaController categoryJpaController = new CategoryJpaController((Persistence.createEntityManagerFactory("ServerRESTfulAPIPU")));
        NotifyMessage msg = new NotifyMessage();
        try {
            if (cate.getCategoryName().isEmpty() || "".equals(cate.getCategoryName())) {
                msg.setMsg("Category's name can not be null");
                msg.setCode(Constant.UPDATE_CODE_FAIL);
            } else {
                int result = categoryJpaController.editCategory(cate);
                if (result == 1) {
                    msg.setMsg(Constant.EDIT_CATEGORY_SUCCESS);
                    msg.setCode(Constant.UPDATE_CODE_SUSCCESS);
                } else {
                    msg.setMsg("Else");
                    msg.setCode(Constant.UPDATE_CODE_FAIL);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            msg.setMsg("catch");
            msg.setCode(Constant.UPDATE_CODE_FAIL);
        }
        return msg;
    }

    public NotifyMessage deleteCategory(Integer id) {
        CategoryJpaController categoryJpaController = new CategoryJpaController((Persistence.createEntityManagerFactory("ServerRESTfulAPIPU")));
        NotifyMessage msg = new NotifyMessage();
        try {
            if (id <= 0 || id == null) {
                msg.setMsg("Invalid Category's id");
                msg.setCode(Constant.DELETE_CODE_FAIL);
            } else {
                categoryJpaController.deleteCategory(id);
                msg.setMsg(Constant.DELETE_CATEGORY_SUCCESS);
                msg.setCode(Constant.DELETE_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            msg.setMsg(Constant.DELETE_FAIL);
            msg.setCode(Constant.DELETE_CODE_FAIL);
        }
        return msg;
    }
}
