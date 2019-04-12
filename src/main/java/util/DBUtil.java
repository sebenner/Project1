package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import exception.DatabaseException;

public class DBUtil {
	
	private static BasicDataSource ds= new BasicDataSource();
	//static Logger logger= Logger.getLogger(DBUtil.class);
	
	static {
		Properties property= new Properties();
		try {
			property.load(new FileInputStream("C:\\Users\\steve\\eclipse-workspace-photon\\ServletDemo3\\resources\\database.properties"));
			ds.setUsername(property.getProperty("username"));
			ds.setPassword(property.getProperty("password"));
			ds.setUrl(property.getProperty("url"));
			ds.setDriverClassName(property.getProperty("driverClassName"));
			ds.setDefaultAutoCommit(false);
		} catch (IOException e) {
			//logger.error("Unable to get connection"+ e.getMessage());
			e.printStackTrace();
		} 
		
		
		
	}
	
	public static Connection getConnection() throws DatabaseException {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			//logger.error("Unable to get connection"+ e.getMessage());
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}


}
