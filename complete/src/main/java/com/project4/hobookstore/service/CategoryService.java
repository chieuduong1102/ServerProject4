/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.controller.CategoryJpaController;
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author DuongPH
 */
public class CategoryService implements Serializable {

    public CategoryService() {
    }

    public List<Category> findAll() {
        CategoryJpaController jpaController = new CategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.findCategoryEntities();
    }

    public Category findIdOfCategoryByName(String name) {
        CategoryJpaController jpaController = new CategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.findIdOfCategoryByName(name);
    }

    public Category findCategoryByCId(int cid) {
        CategoryJpaController jpaController = new CategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.findCategory(cid);
    }
    
    public NotifyMessage addCategory(Category cate) {
        CategoryJpaController categoryJpaController = new CategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if (categoryJpaController.findIdOfCategoryByName(cate.getCategoryName()) == null) {
                categoryJpaController.create(cate);
                msg.setMsg(Constant.INSERT_CATEGORY_SUCCESS);
                msg.setCode(Constant.REGISTER_CODE_SUSCCESS);
            } else {
                msg.setMsg(Constant.CATEGORY_EXIST);
                msg.setCode(Constant.REGISTER_CODE_FAIL);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
            msg.setMsg(Constant.DELETE_FAIL);
            msg.setCode(Constant.DELETE_CODE_FAIL);
        }
        return msg;
    }
}
