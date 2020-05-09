package com.monk.myreader.controller;

import com.monk.myreader.bean.Comment;
import com.monk.myreader.dto.CommentDTO;
import com.monk.myreader.dto.Query;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.BookService;
import com.monk.myreader.service.CommentService;
import com.monk.myreader.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Api("评论模块")
@Slf4j
public class CommentController{

    @Autowired
    private CommentService commentService;
    @Autowired
    private BookService bookService;
    @Autowired
    UserService userService;

    @PostMapping(value = "list/book/{id}")
    public Result listByBookId(@PathVariable("id")Long bookId, @RequestBody Query query){
        List<Comment> list = commentService.selectByBookIdPage(bookId,query.getPage(),query.getLimit());
        if(list != null){
            List<CommentDTO> data = getDTOs(list);

            return Result.SUCCESS(data,commentService.count());
        }
        return Result.FAIL("失败");
    }

    @GetMapping(value = "list/book/{id}")
    public Result listByBookId(@PathVariable("id")Long bookId,Integer page,Integer limit){
        List<Comment> list = commentService.selectByBookIdPage(bookId,page,limit);
        if(list != null){
            List<CommentDTO> data = getDTOs(list);
            return Result.SUCCESS(data,commentService.count());
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "list/user/{id}")
    public Result listByUserId(@PathVariable("id")Long bookId, @RequestBody Query query){
        List<Comment> list = commentService.selectByUserIdPage(bookId,query.getPage(),query.getLimit());
        if(list != null){
            List<CommentDTO> data = getDTOs(list);

            return Result.SUCCESS(data,commentService.count());
        }
        return Result.FAIL("失败");
    }

    @GetMapping(value = "list/user/{id}")
    public Result listByUserId(@PathVariable("id")Long userId){
        List<Comment> commentList = commentService.selectByUserId(userId);
        if(commentList != null){
            return Result.SUCCESS(commentList);
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "")
    public Result insert(Comment comment){
        if(comment != null){
            int i = commentService.insert(comment);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }


    @DeleteMapping(value = "{id}")
    public Result delete(@PathVariable("id") Long id){
        if(id != null){
            int i = commentService.delete(id);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    private List<CommentDTO> getDTOs(List<Comment> list) {
        List<CommentDTO> data = new ArrayList<>(list.size());
        for(Comment d : list) {
            CommentDTO dto = getDTO(d);
            data.add(dto);
        }
        return data;
    }

    private CommentDTO getDTO(Comment d) {
        CommentDTO dto = new CommentDTO();
        dto.setId(d.getId());
        dto.setBookId(d.getBookId());
        dto.setBookName(bookService.getName(d.getBookId()));
        dto.setUserId(d.getUserId());
        dto.setUserName(userService.getName(d.getUserId()));
        dto.setContent(d.getContent());
        dto.setStar(d.getStar());
        return dto;
    }
}
