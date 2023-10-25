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

import com.afriland.afbpaypartnerservices.dtos.PartnersDto;
import com.afriland.afbpaypartnerservices.response.PartnersResponse;
import com.afriland.afbpaypartnerservices.services.IPartnerService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/rest/api/paypartnerservices/partner")
@Tag(name = "Partner Services")
@CrossOrigin
public class PartnerRestController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PartnerRestController.class);
	
	public static final String _Role="PartnerServices";
	public static final String _Url="/paypartnerservices/partner/**";

	@Autowired
	private IPartnerService partnerService;
	


	/**
	 * Ajouter un nouveau Objet de Partner dans la BD. Si le Objet de Partner existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param partnerDtoRequest
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/add")
	@Operation(description = "Ajouter un Objet de Partner", summary = "Ajouter un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de Partner existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de Partner a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de Partner n'a pa ete cree") })
	public ResponseEntity<PartnersResponse> savePartner(@RequestBody PartnersDto partnerDtoRequest) {
		return partnerService.savePartner(partnerDtoRequest);
	}
	
	
	@CrossOrigin
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter many Objets de Partner", summary = "Ajouter plusieurs partenaires")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de Partner existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de Partner ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde Partner n'ont pas ete crees") })
	public ResponseEntity<PartnersResponse> saveListPartners(@RequestBody List<PartnersDto> partnerDtoRequests) {
		return partnerService.saveListPartners(partnerDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de Partner dans la BD. Si le Objet de Partner n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param partnerDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing Partner", summary = "Modifier un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de Partner does not exist"),
			@ApiResponse(code = 200, message = "Ok: the Partner is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the Partner  is unsuccessfully updated") })
	public ResponseEntity<PartnersResponse> updatePartner(@RequestBody PartnersDto partnerDtoRequest) {
		return partnerService.updatePartner(partnerDtoRequest);
	}


	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de Partner n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param PartnerId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a Partner, if the Partner does not exist, nothing is done", summary = "Supprimer un partenaire à l'aide de l'ID")
	@ApiResponse(code = 204, message = "No Content: Partner sucessfully deleted")
	public ResponseEntity<PartnersResponse> deletePartners(@PathVariable String id) {
		return partnerService.deletePartner(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description = "Search a Partner by it id", summary = "Rechercher un partenaire à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PartnersResponse> findByIdPartner(@PathVariable String id) {
		return partnerService.findByIdPartner(id);
	}
	
	
	
	/**
	 * @param code
	 * @return
	 */
	@GetMapping("/findbypartcode/{partcode}")
	@Operation(description ="Search a Partner by it partcode", summary = "Rechercher un partenaire à l'aide de son code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PartnersResponse> findByPartcode(@PathVariable("partcode") String partcode) {
		return partnerService.findByPartcode(partcode);
	}
	
	
	
	/**
	 * @param code
	 * @param actif
	 * @return
	 */
	@GetMapping("/findbycodeactif/{partcode}/{actif}")
	@Operation(description = "Search a Partner by it code and boolean partner", summary = "Rechercher un partenaire actif à l'aide de son code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PartnersResponse> findByPartcodeAndActif(@PathVariable("partcode") String partcode, @PathVariable("actif") boolean actif) {
		return partnerService.findByPartcodeAndActif(partcode, actif);
	}
	
		
	
	/**
	 * Retourne la liste de tous les partners
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all partners", summary = "Obtenir l'ensemble des partenaires")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PartnersResponse> getAllCheques() {
		return partnerService.getAllPartners();
	}
	
	
	
	/**
	 * @param partname
	 * @return
	 */
	@GetMapping("/findbypartname/{partname}")
	@Operation(description = "Search a Partner by it partname", summary = "Recherche de partenaires à l'aide du libellé")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PartnersResponse> findByPartnameLike(@PathVariable("partname") String partname) {
		return partnerService.findByPartnameLike(partname);
	}
		
	
	@PostMapping("/findpartner")
	@Operation(description = "Rechercher des partners en fonction de plusieurs parametres", summary = "Rechercher des partners en fonction de plusieurs parametres")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PartnersResponse> findPartner(@RequestBody PartnersDto partnerDtoRequest) {
		return partnerService.findPartner(partnerDtoRequest);
	}
	

}
