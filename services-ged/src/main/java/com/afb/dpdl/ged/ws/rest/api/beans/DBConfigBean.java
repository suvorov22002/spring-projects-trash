/**
 * 
 */
package com.afb.dpdl.ged.ws.rest.api.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.afb.dpdl.ged.ws.rest.api.dao.DataSource;
import com.afb.dpdl.ged.ws.rest.api.dao.DataSourceAltgrh;
import com.afb.dpdl.ged.ws.rest.api.dao.DataSourceAnnuaire;
import com.afb.dpdl.ged.ws.rest.api.dao.DataSourceCBS;
import com.afb.dpdl.ged.ws.rest.api.dao.DataSourceGed;
import com.afb.dpdl.ged.ws.rest.api.dao.DataSourcePortal;
import com.afb.dpdl.ged.ws.rest.api.dao.RunSqlQueryImpl;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;

/**
 * @author alex_jaza
 *
 */
@ManagedBean
@RequestScoped
public class DBConfigBean {
	
	@Inject
	private IConnectionFactory connectionFactory;
	
	private String message = ""; 
	private String name = ""; 
	private Boolean add = Boolean.FALSE;
	private Boolean edit = Boolean.FALSE;
	private Boolean del = Boolean.FALSE;
	private Boolean test = Boolean.FALSE;
	
	public ArrayList<DataSource> dbConfigsList = new ArrayList<>();
	private Properties properties = new Properties();
	public Set<String> nomDBList =  new HashSet<>();
	
	private static String ret = "wsdbadmin";
	
	private DataSource ds = new DataSource();
	
	private List<SelectItem> dsItems = new ArrayList<SelectItem>();
	
	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
	
	/**
	 * 
	 */
	public DBConfigBean() {
		// TODO Auto-generated constructor stub
		encryptor.setPassword(Utils.WS_GED_ENC_PASSWORD);
		dsItems = new ArrayList<SelectItem>();
		dsItems.add( new SelectItem(IConnectionFactory.ACHAT_DB, IConnectionFactory.ACHAT_DB) );
		dsItems.add( new SelectItem(IConnectionFactory.ALTGRH_DB, IConnectionFactory.ALTGRH_DB) );
		dsItems.add( new SelectItem(IConnectionFactory.ANNUAIRE_DB, IConnectionFactory.ANNUAIRE_DB) ); 
		dsItems.add( new SelectItem(IConnectionFactory.ATFF_DB, IConnectionFactory.ATFF_DB) ); 
		dsItems.add( new SelectItem(IConnectionFactory.CBS_DB, IConnectionFactory.CBS_DB) ); 
		dsItems.add( new SelectItem(IConnectionFactory.FT_DB, IConnectionFactory.FT_DB) ); 
		dsItems.add( new SelectItem(IConnectionFactory.GACHA_DB, IConnectionFactory.GACHA_DB) ); 
		dsItems.add( new SelectItem(IConnectionFactory.GED_DB, IConnectionFactory.GED_DB) ); 
		dsItems.add( new SelectItem(IConnectionFactory.PORTAL_DB, IConnectionFactory.PORTAL_DB) ); 
		dsItems.add( new SelectItem(IConnectionFactory.SMVISTA_DB, IConnectionFactory.SMVISTA_DB) ); 
		
		
	}
	
	
	/**
	 * @return the ds
	 */
	public DataSource getDs() {
		return ds;
	}


	/**
	 * @param ds the ds to set
	 */
	public void setDs(DataSource ds) {
		this.ds = ds;
	}
	

	/**
	 * @return the dsItems
	 */
	public List<SelectItem> getDsItems() {
		return dsItems;
	}


