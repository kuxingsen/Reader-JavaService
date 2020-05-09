package com.monk.myreader.service.impl;

import com.github.pagehelper.PageHelper;
import com.monk.myreader.bean.Cache;
import com.monk.myreader.bean.CacheExample;
import com.monk.myreader.bean.Ranking;
import com.monk.myreader.bean.RankingExample;
import com.monk.myreader.mapper.RankingMapper;
import com.monk.myreader.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RankingServiceImpl implements RankingService{

    @Autowired
    RankingMapper rankingMapper;


    @Override
    public Ranking selectById(Long id) {
        RankingExample rankingExample = new RankingExample();
        rankingExample.createCriteria().andIdEqualTo(id);
        List<Ranking> rankingList = rankingMapper.selectByExample(rankingExample);
        return rankingList.size()==0?null:rankingList.get(0);
    }

    @Override
    public List<Ranking> selectByCategoryId(Long categoryId) {
        RankingExample rankingExample = new RankingExample();
        rankingExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<Ranking> rankingList = rankingMapper.selectByExample(rankingExample);
        return rankingList;
    }

    @Override
    public int insertRanking(Ranking ranking) {
        return rankingMapper.insert(ranking);
    }

    @Override
    public int update(Long id, Ranking ranking) {

        RankingExample rankingExample = new RankingExample();
        rankingExample.createCriteria().andIdEqualTo(id);
        return rankingMapper.updateByExample(ranking, rankingExample);
    }

    @Override
    public int insert(Ranking ranking) {
        int insert = rankingMapper.insert(ranking);
        return insert;
    }

    @Override
    public int count() {
        RankingExample example = new RankingExample();
        return rankingMapper.countByExample(example);
    }

    @Override
    public List<Ranking> listPage(Integer page, Integer limit) {
        RankingExample example = new RankingExample();
        PageHelper.startPage(page, limit);
        return rankingMapper.selectByExample(example);
    }
}
