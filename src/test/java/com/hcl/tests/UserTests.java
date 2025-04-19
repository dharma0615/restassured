package com.hcl.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.hcl.apis.UserAPI;
import com.hcl.payloads.UserPayload;
import com.hcl.utils.Log4j2;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	UserPayload userPayload;
	JSONObject jsonObject;
	
	@BeforeClass
	public void setupData() {
		
		faker=new Faker();
		userPayload=new UserPayload();
		
		userPayload.setName(faker.name().fullName());
		userPayload.setJobId(faker.number().randomDigit());		
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		
		Response response=UserAPI.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		response.then()
				.body("name", equalTo(this.userPayload.getName()))
				.statusCode(201);
		
		String name=response.jsonPath().get("name");
		System.out.println("======================================");
		System.out.println(name);
		System.out.println("======================================");
		
		jsonObject=new JSONObject(response.asString());
		System.out.println("======================================");
		System.out.println(jsonObject.get("id").toString());
		System.out.println("======================================");
		
		File file=new File("src/test/resources/RunTimeAppData/user_data.json");
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, response.asPrettyString());
			
			System.out.print(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.then()
				.assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("TestData/UserData/createUserResponseType.json"));
		
		response.then()
//				.time(lessThan(2000L));
				.time(both(greaterThan(500L)).and (lessThan(2000L)));
		System.out.println("======================================");
		System.out.println(response.getTime());
		Log4j2.info(response.asPrettyString());
		
		
		
		
	}
	
	

}
