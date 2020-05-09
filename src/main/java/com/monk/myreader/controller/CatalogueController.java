package com.monk.myreader.controller;

import com.monk.myreader.bean.Catalogue;
import com.monk.myreader.dto.CatalogueDTO;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.CatalogueService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("目录接口")
@Slf4j
@RestController
@RequestMapping("catalogue")
public class CatalogueController{

    @Autowired
    private CatalogueService catalogueService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result get(@PathVariable("id") Long id){
        if(id != null){
            Catalogue catalogue = catalogueService.selectById(id);
            if(catalogue != null){
                return Result.SUCCESS(new ArrayList<Catalogue>(){{add(catalogue);}});
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping("book/{bookId}")
    public Result getCatalogueByBookId(@PathVariable("bookId") Long bookId){
        if(bookId != null){
            List<Catalogue> catalogueList = catalogueService.selectCatalogueByBookId(bookId);
            if(catalogueList != null){
                List<CatalogueDTO> catalogueDTOList = new ArrayList<>(catalogueList.size());
                for(Catalogue c:catalogueList){
                    CatalogueDTO cd = new CatalogueDTO();
                    cd.setBookCatalogue(c.getName());
                    cd.setBookCatalogueStartPos(c.getStart());
                    cd.setBookId(c.getBookId());
                    catalogueDTOList.add(cd);
                }
                return Result.SUCCESS(catalogueDTOList);
            }
        }
        return Result.FAIL("失败");
    }
}
