package com.afb.dpdl.ged.ws.rest.api.service.client;

import java.sql.SQLException;
import java.text.ParseException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.afb.dpdl.ged.ws.rest.api.service.client.affauto.IServiceAffAuto;
import com.afb.dpdl.ged.ws.rest.api.service.client.calculquotite.IServiceCalculQuotite;
import com.afb.dpdl.ged.ws.rest.api.service.client.cptclt.IServiceCompteClient;
import com.afb.dpdl.ged.ws.rest.api.service.client.enga.IServiceEngagements;
import com.afb.dpdl.ged.ws.rest.api.service.client.engaclt.IServiceEngagementClient;
import com.afb.dpdl.ged.ws.rest.api.service.client.infoclt.IServiceInfoClient;
import com.afb.dpdl.ged.ws.rest.api.service.client.infocltrecla.IServiceInfoClientRecla;
import com.afb.dpdl.ged.ws.rest.api.service.client.infopret.IServiceInfoPret;
import com.afb.dpdl.ged.ws.rest.api.service.client.infotransfertcomex.IServiceInfoTransfertComex;
import com.afb.dpdl.ged.ws.rest.api.service.client.infouser.IServiceInfoUser;
import com.afb.dpdl.ged.ws.rest.api.service.client.listecomptes.IServiceListeComptes;
import com.afb.dpdl.ged.ws.rest.api.service.client.numpret.IServiceNumeroPret;

/**
 * 
 * @author alex_jaza
 *
 */
@Path("/client")
@Produces("application/json;charset=UTF8")
public class ServiceRestClient{
	
	@Inject
	private IServiceInfoClient iServiceInfoClient;
	
	@Inject
	private IServiceCompteClient iServiceCompteClient;
	
	@Inject
	private IServiceNumeroPret iServiceNumeroPret;

	@Inject
	private IServiceAffAuto iServiceAffAuto ;
	
	@Inject
	private IServiceInfoUser iServiceInfoUser;

	@Inject
	private IServiceCalculQuotite iServiceCalculQuotite;
	
	@Inject
	private IServiceEngagementClient iServiceEngagementClient;
	
	@Inject
	private IServiceInfoClientRecla iServiceInfoClientRecla;

	@Inject
	private IServiceEngagements iServiceEngagements;

	@Inject
	private IServiceInfoPret iServiceInfoPret;

	@Inject
	private IServiceInfoTransfertComex iServiceInfoTransfertComex;

