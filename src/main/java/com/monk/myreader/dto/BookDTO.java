package com.monk.myreader.dto;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2020/3/28 20:06
 */
public class BookDTO{
    private Long id;
    private String name;
    private String author;
    private String picture;
    private Integer length;
    private String charSet;
    private Long size;
    private String upDate;
    private String path;
    private Long downloadCount;
    private Double starAvg;
    private Long categoryId;
    private String categoryName;
    private String introduction;
    private String rangeValue;

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", picture='" + picture + '\'' +
                ", length=" + length +
                ", charSet='" + charSet + '\'' +
                ", size=" + size +
                ", upDate='" + upDate + '\'' +
                ", downloadCount=" + downloadCount +
                ", starAvg=" + starAvg +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", rangeValue='" + rangeValue + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getRangeValue() {
        return rangeValue;
    }

    public void setRangeValue(String rangeValue) {
        this.rangeValue = rangeValue;
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
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
        this.charSet = charSet;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
