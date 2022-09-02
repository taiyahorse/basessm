package com.base.controller;

import com.alibaba.druid.util.StringUtils;
import com.base.entity.Book;
import com.base.entity.ResultDTO;
import com.base.service.BookService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/queryBook")
    @ApiOperation("传入ID、书名、作者，查询相关的书籍")
    public ResultDTO queryBook(int id,String name,String author){
        Book book = new Book();
        if(id!=0){
            book.setId(id);
        }
        if(!StringUtils.isEmpty(name))
            book.setName(name);
        if(!StringUtils.isEmpty(author)){
            book.setAuthor(author);
        }
        return ResultDTO.success(bookService.queryBook(book));
    }

    @GetMapping("/getAllBook")
    @ApiOperation("获取所有书籍")
    public ResultDTO queryAllBook(){
        List<Book> blist = bookService.queryAllBook();
        return ResultDTO.success(blist);
    }
}
