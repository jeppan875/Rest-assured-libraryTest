/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import com.jayway.restassured.response.Response;
import nackademin.se.rest.test.ResponseOperation;
import org.junit.Test;
import static org.junit.Assert.*;
import static se.nackademin.rest.test.BooksTest.BASE_URL;

/**
 *
 * @author jesper
 */
public class BooksByAuthorIdTest {
 
    final static String BASE_URL = "http://localhost:8080/librarytest/rest/books/byauthor/";     
    
    public BooksByAuthorIdTest() {
    }
    @Test
    public void testGetAuthorById() {
        int id=4;
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 200",200, response.getStatusCode());        
    }     
}
