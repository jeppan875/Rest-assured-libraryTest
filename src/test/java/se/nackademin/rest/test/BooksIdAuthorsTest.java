/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import nackademin.se.rest.test.BookOperation;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.models.Author;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.SingleBook;
import org.junit.Test;
import static org.junit.Assert.*;
import static se.nackademin.rest.test.AuthorsTest.BASE_URL;
import static se.nackademin.rest.test.BooksByAuthorIdTest.BASE_URL;
import static se.nackademin.rest.test.BooksTest.BASE_URL;

/**
 *
 * @author jesper
 */
public class BooksIdAuthorsTest {
    
    final static String BASE_URL = "http://localhost:8080/librarytest/rest/books/"; 
    
    public BooksIdAuthorsTest() {        
    }
    @Test
    public void testGetAuthorByBookId() {
        String bookId=4+"/authors";
        Response response = new ResponseOperation().getResponse(BASE_URL+bookId);
        assertEquals("should return status code 200",200, response.getStatusCode());        
    }
    @Test
    public void testGetAuthorByInvalidBookIdReturn404() {
        String bookId=9999+"/authors";
        Response response = new ResponseOperation().getResponse(BASE_URL+bookId);
        assertEquals("should return status code 404",404, response.getStatusCode());        
    }
    @Test
    public void testAddNewAuthorToBook() {
    
        BookOperation bookOperation = new BookOperation();
        SingleBook singleBook = new SingleBook(bookOperation.createRandomAuthor());
        
        String postAuthorUrl = "http://localhost:8080/librarytest/rest/authors/";                 
        Response postResponse = new ResponseOperation().postResponse(postAuthorUrl, singleBook);
        assertEquals("should return status code 201",201, postResponse.getStatusCode());
   
        int id = new ResponseOperation().getResponse(postAuthorUrl ).jsonPath().getInt("authors.author[-1].id"); 
        Author author = bookOperation.getAuthor(id);
        singleBook = new SingleBook(author);
        String bookId=4+"/authors";   
        
        Response response = new ResponseOperation().postResponse(BASE_URL+bookId, singleBook);
        assertEquals("should return status code 200",200, response.getStatusCode());
        
        Response postSameAuthoToBookresponse = new ResponseOperation().postResponse(BASE_URL+bookId, singleBook);
        assertEquals("should return status code 400",400, postSameAuthoToBookresponse.getStatusCode());          
    }
    @Test
    public void testUpdateAuthorListToBook() {
//        String bookId=4+"/authors/";
//        String postAuthorUrl = "http://localhost:8080/librarytest/rest/authors/";        
//        
//        BookOperation bookOperation = new BookOperation();
//        SingleBook singleBook = new SingleBook(bookOperation.createRandomAuthor());
//                
//        Response postResponse = new ResponseOperation().postResponse(postAuthorUrl, singleBook);
//        assertEquals("should return status code 201",201, postResponse.getStatusCode());
//
//        int id = new ResponseOperation().getResponse(postAuthorUrl ).jsonPath().getInt("authors.author[-1].id");   
//        
//        Author author = bookOperation.getAuthor(id);
//        
//        Response response = new ResponseOperation().putResponse(BASE_URL+bookId, author);
//        assertEquals("should return status code 200",200, response.getStatusCode());   
    } 
}
