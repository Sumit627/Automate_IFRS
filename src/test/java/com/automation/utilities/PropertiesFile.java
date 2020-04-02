package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFile {
	
	
	public static Properties readPropertiesFile()
	{
		Properties properties = null;
		try
		{
			File file = new File("Data\\Input.properties");
			FileInputStream fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return properties;
	}
	
	public static Properties readQuery()
	{
		Properties properties = null;
		try
		{
			File file = new File("Data\\Query.properties");
			FileInputStream fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return properties;
	}

}
