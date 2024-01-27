package Day3;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	//@Test
	void testHeaders()
	{
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Server", "gws");
	}
	
	//@Test
	void getHeaderInfo()
	{
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		//get single header info
		String headervalue = res.getHeader("Content-Type");
		System.out.println("Header value of Content-Type is: "+headervalue);
	}
	
	@Test
	void getAllHeadersInfo()
	{
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		//get all headers info
		Headers allheaders = res.getHeaders();
		
		for(Header hd : allheaders)
		{
			System.out.println("Header value of "+hd.getName()+" is: "+hd.getValue());
		}
	}
}
