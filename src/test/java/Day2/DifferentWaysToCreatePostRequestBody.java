package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DifferentWaysToCreatePostRequestBody {

	//1. Post request body using HashMap 
	//@Test(priority=1)
	void testPostUsingHashMap()
	{
		HashMap data = new HashMap();
		
		data.put("name", "James");
		data.put("location", "UK");
		data.put("phone", "2299888899");
		
		String courseArr[] = {"C","C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("James"))
			.body("location",equalTo("UK"))
			.body("phone",equalTo("2299888899"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json")
			.log().all();
		
	}
	
	//2. Post request body using org.json library
	//@Test(priority=1)
	void testPostUsingJsonLibrary()
	{
		JSONObject data = new JSONObject(); //can also import via json-simple library
		data.put("name", "James");
		data.put("location", "UK");
		data.put("phone", "2299888899");
		
		String coursesArr[] = {"C","C++"};
		data.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("James"))
			.body("location",equalTo("UK"))
			.body("phone",equalTo("2299888899"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json")
			.log().all()
			;
	}
	
	//3. Post request body using POJO Class
	//@Test(priority=1)
		void testPostUsingPOJO()
		{
			Pojo_PostRequest data = new Pojo_PostRequest();
			data.setName("James");
			data.setLocation("UK");
			data.setPhone("2299888899");
			
			String courses[] = {"C","C++"};
			data.setCourses(courses);
			
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name",equalTo("James"))
				.body("location",equalTo("UK"))
				.body("phone",equalTo("2299888899"))
				.body("courses[0]",equalTo("C"))
				.body("courses[1]",equalTo("C++"))
				.header("Content-Type", "application/json")
				.log().all()
				;
		}
		
	//4. Post request body using External JSON file
				@Test(priority=1)
				void testPostUsingExternalJsonFile() throws FileNotFoundException
				{
					File file = new File(".\\body.json"); //from java.io
					FileReader fr = new FileReader(file); //from java.io
					
					JSONTokener jt =  new JSONTokener(fr);
					JSONObject data = new JSONObject(jt);
					given()
						.contentType("application/json")
						.body(data.toString())
					.when()
						.post("http://localhost:3000/students")
					.then()
						.statusCode(201)
						.body("name",equalTo("James"))
						.body("location",equalTo("UK"))
						.body("phone",equalTo("2299888899"))
						.body("courses[0]",equalTo("C"))
						.body("courses[1]",equalTo("C++"))
						.header("Content-Type", "application/json")
						.log().all()
						;
				}
		
	//@Test(priority=2)
	void testDelete()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/2c02")
		.then()
			.statusCode(200);
	}
}