	@Inject
	private IServiceListeComptes iServiceListeComptes;
	
	
	/**
	 * Recuperation des informations d'un client sur la base de son matricule
	 * {@link /services-ged/rest/client/infoclt}
	 * @param matricule Matricule du client
	 * @param login Login de l'utilisateur
	 * @param ageUser Agence de l'utilisateur
	 * @return Les informations d'un client {nomClt, codeGes, nomGes, secteurClt, codeAgeClt, libAgeClt, typClt, codeUserGFC, adrClt, emailClt, telClt, pid, employeur, dirigeant, fju, dCrea, lna, sitMat, phoneGFC, civilGFC}
	 */
	@GET
    @Path("/infoclt")
	public Response getInfoCli( @QueryParam("matricule") String matricule,
								@QueryParam("login") String login,
								@QueryParam("ageUser") String ageUser) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceInfoClient.getInfoCliJSON(matricule, login, ageUser).toString()).build();
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
	 * Liste des Comptes du Client a partir de son matricule
	 * {@link /services-ged/rest/client/cptclt}
	 * @param matricule Matricule du client
	 * @return La liste des Comptes du Client [{ label, value }]
	 */
	@GET
    @Path("/cptclt")
	public Response getComptesClient( @QueryParam("matricule") String matricule) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceCompteClient.getComptesClientJSON(matricule).toString()).build();
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
	 * Recuperation des Numeros de dossiers de pret en cours dans le corebanking a partir du matricule du client
	 * {@link /services-ged/rest/client/findnumpret}
	 * @param matricule Matricule du client
	 * @return La liste des Numeros de dossiers de pret en cours dans le corebanking a partir du matricule du client [{ label, value }]
	 */
	@GET
    @Path("/findnumpret")
	public Response getNumPretClient( @QueryParam("matricule") String matricule) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceNumeroPret.getNumpretClientJSON(matricule).toString()).build();
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
	 * Recuperation des Utilisateurs a qui affecter le dossier de credit pour la phase en cours de traitement
	 * {@link /services-ged/rest/client/affauto}
	 * @param matricule Matricule du client
	 * @param typeClient Type de client
	 * @param codeAgeSaisie Code de l'agence de saisie du dossier de credit
	 * @param montant Montant du credit
	 * @param secteurActivite Secteur d'activite du client
	 * @param currentPhase Phase actuelle du traitement du dossier de credit dans la GED
	 * @return La liste des Utilisateurs a qui affecter le dossier de credit pour la phase en cours de traitement {usernames}
	 */
	@GET
    @Path("/affauto")
	public Response getUsersAffAuto( @QueryParam("matricule") String matricule, @QueryParam("typeClient") String typeClient, @QueryParam("codeAgeSaisie") String codeAgeSaisie, @QueryParam("montant") Double montant,
			@QueryParam("secteurActivite") String secteurActivite, @QueryParam("currentPhase") String currentPhase) {
		
		try {
			
			return Response.status(Response.Status.OK).entity(iServiceAffAuto.getUsersAffAutoJSON(matricule, typeClient, codeAgeSaisie, montant, secteurActivite, currentPhase).toString()).build();
			
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
	 * Affichage des informations sur l'utilisateur connecte a partir de son login
	 * {@link /services-ged/rest/client/infouser}
	 * @param login Login de l'utilisateur
	 * @return Les informations sur l'utilisateur connecte {login, userName, userPhone, userEmail, codeAgeUsr, libAgeUsr}
	 */
	@GET
    @Path("/infouser")
	public Response getInfoUser( @QueryParam("login") String login) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceInfoUser.getInfoUserJSON(login).toString()).build();
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
	 * Calcul de la quotite pour credit particuliers
	 * {@link /services-ged/rest/client/calculquotite}
	 * @param mensualite Mensualite du credit
	 * @param totMimp Total mensualites des impayees
	 * @param salm1 Salaire moyen
	 * @param salm2 Salaire moyen
	 * @param salm3 Salaire moyen
	 * @param autRev
	 * @param retenues Retenues
	 * @return La quotite calculee pour credit particuliers
	 */
	@GET
    @Path("/calculquotite")
	public Response getCalculQuotite( @QueryParam("mensualite") String mensualite, @QueryParam("totMimp") String totMimp, @QueryParam("salm1") String salm1, @QueryParam("salm2") String salm2, @QueryParam("salm3") String salm3,
			@QueryParam("autRev") String autRev, @QueryParam("retenues") String retenues, @QueryParam("montant") String montant) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceCalculQuotite.calculQuotiteJSON(mensualite, totMimp, salm1, salm2, salm3, autRev, retenues, montant).toString()).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}                     
	}
	
	
	/**
	 * Liste des engagements du client
	 * {@link /services-ged/rest/client/engaclt}
	 * @param matricule Matricule du client
	 * @param ncp Numero de compte du client
	 * @return La liste des engagements du client {encours, totMimp, agios, impayes, lastDmep, salm1, salm2, salm3, nbSal, mvtc, solde, dou}
	 */
	@GET
    @Path("/engaclt")
	public Response getEngagementCli( @QueryParam("matricule") String matricule, @QueryParam("ncp") String ncp) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceEngagementClient.getEngagementCliJSON(matricule, ncp).toString()).build();
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
	 * Recuperation des infos d'un client sur la base de son matricule: uniquement pour les r�clamations
	 * {@link /services-ged/rest/client/infoclt-recla}
	 * @param matricule Matricule du client
	 * @param login Login de l'utilisateur
	 * @param ageUser Agence de l'utilisateur
	 * @return Les informations du client {nomClt, codeGes, nomGes, secteurClt, codeAgeClt, libAgeClt, typClt, codeUserGFC, adrClt, emailClt, telClt, pid, employeur, dirigeant, fju, dCrea, lna, sitMat, phoneGFC, civilGFC,anciennete, dou}
	 */
	@GET
    @Path("/infoclt-recla")
	public Response getInfoCliRecla( @QueryParam("matricule") String matricule,
								@QueryParam("login") String login,
								@QueryParam("ageUser") String ageUser) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceInfoClientRecla.getInfoCliReclaJSON(matricule, login, ageUser).toString()).build();
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
	 * Liste des engagements du client
	 * {@link /services-ged/rest/client/engagements}
	 * @param matricule Matricule du client
	 * @return La liste des engagements du client {informations, totAuto, totEnc, totImp, totAgios}
	 */
	@GET
    @Path("/engagements")
	public Response getEngagements( @QueryParam("matricule") String matricule) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceEngagements.getEngagementsJSON(matricule).toString()).build();
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
	 * Recuperation des informations sur le pret simule
	 * {@link /services-ged/rest/client/infopret}
	 * @param numPret Numero du pret
	 * @param ncp Numero de compte du client
	 * @return Les informations sur le pret simule {montant, taux, duree, mensualite, frais, ngar, misenplace}
	 */
	@GET
    @Path("/infopret")
	public Response getInfoPret( @QueryParam("numpret") String numPret, @QueryParam("ncp") String ncp) {
		try {
		    return Response.status(Response.Status.OK).entity( iServiceInfoPret.getInfoPretJSON(numPret, ncp).toString()).build();
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
	 * Recuperation des informations d'un transfert effectue a l'international
	 * {@link /services-ged/rest/client/infotransfertcomex}
	 * @param refTrans Reference du transfert
	 * @return Les informations du transfert effectue {beneficiaire, pays, montantXaf, montantXaf2, dateOperation, refTrans, eta}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@GET
    @Path("/infotransfertcomex")
	public Response getInfoTransfertComex( @QueryParam("refTrans") String reftrans) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceInfoTransfertComex.getInfoTransfertComexJSON(reftrans).toString()).build();
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
	 * Recup�ration de la liste des natures des op�rations pour les op�rations de tranfert du COMEX
	 * {@link /services-ged/rest/client/listdocopetrfcomex}
	 * @param nataureOpe la nature de l'op�ration
	 * @return tableau d'objet JSon (JSONArray). Chaque objet contient les infos label et value
	 */
	@GET
    @Path("/listdocopetrfcomex")
	public Response getListeDocTrftComexByNatureOpe (@QueryParam("natureOpe") String natureOpe) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceInfoTransfertComex.getListeDocTrftComexByNatureOpeJSON (natureOpe).toString()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	

	/**
	 * Recupration de la liste des documents exiges pour une nature d'oprations pour les oprations de tranfert du COMEX
	 * {@link /services-ged/rest/client/natopetrfcomex}
	 * @return tableau d'objet JSon (JSONArray). Chaque objet contient les infos label et value
	 */
	@GET
    @Path("/natopetrfcomex")
	public Response getNatureOpeTrftComex() {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceInfoTransfertComex.getNatureOpeTrftComexJSON().toString()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
		
	/**
	 * Recuperation de la liste des Comptes a partir du matricule de l'employe dans GRH
	 * {@link /services-ged/rest/client/listecomptes}
	 * @param matricule Matricule du client
	 * @return La liste des comptes de l'employe [{numeroCompte}]
	 */
	@GET
    @Path("/listecomptes")
	public Response getListeComptes( @QueryParam("matricule") String matricule) {
		try {
		    return Response.status(Response.Status.OK).entity(iServiceListeComptes.getListeComptesJSON(matricule).toString()).build();
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
	
	
	
}
