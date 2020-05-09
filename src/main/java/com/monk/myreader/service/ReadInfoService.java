package com.monk.myreader.service;

import com.monk.myreader.bean.ReadInfo;

import java.util.List;


public interface ReadInfoService{
    ReadInfo selectById(Long id);
    List<ReadInfo> selectByUserId(Long id);
    List<ReadInfo> selectByBookId(Long id);
    int insert(Long bookId,Long userId);
    int update(Long id, Long start,Long diffDuration);
    int download(Long id);

    int count();
}
