import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.ReusableMethods;
import Files.payload;
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     //Validate if Add place API is working as expected
		// given - all input details
		// when - submit the api -- resource value and http method
		// then -- validate the response
		// log() all() -- to log request and response 
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
				.body("scope",equalTo("APP")).headers("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath Js = new JsonPath(response); // for JSON Parsing
		String Placeid= Js.get("place_id");
		
		System.out.println(Placeid);
		
	//Update place API
		
		String newaddress = "SouthAfrica new way";
	
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "    \"place_id\": \""+Placeid+"\",\r\n"
				+ "    \"address\": \""+newaddress+"\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
	//Get Place API
		
	String getPlaceResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", Placeid)
	.when().get("maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	//JsonPath js1 = new JsonPath(getPlaceResponse); // for JSON Parsing or use sep class to store this java logic
	JsonPath js1=ReusableMethods.rawToJson(response);
	String actualAddress= js1.getString("address");
	
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, "newaddress"); //TestNG assertions
	
	}

}