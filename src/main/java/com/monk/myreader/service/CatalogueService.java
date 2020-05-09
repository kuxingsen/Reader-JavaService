package com.monk.myreader.service;

import com.monk.myreader.bean.Catalogue;

import java.util.List;


public interface CatalogueService{
    Catalogue selectById(Long id);
    int update(Long id, Catalogue catalogue);

    List<Catalogue> selectCatalogueByBookId(Long bookId);

    int insertCatalogueList(Long bookId,List<Catalogue> catalogueList);

}
