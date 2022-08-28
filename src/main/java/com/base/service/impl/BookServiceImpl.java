package com.base.service.impl;

import com.base.entity.Book;
import com.base.mapper.BookMapper;
import com.base.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int addBookByParam(String name, String author) {
        return bookMapper.addBookByParam(name,author);
    }

    @Override
    public int delBook(Book book) {
        return bookMapper.delBook(book);
    }

    @Override
    public int delBookByParam(int id) {
        return bookMapper.delBookByParam(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public int updateBookByParam(int id, String name, String author) {
        return bookMapper.updateBookByParam(id,name,author);
    }

    @Override
    public Book queryBook(Book book) {
        return bookMapper.queryBook(book);
    }

    @Override
    public Book queryBookByParam(int id, String name, String author) {
        return bookMapper.queryBookByParam(id,name,author);
    }

    @Override
    public <T> List queryAllBookByParam(T t) {
        return bookMapper.queryAllBookByParam(t);
    }

    @Override
    public <T> List queryAllBook() {
        return bookMapper.queryAllBook();
    }
}
