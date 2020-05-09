package com.monk.myreader.service;

import com.monk.myreader.bean.Banner;
import com.monk.myreader.bean.Cache;

import java.util.List;


public interface CacheService{
    Cache selectById(Long id);
    Cache selectByBookIdAndIndex(Long bookId,int index);

    List<Cache> selectByBookId(Long bookId);

    int insertCacheList(Long bookId,List<Cache> cacheList);

}
