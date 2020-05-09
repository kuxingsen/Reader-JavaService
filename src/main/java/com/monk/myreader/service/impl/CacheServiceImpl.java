package com.monk.myreader.service.impl;

import com.github.pagehelper.PageHelper;
import com.monk.myreader.bean.Book;
import com.monk.myreader.bean.BookExample;
import com.monk.myreader.bean.Cache;
import com.monk.myreader.bean.CacheExample;
import com.monk.myreader.mapper.BookMapper;
import com.monk.myreader.mapper.CacheMapper;
import com.monk.myreader.service.BookService;
import com.monk.myreader.service.CacheService;
import com.monk.myreader.service.CatalogueService;
import com.monk.myreader.service.CategoryService;
import com.monk.myreader.utils.BookUtil;
import com.monk.myreader.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service
public class CacheServiceImpl implements CacheService{

    @Autowired
    CacheMapper cacheMapper;


    @Override
    public Cache selectById(Long id) {
        CacheExample cacheExample = new CacheExample();
        cacheExample.createCriteria().andIdEqualTo(id);
        List<Cache> cacheList = cacheMapper.selectByExampleWithBLOBs(cacheExample);
        return cacheList.size()==0?null:cacheList.get(0);
    }

    @Override
    public Cache selectByBookIdAndIndex(Long bookId, int index) {
        CacheExample cacheExample = new CacheExample();
        cacheExample.createCriteria().andBookIdEqualTo(bookId).andDataIndexEqualTo(index);
        List<Cache> cacheList = cacheMapper.selectByExampleWithBLOBs(cacheExample);
        return cacheList.size()==0?null:cacheList.get(0);
    }

    @Override
    public List<Cache> selectByBookId(Long bookId) {
        CacheExample cacheExample = new CacheExample();
        cacheExample.createCriteria().andBookIdEqualTo(bookId);
//        List<Cache> cacheList = cacheMapper.selectByExample(cacheExample);
        List<Cache> cacheList = cacheMapper.selectByExampleWithBLOBs(cacheExample);
        return cacheList;
    }

    @Override
    public int insertCacheList(Long bookId, List<Cache> cacheList) {
        int count = 0;
        for(Cache c:cacheList){
            c.setBookId(bookId);
            count += cacheMapper.insert(c);
        }
        return count;
    }
}
