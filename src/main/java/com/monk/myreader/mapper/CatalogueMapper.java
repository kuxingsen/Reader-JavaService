package com.monk.myreader.mapper;

import com.monk.myreader.bean.Catalogue;
import com.monk.myreader.bean.CatalogueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CatalogueMapper {
    int countByExample(CatalogueExample example);

    int deleteByExample(CatalogueExample example);

    int insert(Catalogue record);

    int insertSelective(Catalogue record);

    List<Catalogue> selectByExample(CatalogueExample example);

    int updateByExampleSelective(@Param("record") Catalogue record, @Param("example") CatalogueExample example);

    int updateByExample(@Param("record") Catalogue record, @Param("example") CatalogueExample example);
}