	/**
	 * @param dsItems the dsItems to set
	 */
	public void setDsItems(List<SelectItem> dsItems) {
		this.dsItems = dsItems;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the edit
	 */
	public Boolean getEdit() {
		return edit;
	}
	
	
	/**
	 * @param edit the edit to set
	 */
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	
		
	/**
	 * @return the add
	 */
	public Boolean getAdd() {
		return add;
	}


	/**
	 * @param add the add to set
	 */
	public void setAdd(Boolean add) {
		this.add = add;
	}


	/**
	 * @return the test
	 */
	public Boolean getTest() {
		return test;
	}


	/**
	 * @param test the test to set
	 */
	public void setTest(Boolean test) {
		this.test = test;
	}

	
	/**
	 * @return the del
	 */
	public Boolean getDel() {
		return del;
	}


	/**
	 * @param del the del to set
	 */
	public void setDel(Boolean del) {
		this.del = del;
	}


	@PostConstruct
	public void init() {
		message = "";
//		name = "";
		dbConfigsList = new ArrayList<>();
		properties = new Properties();
		properties = Utils.getConfigProps(Utils.WS_GED_CONFIGS_FILENAME);
		
		nomDBList = Utils.getDSNames(properties.keySet());
		
//        System.out.println("LIST NOM DB : "+nomDBList);
        for(String k:nomDBList){
			DataSource dbc = new DataSource();
			dbc.setName(k);
			dbc.setDriver(properties.getProperty(k.concat(Utils.WS_GED_DB_DRIVER)));
			dbc.setConString(properties.getProperty(k.concat(Utils.WS_GED_DB_CONNECTION_STRING)));
			dbc.setUsername(properties.getProperty(k.concat(Utils.WS_GED_DB_USERNAME)));
			dbc.setPassword(encryptor.decrypt(properties.getProperty(k.concat(Utils.WS_GED_DB_PASSWORD))));
			dbConfigsList.add(dbc);
        }
//		dbConfigsList = Collections.list(Utils.getConfigProps(Utils.WS_GED_CONFIGS_FILENAME).elements());
	}
	
	
	public void initVars() {
		/*System.out.println("Var initialization!");*/
		ds = new DataSource();
		message = "";
	}
	
	
	public ArrayList<DataSource> dbList() {
//		dbConfigsList = new ArrayList<>();
//		System.out.println("DBConfig : List db connections");
//		for(String k:nomDBList){
//			DataSource dbc = new DataSource();
//			dbc.setName(k);
//			dbc.setDriver(properties.getProperty(k.concat(Utils.WS_GED_DB_DRIVER)));
//			dbc.setConString(properties.getProperty(k.concat(Utils.WS_GED_DB_CONNECTION_STRING)));
//			dbc.setUsername(properties.getProperty(k.concat(Utils.WS_GED_DB_USERNAME)));
//			dbc.setPassword(properties.getProperty(k.concat(Utils.WS_GED_DB_PASSWORD)));
//			dbConfigsList.add(dbc);
//        }
		return dbConfigsList;
	}
	
	
	public void saveDBConfig(DataSource dbConfig) {
		/*System.out.println("DBConfig : Save db connection");
		System.out.println("NAME : "+dbConfig.getName()+" / "+name);
		System.out.println("DRIVER : "+dbConfig.getDriver());
		System.out.println("CONSTRING : "+dbConfig.getConString());
		System.out.println("USERNAME : "+dbConfig.getUsername());
		System.out.println("PWD : "+dbConfig.getPassword());*/
//		if(!(dbConfig.getName() != null && !dbConfig.getName().isEmpty())){
//			if(name != "" && name != null) dbConfig.setName(name);
//		}
		if(dbConfig.getName() != null && !dbConfig.getName().isEmpty() && dbConfig.getDriver() != null && !dbConfig.getDriver().isEmpty() && dbConfig.getConString() != null && !dbConfig.getConString().isEmpty() && 
				dbConfig.getUsername() != null && !dbConfig.getUsername().isEmpty() && dbConfig.getPassword() != null && !dbConfig.getPassword().isEmpty()){
			/*System.out.println("CHECK OK");*/
			properties.put(dbConfig.getName().concat(Utils.WS_GED_DB_DRIVER), dbConfig.getDriver());
			properties.put(dbConfig.getName().concat(Utils.WS_GED_DB_CONNECTION_STRING), dbConfig.getConString());
			properties.put(dbConfig.getName().concat(Utils.WS_GED_DB_USERNAME), dbConfig.getUsername());
			properties.put(dbConfig.getName().concat(Utils.WS_GED_DB_PASSWORD), encryptor.encrypt( dbConfig.getPassword() ));
			//System.out.println("PWD/ENC_PWD : "+ds.getPassword()+" / "+encryptor.encrypt( dbConfig.getPassword() ));
			
			Utils.setConfigProps(properties, Utils.WS_GED_CONFIGS_FILENAME);
		}/*else System.out.println("CHECK KO");*/
		name = "";
	}
	
	
	public String addDBConfig() {
		/*System.out.println("DBConfig : Add db connection");*/
		initVars();
		add = Boolean.TRUE;
		edit = Boolean.FALSE;
		test = Boolean.FALSE;
		del = Boolean.FALSE;
		return ret;
	}
	
	
	public String editDBConfig(DataSource dbConfig) {
		/*System.out.println("DBConfig : Edit db connection");*/
//		name = dbConfig.getName();
//		System.out.println("NAME EDIT : "+name);
		this.ds.setName(dbConfig.getName());  
		this.ds.setDriver(dbConfig.getDriver());  
		this.ds.setConString(dbConfig.getConString());  
		this.ds.setUsername(dbConfig.getUsername());;  
		this.ds.setPassword(dbConfig.getPassword());
		
		/*System.out.println("NAME EDIT : "+this.ds.getName());*/
		
		edit = Boolean.TRUE;
		add = Boolean.FALSE;
		test = Boolean.FALSE;
		del = Boolean.FALSE;
		return ret;
	}
	
	
	public String updateDBConfig() {
		/*System.out.println("DBConfig : Update db connection");
		System.out.println("DS NAME : "+this.ds.getName());*/
		saveDBConfig(this.ds);
		initVars();
		edit = Boolean.FALSE;
		add = Boolean.FALSE;
		test = Boolean.FALSE;
		del = Boolean.FALSE;
		init();

		if(IConnectionFactory.CBS_DB.equals(this.ds.getName()))
			DataSourceCBS.setDs();
		
		if(IConnectionFactory.PORTAL_DB.equals(this.ds.getName()))
			DataSourcePortal.setDs();
		
		if(IConnectionFactory.ALTGRH_DB.equals(this.ds.getName()))
			DataSourceAltgrh.setDs();
		
		if(IConnectionFactory.ANNUAIRE_DB.equals(this.ds.getName()))
			DataSourceAnnuaire.setDs();
		
		if(IConnectionFactory.GED_DB.equals(this.ds.getName()))
			DataSourceGed.setDs();
		
		RunSqlQueryImpl.removeDS(this.ds.getName());
		return ret;
	}
	
	
	public String deleteDBConfig(String name) {
		/*System.out.println("DBConfig : Delete db connection "+name!=null ? name : "");*/
		properties.remove(name.concat(Utils.WS_GED_DB_DRIVER));
		properties.remove(name.concat(Utils.WS_GED_DB_CONNECTION_STRING));
		properties.remove(name.concat(Utils.WS_GED_DB_USERNAME));
		properties.remove(name.concat(Utils.WS_GED_DB_PASSWORD));
		
		Utils.setConfigProps(properties, Utils.WS_GED_CONFIGS_FILENAME);
		
		del = Boolean.TRUE;
		add = Boolean.FALSE;
		test = Boolean.FALSE;
		edit = Boolean.FALSE;
		init();
		return ret;
	}
	

	public String testDBConfig(DataSource dbConfig){
		System.out.println("DBConfig : Test db connection");
		try {
			if(connectionFactory.getConnection(
					dbConfig.getDriver(), 
					dbConfig.getConString(), 
					dbConfig.getUsername(), 
					dbConfig.getPassword()) != null){
				message = "Connexion au Systeme de donnees "+ dbConfig.getName() +" effectuee avec succes!!!";
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				            "Succes", "Connexion au Systeme de donnees "+ dbConfig.getName() +" effectuee avec succes!!!");
				FacesContext.getCurrentInstance().addMessage("", msg);
			}
			else {
				message = "Echec de connexion au Systeme de donnees "+ dbConfig.getName();
				FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				            "Erreur", "Echec de connexion au Systeme de donnees "+ dbConfig.getName());
				FacesContext.getCurrentInstance().addMessage("", msg2);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = "Echec de connexion au Systeme de donnees "+ dbConfig.getName()+" : "+e.getMessage();
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
			            "Erreur", "Echec de connexion au Systeme de donnees "+ dbConfig.getName()+" : "+e.getMessage());
			        FacesContext.getCurrentInstance().addMessage("", message);
			e.printStackTrace();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = "Echec de connexion au Systeme de donnees "+ dbConfig.getName()+" : "+e.getMessage();
			// adding warning message
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	            "Erreur", "Echec de connexion au Systeme de donnees "+ dbConfig.getName()+" : "+e.getMessage());
	        FacesContext.getCurrentInstance().addMessage("", message);
			e.printStackTrace();
			return ret;
		}
		
		test = Boolean.TRUE;
		add = Boolean.FALSE;
		del = Boolean.FALSE;
		edit = Boolean.FALSE;
		return ret;
	}
	
	
}	
