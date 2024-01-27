package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

	@Test()
	void testQueryAndPathParameters()
	{
		// URL: https://reqres.in/api/users?page=2&id=5
		given()
			.pathParam("myMainPath", "api") //path or URI parameters
			.pathParam("mySubPath", "users") //path or URI parameters
			.queryParam("page",2) //query parameters
			.queryParam("id",5) //query parameters
		.when()
			.get("https://reqres.in/{myMainPath}/{mySubPath}") //query parameters will go along with request so no need to add
		.then()
			.statusCode(200)
			.log().all();
	}
}
