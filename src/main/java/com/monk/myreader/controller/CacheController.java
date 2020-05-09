package com.monk.myreader.controller;

import com.monk.myreader.bean.Banner;
import com.monk.myreader.bean.Cache;
import com.monk.myreader.dto.CacheDTO;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.CacheService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@Api("书籍缓存接口")
@Slf4j
@RestController
@RequestMapping("cache")
public class CacheController{

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result get(@PathVariable("id") Long id){
        if(id != null){
            Cache cache = cacheService.selectById(id);
            if(cache != null){
                CacheDTO cacheDTO = new CacheDTO();
                cacheDTO.setSize(cache.getDataSize());
                cacheDTO.setData(cache.getBookData());
                cacheDTO.setIndex(cache.getDataIndex());
                return Result.SUCCESS(new ArrayList<CacheDTO>(){{add(cacheDTO);}});
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping("book/{bookId}")
    public Result getByBookId(@PathVariable("bookId") Long bookId){
        if(bookId != null){
            List<Cache> cacheList = cacheService.selectByBookId(bookId);
            if(cacheList != null){
                List<CacheDTO> cacheDTOList = new ArrayList<>();
                for(Cache c:cacheList){
                    CacheDTO cacheDTO = new CacheDTO();
                    cacheDTO.setSize(c.getDataSize());
                    cacheDTO.setData(c.getBookData());
                    cacheDTO.setIndex(c.getDataIndex());
                    cacheDTOList.add(cacheDTO);
                }
                System.out.println(cacheDTOList);
                return Result.SUCCESS(cacheDTOList);
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping("book/{bookId}/index/{index}")
    public Result getByBookIdAndIndex(@PathVariable("bookId") Long bookId,@PathVariable("index")Integer index){
        if(bookId != null){
            Cache cache = cacheService.selectByBookIdAndIndex(bookId,index);
            if(cache != null){
                CacheDTO cacheDTO = new CacheDTO();
                cacheDTO.setSize(cache.getDataSize());
                cacheDTO.setData(cache.getBookData());
                cacheDTO.setIndex(cache.getDataIndex());
                return Result.SUCCESS(new ArrayList<CacheDTO>(){{add(cacheDTO);}});
            }
        }
        return Result.FAIL("失败");
    }
}
