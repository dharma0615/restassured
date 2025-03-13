package com.hcl.tests;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostAPIRequestsTest {
	
	
	@Test
	public void hashMapWay() {
		
		Map<String, String> data=new HashMap<>();
		data.put("name","dharma");
		data.put("job", "qa");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.log().all();
		
	}
	
	@Test
	public void orgJson() {
		
		JSONObject data=new JSONObject();
		data.put("name","dharma");
		data.put("job", "qa");
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("dharma"))
		.log().all();
		
	}
	
	@Test
	public void pojoClassWay() {
		
		PojoInputClassTest data=new PojoInputClassTest();
		data.setName("pramee");
		data.setJob("sql dev");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo(data.getName()))
		.log().all();
		
		
	}
	
	@Test
	public void extJsonFilesWay() {
		
		//File File Reader JsonTokener jSonObject
		
		PojoInputClassTest data=new PojoInputClassTest();
		data.setName("pramee");
		data.setJob("sql dev");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo(data.getName()))
		.log().all();
		
		
	}

}
