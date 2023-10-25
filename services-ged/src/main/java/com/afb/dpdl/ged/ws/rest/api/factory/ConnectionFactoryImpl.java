package com.afb.dpdl.ged.ws.rest.api.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactoryImpl implements IConnectionFactory{
	@Override
	public Connection getConnection(String driver, String connectionString, String userName, String pwd) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName(driver);
		return DriverManager.getConnection(connectionString, userName, pwd);
	}

}
