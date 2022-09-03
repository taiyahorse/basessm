package com.base.controller;

import com.alibaba.druid.util.StringUtils;
import com.base.conf.SwitchDataSource;
import com.base.entity.Book;
import com.base.entity.ResultDTO;
import com.base.service.BookService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

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
    @SwitchDataSource(name = "slave")
    public ResultDTO queryAllBook(){
        logger.info("start  query  all book");
        List<Book> blist = bookService.queryAllBook();
        logger.info("success  all  query");
        return ResultDTO.success(blist);
    }
}
