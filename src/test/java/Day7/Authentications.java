package Day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

	//@Test
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test
	void testDigestAuthentication()
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test
	void testPreemptiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test
	void testBearerTokenAuthentication()
	{
		String bearertoken = "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";
		given()
			.headers("Authorization","Bearer "+bearertoken)
		.when()
			.get("https://github.com/aab201208?tab=repositories")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
	void testOAuth1Authentication()
	{
		String accessToken = "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";
		given()
			.auth().oauth("consumerKey", "consumerSecret ", "accessToken ", "secretToken ")
		.when()
			.get("https://github.com/aab201208?tab=repositories")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
	void testOAuth2Authentication()
	{
		String accessToken  = "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";
		given()
			.auth().oauth2(accessToken)
		.when()
			.get("https://github.com/aab201208?tab=repositories")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testAPIKeyAuthentication()
	{
		String apikey  = "9b049bab975fc1d3b5f02981cde78115";
		String cityname = "Delhi";
		given()
			.pathParam("mypath", "data/2.5/weather")
			.queryParam("q", cityname)
			.queryParam("appid", apikey)
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
