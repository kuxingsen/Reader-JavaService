package com.monk.myreader.mapper;

import com.monk.myreader.bean.Cache;
import com.monk.myreader.bean.CacheExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {
    int countByExample(CacheExample example);

    int deleteByExample(CacheExample example);

    int insert(Cache record);

    int insertSelective(Cache record);

    List<Cache> selectByExampleWithBLOBs(CacheExample example);

    List<Cache> selectByExample(CacheExample example);

    int updateByExampleSelective(@Param("record") Cache record, @Param("example") CacheExample example);

    int updateByExampleWithBLOBs(@Param("record") Cache record, @Param("example") CacheExample example);

    int updateByExample(@Param("record") Cache record, @Param("example") CacheExample example);
}