package com.monk.myreader.service;


import com.monk.myreader.bean.Comment;

import java.util.List;

public interface CommentService{

    Comment selectById(Long id);
    List<Comment> selectByUserId(Long id);
    List<Comment> selectByBookIdPage(Long bookId, Integer page, Integer limit);
    List<Comment> selectByUserIdPage(Long userId, Integer page, Integer limit);
    int insert(Comment comment);
    int delete(Long id);


    int count();

}
