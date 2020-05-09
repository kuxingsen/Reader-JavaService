package com.monk.myreader.dto;

/**
 * 章节
 */
public class CatalogueDTO{
    private Long id;
    private String bookCatalogue;
    private long bookCatalogueStartPos;
    private Long bookId;

    public CatalogueDTO() {
    }

    @Override
    public String toString() {
        return "CatalogueDTO{" +
                "id=" + id +
                ", bookCatalogue='" + bookCatalogue + '\'' +
                ", bookCatalogueStartPos=" + bookCatalogueStartPos +
                ", bookId=" + bookId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookCatalogue() {
        return bookCatalogue;
    }

    public void setBookCatalogue(String bookCatalogue) {
        this.bookCatalogue = bookCatalogue;
    }

    public long getBookCatalogueStartPos() {
        return bookCatalogueStartPos;
    }

    public void setBookCatalogueStartPos(long bookCatalogueStartPos) {
        this.bookCatalogueStartPos = bookCatalogueStartPos;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}

