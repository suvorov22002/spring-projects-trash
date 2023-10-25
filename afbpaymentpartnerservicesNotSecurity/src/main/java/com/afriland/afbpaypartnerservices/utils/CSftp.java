package com.afriland.afbpaypartnerservices.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

public class CSftp {
	
	private String strHost;
	private String strUser;
	private String strPasswd;
	private Session session;
	private String strPortSSHZone;
	private ChannelSftp cannal;
	private static boolean annuler;
	
	public CSftp(String strLeHost, String strLeUser, String strLePasswd){
		this.strHost = strLeHost;
		this.strUser = strLeUser;
		this.strPasswd = strLePasswd;
		annuler = false;
	}
	
	/**
	 * 
	 * @param lfile
	 * @param user
	 * @param host
	 * @param rfile
	 * @param password
	 * @throws Exception
	 */
	public static Boolean send(String lfile, String user, String host, String ftpDir, String rfile, String password)  throws Exception {

		try{

			JSch jsch = new JSch();
			Session  session = jsch.getSession(user,host,22);
			UserInfo ui = new MyUserInfo(password);
			session.setUserInfo(ui);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp cannal = ((ChannelSftp)channel);

			if(!ftpDir.trim().isEmpty())cannal.cd(ftpDir);
			
			File remoteFileName = new File(lfile);
			// cannal.put(lfile, rfile, null, 0);
			cannal.put(new FileInputStream(remoteFileName), remoteFileName.getName());
			// cannal.rm(remoteFileName.getName());
			cannal.exit();
			channel.disconnect();
			session.disconnect();


		}catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}

