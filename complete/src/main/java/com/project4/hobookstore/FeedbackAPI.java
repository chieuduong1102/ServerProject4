/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.RatingfeedbackDTO;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Ratingfeedback;
import com.project4.hobookstore.model.User;
import com.project4.hobookstore.service.BookService;
import com.project4.hobookstore.service.FeedbackService;
import com.project4.hobookstore.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author T480s
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/feedback")
public class FeedbackAPI {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getFeedbackList")
    public List<RatingfeedbackDTO> getFeedbackList() {
        FeedbackService feedBackService = new FeedbackService();
        return feedBackService.getFeedbackList().stream().map(book -> modelMapper.map(book, RatingfeedbackDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getFeedbackByBid")
    public List<RatingfeedbackDTO> getFeedbackByBookId(@RequestParam(name = "bid") Integer bookId) {
        FeedbackService feedBackService = new FeedbackService();
        return feedBackService.getFeedbackByBookId(bookId).stream().map(book -> modelMapper.map(book, RatingfeedbackDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage createFeedback(@RequestBody RatingfeedbackDTO feedback) {
        FeedbackService feedBackService = new FeedbackService();
        UserService userService = new UserService();
        BookService bookService = new BookService();
        NotifyMessage message = new NotifyMessage();
        try {
            Book book = bookService.findBookByBId(feedback.getBid());
            User user = userService.findUser(feedback.getUsername());
            Boolean createSuccess = false;
            Ratingfeedback newFeedback = new Ratingfeedback(feedback.getScoreRate(),
                    feedback.getFeedback(),
                    user,
                    book);
            createSuccess = feedBackService.createNewFeedback(newFeedback);
            if (createSuccess) {
                message.setCode(Constant.CREATE_SUCCESS);
                message.setMsg("Create Feedback Successfully!");
            } else {
                message.setCode(Constant.CREATE_FAIL);
                message.setMsg("Create Feedback failed!");
            }
        } catch (Exception e) {
            message.setCode(Constant.CREATE_FAIL);
            message.setMsg(e.toString());
        }
        return message;
    }

}
