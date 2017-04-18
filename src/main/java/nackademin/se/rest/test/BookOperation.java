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
    public SingleBook createRandomBook(){

        Book book = new Book();
        book.setDescription(UUID.randomUUID().toString());
        book.setTitle(UUID.randomUUID().toString());
        book.setIsbn(UUID.randomUUID().toString());
        book.setNbOfPage(new Random().nextInt(500));
        SingleBook singleBook= new SingleBook(book);
        
        return singleBook;
    }
    public String getLatestJsonString(){
    return jsonString;
    }
}