		return Boolean.TRUE;

	}

	
	public static Boolean checkDir(String lfile, String user, String host, String ftpDir, String rfile, String password)  throws Exception {

		try{

			JSch jsch = new JSch();
			Session  session = jsch.getSession(user,host,22);
			UserInfo ui = new MyUserInfo(password);
			session.setUserInfo(ui);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp cannal = ((ChannelSftp)channel);

			try {
				if(!ftpDir.trim().isEmpty())cannal.cd(ftpDir);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Boolean.FALSE;
			}
			
			
			cannal.exit();
			channel.disconnect();
			session.disconnect();


		}catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}

		return Boolean.TRUE;

	}
	


	public static File locateFile(String lfile, String user, String host, String ftpDir, String rfile, String password)  throws Exception {

		File file = null;
		try{

			JSch jsch = new JSch();
			Session  session = jsch.getSession(user,host,22);
			UserInfo ui = new MyUserInfo(password);
			session.setUserInfo(ui);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp cannal = ((ChannelSftp)channel);

			if(!ftpDir.trim().isEmpty())cannal.cd(ftpDir);
			file = new File(lfile);

			//cannal.put(new FileInputStream(remoteFileName), remoteFileName.getName());

			cannal.exit();
			channel.disconnect();
			session.disconnect();


		}catch (Exception e){
			e.printStackTrace();
		}

		return file;

	}



	@SuppressWarnings("unchecked")
	public static void find(String user, String host, String ftpDir, String password,String pathFile) {

		try{

			JSch jsch = new JSch();
			Session  session = jsch.getSession(user,host,22);
			UserInfo ui = new MyUserInfo(password);
			session.setUserInfo(ui);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp cannal = ((ChannelSftp)channel);
			if(!ftpDir.trim().isEmpty())cannal.cd(ftpDir);


			//File remoteFileName = new File(lfile);
			Vector<ChannelSftp.LsEntry>  fileList = cannal.ls("*");

			for(ChannelSftp.LsEntry entry : fileList){
				if(!entry.getAttrs().isDir()){
					String name = entry.getFilename();
					//System.out.println("------------name-----------"+name);
					OutputStream output = new FileOutputStream(pathFile+File.separator+name);
					cannal.get(name,pathFile+File.separator+name);
					output.close();

					// Suppression du Fichier
					cannal.rm(name);
				}
			}

			cannal.exit();
			channel.disconnect();
			session.disconnect();


		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unchecked")
	public static List<String> findRemoteFiles(String user, String host, String ftpDir, String password,String pathFile) {
		
		List<String> files = new ArrayList<String>();
		
		try{

			JSch jsch = new JSch();
			Session  session = jsch.getSession(user,host,22);
			UserInfo ui = new MyUserInfo(password);
			session.setUserInfo(ui);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp cannal = ((ChannelSftp)channel);
			if(!ftpDir.trim().isEmpty())cannal.cd(ftpDir);


			//File remoteFileName = new File(lfile);
			Vector<ChannelSftp.LsEntry>  fileList = cannal.ls("*");

			for(ChannelSftp.LsEntry entry : fileList){
				if(!entry.getAttrs().isDir()){
					String name = entry.getFilename();
					//System.out.println("------------name-----------"+name);
					OutputStream output = new FileOutputStream(pathFile+File.separator+name);
					cannal.get(name,pathFile+File.separator+name);
					output.close();

					// Suppression du Fichier
					// cannal.rm(name);
					files.add(name);
				}
			}

			cannal.exit();
			channel.disconnect();
			session.disconnect();


		}catch (Exception e){
			e.printStackTrace();
		}
		
		return files;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void moveRemoteFile(String host, String user, String password, String ftpDir, String backDir, String filename) {
		
		List<String> files = new ArrayList<String>();
		
		try{

			JSch jsch = new JSch();
			Session  session = jsch.getSession(user,host,22);
			UserInfo ui = new MyUserInfo(password);
			session.setUserInfo(ui);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp cannal = ((ChannelSftp)channel);
			
			String filePath = ftpDir + File.separator + filename;
			System.out.println("File to archive : " + filePath);
			System.out.println("Archive directory : " + backDir);
			if(!ftpDir.trim().isEmpty() && !backDir.trim().isEmpty())
				cannal.rename(filePath, backDir + File.separator + filename);

			cannal.exit();
			channel.disconnect();
			session.disconnect();


		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	

	public boolean openSFTP(){

		try{
			JSch jsch = new JSch();
			this.session = jsch.getSession(this.strUser, this.strHost, Integer.parseInt(strPortSSHZone));
			UserInfo ui = new MyUserInfo(this.strPasswd);
			this.session.setUserInfo(ui);
			this.session.connect();
			Channel channel = this.session.openChannel("sftp");
			channel.connect();
			this.cannal = ((ChannelSftp)channel);
			return true;
		}catch (Exception e){
			e.printStackTrace(System.out);
		}

		return false;

	}

	public boolean closeSFTP(){

		try{

			this.session.disconnect();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return false;
	}

	public boolean envoieSFTP(String strLocalPath, String strRemotePath)
	{
		try
		{
			annuler = false;
			this.cannal.put(strLocalPath, strRemotePath, null, 0);
			if (annuler)
			{
				File remoteFileName = new File(strLocalPath);
				this.cannal.cd(strRemotePath);
				this.cannal.rm(remoteFileName.getName());
				return false;
			}
			return true;
		}
		catch (SftpException e)
		{
			return false;
		}
		catch (Exception exc)
		{
		}
		return false;
	}

	public static class MyUserInfo implements UserInfo
	{
		String strPasswd;

		public MyUserInfo(String strLePasswd)
		{
			this.strPasswd = strLePasswd;
		}

		public String getPassword()
		{
			return this.strPasswd;
		}

		public boolean promptYesNo(String str)
		{
			return true;
		}

		public String getPassphrase()
		{
			return null;
		}

		public boolean promptPassphrase(String strMessage)
		{
			return true;
		}

		public boolean promptPassword(String strMessage)
		{
			return true;
		}

		public void showMessage(String strMessage) {}
	}
}
