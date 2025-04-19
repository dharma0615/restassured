package com.hcl.apis;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.hcl.endpoints.UserEndPoints;
import com.hcl.payloads.UserPayload;

import io.restassured.response.Response;


public class UserAPI {
	
	public static Response createUser(UserPayload payload) {
		
		Response response= given()
							.header("Content-Type","application/json")
							.header("Accept", "application/json")
							.body(payload)
							.when()
							.post(UserEndPoints.createUser);
		return response;
	}
	
	public static Response getUser(String userId) {
		
		Response response= given()
							.header("Accept", "application/json")
							.pathParams("userId",userId)
							.when()
							.get(UserEndPoints.getUser);
		return response;
		
	}
	public static Response updateUser(UserPayload payload, String userId) {
		
		Response response= given()
							.header("Content-Type","application/json")
							.header("Accept", "application/json")
							.pathParams("userId",userId)
							.body(payload)
							.when()
							.put(UserEndPoints.updateUser);
		return response;
	}
	public static Response deleteUser(String userId) {
		
		Response response= given()
							.header("Content-Type","application/json")
							.pathParams("userId",userId)
							.when()
							.post(UserEndPoints.deleteUser);
		return response;
	}
}
