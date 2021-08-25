/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.service.CategoryService;
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.base.NotifyMessage;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DuongPH
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryAPI {
    private List<Category> list = new ArrayList<>();
    
    @GetMapping("/getAllCategories")
    public List<Category> findAllCategories() {
        CategoryService categoryDAO = new CategoryService();
        return categoryDAO.findAll();
    }
    
    @PostMapping(path = "checkCategory", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public Category checkCategory(@RequestBody Category cate) {
        CategoryService categoryDAO = new CategoryService();
        NotifyMessage msg = new NotifyMessage();
        Category category = categoryDAO.checkCategoryExist(cate.getCategoryName());
        try {
           if(category == null)
               return new Category(123,"ok");
        } catch (Exception e) {

        }
        return new Category(123,"trung");
    }
    
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage addNewCategory(@RequestBody Category cate) {
        CategoryService categoryDAO = new CategoryService();
        Category newCate = new Category();
        NotifyMessage msg = new NotifyMessage();
        try {
            newCate.setCategoryName(cate.getCategoryName());
            msg = categoryDAO.addCategory(newCate);
        } catch (Exception e) {

        }
        return msg;
    }
    @PutMapping(path= "edit",consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage editCategory(@RequestBody Category cate){
        CategoryService categoryDAO = new CategoryService();
        NotifyMessage msg = new NotifyMessage();
        try{
            msg = categoryDAO.editCategory(cate);
        }catch(Exception e){
            
        }
        return msg;
    }
    
    @DeleteMapping (path= "delete")
    public NotifyMessage deleteCategory(Integer id){
        CategoryService categoryDAO = new CategoryService();
        NotifyMessage msg = new NotifyMessage();
        try{
            msg = categoryDAO.deleteCategory(id);
        }catch(Exception e){
            
        }
        return msg;
    }
}
