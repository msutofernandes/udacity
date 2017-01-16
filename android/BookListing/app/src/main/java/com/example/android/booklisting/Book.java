package com.example.android.booklisting;

/**
 * Created by msuto on 07/01/2017.
 */

public class Book {

    public String title;
    public String author;


    public Book(String bookTitle, String bookAuthor) {
        title = bookTitle;
        author = bookAuthor;
    }

    public String getTile() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

}
