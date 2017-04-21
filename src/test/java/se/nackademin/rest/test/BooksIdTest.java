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
import static se.nackademin.rest.test.BooksTest.BASE_URL;

/**
 *
 * @author jesper
 */
public class BooksIdTest {
    
    final static String BASE_URL = "http://localhost:8080/librarytest/rest/books/";     
    public BooksIdTest() {
    }
    @Test
    public void testFetchBook() {
        int id = 4;
        
        Book book = new BookOperation().getBook(id);
        System.out.println("Title: "+book.getTitle()); 
        System.out.println("Author: "+book.getAuthor());        
        System.out.println("Description: "+book.getDescription());
        System.out.println("Number of pages: "+book.getNbOfPage());
        System.out.println("Isbn: "+book.getIsbn());  
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 200",200, response.getStatusCode());    
    }    
      @Test
    public void testGetInvalidBookReturn404() {        
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id")+1;
        
        Response response = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, response.getStatusCode());    
    }
      @Test
    public void deleteBook() {        
        BookOperation bookOperation = new BookOperation();
        bookOperation.createRandomBook();
        int id = new ResponseOperation().getResponse(BASE_URL).jsonPath().getInt("books.book[-1].id");
        
        Response response = new ResponseOperation().deleteResponse(BASE_URL+id);
        assertEquals("should return status code 204",204, response.getStatusCode());
        
        Response getResponse = new ResponseOperation().getResponse(BASE_URL+id);
        assertEquals("should return status code 404",404, getResponse.getStatusCode());          
    }   
}
