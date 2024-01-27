package Day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {

	//@Test
	void testCookies()
	{
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC","Ae3NU9M8Dduy7Zm8KvVuA1O1sEPT2YzbeAYB6xOKm_ctrP9qknZ40F7hFw")
			.log().all();
		
	}
	
	//@Test
	void captureCookiesInformation()
	{
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
		String cookie_value = res.getCookie("AEC");
		System.out.println("Value of cookie AEC is: "+cookie_value);
	}
	
	@Test
	void captureAllCookiesInformation()
	{
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		//get all cookie info
		Map<String,String> cookies_values = res.getCookies();
		
		for(String k :cookies_values.keySet())
		{
			System.out.println("Value of Cookie "+k+" is: "+res.getCookie(k));
		}
	}
}
