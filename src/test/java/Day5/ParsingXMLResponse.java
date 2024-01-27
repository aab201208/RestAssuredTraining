package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {

	//@Test
	void testXMLResponseWay1()
	{
		//Approach 1
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
	}
	
	
	//@Test
	void testXMLResponseWay2()
	{
		//Approach 2
		Response res = given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		Assert.assertEquals(res.xmlPath().get("TravelerinformationResponse.page").toString(), "1");
		Assert.assertEquals(res.xmlPath().
				get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString(),"Developer");
	}
	
	@Test
	void testXMLResponseBody()
	{
		//Approach 2
		Response res = given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());
		
		//Verify total travellers
		List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(), 10);
		
		//Verify traveller name present in response
		List<String> traveller_names = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		boolean status=false;
		for(String name: traveller_names)
		{
			if(name.equalsIgnoreCase("vano"))
			{
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
}
