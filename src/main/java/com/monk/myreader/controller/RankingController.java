package com.monk.myreader.controller;

import com.monk.myreader.bean.Banner;
import com.monk.myreader.bean.Ranking;
import com.monk.myreader.dto.Query;
import com.monk.myreader.dto.RankingDTO;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.BannerService;
import com.monk.myreader.service.CategoryService;
import com.monk.myreader.service.RankingService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("排行榜接口")
@Slf4j
@RestController
@RequestMapping("range")
public class RankingController{

    @Autowired
    private RankingService rankingService;
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "{id}")
    public Result get(@PathVariable("id") Long id){
        if(id != null){
            Ranking ranking = rankingService.selectById(id);
            if(ranking != null){
                return Result.SUCCESS(new ArrayList<Ranking>(){{add(ranking);}});
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping("category/{categoryId}")
    public Result getRankingByCategoryId(@PathVariable("categoryId") Long categoryId){
        if(categoryId != null){
            List<Ranking> rankingList = rankingService.selectByCategoryId(categoryId);
            if(rankingList != null){
                return Result.SUCCESS(rankingList);
            }
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "")
    public Result insert(Ranking ranking){
        if(ranking != null){
            int i = rankingService.insert(ranking);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @PutMapping(value = "{id}")
    public Result update(@PathVariable("id") Long id, Ranking ranking){
        if(ranking != null){
            int i = rankingService.update(id,ranking);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @PostMapping("list")
    public Result listPage(@RequestBody Query query){
        if(query != null){
            List<Ranking> list = rankingService.listPage(query.getPage(),query.getLimit());
            if(list != null){
                List<RankingDTO> data = getRankingDTOS(list);
                return Result.SUCCESS(data,rankingService.count());
            }
        }
        return Result.FAIL("失败");
    }


    private List<RankingDTO> getRankingDTOS(List<Ranking> list) {
        List<RankingDTO> data = new ArrayList<>(list.size());
        for(Ranking d : list) {
            RankingDTO dto = getCategoryDTO(d);
            data.add(dto);
        }
        return data;
    }

    private RankingDTO getCategoryDTO(Ranking d) {
        RankingDTO dto = new RankingDTO();
        dto.setId(d.getId());
        dto.setName(d.getName());
        dto.setOrderBy(d.getOrderBy());
        dto.setCategoryId(d.getCategoryId());
        if(d.getCategoryId() != null) dto.setCategoryName(categoryService.getName(d.getCategoryId()));
        return dto;
    }
}
