package com.monk.myreader.service;

import com.monk.myreader.bean.Cache;
import com.monk.myreader.bean.Ranking;

import java.util.List;


public interface RankingService{
    Ranking selectById(Long id);

    List<Ranking> selectByCategoryId(Long categoryId);

    int insertRanking(Ranking ranking);

    int update(Long id, Ranking ranking);

    int insert(Ranking ranking);

    int count();

    List<Ranking> listPage(Integer page, Integer limit);
}
