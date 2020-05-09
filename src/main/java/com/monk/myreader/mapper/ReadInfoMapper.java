package com.monk.myreader.mapper;

import com.monk.myreader.bean.ReadInfo;
import com.monk.myreader.bean.ReadInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReadInfoMapper {
    int countByExample(ReadInfoExample example);

    int deleteByExample(ReadInfoExample example);

    int insert(ReadInfo record);

    int insertSelective(ReadInfo record);

    List<ReadInfo> selectByExample(ReadInfoExample example);

    int updateByExampleSelective(@Param("record") ReadInfo record, @Param("example") ReadInfoExample example);

    int updateByExample(@Param("record") ReadInfo record, @Param("example") ReadInfoExample example);
}