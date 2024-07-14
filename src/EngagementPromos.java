import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.ReusableMethods;
import Files.payload;


public class EngagementPromos {

//public static void main(String[] args) {
	
	@Test
	public void sampleTest() {
		// TODO Auto-generated method stub

		RestAssured.baseURI ="http://lcg-crm-api.partygaming.com.e7new.com";
		
		//getEngagementToolAPI response
      String response =   given().log().all().header("Content-Type", "application/json")
		.body("{\r\n"
				+ "    \"promoType\": \"SPIN_THE_WHEEL\",\r\n"
				+ "    \"acctName\": \"ld_sanju6\",\r\n"
				+ "    \"brandId\": \"LADBROKEUK\",\r\n"
				+ "    \"productId\": \"CASINO\",\r\n"
				+ "    \"promoId\": \"934095\"\r\n"
				+ "}")
		.when().post("/api/rest/v1/promo/getEngagementToolResponse")
	    .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        System.out.println(response);
        
     //   int count =	js.getInt("responseList.size()");
    //	System.out.println(count);
        
     int promoID= js.getInt("responseList[0].promoDtls.id");
    System.out.println(promoID);
     
     //Get Spin API
     
     Response getspinresponse =  given().log().all().body("{\r\n"
     		+ "  \"action\":\"getSpin\",\r\n"
     		+ "  \"accountName\":\"ld_sanju6\",\r\n"
     		+ "  \"brandId\":\"LADBROKEUK\",\r\n"
     		+ "  \"productId\":\"CASINO\"\r\n"
     		+ "}")
    		 .header("Content-Type", "application/json")
     .when().put("/api/rest/v2/promo/SPIN_THE_WHEEL/"+promoID+"");
   //  .then().log().all().statusCode(200).extract().response().toString();
       System.out.println(getspinresponse.getStatusCode());
       System.out.println(getspinresponse.getBody().asPrettyString());
		
	}
  

}
