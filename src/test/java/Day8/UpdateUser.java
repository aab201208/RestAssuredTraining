package Day8;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void testUpdateUser(ITestContext context)
	{
		//int id= (Integer) context.getAttribute("user_id");
		int id=(Integer) context.getSuite().getAttribute("user_id");
		String bearertoken = "c16773ef494d30a81e7a782097748088d347d893d6ddcada4971a446413afca2";
		
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("status", "inactive");
		
		given()
			.headers("Authorization","Bearer "+bearertoken)
			.pathParam("id",id)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
}