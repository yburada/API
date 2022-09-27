package allianz;

import static org.testng.Assert.assertEquals;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class exampl1 {
	@Test
	void user() {
		RestAssured.baseURI = "http://localhost:3000/";
		RequestSpecification httprequest = RestAssured.given();
		Response rs = httprequest.request(Method.GET,"books/");
		System.out.println(rs.getBody().asPrettyString());
		int actual_Status_Code = rs.getStatusCode();
		int expected_Status_Code = 200;
		assertEquals(actual_Status_Code, expected_Status_Code);
		//String id_Value = rs.getHeader("Content-Type");
		//JsonPath jsonPathEvaluator = rs.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		//String city = jsonPathEvaluator.get("data.id").toString();
		//System.out.println(city.toString());
	}

}
