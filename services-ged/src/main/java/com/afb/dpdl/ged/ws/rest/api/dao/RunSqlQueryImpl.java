package com.afb.dpdl.ged.ws.rest.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class RunSqlQueryImpl implements IRunSqlQuery{
	
	@Inject
	private IConnectionFactory connectionFactory;

	private static Map<String, Connection> mapConnection;
	//private static Map<String, IDataSource> mapConnection;
	
	private static Map<String, IDataSource > mapDataSource;
	
	
	Logger logger;
	
	StandardPBEStringEncryptor encryptor ;
	
	public RunSqlQueryImpl() {
		// TODO Auto-generated constructor stub

		encryptor = new StandardPBEStringEncryptor(); 
		encryptor.setPassword(Utils.WS_GED_ENC_PASSWORD);
		logger  = Logger.getLogger(RunSqlQueryImpl.class.getName());
	}
	
	@Override
	public ResultSet selectData(PreparedStatement st, Object[] parameters) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		if(parameters != null && parameters.length > 0){
			for(int i = 0;i<parameters.length;i++) {
				if(parameters[i] != null) {
					if(parameters[i] instanceof String)
						st.setString(i+1, parameters[i].toString());
					else if(parameters[i] instanceof Integer)
						st.setInt(i+1, (Integer)parameters[i]);
					else if(parameters[i] instanceof Double)
						st.setDouble(i+1, (Double)parameters[i]);
					else if(parameters[i] instanceof Long)
						st.setLong(i+1, (Long)parameters[i]);
					else if(parameters[i] instanceof Date)
						st.setDate(i+1, new java.sql.Date(((Date)parameters[i]).getTime()));
					else if(parameters[i] instanceof Boolean)
						st.setBoolean(i+1, (Boolean)parameters[i]);
					else 
						st.setObject(i+1, parameters[i]);
				}
			}
		}
		
		//logger.info(" --> CURRENT SQL : "+generateActualSql(sql.toUpperCase(), parameters));
		return st.executeQuery();
	}
	
	@Override
	public void updateData(PreparedStatement st, Object[] parameters) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		if(parameters != null && parameters.length > 0){
			for(int i = 0;i<parameters.length;i++) {
				if(parameters[i] != null) {
					if(parameters[i] instanceof String)
						st.setString(i+1, parameters[i].toString());
					else if(parameters[i] instanceof Integer)
						st.setInt(i+1, (Integer)parameters[i]);
					else if(parameters[i] instanceof Double)
						st.setDouble(i+1, (Double)parameters[i]);
					else if(parameters[i] instanceof Long)
						st.setLong(i+1, (Long)parameters[i]);
					else if(parameters[i] instanceof Date)
						st.setDate(i+1, new java.sql.Date(((Date)parameters[i]).getTime()));
					else if(parameters[i] instanceof Boolean)
						st.setBoolean(i+1, (Boolean)parameters[i]);
					else
						st.setObject(i+1, parameters[i]);	
				}
				else {
					st.setObject(i+1, null);
				}
			}
		}
		//logger.info(source+" --> CURRENT SQL : "+generateActualSql(sql.toUpperCase(), parameters));
		st.executeUpdate();
	}

	
	/*private Connection getConnection(String source) throws ClassNotFoundException, SQLException{
		if(mapConnection == null)
			mapConnection = new HashMap<>();
		Connection con = mapConnection.get(source);

		Properties properties = new Properties();
		properties = Utils.getConfigProps(Utils.WS_GED_CONFIGS_FILENAME);
		
		con = connectionFactory.getConnection(
				properties.getProperty(source.concat(Utils.WS_GED_DB_DRIVER)), 
				properties.getProperty(source.concat(Utils.WS_GED_DB_CONNECTION_STRING)), 
				properties.getProperty(source.concat(Utils.WS_GED_DB_USERNAME)), 
				encryptor.decrypt(properties.getProperty(source.concat(Utils.WS_GED_DB_PASSWORD))));
				
	    mapConnection.put(source, con);
		
		return con;
	}*/
	
	@Override
	public Connection getConnection(String source) throws ClassNotFoundException, SQLException{
		if(mapDataSource == null)
			mapDataSource = new HashMap<>();
		IDataSource ds = mapDataSource.get(source);
		if(ds != null) {
			return ds.getBasicDS().getConnection();
		}
		
		//IDataSource ds = null;
		Properties properties = new Properties();
		properties = Utils.getConfigProps(Utils.WS_GED_CONFIGS_FILENAME);
		
		if(IConnectionFactory.CBS_DB.equals(source)) {
			ds = DataSourceCBS.getInstance(properties.getProperty(source.concat(Utils.WS_GED_DB_DRIVER)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_CONNECTION_STRING)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_USERNAME)),
					encryptor.decrypt(properties.getProperty(source.concat(Utils.WS_GED_DB_PASSWORD))));
		}
		
		if(IConnectionFactory.ALTGRH_DB.equals(source)) {
			Connection con = connectionFactory.getConnection(
					properties.getProperty(source.concat(Utils.WS_GED_DB_DRIVER)), 
					properties.getProperty(source.concat(Utils.WS_GED_DB_CONNECTION_STRING)), 
					properties.getProperty(source.concat(Utils.WS_GED_DB_USERNAME)), 
					encryptor.decrypt(properties.getProperty(source.concat(Utils.WS_GED_DB_PASSWORD))));
			return con;
			/*ds = DataSourceAltgrh.getInstance(properties.getProperty(source.concat(Utils.WS_GED_DB_DRIVER)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_CONNECTION_STRING)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_USERNAME)),
					encryptor.decrypt(properties.getProperty(source.concat(Utils.WS_GED_DB_PASSWORD))));*/
		}
		
		if(IConnectionFactory.PORTAL_DB.equals(source)) {
			ds = DataSourcePortal.getInstance(properties.getProperty(source.concat(Utils.WS_GED_DB_DRIVER)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_CONNECTION_STRING)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_USERNAME)),
					encryptor.decrypt(properties.getProperty(source.concat(Utils.WS_GED_DB_PASSWORD))));
		}
		
		if(IConnectionFactory.ANNUAIRE_DB.equals(source)) {
			ds = DataSourceAnnuaire.getInstance(properties.getProperty(source.concat(Utils.WS_GED_DB_DRIVER)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_CONNECTION_STRING)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_USERNAME)),
					encryptor.decrypt(properties.getProperty(source.concat(Utils.WS_GED_DB_PASSWORD))));
		}
		
		if(IConnectionFactory.GED_DB.equals(source)) {
			ds = DataSourceGed.getInstance(properties.getProperty(source.concat(Utils.WS_GED_DB_DRIVER)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_CONNECTION_STRING)),
					properties.getProperty(source.concat(Utils.WS_GED_DB_USERNAME)),
					encryptor.decrypt(properties.getProperty(source.concat(Utils.WS_GED_DB_PASSWORD))));
		}
				
	    mapDataSource.put(source, ds);
		
		if(ds == null)
			return null;
		return ds.getBasicDS().getConnection();
	}
	
	public static void removeDS(String key) {
		if(mapDataSource != null)
			mapDataSource.remove(key);
	}
	
	private String generateActualSql(String sqlQuery, Object... parameters) {
		if(parameters == null)
			return sqlQuery;
	    String[] parts = sqlQuery.split("\\?");
	    StringBuilder sb = new StringBuilder();

	    // This might be wrong if some '?' are used as litteral '?'
	    for (int i = 0; i < parts.length; i++) {
	        String part = parts[i];
	        sb.append(part);
	        if (i < parameters.length) {
	            sb.append(formatParameter(parameters[i]));
	        }
	    }

	    return sb.toString();
	}

	private String formatParameter(Object parameter) {
	    if (parameter == null) {
	        return "NULL";
	    } else {
	        if (parameter instanceof String) {
	            return "'" + ((String) parameter).replace("'", "''") + "'";
	        } else if (parameter instanceof Timestamp) {
	            return "to_timestamp('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS").
	                    format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss.ff3')";
	        } else if (parameter instanceof Date) {
	            return "to_date('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
	                    format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss')";
	        } else if (parameter instanceof Boolean) {
	            return ((Boolean) parameter).booleanValue() ? "1" : "0";
	        } else {
	            return parameter.toString();
	        }
	    }
	}

}
