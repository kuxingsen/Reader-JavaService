package com.monk.myreader.service;

import com.monk.myreader.bean.Book;

import java.util.List;


public interface BookService{
    Book selectById(Long id);
    String getName(Long id);
    int insert(Book book);
    int update(Long id, Book book);
    List<Book> selectBookList();

    List<Book> selectNewBookList(Integer count, Long categoryFatherId);

    List<Book> selectRangeBookList(Long rangeId, String duration);

    List<Book> selectSearchBookList(Integer page, Integer limit, String search, Long categoryId, Integer length, String order);

    List<Book> listTongji(Integer limit, Long categoryId, String orderBy);

    int delete(Long id);

    int count();
}
