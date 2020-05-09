package com.monk.myreader.bean;

import java.util.Date;

public class Book {
    private Long id;

    private String name;

    private String author;

    private String picture;

    private Integer length;

    private String charSet;

    private Long size;

    private String path;

    private Date upDate;

    private Long downloadCount;

    private Double starAvg;

    private Long categoryId;

    private String introduction;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", picture='" + picture + '\'' +
                ", length=" + length +
                ", charSet='" + charSet + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", upDate=" + upDate +
                ", downloadCount=" + downloadCount +
                ", starAvg=" + starAvg +
                ", categoryId=" + categoryId +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet == null ? null : charSet.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Double getStarAvg() {
        return starAvg;
    }

    public void setStarAvg(Double starAvg) {
        this.starAvg = starAvg;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}