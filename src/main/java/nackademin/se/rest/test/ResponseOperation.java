/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nackademin.se.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static nackademin.se.rest.test.BookOperation.BASE_URL;
import nackademin.se.rest.test.models.SingleBook;

/**
 *
 * @author jesper
 */
public class ResponseOperation {
    
    public Response getResponse(String BASE_URL){
        Response response = given().accept(ContentType.JSON).get(BASE_URL).prettyPeek();
        return response;
}
    public Response postResponse(String BASE_URL, SingleBook singleBook){
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().post(BASE_URL).prettyPeek(); 
        return response;
}    
    public Response putResponse(String BASE_URL, SingleBook singleBook){
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().put(BASE_URL).prettyPeek(); 
        return response;
}
    public Response deleteResponse(String BASE_URL){
        Response response = delete(BASE_URL); 
        return response;
}    
}
