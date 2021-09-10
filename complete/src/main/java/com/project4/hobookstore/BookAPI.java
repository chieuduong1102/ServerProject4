/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.service.BookService;
import com.project4.hobookstore.dto.BookDTO;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.ImageDTO;
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.model.Image;
import com.project4.hobookstore.service.CategoryService;
import com.project4.hobookstore.service.ImageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author DuongPH
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/book")
public class BookAPI {

    private List<Book> list = new ArrayList<>();

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllBook")
    public List<BookDTO> findAllBooks() throws IOException {
        BookService bookSer = new BookService();
        return bookSer.findAllBookFull().stream().map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getAllBookByCategory")
    public List<BookDTO> findAllBookOfCategory(@RequestParam(name = "cid") Integer cid) throws IOException {
        BookService bookSer = new BookService();
        return bookSer.findAllBookOfCategory(cid).stream().map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/getAllBookByPrice")
    public List<BookDTO> findAllBookByPrice(@RequestParam(name = "price") String PtoP) throws IOException {
        BookService bookSer = new BookService();
        return bookSer.findAllBookByPrice(PtoP).stream().map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/getNewBooks")
    public List<BookDTO> findNewBooks() throws IOException {
        BookService bookSer = new BookService();
        return bookSer.findAllBookFull().stream()
                .sorted((Book b1, Book b2) -> b2.getDateSale().compareTo(b1.getDateSale()))
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList()).subList(0, 9);
    }
    
    @GetMapping(path = "/bookInfo")
    public BookDTO getAdminInfo(@RequestParam(name = "bid") Integer bid) {
        BookService bookSer = new BookService();
        Book newBook = bookSer.findBookByBId(bid);
        BookDTO bookDTO = modelMapper.map(newBook, BookDTO.class);
        return bookDTO;
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public Book createBook(@RequestBody BookDTO book) {
        BookService bookSer = new BookService();
        
        Book newbook = new Book(book.getTitleBook(),
                book.getAuthor(),
                book.getManufacture(),
                book.getPublishingCompany(),
                book.getYearPublish(),
                book.getDateSale().toString(),
                book.getPrice(),
                book.getDescription(),
                book.getStatus());
//
//        bookSer.createBook(newbook);
        return bookSer.createBook(newbook);
    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public Book updateBook(@RequestBody BookDTO book) {
        BookService bookSer = new BookService();
        NotifyMessage msg = new NotifyMessage();
        Book updatedBook = new Book();
        try {
            Book updbook = new Book(
                    book.getBid(),
                    book.getTitleBook(),
                    book.getAuthor(),
                    book.getManufacture(),
                    book.getPublishingCompany(),
                    book.getYearPublish(),
                    book.getDateSale(),
                    book.getPrice(),
                    book.getDescription(),
                    book.getStatus());
            updatedBook = bookSer.updateBook(updbook);
        } catch (Exception e) {
            
        }
        return updatedBook;
    }
    
    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage deleteBook(@RequestBody BookDTO book) {
        BookService bookSer = new BookService();
        NotifyMessage msg = new NotifyMessage();
        Book delBook = new Book();
        try {
            
        } catch (Exception e) {
            
        }
        return msg;
    }
}
