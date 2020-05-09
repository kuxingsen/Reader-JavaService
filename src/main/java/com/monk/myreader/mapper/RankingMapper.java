package com.monk.myreader.mapper;

import com.monk.myreader.bean.Ranking;
import com.monk.myreader.bean.RankingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RankingMapper {
    int countByExample(RankingExample example);

    int deleteByExample(RankingExample example);

    int insert(Ranking record);

    int insertSelective(Ranking record);

    List<Ranking> selectByExample(RankingExample example);

    int updateByExampleSelective(@Param("record") Ranking record, @Param("example") RankingExample example);

    int updateByExample(@Param("record") Ranking record, @Param("example") RankingExample example);
}