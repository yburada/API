package allianz;

import static org.testng.Assert.assertEquals;
import io.restassured.response.ResponseBody;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class example_2 {
	Response common() {
		// Initializing the base URI
		RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";
				
		// creating request object
		RequestSpecification request = RestAssured.given();
				
		// send the request and receive the response
		Response response = request.request(Method.GET,"products/4643");
		
		return response;
	}
	@Test
	void products() { 
		
		Response response = common();
		// storing body from the response in a string
		String products = response.getBody().asPrettyString();
		
		System.out.println(products);
		
		//validating the body
		// storing the JSON path of the response
		JsonPath bodyEvaluator = response.jsonPath();
		
		// fetching the id value from the body
		String actual_Id_Value = bodyEvaluator.getString("id").toString();
		
		String expected_Id_Value ="4643";
		
		// comparing the actual and expected values
		assertEquals(actual_Id_Value, expected_Id_Value, "Both the ID are not same");
		
		
		
	}
	
	@Test
	void fetchHeaders() {
		
		Response response = common();
		
		// storing all the headers and printing
		Headers headers = response.getHeaders();
				
		System.out.println(headers.toString());
		
		//validating the headers
		String headerName = headers.getValue("x-powered-by");
		
		String expectedHeader = "Express";
		
		assertEquals(headerName, expectedHeader, "Both the headers are not same");
		
	}
	
	@Test
	void validatePrice() {
		Response response = common();
		//validating the body
		// storing the Json path of the response
		JsonPath bodyEvaluator = response.jsonPath();
		
		// fetching the price value from the body
		String actual_Id_Value1 = bodyEvaluator.getString("price").toString();
						
		String expected_Id_Value1 ="40.91";
						
		// comparing the actual and expected values
		assertEquals(actual_Id_Value1, expected_Id_Value1, "Both the values are not same");
		
	}
	
	@Test
	void statusCode(){
		Response response = common();
		
		// Gathering status code and status line from the response
		int actual_Id_Value1 = response.getStatusCode();
		String actual_Id_Value = response.getStatusLine();
		
		int expected_Id_Value1 = 200;
		String expected_Id_Value = "HTTP/1.1 200 OK";
		// comparing the actual and expected values
		assertEquals(actual_Id_Value1, expected_Id_Value1, "Both the values are not same");
		assertEquals(actual_Id_Value, expected_Id_Value, "Both the values are not same");
	}
	
	@Test
	void parameter() {
		RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";
		
		// creating request object
		RequestSpecification request = RestAssured.given();
				
		// send the request and receive the response
		Response response = request.param("id", 4546).get("/products");
		
		ResponseBody body = response.body();
		
		String res = body.asString();
		
		System.out.println(res);
		
		JsonPath jpath = new JsonPath(res);
		
		String s2 = jpath.getString("name");
		
		System.out.println(s2 +"new");
	}

}
