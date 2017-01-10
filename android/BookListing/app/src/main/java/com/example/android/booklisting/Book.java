package com.example.android.booklisting;

/**
 * Created by msuto on 07/01/2017.
 */

public class Book {

    public String title;
    public String publisher;

    public Book(String bookTitle, String bookPublisher) {
        title = bookTitle;
        publisher = bookPublisher;
    }

    public String getTile() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }
}
