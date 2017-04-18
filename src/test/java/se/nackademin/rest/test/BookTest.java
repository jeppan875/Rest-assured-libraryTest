/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.response.Response;
import java.util.UUID;
import nackademin.se.rest.test.BookOperation;
import static org.junit.Assert.assertEquals;
import static com.jayway.restassured.path.json.JsonPath.*;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.SingleBook;

import org.junit.Test;

/**
 *
 * @author jesper
 */
public class BookTest {

    final static String BASE_URL = "http://localhost:8080/librarytest/rest/";    
    
    public BookTest() {
    }
    @Test
    public void testFetchBook() {
        
        Book book = new BookOperation().getBook(4);
        System.out.println("Title: "+book.getTitle()); 
        System.out.println("Author: "+book.getAuthor());        
        System.out.println("Description: "+book.getDescription());
        System.out.println("Number of pages: "+book.getNbOfPage());
        System.out.println("Isbn: "+book.getIsbn());  

        
        Response responce = new BookOperation().getBookResponse(4);
        assertEquals("should return status code 200",200, responce.getStatusCode());    
    }
     @Test
    public void testGetInvalidBookReturn404() {        
        
        Response responce = new BookOperation().getBookResponse(894);
        assertEquals("should return status code 404",404, responce.getStatusCode());    
    }   

    
    @Test
    public void testAddNewBook() {
        BookOperation bookOperation = new BookOperation();
        SingleBook singleBook = bookOperation.createRandomBook();
         System.out.println("Title: "+singleBook.getBook().getDescription()); 
        
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().post(BASE_URL+"books").prettyPeek();             
        assertEquals("should return status code 201",201, response.getStatusCode());
    }    
}
