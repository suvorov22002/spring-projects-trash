package com.afb.dpdl.ged.ws.rest.api.service.bank;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.service.bank.depcamer.IServiceListDeptCamer;
import com.afb.dpdl.ged.ws.rest.api.service.bank.dernierjourouvre.IServiceDernierJourOuvre;
import com.afb.dpdl.ged.ws.rest.api.service.bank.docapur.IServiceListeDocApur;
import com.afb.dpdl.ged.ws.rest.api.service.bank.fraismission.IServiceFraisMission;
import com.afb.dpdl.ged.ws.rest.api.service.bank.infosemploye.IServiceInfosEmploye;
import com.afb.dpdl.ged.ws.rest.api.service.bank.infounitebudg.IServiceInfoUniteBudgetaire;
import com.afb.dpdl.ged.ws.rest.api.service.bank.insertmission.IServiceInsertMissionPortal;
import com.afb.dpdl.ged.ws.rest.api.service.bank.insertpermission.IServiceInsertPermissionAlt;
import com.afb.dpdl.ged.ws.rest.api.service.bank.listeanomalies.IServiceListeAnomalies;
import com.afb.dpdl.ged.ws.rest.api.service.bank.listedev.IServiceListeDevise;
import com.afb.dpdl.ged.ws.rest.api.service.bank.listeunites.IServiceListeUnites;
import com.afb.dpdl.ged.ws.rest.api.service.bank.listeunitesv2.IServiceListeUnitesV2;
import com.afb.dpdl.ged.ws.rest.api.service.bank.moyentransport.IServiceMoyenTransport;
import com.afb.dpdl.ged.ws.rest.api.service.bank.numordprocess.IServiceNumOrdProcess;
import com.afb.dpdl.ged.ws.rest.api.service.bank.recdom.IServiceDomaineReclamation;
import com.afb.dpdl.ged.ws.rest.api.service.bank.recnat.IServiceNatureReclamation;
import com.afb.dpdl.ged.ws.rest.api.service.bank.refqual.IServiceReferantQualite;
import com.afb.dpdl.ged.ws.rest.api.service.bank.sendsms.IServiceSendSMS;
import com.afb.dpdl.ged.ws.rest.api.service.bank.testdispo.IServiceTestDispoPeriode;
import com.afb.dpdl.ged.ws.rest.api.service.bank.townlist.IServiceListeVille;
import com.afb.dpdl.ged.ws.rest.api.service.bank.typeconge.IServiceTypeConge;
import com.afb.dpdl.ged.ws.rest.api.service.bank.typedoc.IServiceTypeDoc;
import com.afb.dpdl.ged.ws.rest.api.service.bank.typedossier.IServiceTypeDossierFin;
import com.afb.dpdl.ged.ws.rest.api.service.bank.typeforcage.IServiceTypeForcage;


/**
 * 
 * @author alex_jaza
 *
 */
@Path("/bank")
@Produces("application/json;charset=UTF8")
public class ServiceRestBank{
		
	@Inject
	private IServiceListeUnites iServiceListeUnites;
	
	@Inject
	private IServiceListeUnitesV2 iServiceListeUnitesV2;
		
	@Inject
	private IServiceReferantQualite iServiceReferantQualite;
		
	@Inject
	private IServiceSendSMS iServiceSendSMS;
		
	@Inject
	private IServiceTypeForcage iServiceTypeForcage;
	
	@Inject
	private IServiceTypeDossierFin iServiceTypeDossierFin;

	@Inject
	private IServiceTypeDoc iServiceTypeDoc;

	@Inject
	private IServiceNatureReclamation iServiceNatureReclamation;

	@Inject
	private IServiceDomaineReclamation iServiceDomaineReclamation;

	@Inject
	private IServiceInfosEmploye iServiceInfosEmploye;
	
	@Inject
	private IServiceTypeConge iServiceTypeConge;
	
	@Inject
	private IServiceListeAnomalies iServiceListeAnomalies;

	@Inject
	private IServiceInsertMissionPortal iServiceInsertMissionPortal;

	@Inject
	private IServiceFraisMission iServiceFraisMission;

	@Inject
	private IServiceTestDispoPeriode iServiceTestDispoPeriode;

	@Inject
	private IServiceMoyenTransport iServiceMoyenTransport;

