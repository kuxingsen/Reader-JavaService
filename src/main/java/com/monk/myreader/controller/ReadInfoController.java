package com.monk.myreader.controller;

import com.monk.myreader.bean.ReadInfo;
import com.monk.myreader.dto.Query;
import com.monk.myreader.dto.ReadInfoDTO;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.BookService;
import com.monk.myreader.service.ReadInfoService;
import com.monk.myreader.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("阅读信息接口")
@Slf4j
@RestController
@RequestMapping("read")
public class ReadInfoController{

    @Autowired
    ReadInfoService readInfoService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping(value = "list/book/{id}")
    public Result listByBookId(@PathVariable("id")Long bookId){
        List<ReadInfo> readInfoList = readInfoService.selectByBookId(bookId);
        if(readInfoList != null){
            return Result.SUCCESS(readInfoList);
        }
        return Result.FAIL("失败");
    }
    @PostMapping(value = "list/user/{id}")
    public Result listByUserId(@PathVariable("id")Long userId, @RequestBody Query query){
        List<ReadInfo> readInfoList = readInfoService.selectByUserId(userId);
        if(readInfoList != null){
            List<ReadInfoDTO> readInfoDTOList = getDTOS(readInfoList);

            return Result.SUCCESS(readInfoDTOList,readInfoService.count());
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "")
    public Result insert(Long userId,Long bookId){
        if(userId != null && bookId!= null){
            int i = readInfoService.insert(bookId,userId);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "{id}")
    public Result update(@PathVariable("id") Long id,Long start,Long duration){
        if(start != null && duration!= null){
            int i = readInfoService.update(id,start,duration);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    private List<ReadInfoDTO> getDTOS(List<ReadInfo> list) {
        List<ReadInfoDTO> data = new ArrayList<>(list.size());
        for(ReadInfo d : list) {
            ReadInfoDTO dto = getDTO(d);
            data.add(dto);
        }
        return data;
    }

    private ReadInfoDTO getDTO(ReadInfo d) {
        ReadInfoDTO dto = new ReadInfoDTO();
        dto.setId(d.getId());
        dto.setBookId(d.getBookId());
        dto.setBookName(bookService.getName(d.getBookId()));
        dto.setDownload(d.getIsDownload());
        dto.setDuration(d.getDuration());
        dto.setStart(d.getStart());
        dto.setUserId(d.getUserId());
        dto.setUserName(userService.getName(d.getUserId()));
        return dto;
    }
}
