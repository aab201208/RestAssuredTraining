package Day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void testDeleteUser(ITestContext context)
	{
		//int id=(Integer) context.getAttribute("user_id");
		int id=(Integer) context.getSuite().getAttribute("user_id");
		String bearertoken = "c16773ef494d30a81e7a782097748088d347d893d6ddcada4971a446413afca2";
		
		given()
			.headers("Authorization","Bearer "+bearertoken)
			.pathParam("id",id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
	}
}
