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

/**
 *
 * @author jesper
 */
public class AuthorsIdTest {
    
    final static String BASE_URL = "http://localhost:8080/librarytest/rest/authors/";   
    
    public AuthorsIdTest() {
    }
    @Test
    public void testFetchAuthor() {
        int id = 5;
        
        Author author = new BookOperation().getAuthor(id);
        System.out.println("Name"+author.getName());
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 200",200, response.getStatusCode());    
    }
    @Test    
    public void testGetInvalidAuthorReturn404() {
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("authors.author[-1].id")+1;
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, response.getStatusCode());    
    }
      @Test
    public void deleteBook() {        
        BookOperation bookOperation = new BookOperation();
        SingleBook singleBook = new SingleBook(bookOperation.createRandomAuthor());
                
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, singleBook);
        assertEquals("should return status code 201",201, postResponse.getStatusCode());
        
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("authors.author[-1].id");
        
        Response response = new ResponseOperation().deleteResponse(BASE_URL+id);
        assertEquals("should return status code 204",204, response.getStatusCode());
        
        Response getResponse = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, getResponse.getStatusCode());          
    }    
}
