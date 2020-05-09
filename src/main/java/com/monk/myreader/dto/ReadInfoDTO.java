package com.monk.myreader.dto;

public class ReadInfoDTO{
    private Long id;

    private Long start;

    private Long duration;
    private String durationStr;

    private Boolean isDownload;

    private Long bookId;
    private String bookName;

    private Long userId;
    private String userName;

    public Boolean getDownload() {
        return isDownload;
    }

    public void setDownload(Boolean download) {
        isDownload = download;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
        setDurationStr();
    }

    public Boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Boolean isDownload) {
        this.isDownload = isDownload;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDurationStr() {
        return durationStr;
    }

    private void setDurationStr() {
        long h = duration/60;
        int m = Math.toIntExact(duration % 60);
        durationStr = "";
        if(h > 0) durationStr = h+"小时";
        this.durationStr += m+"分钟";
    }
}