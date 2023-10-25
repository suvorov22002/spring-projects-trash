/**
 * 
 */
package com.afb.dpdl.ged.ws.rest.api.dao;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

/**
 * @author alex_jaza
 * @
 *
 */
public class Utils {
	
	/**
	 * Repertoire data de Configuration de l'instance du serveur
	 */
	public final static String JBOSS_DATA_DIR = System.getProperty("jboss.server.data.dir", ".");
	
	/**
	 * Repertoire des ressources des web services
	 */
	public final static String WS_GED_RESOURCES_DIR = "ws-ged-v3";
	
	/**
	 * Repertoire des configurations des web services
	 */
	public final static String WS_GED_CONFIGS_DIR = "configs";
	
	/**
	 * Nom du fichier de configurations des web services
	 */
	public final static String WS_GED_CONFIGS_FILENAME = "wsdsconfig.properties";
	public final static String WS_GED_CONFIGS_IP = "wsipconfig.properties";
	
	public final static String WS_GED_ENC_PASSWORD = "_P@$�W0RD";

	/**
	 * Nom des parametres de connexion qux BD
	 */
	public final static String WS_GED_DB_DRIVER = "_DRIVER";
	public final static String WS_GED_DB_CONNECTION_STRING = "_CONNECTION_STRING";
	public final static String WS_GED_DB_USERNAME = "_USERNAME";
	public final static String WS_GED_DB_PASSWORD = "_PASSWORD";

	/**
	 * 
	 */
	public Utils() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Recuperation du repertoire des fichiers de configuration des services web de la GED
	 * @return Le chemin du repertoire des fichiers de configuration des services web de la GED
	 */
	public static String getConfigsDirLocation() {
    	return Utils.JBOSS_DATA_DIR + File.separator + Utils.WS_GED_RESOURCES_DIR + File.separator + Utils.WS_GED_CONFIGS_DIR;
    }
	
	
	/**
	 * Recuperation du fichier de configuration des services web de la GED
	 * @param fileName le nom du fichier de configuration
	 * @return L'ensemble des proprietes contenues dans le fichier de configuration
	 */
	public static Properties getConfigProps(String fileName){
		File directory = new File(getConfigsDirLocation());
		// Creation des repertoires s'ils n'existent pas
	    if (! directory.exists()){
	        directory.mkdirs();
	    }
		
	    String fileLocation = getConfigsDirLocation() + File.separator + fileName;
	    
		Properties properties = new Properties();
		try {
			File file = new File(fileLocation);
			// Creation du fichier s'il n'existe pas
			file.createNewFile();
			
			FileInputStream fis = new FileInputStream(fileLocation);
			properties.load(fis);
			fis.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return properties;
	}
	
	
	/**
	 * Recuperation du fichier de configuration des services web de la GED
	 * @param fileName le nom du fichier de configuration
	 * @return L'ensemble des proprietes contenues dans le fichier de configuration
	 */
	public static void setConfigProps(Properties prop, String fileName){
		
		File directory = new File(getConfigsDirLocation());
		// Creation des repertoires s'ils n'existent pas
	    if (! directory.exists()){
	        directory.mkdirs();
	    }
		
	    String fileLocation = getConfigsDirLocation() + File.separator + fileName;
	    
		try {
			File file = new File(fileLocation);
			// Creation du fichier s'il n'existe pas
			file.createNewFile();
			//
			
			prop.store(new FileWriter(file), null);
			
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	public static Set<String> getDSNames(Set<Object> keys){
		Set<String> ret =  new HashSet<>();
		String key;
        for(Object k:keys){
        	key = (String)k;
        	if(key.contains("_")){
        		String[] nom = key.split("_");
        		String nomDB = nom[0];
        		ret.add(nomDB);
        	}
        }
        return ret;
	}
	
	
	/**
	 * 
	 * @param rs
	 */
	public static void closeResulSet(Connection con, PreparedStatement st) {
		if(st != null){
		     try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if(con != null){
	    	try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	
	/**
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	public static String getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        int diffMois = b.get(MONTH) - a.get(MONTH);
        if(diffMois < 0)
            diffMois = 12+diffMois;
        String ancien = "";
        if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
            
        }
        if(diff > 0){
            if(diff == 1)
                ancien += "1 an ";
            else
                ancien += diff+" ans ";
        }
        if(diffMois > 0){
            ancien += diffMois+" mois";
        }
        return ancien;
    }

    
    /**
     * 
     * @param date
     * @return
     */
	public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.FRENCH);
        cal.setTime(date);
        return cal;
    }
	
	public static String transformToANSI(String text) {
		try {
			return new String(text.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return text;
		}
	}
	

}
