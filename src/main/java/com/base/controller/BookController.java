package com.base.controller;

import com.alibaba.druid.util.StringUtils;
import com.base.entity.Book;
import com.base.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/queryBook")
    public Book queryBook(int id,String name,String author){
        Book book = new Book();
        if(id!=0){
            book.setId(id);
        }
        if(!StringUtils.isEmpty(name))
            book.setName(name);
        if(!StringUtils.isEmpty(author)){
            book.setAuthor(author);
        }
//        return bookService.queryBookByParam(id,null,null);
        return bookService.queryBook(book);
    }

    @RequestMapping("/getAllBook")
    public List<Book> queryAllBook(){
        return bookService.queryAllBook();
    }
}
