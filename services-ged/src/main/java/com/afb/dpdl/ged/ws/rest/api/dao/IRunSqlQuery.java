package com.afb.dpdl.ged.ws.rest.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRunSqlQuery {
	
	
	ResultSet selectData(PreparedStatement st , Object[] parameters) throws ClassNotFoundException, SQLException ;
	
	void updateData(PreparedStatement st , Object[] parameters) throws ClassNotFoundException, SQLException ;

	Connection getConnection(String source) throws ClassNotFoundException, SQLException;
	
}
