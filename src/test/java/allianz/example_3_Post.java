package allianz;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import allianz1.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class example_3_Post {
        
		@Test(dataProvider= "userdata")
		void post(String name, String job){
			
			RestAssured.baseURI = "https://reqres.in/api/users";
			
			RequestSpecification httprequest = RestAssured.given();
			
			JSONObject js = new JSONObject();
			
			js.put("name", name);
			js.put("job", job);
			
			httprequest.header("Content-Type", "application/json");
			
			httprequest.body(js.toJSONString());
			
			Response response = httprequest.request(Method.POST);
			
			System.out.println(response.getStatusCode()+" "+response.getStatusLine());
			
			//System.out.println(response.getBody().asPrettyString());
		}
		
		@DataProvider(name="userdata")
		String[][] dataProvider() throws InvalidFormatException, IOException {
			String path ="C:\\Users\\yburada\\eclipse-workspace\\allianz\\src\\data\\data.xlsx";
			
			int rowCount = example_3.rowCount(path);
			int colCount = example_3.cellCount(path,1);
			
			//System.out.println(rowCount+" "+colCount);
			
			String[][] user = new String[rowCount][colCount];
			
			for(int i=1;i<=rowCount;i++) {
				for(int j=0;j<colCount;j++) {
					user[i-1][j] = example_3.getdata(path,i,j);
					//System.out.println(user[i-1][j]);
				}
			}
			return user;
		}

}
