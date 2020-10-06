package com.text.analysis.util;


import java.util.Properties;


import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static Properties properties;

	static Logger propertyLog = Logger.getLogger(PropertiesUtil.class);

	public static String getProperty(String key) {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(PropertiesUtil.class
						.getResourceAsStream("/config.properties"));
			} catch (Exception e) {
				propertyLog.error("Error in Reading the Properties as :\n" + e);
			}
		}
		return properties.getProperty(key);

	}
}
