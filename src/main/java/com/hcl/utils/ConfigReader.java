package com.hcl.utils;

import java.util.Properties;

public class ConfigReader {

	private final Properties properties;
	private static ConfigReader configReader;
	
	String configPath=System.getProperty("user.dir")+"/src/test/resources/ConfigData/ConfigFile.properties";

	private ConfigReader() {
		properties = PropertiesReader.propertyLoader(configPath);
		if (System.getProperty("baseUrl") != null && System.getProperty("baseUrl") != "") {
			properties.setProperty("baseUrl", System.getProperty("baseUrl"));
		}
	}

	public static ConfigReader getInstance() {
		if (configReader == null) {
			configReader = new ConfigReader();
		}
		return configReader;
	}

	public String getBaseUrl() {
		String baseUrl = properties.getProperty("baseUrl");
		if (baseUrl != null)
			return baseUrl;
		else
			throw new RuntimeException("property baseUrl is not specified in the config.properties file");
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
	

}
