package com.monk.myreader.controller;

import com.monk.myreader.bean.Book;
import com.monk.myreader.dto.BookDTO;
import com.monk.myreader.dto.Query;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.BookService;
import com.monk.myreader.service.CategoryService;
import com.monk.myreader.service.RankingService;
import com.monk.myreader.utils.DateUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("书籍接口")
@Slf4j
@RestController
@RequestMapping("book")
public class BookController{


    @Autowired
    private BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    RankingService rankingService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result get(@PathVariable("id") Long id){
        if(id != null){
            Book book = bookService.selectById(id);
            if(book != null){
                BookDTO bookDTO = getDTO(book);
                return Result.SUCCESS(new ArrayList<BookDTO>(){{add(bookDTO);}},bookService.count());
            }
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "")
    public Result insert(Book book){
        if(book != null){
            int i = bookService.insert(book);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @DeleteMapping(value = "{id}")
    public Result delete(@PathVariable("id") Long id){
        if(id != null){
            int i = bookService.delete(id);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable("id") Long id, Book book){
        if(book != null){
            int i = bookService.update(id,book);
            if(i > 0){
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL("失败");
    }

    @GetMapping(value = "list/new")
    public Result listNew(Integer count,Long categoryParentId){
        List<Book> list = bookService.selectNewBookList(count,categoryParentId);
        if(list != null){
            List<BookDTO> data = getDTOs(list,"up_date");
            return Result.SUCCESS(data,bookService.count());
        }
        return Result.FAIL("失败");
    }
    @GetMapping(value = "list/range/{id}")
    public Result listRangeByRangeId(@PathVariable("id")Long rangeId, Long categoryId,String duration){
        List<Book> list = bookService.selectRangeBookList(rangeId,duration);
        if(list != null){
            String orderBy = rankingService.selectById(rangeId).getOrderBy();
            List<BookDTO> data = getDTOs(list,orderBy);
            return Result.SUCCESS(data,bookService.count());
        }
        return Result.FAIL("失败");
    }

    @PostMapping(value = "list")
    public Result listSearch(@RequestBody Query query){
        List<Book> list = bookService.selectSearchBookList(query.getPage(),query.getLimit(),null,null,null,null);
        if(list != null){
            List<BookDTO> data = getDTOs(list);
            return Result.SUCCESS(data,bookService.count());
        }
        return Result.FAIL("失败");
    }
    @RequestMapping(value = "list/search")
    public Result listSearch(Integer page,Integer limit,
                             String search,
                             Long categoryId,
                             Integer length, //字数，单位万字
                             String order //排序，star、up_date、download、access
    ){
        System.out.println(search+"  "+ categoryId+ "  "+order);
        List<Book> list = bookService.selectSearchBookList(page,limit,search,categoryId,length,order);
        System.out.println(list);
        if(list != null){
            List<BookDTO> data = getDTOs(list,order);
            return Result.SUCCESS(data,bookService.count());
        }
        return Result.FAIL("失败");
    }


    @GetMapping(value = "list/tongji")
    public Result listTongJi(Integer limit,
                             Long categoryId,
                             String orderBy //排序，star_avg、size、download,comment_count
            ){
        List<Book> list = bookService.listTongji(limit,categoryId,orderBy);
        if(list != null){
            List<BookDTO> data = getDTOs(list,orderBy);
            return Result.SUCCESS(data,bookService.count());
        }
        return Result.FAIL("失败");
    }


    private List<BookDTO> getDTOs(List<Book> list) {
        List<BookDTO> data = new ArrayList<>(list.size());
        for(Book book:list){
            BookDTO dto = getDTO(book);
            data.add(dto);
        }
        return data;
    }
    private List<BookDTO> getDTOs(List<Book> list,String orderBy) {
        List<BookDTO> data = new ArrayList<>(list.size());
        String v="";
        for(Book book:list){
            BookDTO dto = getDTO(book);
            switch(orderBy){
                case "star_avg": v = String.valueOf(book.getStarAvg()); break;
                case "size": v = String.valueOf(book.getSize());break;
                case "download_count": v = String.valueOf(book.getDownloadCount());break;
                case "up_date": v = DateUtils.date2string(book.getUpDate());break;
            }
            dto.setRangeValue(v);
            data.add(dto);
        }
        return data;
    }

    private BookDTO getDTO(Book book) {
        BookDTO dto= new BookDTO();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setCategoryId(book.getCategoryId());
        dto.setCategoryName(categoryService.getName(book.getCategoryId()));
        dto.setCharSet(book.getCharSet());
        dto.setIntroduction(book.getIntroduction());
        dto.setLength(book.getLength());
        dto.setSize(book.getSize());
        dto.setPicture(book.getPicture());
        dto.setPath(book.getPath());
        dto.setUpDate(DateUtils.date2string(book.getUpDate()));
        dto.setName(book.getName());
        dto.setStarAvg(book.getStarAvg());
        dto.setDownloadCount(book.getDownloadCount());
        return dto;
    }


}
