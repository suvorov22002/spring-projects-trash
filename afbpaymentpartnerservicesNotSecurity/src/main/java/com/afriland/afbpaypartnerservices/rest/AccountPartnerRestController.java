package com.afriland.afbpaypartnerservices.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afriland.afbpaypartnerservices.dtos.AccountPartnersDto;
import com.afriland.afbpaypartnerservices.response.AccountPartnersResponse;
import com.afriland.afbpaypartnerservices.services.IAccountPartnerService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author yves_labo
 *
 */
@RestController
@RequestMapping("/rest/api/paypartnerservices/accountpartner")
@Tag(name = "Account partner Services")
@CrossOrigin
public class AccountPartnerRestController  {
			
	public static final Logger LOGGER = LoggerFactory.getLogger(AccountPartnerRestController.class);
	
	public static final String _Role="AccountPartnerServices";
	public static final String _Url="/paypartnerservices/accountpartner/**";

	@Autowired
	private IAccountPartnerService accountPartnerService;
	


	/**
	 * Ajouter un nouveau Objet de AccountPartner dans la BD. Si le Objet de AccountPartner existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param accountPartnerDtoRequest
	 * @return
	 */
	@PostMapping("/add") 
	@Operation(description = "Ajouter un Objet de AccountPartner", summary = "Ajouter un Objet de compte d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de AccountPartner existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de AccountPartner a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de AccountPartner n'a pa ete cree") })
	public ResponseEntity<AccountPartnersResponse> saveAccountPartner(@RequestBody AccountPartnersDto accountPartnerDtoRequest) {
		return accountPartnerService.saveAccountPartner(accountPartnerDtoRequest);
	}
	
	
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter many Objets de AccountPartner", summary = "Ajouter plusieurs Objets de compte d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de AccountPartner existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de AccountPartner ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde AccountPartner n'ont pas ete crees") })
	public ResponseEntity<AccountPartnersResponse> saveListAccountPartners(@RequestBody List<AccountPartnersDto> accountPartnerDtoRequests) {
		return accountPartnerService.saveListAccountPartners(accountPartnerDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de AccountPartner dans la BD. Si le Objet de AccountPartner n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param accountPartnerDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing AccountPartner", summary = "mettre à jour un Objet de compte d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de AccountPartner does not exist"),
			@ApiResponse(code = 200, message = "Ok: the AccountPartner is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the AccountPartner  is unsuccessfully updated") })
	public ResponseEntity<AccountPartnersResponse> updateAccountPartner(@RequestBody AccountPartnersDto accountPartnerDtoRequest) {
		System.out.println("************** update updateAccountPartner ***************");		
		return accountPartnerService.updateAccountPartner(accountPartnerDtoRequest);
	}


	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de AccountPartner n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param AccountPartnerId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a AccountPartner, if the AccountPartner does not exist, nothing is done", summary = "supprimer un Objet de compte d'un partenaire")
	@ApiResponse(code = 204, message = "No Content: AccountPartner sucessfully deleted")
	public ResponseEntity<AccountPartnersResponse> deleteAccountPartners(@PathVariable String id) {
		return accountPartnerService.deleteAccountPartner(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description = "Search a AccountPartner by it id", summary = "Rechercher un Objet de compte d'un partenaire à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByIdAccountPartner(@PathVariable String id) {
		return accountPartnerService.findByIdAccountPartner(id);
	}
	
	
	
	/**
	 * @param code
	 * @return
	 */
	@GetMapping("/findbycode/{code}")
	@Operation(description ="Search a AccountPartner by it code", summary = "Rechercher un Objet de compte d'un partenaire à l'aide son code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByCode(@PathVariable("code") String code) {
		return accountPartnerService.findByCode(code);
	}
	
	
	
	/**
	 * @param code
	 * @param actif
	 * @return
	 */
	@GetMapping("/findbycodeactif/{code}/{actif}")
	@Operation(description ="Search a AccountPartner by it code and value actif", summary = "Rechercher un Objet de compte d'un partenaire à l'aide son code et la valeur actif")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByCodeAndActif(@PathVariable("code") String code, @PathVariable("actif") Boolean actif) {
		return accountPartnerService.findByCodeAndActif(code, actif);
	}
	
	
	
	/**
	 * @param ncp
	 * @return
	 */
	@GetMapping("/findbyncp/{ncp}")
	@Operation(description ="Search AccountPartners by ncp", summary = "Rechercher un Objet de forfait d'un partenaire à l'aide de son ncp")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByNcp(@PathVariable("ncp") String ncp) {
		return accountPartnerService.findByNcp(ncp);
	}
	
	
	
	/**
	 * @param libelle
	 * @return
	 */
	@GetMapping("/findbylibelle/{libelle}")
	@Operation(description ="Search AccountPartners by libelle", summary = "Rechercher un Objet de forfait d'un partenaire à l'aide du libelle")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByLibelle(@PathVariable("libelle") String libelle) {
		return accountPartnerService.findByLibelleLike(libelle);
	}
	
	
	
	/**
	 * @param account
	 * @return
	 */
	@GetMapping("/findbyaccount/{account}")
	@Operation(description ="Search AccountPartners by account", summary = "Rechercher un Objet de forfait d'un partenaire à l'aide du compte")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByAccountLike(@PathVariable("account") String account) {
		return accountPartnerService.findByAccountLike(account);
	}
	
	
	
	/**
	 * @param partnercode
	 * @param actif
	 * @return
	 */
	@GetMapping("/findbypartnercode/{partnercode}/{actif}")
	@Operation(description ="Search AccountPartners by partner code and value actif", summary = "Search many forfaits by partner code and value actif")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByPartnerCodeAndActif(@PathVariable("partnercode") String partnercode, @PathVariable("actif") Boolean actif) {
		return accountPartnerService.findByPartnerCodeAndActif(partnercode, actif);
	}
	
	
	
	/**
	 * Retourne la liste de tous les comptes des partenaires
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all Account Partners", summary = "Get all Account Partners")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> getAllAccountPartners() {
		return accountPartnerService.getAllAccountPartners();
	}
	
	
	/**
	 * @param accountPartnerDtoRequest
	 * @return
	 */
	@PostMapping("/findbyaccountpartner")
	@Operation(description ="Search AccountPartners by accountPartnerDtoRequest", summary = "Search many forfaits by accountPartnerDtoRequest")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<AccountPartnersResponse> findByAccountPartner(@RequestBody AccountPartnersDto accountPartnerDtoRequest) { 
		return accountPartnerService.findByAccountPartner(accountPartnerDtoRequest);
	}
	

}