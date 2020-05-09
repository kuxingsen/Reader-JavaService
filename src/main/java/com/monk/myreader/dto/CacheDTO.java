package com.monk.myreader.dto;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class CacheDTO{
    private long size;
    private String data;
    private int index;

    @Override
    public String toString() {
        return "CacheDTO{" +
                "size=" + size +
                ", data='" + data + '\'' +
                ", index=" + index +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
