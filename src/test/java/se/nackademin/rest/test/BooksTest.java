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
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.SingleBook;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 *
 * @author jesper
 */
public class BooksTest {

    final static String BASE_URL = "http://localhost:8080/librarytest/rest/books/";    
    
    public BooksTest() {
    }
    
    @Test
    public void testGetAllBooks() {
        Response response = new ResponseOperation().getResponse(BASE_URL);
        assertEquals("should return status code 200",200, response.getStatusCode());        
    } 

    
    @Test
    public void testAddNewBook() {
        BookOperation bookOperation = new BookOperation();
        SingleBook singleBook = bookOperation.createRandomBook();
                
        Response response = new ResponseOperation().postResponse(BASE_URL, singleBook);
        assertEquals("should return status code 201",201, response.getStatusCode());
        assertEquals(singleBook.getBook().getTitle(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].title") );
        assertEquals(singleBook.getBook().getDescription(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].description") );    
        assertEquals(singleBook.getBook().getIsbn(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].isbn") );  
  
    }  
    @Test
    public void testUpdateBook() {

        BookOperation bookOperation = new BookOperation();
        SingleBook postSingleBook = bookOperation.createRandomBook();
                
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, postSingleBook);
        assertEquals("should return status code 201",201, postResponse.getStatusCode());

        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id");  
        
        Book book = bookOperation.getBook(id);
        book.setDescription("dgfgd");
        SingleBook singleBook = new SingleBook(book);
                 
        Response response = new ResponseOperation().putResponse(BASE_URL, singleBook);
        assertEquals("should return status code 200",200, response.getStatusCode());
        assertNotEquals(postSingleBook.getBook().getDescription(), new ResponseOperation().getResponse(BASE_URL).jsonPath().getString("books.book[-1].description") );
        
    }    
}
