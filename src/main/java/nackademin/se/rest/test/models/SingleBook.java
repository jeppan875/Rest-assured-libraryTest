/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test.models;

/**
 *
 * @author jesper
 */
public class SingleBook {


    private Book book;
    private Author author;
    
    public SingleBook(Book book){
    this.book= book;
    }
    public SingleBook(Author author){
    this.author= author;
    }
 
    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }
    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }    
}
