package com.monk.myreader.service.impl;

import com.github.pagehelper.PageHelper;
import com.monk.myreader.bean.Comment;
import com.monk.myreader.bean.CommentExample;
import com.monk.myreader.mapper.CommentMapper;
import com.monk.myreader.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2020/4/16 1:10
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper mapper;

    @Override
    public Comment selectById(Long id) {
        CommentExample example = new CommentExample();
        example.createCriteria().andIdEqualTo(id);
        List<Comment> list = mapper.selectByExampleWithBLOBs(example);
        return list.size()>0?list.get(0):null;
    }

    @Override
    public List<Comment> selectByUserId(Long id) {
        CommentExample example = new CommentExample();
        example.createCriteria().andUserIdEqualTo(id);
        return mapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Comment> selectByBookIdPage(Long bookId, Integer page, Integer limit) {
        CommentExample example = new CommentExample();
        example.createCriteria().andBookIdEqualTo(bookId);
        PageHelper.startPage(page, limit);
        return mapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Comment> selectByUserIdPage(Long userId, Integer page, Integer limit) {
        CommentExample example = new CommentExample();
        example.createCriteria().andUserIdEqualTo(userId);
        PageHelper.startPage(page, limit);
        return mapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int insert(Comment comment){
        return mapper.insert(comment);
    }

    @Override
    public int delete(Long id) {
        CommentExample example = new CommentExample();
        example.createCriteria().andIdEqualTo(id);
        return mapper.deleteByExample(example);
    }

    @Override
    public int count() {
        CommentExample example = new CommentExample();
        return mapper.countByExample(example);
    }


}
