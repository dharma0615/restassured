package com.hcl.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BasicLoginTest {
	
	public static void main(String[] args) {
		
		Response response= RestAssured
		.given()
		.auth()
		.preemptive()
		.basic("",	"")
		.get();
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().toString());
	}

}
