package Day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SerializationDeserialization {

	//@Test
	void convertPojoToJson() throws JsonProcessingException
	{
		Student stupojo = new Student();
		stupojo.setName("James");
		stupojo.setLocation("UK");
		stupojo.setPhone("2299888899");
		
		String courses[] = {"C","C++"};
		stupojo.setCourses(courses);
		
		//Convert Java object i.e, POJO to JSON Object (Serialization)
		ObjectMapper obj = new ObjectMapper(); //from com.fasterxml.jackson.databind.ObjectMapper
		String jsondata = obj.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println(jsondata);
		
		given()
		.contentType("application/json")
		.body(jsondata)
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.log().all()
		;
	}
	
	@Test
	void convertJsonToPojo() throws JsonProcessingException
	{
		String jsondata = "{\r\n"
				+ "  \"name\" : \"James\",\r\n"
				+ "  \"location\" : \"UK\",\r\n"
				+ "  \"phone\" : \"2299888899\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
	
		//Convert JSON data to POJO  (De-serialization)
		ObjectMapper obj = new ObjectMapper();
		Student stupojo = obj.readValue(jsondata, Student.class); //converts jsondata to student class object (pojo)
		
		
		given()
		.contentType("application/json")
		.body(stupojo)
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.log().all()
		;
	}
}
