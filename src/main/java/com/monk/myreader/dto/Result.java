package com.monk.myreader.dto;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kuexun on 2018/8/11.
 */
public class Result<T> {
    private int code;
    private String msg;
    private List<T> data;
    private int total;

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(int code, List<T> data,int total) {
        this.code = code;
        this.data = data;
        this.total = total;
    }
    private Result(int code, List<T> data) {
        this.code = code;
        this.data = data;
    }
    public static <S> Result<S> SUCCESS(List<S> data,int total){
        return new Result<>(0, data,total);
    }
    public static <S> Result<S> SUCCESS(List<S> data){
        return new Result<>(0, data);
    }
    public static  Result SUCCESS(String msg){
        return new Result<>(0, msg);
    }
    public static Result FAIL(String msg){
        return new Result(-1, msg);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
