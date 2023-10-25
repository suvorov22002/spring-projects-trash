package com.afriland.afbpaypartnerservices.utils;

public class ServerParameter {

private String host;
	
	private String ftpDir;
	private String ftpDirArch;
	private String protocol;
	private int port;
	private String user;
	private String password;
	
	/**
	 * 
	 */
	public ServerParameter() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param host
	 * @param ftpDir
	 * @param protocol
	 * @param ife
	 * @param user
	 * @param sin
	 */
	public ServerParameter(String host, String ftpDir, String protocol, int port, String user, String password, String ftpDirArch) {
		super();
		this.host = host;
		this.ftpDir = ftpDir;
		this.protocol = protocol;
		this.port = port;
		this.user = user;
		this.password = password;
		this.ftpDirArch = ftpDirArch;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getFtpDir() {
		return ftpDir;
	}


	public void setFtpDir(String ftpDir) {
		this.ftpDir = ftpDir;
	}


	public String getFtpDirArch() {
		return ftpDirArch;
	}


	public void setFtpDirArch(String ftpDirArch) {
		this.ftpDirArch = ftpDirArch;
	}


	public String getProtocol() {
		return protocol;
	}


	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}
