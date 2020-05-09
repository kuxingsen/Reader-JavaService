package com.monk.myreader.dto;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2020/4/16 16:41
 */
public class Query{
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
