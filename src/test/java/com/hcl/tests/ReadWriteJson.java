package com.hcl.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadWriteJson {
	
	@Test
	public void extractSingleKeyData() {
		
		Response response=given()
				.header("ContentType", "application/json")
				.baseUri("https://reqres.in/api")
				.pathParams("Id",2)
				.when()
				.get("/users?page={Id}");
		
		int count=Integer.valueOf(response.jsonPath().get("total").toString());
		Assert.assertEquals(count,  12);
		

		JsonPath jsonpath=response.jsonPath();
		
		
		System.out.println(jsonpath.getString("data.first_name"));
		
		
	}
	
	
	
	@Test
	public void extractData() {
		
		given()
				.header("ContentType", "application/json")
				.baseUri("https://reqres.in/api")
				.pathParams("Id",2)
				.when()
				.get("/users?page={Id}")
				.then()
				.body("total", equalTo(Integer.valueOf("12")));
	}
	
	@Test
	public void readData() {
		Response response=given()
							.header("ContentType", "application/json")
							.baseUri("https://reqres.in")
							.pathParams("Id",2)
							.when()
							.get("/api/users?page={Id}");
		
		JSONObject jo=new JSONObject(response.asString());
		for(int i=0; i<jo.getJSONArray("data").length();i++) {
			String name=jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
		System.out.println(name);
			}
		}
	
	@Test
	public void readDataFromJsonFile() {
		
		File file=new File("src/test/resources/TestData/UserData/createUser.json");
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper=new ObjectMapper();
		try {
			EmployeePayload empdata=mapper.readValue(fis, EmployeePayload.class);
			System.out.println("Data from json file: "+empdata.getName());
			System.out.println("Data from json file: "+empdata.getJob());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	@Test
	public void readDataFromJson() {
		
	String payload="{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}";
		ObjectMapper mapper=new ObjectMapper();
		try {
			EmployeePayload empdata=mapper.readValue(payload, EmployeePayload.class);
			System.out.println(empdata.getName());
			System.out.println(empdata.getJob());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileOutputStream fos=new FileOutputStream(new File("src/test/resources/RunTimeAppData/user_data2.json"));
			mapper.writerWithDefaultPrettyPrinter().writeValue(fos, payload);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void extractHeaders() {
		
		Headers headers=given()
							.header("Content-Type", "application/json")
							.baseUri("https://reqres.in/api")
							.pathParams("Id",2)
							.when()
							.get("/users?page={Id}")
							.getHeaders();
		
		for (Header head:headers) {
			System.out.println(head.getName()+"--"+head.getValue());
			System.out.println("-----------------");
			System.out.println(head);

		}
	}
	
	@Test
	public void extractCookies() {
		
		Map<String,String> cookies=given()
									.header("Content-Type", "application/json")
									.baseUri("https://reqres.in/api")
									.pathParams("Id",2)
									.when()
									.get("/users?page={Id}")
									.getCookies();
		
		for (Map.Entry<String,String> cookie:cookies.entrySet()) {
			System.out.println(cookie.getKey()+"--"+cookie.getValue());
		}
	}
		
	
		
	}