	@Inject
	private IServiceInfoUniteBudgetaire iServiceInfoUniteBudgetaire;

	@Inject
	private IServiceNumOrdProcess iServiceNumOrdProcess;
	
	@Inject
	private IServiceListeDevise iServiceListeDevise;

	@Inject
	private IServiceDernierJourOuvre iServiceDernierJourOuvre;

	@Inject
	private IServiceListeVille iServiceListeVille;

	@Inject
	private IServiceInsertPermissionAlt iServiceInsertPermissionAlt;

	@Inject
	private IServiceListeDocApur iServiceListeDocApur;
	
	@Inject
	IServiceListDeptCamer iServiceListDeptCamer;
	Logger logger = Logger.getLogger(ServiceRestBank.class.getName());
	

	
	/**
	 * Recuperation de tous les département du Cameroun
	 * {@link /services-ged/rest/bank/listedeptcamer}
	 * @return La liste de tous les département du Cameroun
	 */
	@GET
    @Path("/listedeptcamer")
	public Response getListDeptCamer() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListDeptCamer.getListDeptCamerJSON().toString()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} 
	}
	
	/**
	 * Recuperation de toutes les unites/Directions dans l'annuaire
	 * {@link /services-ged/rest/bank/listeunites}
	 * @return La liste de toutes les unites/Directions dans l'annuaire [{unite}]
	 */
	@GET
    @Path("/listeunites")
	public Response getListeUnites() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListeUnites.getListeUnitesJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Recuperation de la liste de toutes les unites parametrees dans le Core Banking
	 * {@link /services-ged/rest/bank/listeunites-v2}
	 * @return Liste des unites parametrees dans le Core Banking [{unite}]
	 */
	@GET
    @Path("/listeunites-v2")
	public Response getListeUnitesV2() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListeUnitesV2.getListeUnitesV2JSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
		
	/**
	 * Recuperation du Référant Qualité d'une Agence
	 * {@link /services-ged/rest/bank/refqual}
	 * @param codeAgeUser Code de l'agence de l'utilisateur
	 * @return Le code utilisateur du referant qualite {codeUserRQ}
	 */
	@GET
    @Path("/refqual")
	public Response getReferantQualite( @QueryParam("codeAgeUser") String codeAgeUser) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceReferantQualite.getReferantQualiteJSON(codeAgeUser).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
		
	/**
	 * Envoi de SMS
	 * {@link /services-ged/rest/bank/sendsms}
	 * @param message Message a envoyer
	 * @param phoneNumber Numero de telephone de destination
	 * @param objet Objet qu SMS
	 * @return Le statut de l'envoi du SMS {smsResponse, idSms}
	 */
	@GET
    @Path("/sendsms")
	public Response getSendSMS( @QueryParam("smsMsg") String message, @QueryParam("phoneNumber") String phoneNumber, @QueryParam("smsObjet") String objet) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceSendSMS.sendSMSJSON(message, phoneNumber, objet).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	/**
	 * Envoi de SMS
	 * {@link /services-ged/rest/bank/sendsms}
	 * @param message Message a envoyer
	 * @param phoneNumber Numero de telephone de destination
	 * @param objet Objet qu SMS
	 * @return Le statut de l'envoi du SMS {smsResponse, idSms}
	 */
	@POST
    @Path("/sendsms")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendSMS( @FormParam("smsMsg") String message, @FormParam("phoneNumber") String phoneNumber, @FormParam("smsObjet") String objet) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceSendSMS.sendSMSJSON(message, phoneNumber, objet).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	/**
	 * Liste des Types de Forcage
	 * {@link /services-ged/rest/bank/lttypforcage}
	 * @return Liste des Types de Forcage [{typeForcage}]
	 */
	@GET
    @Path("/lttypforcage")
	public Response getListeTypeForcage() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceTypeForcage.getListeTypeForcageJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Liste des Types de Dossiers de Financement
	 * {@link /services-ged/rest/bank/lttypdos}
	 * @return Liste des Types de Dossiers de Financement [{typeDossier}]
	 */
	@GET
    @Path("/lttypdos")
	public Response getListeTypeDossierFin() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceTypeDossierFin.getListeTypeDossierFinJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

		
	/**
	 * Liste des Types de Documents par dossier de financement
	 * {@link /services-ged/rest/bank/lttypdoc}
	 * @param typedossier ID du type de dossier
	 * @param typeclient Type de client
	 * @return Liste des Types de Dossiers de Documents par dossier de financement [{typeDoc}]
	 */
	@GET
    @Path("/lttypdoc")
	public Response getListeTypeDoc( @QueryParam("typedossier") String typedossier, @QueryParam("typeclient") String typeclient) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceTypeDoc.getListeTypeDocJSON(typedossier, typeclient).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	/**
	 * Liste des Natures de reclamations
	 * {@link /services-ged/rest/bank/recnat}
	 * @param domaine Domaine de la reclamation
	 * @return Liste des Natures de reclamations [{libelle}]
	 */
	@GET
    @Path("/recnat")
	public Response getListeTypeDoc( @QueryParam("domaine") String domaine) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceNatureReclamation.getListeNatureReclamationJSON(domaine).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Liste des domaines de reclamations
	 * {@link /services-ged/rest/bank/recdom}
	 * @return Liste des domaines de reclamations [{libelle}]
	 */
	@GET
    @Path("/recdom")
	public Response getListeDomaineReclamation() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceDomaineReclamation.getListeDomaineReclamationJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	/**
	 * Recuperation des informations d'un employe sur la base de son matricule
	 * {@link /services-ged/rest/bank/infosemploye}
	 * @param matricule Matricule de l'employe
	 * @return Les informations de employe {nom, fonction, grade, cellule, unite, classe,manager1, manager2, manager3, nbjours, joursEff, ncp, permAccord}
	 */
	@GET
    @Path("/infosemploye")
	public Response getInfosEmploye( @QueryParam("matricule") String matricule) {
		try {
			String result = iServiceInfosEmploye.getInfosEmployeJSON(matricule).toString();
		    return Response.status(Response.Status.OK).entity(result).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
    @Path("/infosvehiculeemploye")
	public Response getInfosVehiculeEmploye( @QueryParam("matricule") String matricule) {
		try {
			String result = iServiceInfosEmploye.getMatVehiculeEmployeJSON(matricule).toString();
		    return Response.status(Response.Status.OK).entity(result).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Liste des Types de Conges
	 * {@link /services-ged/rest/bank/typconges}
	 * @return Liste des Types de Conges [{typeConge}]
	 */
	@GET
    @Path("/typconges")
	public Response getListeTypeConges() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceTypeConge.getListeTypeCongeJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Recuperation de toutes les anomalies predefinies par le controle permanent
	 * {@link /services-ged/rest/bank/listeanomalies}
	 * @return La liste de toutes les anomalies predefinies par le controle permanent [{anomalie}]
	 */
	@GET
    @Path("/listeanomalies")
	public Response getListeAnomalies() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListeAnomalies.getListeAnomaliesJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	/**
	 * Insertion des donnees relatives aux missions dans portal
	 * {@link /services-ged/rest/bank/insertmissionportal}
	 * @param nom Nom de la mission
	 * @param ref Reference de la mission
	 * @param fonction Fonction de léemploye
	 * @param grade Grade de léemploye
	 * @param cellule Unite de léemploye
	 * @param ncp Numero de compte de léemploye
	 * @param nbrJours Nombre de jours de la mission
	 * @param moyenTrans Moyen de transport de léemploye
	 * @param unitebudg Unite budgetaire
	 * @param villeDepart Ville de depart de léemploye
	 * @param villeDestinataire Ville de la mission
	 * @param paysAfricain Pays Africain de la mission
	 * @param paysEtranger Pays Etranger de la mission
	 * @param codeOpe Code operation
	 * @param transportReel Frais de transport reel
	 * @param missionReel  Frais de mission reel
	 * @param ncpTransport Numero de compte frais de transport
	 * @param ncpMission Numero de compte frais de mission
	 * @param cutiAnalComp Code utilisateur ***
	 * @param cutiDcompt Code utilisateur ***
	 * @return Statut de l'insertion des donnees de la mission {insertPortalOK}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@POST
    @Path("/insertmissionportal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertMissionPortal( InfoMission infoMission) {
		try {
			logger.info(infoMission.toString());
		    return Response.status(Response.Status.OK).entity(iServiceInsertMissionPortal.insertMissionPortalJSON(infoMission.nom, infoMission.ref, infoMission.fonction, infoMission.grade, infoMission.cellule, infoMission.
		    		ncp, infoMission.nbrJours, infoMission.moyenTrans, infoMission.unitebudg, infoMission.villeDepart, infoMission.villeDestinataire, infoMission.paysAfricain, infoMission.paysEtranger, infoMission.codeOpe, infoMission.transportReel, infoMission.
		    		missionReel, infoMission.ncpTransport, infoMission.ncpMission, infoMission.cutiAnalComp, infoMission.cutiDcompt).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Recuperation des frais de missions
	 * {@link /services-ged/rest/bank/fraismission}
	 * @param lieu Lieu de la mission
	 * @param nbrJours Nombre de jours de la mission
	 * @param grade Grade de léemploye
	 * @param fonction Fonction de léemploye
	 * @param moyenTrans Moyen de transport
	 * @param villeDepart Ville de départ en mission
	 * @param villeDestinataire Ville de la mission
	 * @return Les frais de missions {missionPropose, missionReel, transportPropose, transportReel}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/fraismission")
	public Response getFraisMission( @QueryParam("lieu") String lieu, @QueryParam("nbrJours") String nbrJours, @QueryParam("grade") String grade, @QueryParam("fonction") String fonction, 
			@QueryParam("moyenTrans") String moyenTrans, @QueryParam("villedepart") String villeDepart, @QueryParam("villedestinataire") String villeDestinataire) {
		try {
			logger.info("moyenTrans = "+moyenTrans+", lieu = "+lieu+",villedepart = "+villeDepart+", nbrJours= "+nbrJours+", = villedestinataire"+villeDestinataire+", grade= "+new String(grade.getBytes(), "UTF8")+", fonction= "+fonction);
		    return Response.status(Response.Status.OK).entity(iServiceFraisMission.getFraisMissionJSON(lieu, nbrJours, grade, fonction, moyenTrans, villeDepart, villeDestinataire).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Test pour un employe que la periode sollicitee pour sa permission ou son conge est disponible
	 * {@link /services-ged/rest/bank/testdisponibiliteperiode}
	 * @param matricule Matricule du client
	 * @param dateDeb Date de debut de periode
	 * @param dateFin Date de fin de periode
	 * @return L'etat de disponibilite de l'employe {dispoPeriode}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/testdisponibiliteperiode")
	public Response getTestDispoPeriode( @QueryParam("matricule") String matricule, @QueryParam("dateDeb") String dateDeb, @QueryParam("dateFin") String dateFin) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceTestDispoPeriode.getTestDispoPeriodeJSON(matricule, new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.US).parse(dateDeb), new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.US).parse(dateFin)).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
    @Path("/testdisponibiliteperiodemission")
	public Response getTestDispoPeriodeMission( @QueryParam("matricule") String matricule, @QueryParam("dateDeb") String dateDeb, @QueryParam("dateFin") String dateFin) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceTestDispoPeriode.getTestDispoPeriodeMissionJSON(matricule, new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.US).parse(dateDeb), new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.US).parse(dateFin)).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Recuperation des moyens de transport dans portal
	 * {@link /services-ged/rest/bank/moyentransport}
	 * @return Les moyens de transport dans portal [{libelle}]
	 */
	@GET
    @Path("/moyentransport")
	public Response getMoyenTransport() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceMoyenTransport.getMoyenTransportJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	/**
	 * Recuperation des informations sur l'unite budgetaire
	 * {@link /services-ged/rest/bank/infounitebudgetaire}
	 * @param unitebudg Libelle unite budgetaire
	 * @param lieu Lieu de la mission
	 * @return Les informations sur l'unite budgetaire {codeOpe, ncpMission, ncpTransport}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/infounitebudgetaire")
	public Response getInfoUniteBudgetaire( @QueryParam("unitebudg") String unitebudg, @QueryParam("lieu") String lieu) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceInfoUniteBudgetaire.getInfoUniteBudgetaireJSON(unitebudg, lieu).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Generation du numero d'ordre du processus
	 * {@link /services-ged/rest/bank/numordprocess}
	 * @param codeAgeSaisie Code de l'agence de saisie
	 * @param idProcess ID du processus
	 * @return Le numero d'ordre du processus genere {numord}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/numordprocess")
	public Response getNumOrdProcess( @QueryParam("codeAgeSaisie") String codeAgeSaisie, @QueryParam("idProcess") String idProcess) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceNumOrdProcess.getNumOrdProcessJSON(codeAgeSaisie, idProcess).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Recuperation de la liste des devises existantes dans Amplitude
	 * {@link /services-ged/rest/bank/findlistdev}
	 * @return La liste des devises existantes dans Amplitude [{devise}]
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/findlistdev")
	public Response findListDev() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListeDevise.getListeDeviseJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Test pour un employe que la periode sollicitee pour sa permission ou son conge est disponible
	 * {@link /services-ged/rest/bank/dernierjourouvre}
	 * @param dateDeb Date de debut de la permission
	 * @param nbjrPerm Nombre de jours de la permission
	 * @return La date du dernier jour d'une permission {dateFin, dateFinAlt}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/dernierjourouvre")
	public Response getDernierJourOuvre( @QueryParam("dateDeb") String dateDeb, @QueryParam("nbjrPerm") Double nbjrPerm) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceDernierJourOuvre.getDernierJourOuvreJSON(new SimpleDateFormat("dd-MM-yyyy").parse(dateDeb.toString()), nbjrPerm).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	/**
	 * Recuperation de la liste des villes dans portal
	 * {@link /services-ged/rest/bank/townlist}
	 * @return La liste des villes dans portal [{name}]
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/townlist")
	public Response findListeVille() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListeVille.getListeVilleJSON().toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Insertion des donnees relatives aux missions dans AltGRH
	 * {@link /services-ged/rest/bank/insertpermissionalt}
	 * @param matricule Matricule de l'employe
	 * @param dateDepart Date de depart en permission
	 * @param dateRetour Date de retour de la permission
	 * @param motif Motif de la permission
	 * @param loginUtil Login de l'utilisateur
	 * @param dateModif Date de modification de la permission
	 * @param priseCpte 
	 * @param nbjrs Nombre de jours de la permission
	 * @param codeConge Code du conge
	 * @return Le statut du traitement de l'insertion {insertPortalOK}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@POST
    @Path("/insertpermissionalt")
	@Consumes("application/json")
	public Response insertPermissionAlt( InfoPermission infoPermission) {
		try {
			/*logger.info(new StringBuilder()
					.append("matricule = ").append(infoPermission.getMatricule())
					.append("\t dateRetour = ").append(infoPermission.getDateRetour())
					.append("\t motif = ").append(infoPermission.getMotif())
					.append("\t loginUtil = ").append(infoPermission.getLoginUtil())
					.append("\t dateModif = ").append(infoPermission.getDateModif())
					.append("\t priseCpte = ").append(infoPermission.getPriseCpte())
					.append("\t nbjrs = ").append(infoPermission.getNbjrs())
					.append("\t codeConge = ").append(infoPermission.getCodeConge())
					.toString());*/
		    return Response.status(Response.Status.OK).entity(iServiceInsertPermissionAlt.insertPermissionAltJSON(infoPermission.getMatricule().trim(), infoPermission.getDateDepart(), infoPermission.getDateRetour(), infoPermission.getMotif(), infoPermission.getLoginUtil() != null && !infoPermission.getLoginUtil().isEmpty() ? infoPermission.getLoginUtil() :  infoPermission.getLoginutil(), infoPermission.getDateModif(), infoPermission.getPriseCpte(), infoPermission.getNbjrs(), infoPermission.getCodeConge()).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
		
	
	/**
	 * Liste des documents apures
	 * {@link /services-ged/rest/bank/listdocapur}
	 * @param refDos Reference du dossier
	 * @return Liste des documents apures [{docApur}]
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/listdocapur")
	public Response getListeDocApur( @QueryParam("refDos") String refDos) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListeDocApur.getListeDocApurJSON(refDos).toString()).build();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	@GET
    @Path("/ipws")
	public Response getIpWs() {
		try {
			Properties properties = Utils.getConfigProps(Utils.WS_GED_CONFIGS_IP);
			logger.info("**************> DONNEES WS = "+"{\"ip\":\""+properties.getProperty("ip")+"\",\"port\":\""+properties.getProperty("port")+"\"}");
		    return Response.status(Response.Status.OK).entity("{\"ip\":\""+properties.getProperty("ip")+"\",\"port\":\""+properties.getProperty("port")+"\"}").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} 
	}
	
	
}
