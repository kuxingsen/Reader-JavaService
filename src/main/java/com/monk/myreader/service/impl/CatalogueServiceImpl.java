package com.monk.myreader.service.impl;

import com.monk.myreader.bean.Catalogue;
import com.monk.myreader.bean.CatalogueExample;
import com.monk.myreader.mapper.CatalogueMapper;
import com.monk.myreader.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CatalogueServiceImpl implements CatalogueService{
    @Autowired
    CatalogueMapper catalogueMapper;

    @Override
    public Catalogue selectById(Long id) {

        CatalogueExample catalogueExample = new CatalogueExample();
        catalogueExample.createCriteria().andIdEqualTo(id);
        List<Catalogue> catalogueList = catalogueMapper.selectByExample(catalogueExample);

        return catalogueList.size()==0?null:catalogueList.get(0);
    }

    @Override
    public List<Catalogue> selectCatalogueByBookId(Long bookId) {
        CatalogueExample catalogueExample = new CatalogueExample();
        catalogueExample.createCriteria().andBookIdEqualTo(bookId);
        List<Catalogue> catalogueList = catalogueMapper.selectByExample(catalogueExample);

        return catalogueList;
    }


    @Override
    public int update(Long id, Catalogue catalogue) {

        CatalogueExample catalogueExample = new CatalogueExample();
        catalogueExample.createCriteria().andIdEqualTo(id);
        return catalogueMapper.updateByExample(catalogue,catalogueExample);
    }

    @Override
    public int insertCatalogueList(Long bookId,List<Catalogue> catalogueList) {
        int count = 0;
        for(Catalogue c:catalogueList){
            c.setBookId(bookId);
            count += catalogueMapper.insert(c);
        }
        return count;
    }
}
