package com.hcl.endpoints;

import com.hcl.utils.ConfigReader;

public class UserEndPoints {
	
	public static String createUser=ConfigReader.getInstance().getBaseUrl()+"/api/users";
	public static String getUser=ConfigReader.getInstance().getBaseUrl()+"/api/users/{userId}";
	public static String updateUser=ConfigReader.getInstance().getBaseUrl()+"/api/users/{userId}";
	public static String deleteUser=ConfigReader.getInstance().getBaseUrl()+"/api/users/{userId}";
	


}
