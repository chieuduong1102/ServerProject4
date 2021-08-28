/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.service.CategoryService;
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.CategoryDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
   @Autowired
    private ModelMapper modelMapper;
   
    @GetMapping("/getAllCategories")
    public List<CategoryDTO> findAllCategories() {
        CategoryService categorySer = new CategoryService();
        return categorySer.findAll().stream().map(book -> modelMapper.map(book, CategoryDTO.class))
                .collect(Collectors.toList());
    }
    
    @PostMapping(path = "checkCategory", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public Category checkCategory(@RequestBody Category cate) {
        CategoryService categorySer = new CategoryService();
        NotifyMessage msg = new NotifyMessage();
        Category category = categorySer.checkCategoryExist(cate.getCategoryName());
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
        CategoryService categorySer = new CategoryService();
        Category newCate = new Category();
        NotifyMessage msg = new NotifyMessage();
        try {
            newCate.setCategoryName(cate.getCategoryName());
            msg = categorySer.addCategory(newCate);
        } catch (Exception e) {

        }
        return msg;
    }
    @PutMapping(path= "edit",consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage editCategory(@RequestBody Category cate){
        CategoryService categorySer = new CategoryService();
        NotifyMessage msg = new NotifyMessage();
        try{
            msg = categorySer.editCategory(cate);
        }catch(Exception e){
            
        }
        return msg;
    }
    
    @DeleteMapping (path= "delete")
    public NotifyMessage deleteCategory(Integer id){
        CategoryService categorySer = new CategoryService();
        NotifyMessage msg = new NotifyMessage();
        try{
            msg = categorySer.deleteCategory(id);
        }catch(Exception e){
            
        }
        return msg;
    }
}
