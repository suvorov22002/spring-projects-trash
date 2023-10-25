/**
 * 
 */
package com.afb.dpdl.ged.ws.rest.api.dao;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author alex_jaza
 *
 */
public class DataSource {
	
	
	private String name;  
	private String driver;  
	private String conString;  
	private String username;  
	private String password;
		
	private static String ret = "wsdbadmin";
	
	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
	

	/**
	 * 
	 */
	public DataSource() {
		// TODO Auto-generated constructor stub
	}
	
			 
	/**
	 * @param name
	 * @param driver
	 * @param conString
	 * @param username
	 * @param password
	 * @param encryptor
	 */
	public DataSource(String name, String driver, String conString, String username, String password,
			StandardPBEStringEncryptor encryptor) {
		super();
		this.name = name;
		this.driver = driver;
		this.conString = conString;
		this.username = username;
		this.password = password;
		this.encryptor = encryptor;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}
	
	
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	
	/**
	 * @return the conString
	 */
	public String getConString() {
		return conString;
	}
	
	
	/**
	 * @param conString the conString to set
	 */
	public void setConString(String conString) {
		this.conString = conString;
	}
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
		
}	
