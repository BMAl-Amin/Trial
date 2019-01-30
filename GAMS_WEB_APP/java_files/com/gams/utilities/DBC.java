package com.gams.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBC {
	private static Connection conn;
	private static DBC instance = null;
	//Constructor
		private DBC(){
			 String url = "jdbc:mysql://localhost:3306/project_gams";
			 String driver = "com.mysql.jdbc.Driver";
			 String userName = "root"; 
			 String password = "";
			 
			        try {
			                Class.forName(driver);
			                conn = DriverManager.getConnection(url,userName, password);
			                }

			                    catch(ClassNotFoundException cnfErr)
			                    {cnfErr.printStackTrace();
			                    }
			                    catch(SQLException err)
			                    {err.printStackTrace();
			                    }
		}
		
		
		//Instance method of the connection class
		public static DBC getInstance() {
			if(instance == null)
				return new DBC();
			else
				return instance;
		}
		
		
		public Connection getConnection(){
			return conn;
		}
}
