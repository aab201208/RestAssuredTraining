package Day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	
	//Approch 1:
	//@Test()
	void testJSONResponseWay1()
	{	
		given()
			.contentType("aplication/json")
		.when()
			.get("http://localhost:3000/student")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("[2].name", equalTo("Smith"));
	}
	
	//Approch 2:
	@Test()
	void testJSONResponseWay2()
	{	
		Response res = given()
			.contentType("ContentType.JSON")
		.when()
			.get("https://reqres.in/api/unknown");
		
		/*Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json");
		Assert.assertEquals(res.jsonPath().get("[2].name").toString(),"Smith");*/
		
		JSONObject jo = new JSONObject(res.asString()); //converting response from Response type to JSONObject type
		
		//Validate if name is present 
		boolean status=false;;
		for(int i=0;i<jo.getJSONArray("data").length();i++)
		{
			//System.out.println(jo.getJSONArray("data").getJSONObject(i).get("name").toString());
			String name = jo.getJSONArray("data").getJSONObject(i).get("name").toString();
			if(name.equals("fuchsia rose"))
			{
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
		//Validate total
				double total=0;
				for(int i=0;i<jo.getJSONArray("data").length();i++)
				{
					//System.out.println(jo.getJSONArray("data").getJSONObject(i).get("name").toString());
					String year = jo.getJSONArray("data").getJSONObject(i).get("year").toString();
					total=total+Double.parseDouble(year);
				}
				Assert.assertEquals(total, 12015);
		
	}

}
