package com.base.mapper;

import com.base.entity.Book;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
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

    <T> Page queryBookForPage(T t);
}
