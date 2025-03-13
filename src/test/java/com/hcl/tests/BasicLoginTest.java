package com.hcl.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BasicLoginTest {

	//Comment updated
	String url = "https://reqres.in/api/users/2";
	String name="restAssured";
	String qaRole="Lead";
	int id;

	@Test (priority = 0)
	public void basicTest() {

		Response response = RestAssured.given().auth().preemptive().basic("", "").get();

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().toString());
	}

	@Test (priority = 1)
	public void getUser() {
		given()
		.when()
		.get("https://reqres.in/api/users/2")
		.then()
		.statusCode(200)
		.body("id", equalTo(2))
		.log().all();
		
	}
	
	@Test (priority=2)
	public void createUser() {
		
		Map<String, String> data=new HashMap<>();
		data.put("name", "dharmarao");
		data.put("job", "qa");
		
		id = given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		
	}
	
	@Test (priority=3, dependsOnMethods= {"createUser"})
	public void updateUser() {
		
		Map<String, String> data=new HashMap<>();
		data.put("name", "dharmarao");
		data.put("job", "qa lead");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test (priority=4)
	public void deleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();
		
	}
	
	

}
