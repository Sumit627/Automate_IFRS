package com.automation.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class UpdateProperties {
	
	
	public static void updatePropertiesFile(String Key, String Value)
	{
		try{
		FileInputStream in = new FileInputStream(System.getProperty("user.dir")+"/Data/Input.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();
		
		FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"/Data/Input.properties");
		props.setProperty(Key, Value);
		props.store(out, null);
		out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	
	
	

}
