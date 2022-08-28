package com.base.controller;

import com.base.entity.Book;
import com.base.entity.ResultDTO;
import com.base.service.RedisService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/redis")
@Data
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping(value = "/setBean")
    public ResultDTO setBean(int id, String name, String author) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        redisService.set(String.valueOf(id),book);
        return ResultDTO.success();
    }

    @GetMapping("/getBean")
    public ResultDTO getBean(int id){
        Book book = redisService.get(String.valueOf(id));
        return ResultDTO.success(book);
    }

    @GetMapping("/setBeanEX")
    public ResultDTO setBeanEx(int id, String name, String author){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        redisService.setEx(String.valueOf(id),book,10L);
        return ResultDTO.success();
    }

    @GetMapping("/delBean")
    public ResultDTO delBean(int id){
        String str = String.valueOf(id);
        Long res = redisService.del(str.getBytes());
        return ResultDTO.success(res);
    }

}
