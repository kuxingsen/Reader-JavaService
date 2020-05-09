package com.monk.myreader.controller;

import com.monk.myreader.bean.Category;
import com.monk.myreader.dto.CategoryDTO;
import com.monk.myreader.dto.Query;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("分类接口")
@Slf4j
@RestController
@RequestMapping("category")
public class CategoryController{


    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result get(@PathVariable("id") Long id){
        if(id != null){
            Category category = categoryService.selectById(id);
            if(category != null){
                return Result.SUCCESS(new ArrayList<Category>(){{add(category);}});
            }
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "")
    public Result insert(Category category){
        if(category != null){
            int i = categoryService.insert(category);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable("id") Long id, Category category){
        if(category != null){
            int i = categoryService.update(id,category);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping("sub/{id}")
    public Result getSubCategory(@PathVariable("id") Long id){
        if(id != null){
            List<Category> categoryList = categoryService.selectSubCategoryById(id);
            if(categoryList != null){
                return Result.SUCCESS(categoryList);
            }
        }
        return Result.FAIL("失败");
    }
    @PostMapping("sub/{id}")
    public Result getSubCategory(@PathVariable("id") Long id,@RequestBody Query query){
        if(id != null){
            List<Category> list = categoryService.selectSubCategoryById(id);
            if(list != null){
                List<CategoryDTO> data = getCategoryDTOS(list);
                return Result.SUCCESS(data,categoryService.count());
            }
        }
        return Result.FAIL("失败");
    }


    @PostMapping("list")
    public Result listPage(@RequestBody Query query){
        if(query != null){
            List<Category> list = categoryService.listPage(query.getPage(),query.getLimit());
            if(list != null){
                List<CategoryDTO> data = getCategoryDTOS(list);
                return Result.SUCCESS(data,categoryService.count());
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping("list")
    public Result listAll(){
            List<Category> list = categoryService.list();
            if(list != null){
                List<CategoryDTO> data = getCategoryDTOS(list);
                return Result.SUCCESS(data,categoryService.count());
            }
        return Result.FAIL("失败");
    }

    private List<CategoryDTO> getCategoryDTOS(List<Category> list) {
        List<CategoryDTO> data = new ArrayList<>(list.size());
        for(Category d : list) {
            CategoryDTO dto = getCategoryDTO(d);
            data.add(dto);
        }
        return data;
    }

    private CategoryDTO getCategoryDTO(Category d) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(d.getId());
        dto.setName(d.getName());
        dto.setParentId(d.getParentId());
        if(d.getParentId() != null) dto.setParentName(categoryService.getName(d.getParentId()));
        return dto;
    }
}
