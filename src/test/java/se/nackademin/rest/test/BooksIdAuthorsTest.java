/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import nackademin.se.rest.test.BookOperation;
import nackademin.se.rest.test.ResponseOperation;
import nackademin.se.rest.test.models.Book;
import nackademin.se.rest.test.models.SingleBook;
import org.junit.Test;
import static org.junit.Assert.*;
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
    public void testAddNewAuthor() {

    }    
}
