package Day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {

	@Test
	void testCreateUser(ITestContext context)
	{
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("email",faker.internet().emailAddress());
		data.put("gender", faker.demographic().sex());
		data.put("status", "active");
		
		String bearertoken = "c16773ef494d30a81e7a782097748088d347d893d6ddcada4971a446413afca2";
		
		int id = given()
			.headers("Authorization","Bearer "+bearertoken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		//context.setAttribute("user_id", id);
		context.getSuite().setAttribute("user_id", id);
	}
}
