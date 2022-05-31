package com.example.apps1;

public class Book {
    private int ID_Book;
    private String nameBook;
    private String authorName ;
    private String relaseYear;
    private String pagesNum;
    private byte[] image;
    private String catBook;
    private boolean faverot ;
    public Book(String nameBook, String authorName, String relaseYear, String pagesNum , String catBook ) {
        this.nameBook = nameBook;
        this.authorName = authorName;
        this.relaseYear = relaseYear;
        this.pagesNum = pagesNum;
        this.catBook = catBook;

    }

    public int getID_Book() {
        return ID_Book;
    }

    public void setID_Book(int ID_Book) {
        this.ID_Book = ID_Book;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getRelaseYear() {
        return relaseYear;
    }

    public void setRelaseYear(String relaseYear) {
        this.relaseYear = relaseYear;
    }

    public String getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(String pagesNum) {
        this.pagesNum = pagesNum;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCatBook() {
        return catBook;
    }

    public void setCatBook(String catBook) {
        this.catBook = catBook;
    }

    public boolean isFaverot() {
        return faverot;
    }

    public void setFaverot(boolean faverot) {
        this.faverot = faverot;
    }
}
