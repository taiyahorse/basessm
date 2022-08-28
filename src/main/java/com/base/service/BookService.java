package com.base.service;

import com.base.entity.Book;

import java.util.List;

public interface BookService {
    int addBook(Book book);

    int addBookByParam(String name, String author);

    int delBook(Book book);

    int delBookByParam(int id);

    int updateBook(Book book);

    int updateBookByParam(int id, String name, String author);

    Book queryBook(Book book);

    Book queryBookByParam(int id, String name, String author);

    <T> List queryAllBookByParam(T t);

    <T> List queryAllBook();

}
