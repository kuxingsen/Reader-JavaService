package com.monk.myreader.service;

import com.monk.myreader.bean.Category;

import java.util.List;


public interface CategoryService{
    Category selectById(Long id);
    String getName(Long id);
    int insert(Category category);
    int update(Long id, Category category);

    List<Category> selectSubCategoryById(Long parentId);

    boolean isParentId(Long id);

    int count();

    List<Category> listPage(Integer page, Integer limit);

    List<Category> list();

}
