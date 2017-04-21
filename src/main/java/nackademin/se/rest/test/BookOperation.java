/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;
import nackademin.se.rest.test.models.Author;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.SingleBook;

/**
 *
 * @author jesper
 */
public class BookOperation {

    final static String BASE_URL = "http://localhost:8080/librarytest/rest/";
    private String jsonString ="";


    public Book getBook(int id) {
        Book book = given().accept(ContentType.JSON).get(BASE_URL+"books/"+id).jsonPath().getObject("book",Book.class);
 
        return book;
    }    
    public Book createRandomBook(){

        Book book = new Book();
        book.setDescription(UUID.randomUUID().toString());
        book.setTitle(UUID.randomUUID().toString());
        book.setIsbn(UUID.randomUUID().toString());
        book.setNbOfPage(new Random().nextInt(500));
        
        return book;
    }
     public Author getAuthor(int id) {
        Author author = given().accept(ContentType.JSON).get(BASE_URL+"authors/"+id).jsonPath().getObject("author",Author.class);
 
        return author;
    }   
    public Author createRandomAuthor(){

        Author author = new Author();
        author.setName(UUID.randomUUID().toString());
        
        return author;       
        
        
    }  
    public String getLatestJsonString(){
    return jsonString;
    }
}
