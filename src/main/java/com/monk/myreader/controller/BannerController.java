package com.monk.myreader.controller;

import com.monk.myreader.bean.Banner;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.BannerService;
import com.monk.myreader.service.CatalogueService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("轮播图接口")
@Slf4j
@RestController
@RequestMapping("banner")
public class BannerController{

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result get(@PathVariable("id") Long id){
        if(id != null){
            Banner banner = bannerService.selectById(id);
            if(banner != null){
                return Result.SUCCESS(new ArrayList<Banner>(){{add(banner);}},3);
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping("category/{categoryId}")
    public Result getCatalogueByBookId(@PathVariable("categoryId") Long categoryId){
        if(categoryId != null){
            List<Banner> bannerList = bannerService.selectBannerByCategoryId(categoryId);
            if(bannerList != null){
                return Result.SUCCESS(bannerList,3);
            }
        }
        return Result.FAIL("失败");
    }
}
