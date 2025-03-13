package com.hcl.tests;

import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicLoginTest2 {
	
	RequestSpecification requestSpecification;
	Response response;
	

	@Test
	public void getCall() {
		
		RestAssured.baseURI= "https://reqres.in/api/users";
		requestSpecification = RestAssured.given();
		response = requestSpecification.get();
		System.out.println(response.asPrettyString());
		

	}

}
