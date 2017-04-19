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
import static se.nackademin.rest.test.BooksIdAuthorsTest.BASE_URL;
import static se.nackademin.rest.test.BooksTest.BASE_URL;

/**
 *
 * @author jesper
 */
public class AuthorsTest {
    final static String BASE_URL = "http://localhost:8080/librarytest/rest/authors/";     
    public AuthorsTest() {
    }
    @Test
    public void testGetAllAuthors() {
        
        
        Response response = new ResponseOperation().getResponse(BASE_URL);
        assertEquals("should return status code 200",200, response.getStatusCode());        
    }
    @Test
    public void testAddNewAuthor() {
        BookOperation bookOperation = new BookOperation();
        SingleBook singleBook = bookOperation.createRandomAuthor();
                
        Response response = new ResponseOperation().postResponse(BASE_URL, singleBook);
        assertEquals("should return status code 201",201, response.getStatusCode());
    }
    @Test
    public void testUpdateAuthor() {

        BookOperation bookOperation = new BookOperation();
        SingleBook singleBook = bookOperation.createRandomAuthor();
                
        Response postResponse = new ResponseOperation().postResponse(BASE_URL, singleBook);
        assertEquals("should return status code 201",201, postResponse.getStatusCode());

        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("authors.author[-1].id");  
        
        Author author = bookOperation.getAuthor(id);
        author.setName("olle");
        singleBook = new SingleBook(author);
                 
        Response response = new ResponseOperation().putResponse(BASE_URL, singleBook);
        assertEquals("should return status code 200",200, response.getStatusCode());
    }    
}
