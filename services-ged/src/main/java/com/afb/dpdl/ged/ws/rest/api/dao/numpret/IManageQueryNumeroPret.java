package com.afb.dpdl.ged.ws.rest.api.dao.numpret;

import java.sql.SQLException;
import java.util.List;

public interface IManageQueryNumeroPret {
	
	
	List<String> getNumeroPret(String matricule)  throws ClassNotFoundException, SQLException;
	
}
