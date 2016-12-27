package com.avery.config;

public class ConfigService {
	public static String configFilePath;

	public static String getConfigFilePath() {
		return configFilePath;
	}

	public static void setConfigFilePath(String configFilePath) {
		ConfigService.configFilePath = configFilePath;
	}

}
