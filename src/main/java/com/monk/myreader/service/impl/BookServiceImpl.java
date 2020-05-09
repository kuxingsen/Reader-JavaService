package com.monk.myreader.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.monk.myreader.bean.Book;
import com.monk.myreader.bean.BookExample;
import com.monk.myreader.bean.Category;
import com.monk.myreader.bean.Ranking;
import com.monk.myreader.mapper.BookMapper;
import com.monk.myreader.service.*;
import com.monk.myreader.utils.BookUtil;
import com.monk.myreader.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookMapper bookMapper;

    @Autowired
    CategoryService categoryService;
    @Autowired
    CatalogueService catalogueService;
    @Autowired
    CacheService cacheService;
    @Autowired
    RankingService rankingService;

    @Override
    public Book selectById(Long id) {
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andIdEqualTo(id);
        List<Book> bookList = bookMapper.selectByExampleWithBLOBs(bookExample);
        return bookList.size()>0?bookList.get(0):null;
    }

    @Override
    public String getName(Long id) {
        Book book = selectById(id);
        return book.getName();
    }

    @Override
    public int insert(Book book) {

        BookUtil bookUtil = new BookUtil();
        try {
            bookUtil.openBook(book);
        } catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
        book.setCharSet(bookUtil.getCharset());
        book.setSize(bookUtil.getBookLen());
        book.setUpDate(new Date());
        System.out.println(book);
        int i = bookMapper.insert(book);
        new Thread(()->{
            bookUtil.getChapter();
            System.out.println(bookUtil.getDirectoryList().size());
            catalogueService.insertCatalogueList(book.getId(),bookUtil.getDirectoryList());
        }).start();
        new Thread(()->{
            //data列
            cacheService.insertCacheList(book.getId(),bookUtil.getMyArray());
        }).start();
        return i;
    }

    @Override
    public int update(Long id, Book book) {

        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andIdEqualTo(id);
        return bookMapper.updateByExampleSelective(book, bookExample);
    }

    @Override
    public List<Book> selectBookList() {
        return bookMapper.selectByExample(new BookExample());
    }

    @Override
    public List<Book> selectNewBookList(Integer count, Long categoryParentId) {
        List<Long> categoryIdList = new ArrayList<>();
        for(Category c:categoryService.selectSubCategoryById(categoryParentId)){
            categoryIdList.add(c.getId());
        }
        BookExample bookExample = new BookExample();
        bookExample.setOrderByClause("up_date desc");
        if(categoryIdList.size() > 0)
            bookExample.createCriteria().andCategoryIdIn(categoryIdList);
        PageHelper.startPage(0, count);
        List<Book> bookList = bookMapper.selectByExampleWithBLOBs(bookExample);
        return bookList;
    }

    @Override
    public List<Book> selectRangeBookList(Long rangeId, String duration) {
        BookExample bookExample = new BookExample();
        Ranking ranking = rankingService.selectById(rangeId);
        //todo 这里应该加上分类的。获取某分类下的书籍的排行榜
        bookExample.setOrderByClause(ranking.getOrderBy()+" desc");

        //某段时间内上传的书籍
        Date today = new Date();
        Date past;
        switch(duration){
            case "week":
                past = DateUtils.getPastDate(7,today);
                bookExample.createCriteria().andUpDateBetween(past,today);
                break;
            case "month":
                //30-31-29-28
                past = DateUtils.getPastDate(30,today);
                bookExample.createCriteria().andUpDateBetween(past,today);
                break;
            case "total":
                break;
            default:
                break;
        }
        PageHelper.startPage(0, 10);
        List<Book> bookList = bookMapper.selectByExampleWithBLOBs(bookExample);
        return bookList;
    }

    private int[] lengthArr = {0,1,2,3};
    private String[] lengthTextArr = {"不限","100万字以下","100-200万字","200万字以上"};
    @Override
    public List<Book> selectSearchBookList(Integer page, Integer limit,
                                           String search, Long categoryId,
                                           Integer length, String order) {
        BookExample bookExample = new BookExample();
        BookExample.Criteria exampleCriteria = bookExample.createCriteria();
        if(order != null) bookExample.setOrderByClause(order+" desc");
        if(StringUtil.isNotEmpty(search)) exampleCriteria.andNameLike("%"+search+"%");
        if(categoryId != null){
            List<Category> categoryList = categoryService.selectSubCategoryById(categoryId);
            if(categoryList.size() > 0){
                List<Long> categoryIdList = new ArrayList<>();
                for(Category c:categoryList){
                    categoryIdList.add(c.getId());
                }
                exampleCriteria.andCategoryIdIn(categoryIdList);
            }else {
                exampleCriteria.andCategoryIdEqualTo(categoryId);
            }
        }
        if(length != null) {
            switch(length){
                case 0:
                    break;
                case 1:
                    exampleCriteria.andSizeLessThanOrEqualTo(1000000L);
                    break;
                case 2:
                    exampleCriteria.andSizeBetween(1000000L,2000000L);
                    break;
                case 3:
                    exampleCriteria.andSizeGreaterThanOrEqualTo(2000000L);
                    break;
                default:
                    break;
            }
        }

        PageHelper.startPage(page, limit);
        List<Book> bookList = bookMapper.selectByExampleWithBLOBs(bookExample);
        return bookList;
    }

    @Override
    public List<Book> listTongji(Integer limit, Long categoryId, String orderBy) {

        BookExample bookExample = new BookExample();
        BookExample.Criteria exampleCriteria = bookExample.createCriteria();
        bookExample.setOrderByClause(orderBy+" desc");
        if(categoryId != 1){
            exampleCriteria.andCategoryIdEqualTo(categoryId);
        }

        PageHelper.startPage(0, limit);
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public int delete(Long id) {
        //todo 删掉相关
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andIdEqualTo(id);
        return bookMapper.deleteByExample(bookExample);
    }

    @Override
    public int count() {
        BookExample bookExample = new BookExample();
        return bookMapper.countByExample(bookExample);
    }
}
