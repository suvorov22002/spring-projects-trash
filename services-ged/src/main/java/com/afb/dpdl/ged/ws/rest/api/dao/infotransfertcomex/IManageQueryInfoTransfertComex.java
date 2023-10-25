package com.afb.dpdl.ged.ws.rest.api.dao.infotransfertcomex;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryInfoTransfertComex {
	
	/**
	 * Repr�sente la liste de toutes les natures de paiement que peuvent avoir une op�ration de transfert
	 */
	public static final String[] LISTE_NATURE_OPERATIION =  {"Importation",
															"Achat biens immobiliers",
															"Assistance familiale",
															"Frais de scolarit�",
															"Prestation de service",
															"Rapatriement revenus investissement",
															"Rapatriement revenus int�r�t sur emprunt",
															"Rapatriement revenus locatif",
															"Rapatriement revenus salaire",
															"Remboursement de dette",
															"Soins m�dicaux",
															"Voyage affaire",
															"Voyage touristique"};
	
	
	/**
	 * Liste des documents par nature des op�rations. Cette variable fonctionne de pair avec la variable LISTE_NATURE_OPERATIION
	 * La nature en positioin n dans LISTE_NATURE_OPERATIION � pour doc le tableau n dans LISTE_DOC_NATURE_OPERATIION
	 */
	public static final String[][] LISTE_DOC_NATURE_OPERATIION = {{"FACTURE PROFORMA OU DEFINITIVE", "TITRE TRANSPORT", "CARTE CONTRIBUABLE", "ATTESTATION DE NON REDEVANCE", "DI DOMICILI�E", "AGREMENT IMPORTATEUR", "ATTESTATION LOCALISATION", "REGISTRE DE COMMERCE", "ENGAGEMENT DE COUVERTURE"},
			  {"COPIE PUBLICATION DE VENTE","ACTE DE VENTE NOTARI�", "COORDONNEES BANCAIRE NOTAIRE", "AUTORISATION MINFI"},
			  {"DEMANDE MOTIVEE", "PIECE IDENTICATION BENEFICIARE"},
			  {"PHOTOCOPIE PASSPORT", "ATTESTATION INSCRIPTION OU LETTRE ADMISSION", "ATTESTATION DE BOURSE", "NOTE DE FRAIS DE SCOLARITE", "FICHE DEMANDE VISA", "CARTE ETUDIANT OU CERTIFICAT DE SCOLARITE"},
			  {"FACTURE PROFORMA OU DEFINITIVE", "TITRE TRANSPORT", "CARTE CONTRIBUABLE", "ATTESTATION DE NON REDEVANCE", "FACTURE OU CONTRAT DOMICILI�E", "AUTORISATION MINFI", "ATTESTATION LOCALISATION", "REGISTRE DE COMMERCE", "ENGAGEMENT DE COUVERTURE"},
			  {"ETATS FINANCIER DE LA SOCIETE", "PV ASSEMBLE GENERALE", "PREUVE DE VERSEMENT DE LA TAXE", "DECLARATION FISCALE", "LISTE DES ACTIONNAIRES"},
			  {"CONVENTION PR�T", "ECHEANCIER DE REMBOURSEMENT","AUTORISATION MINFI"},
			  {"CONTRAT DE LOCATION", "DECLARATION FISCALE", "LISTE DES ACTIONNAIRES", "TITRE DE PROPRIETE"},
			  {"PHOTOCOPIE PASSPORT", "PHOTOCOPIE CARTE DE SEJOUR", "CONTRAT DE TRAVAIL MINTEPS", "BULLETIN DE PAIE", "DECLARATION FISCALE"},
			  {"CONVENTION PR�T", "ECHEANCIER DE REMBOURSEMENT", "AUTORISATION MINFI"},
			  {"FACTURE H�PITAL", "NOTE EVACUATION SANITAIRE", "COORDONNEES BANCAIRE H�PITAL", "TITRE VOYAGE", "COORDONNEES CLIENT", "TITRE TRANSPORT"},
			  {"CARTE OU ATTESTATION PROFESIONNELLE", "DOCUMENT DE VOYAGE VALIDE", "TITRE TRANSPORT"},
			  {"DOCUMENT DE VOYAGE VALIDE", "TITRE TRANSPORT"}};
	
	
	/**
	 * Recuperation des informations d'un transfert effectue a l'international
	 * @param reftrans Reference du transfert
	 * @return Map des informations du transfert effectue
	 * @throws ClassNotFoundException The exception
	 * @throws SQLException The exception
	 */ 
	Map<String, Object> getInfoTransfertComex(String reftrans)  throws ClassNotFoundException, SQLException;
	
}
