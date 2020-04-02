package com.automation.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.automation.test.SetUp;

public class DbConnection {

	 public static Connection con = null;
     public int row1;
     public Statement st = null;
     public ResultSetMetaData rsMetaData=null;
     Properties data = SetUp.data;
     
     
     public Connection establishConnection() throws Exception{
                     try {
                    	 data =PropertiesFile.readPropertiesFile();
                                     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                     System.out.println(data.getProperty("dbUrl"));
                                     con = DriverManager.getConnection(data.getProperty("dbUrl"));
                                     System.out.println("connection is "+con);
                     } catch (Exception e) {
                                     e.printStackTrace();
                                     throw e;
                     }
                     System.out.println("Connection is created");
                     return con;
     }
     
     public ResultSet extractDataFromDatabase(String sqlQuery) throws Exception{
    	 
    	 			Connection con = establishConnection();
                     boolean flag=false;
                     ResultSet rs = null;
                     if (con != null) {
                                     System.out.println("Database Connected");
                                     st = ((java.sql.Connection)con).createStatement();
                                     try{
                                                     flag=st.execute(sqlQuery);
                                                     rs = st.executeQuery(sqlQuery);
                                                     System.out.println("The flag is "+flag);
                                                     
                                     } catch (Exception e){
                                                     
                                                     e.printStackTrace();
                                     }
                                     
                                     
                                     if(flag){
                                                     System.out.println("The main query is running");
//                                                     rs = st.executeQuery(sqlQuery);
                                                     
                                                     
                                     }
                                     
                                     
                                     
                         	rsMetaData = rs.getMetaData();
                            System.out.println(rsMetaData.getColumnName(1));
                            System.out.println(rsMetaData.getColumnDisplaySize(1));
                            System.out.println(rsMetaData.getColumnCount());
                            

                     }else{
                    	 System.out.println("*********************Connection is null******************************");
                     }
                     return rs;
     }
}
