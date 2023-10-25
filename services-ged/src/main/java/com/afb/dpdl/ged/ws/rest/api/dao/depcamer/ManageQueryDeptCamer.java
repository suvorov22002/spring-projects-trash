package com.afb.dpdl.ged.ws.rest.api.dao.depcamer;

import java.util.Arrays;
import java.util.List;


public class ManageQueryDeptCamer implements IManageQueryDeptCamer{

	@Override
	public List<String> getListDeptCamer()  {
		// TODO Auto-generated method stub
		// Initialisation de la liste a retourner
		String[] tabDept = {
				"Bamboutos",
				"Bénoué",
				"Boumba-et-Ngoko",
				"Boyo",
				"Bui",
				"Départements",
				"Diamaré",
				"Dja-et-Lobo",
				"Djérem",
				"Donga-Mantung",
				"Fako",
				"Faro",
				"Faro-et-Déo",
				"Haute-Sanaga",
				"Haut-Nkam",
				"Haut-Nyong",
				"Hauts-Plateaux",
				"Kadey",
				"Koung-Khi",
				"Koupé-Manengouba",
				"Lebialem",
				"Lekié",
				"Logone-et-Chari",
				"Lom-et-Djérem",
				"Manyu",
				"Mayo-Banyo",
				"Mayo-Danay",
				"Mayo-Kani",
				"Mayo-Louti",
				"Mayo-Rey",
				"Mayo-Sava",
				"Mayo-Tsanaga",
				"Mbam-et-Inoubou",
				"Mbam-et-Kim",
				"Mbéré",
				"Méfou-et-Afamba",
				"Méfou-et-Akono",
				"Meme",
				"Menchum",
				"Menoua",
				"Mezam",
				"Mfoundi",
				"Mifi",
				"Momo",
				"Moungo",
				"Mvila",
				"Ndé",
				"Ndian",
				"Ngo-Ketunjia",
				"Nkam",
				"Noun",
				"Nyong-et-Kellé",
				"Nyong-et-Mfoumou",
				"Nyong-et-Soéo",
				"Océan",
				"Sanaga-Maritime",
				"Vallée-du-Ntem",
				"Vina",
				"Wouri"
		};
		return Arrays.asList(tabDept);
		
	}

	
}
