package com.hcl.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import java.util.Map.Entry;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ParamsCookieHeaderTest {
	
	@Test
	public void paramTest() {
		
		given()
		.pathParam("mypath","users")
		.queryParam("id", 2)
		.queryParam("page", 2)
		
		.when()
		.get("https://reqres.in/api/{mypath}")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test
	public void cookieTest() {
		
	Response response=	given()
		
		.when()
		.get("https://google.com");
		
		String cookie= response.getCookie("AEC");
		System.out.println(cookie);
		
		
		 Map<String, String> map= response.cookies();
		 for(Entry<String, String> data: map.entrySet()) {
			 
			 System.out.println(data.getKey()+"----"+data.getValue());
			 
		 }
	
		
	}


}
