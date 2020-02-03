package kol;

import java.io.PrintStream;

public class Book {
    int ibukId;
    String title;
    String author;
    String isbn;
    String publisher;
    int year;
    String category;
    String subCategory;
    String link;

    public Book( int ibukId, String title, String author, String isbn, String publisher, int year, String category,
                 String subCategory, String link){
        this.ibukId=ibukId;
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.publisher=publisher;
        this.year=year;
        this.category=category;
        this.subCategory=subCategory;
        this.link=link;
    }
    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    void print ( PrintStream out ){
            out.println(toString());
    }

    @Override
    public String toString() {
        return "Book{" +
                "ibukId=" + ibukId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
