/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.controller.BookJpaController;
import com.project4.hobookstore.controller.RatingfeedbackJpaController;
import com.project4.hobookstore.controller.UserJpaController;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Ratingfeedback;
import com.project4.hobookstore.model.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author T480s
 */
public class FeedbackService implements Serializable {

    public FeedbackService() {
    }

    public List<Ratingfeedback> getFeedbackList() {
        RatingfeedbackJpaController ratingfeedbackJpaController = new RatingfeedbackJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return ratingfeedbackJpaController.findRatingfeedbackEntities();
    }

    public List<Ratingfeedback> getFeedbackByBookId(Integer bid) {
        RatingfeedbackJpaController ratingfeedbackJpaController = new RatingfeedbackJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookJpaController bookJpaController = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Book book =bookJpaController.findBook(bid);
        return ratingfeedbackJpaController.getFeedbackByBookId(book);
    }
    
    public Boolean checkFeebackExister(String username, Integer bid){
        RatingfeedbackJpaController ratingfeedbackJpaController = new RatingfeedbackJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        UserJpaController userJpaController = new UserJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookJpaController bookJpaController = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Book book =bookJpaController.findBook(bid);
        User user = userJpaController.findUserByName(username);
        return ratingfeedbackJpaController.checkFeedbackExist(user,book)==null;
    }

    public Boolean createNewFeedback(Ratingfeedback feedback) {
        RatingfeedbackJpaController ratingfeedbackJpaController = new RatingfeedbackJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        if (ratingfeedbackJpaController.createNewFeedback(feedback) == 1) {
            return true;
        }
        return false;
    }

}
