package com.monk.myreader.service.impl;

import com.github.pagehelper.PageHelper;
import com.monk.myreader.bean.Category;
import com.monk.myreader.bean.CategoryExample;
import com.monk.myreader.mapper.CategoryMapper;
import com.monk.myreader.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Category selectById(Long id) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(id);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        return categoryList.size()==0?null:categoryList.get(0);
    }

    @Override
    public String getName(Long id) {
        Category category = selectById(id);
        if(category != null){
            return category.getName();
        }
        return null;
    }

    @Override
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int update(Long id, Category category) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(id);
        return categoryMapper.updateByExample(category,categoryExample);
    }

    @Override
    public List<Category> selectSubCategoryById(Long parentId) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andParentIdEqualTo(parentId);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        return categoryList;
    }

    @Override
    public boolean isParentId(Long id) {
        return selectSubCategoryById(id).size() > 0;
    }

    @Override
    public int count() {
        CategoryExample categoryExample = new CategoryExample();
        return categoryMapper.countByExample(categoryExample);
    }

    @Override
    public List<Category> listPage(Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        PageHelper.startPage(page, limit);
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public List<Category> list() {
        CategoryExample categoryExample = new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }

}
