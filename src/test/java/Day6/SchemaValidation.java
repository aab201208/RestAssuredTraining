package Day6;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SchemaValidation {
	
	//@Test
	void jsonSchemaValidation()
	{
		given()
		.when()
			.get("http://localhost:3000/students")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("studentsJsonSchema.json"))
			;
	}
	
	@Test
	void xmlSchemaValidation()
	{
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveller.xsd"))
			;
	}
}
