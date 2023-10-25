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

import com.afriland.afbpaypartnerservices.dtos.ForfaitClassesDto;
import com.afriland.afbpaypartnerservices.response.ForfaitClassesResponse;
import com.afriland.afbpaypartnerservices.services.IForfaitClasseService;

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
@RequestMapping("/rest/api/paypartnerservices/forfaitclasse")
@Tag(name = "Forfait Classe Services")
@CrossOrigin
public class ForfaitClasseRestController  {
			
	public static final Logger LOGGER = LoggerFactory.getLogger(ForfaitClasseRestController.class);
	
	public static final String _Role="ForfaitClasseServices";
	public static final String _Url="/paypartnerservices/forfaitclasse/**";

	@Autowired
	private IForfaitClasseService forfaitClasseService;
	


	/**
	 * Ajouter un nouveau Objet de ForfaitClasse dans la BD. Si le Objet de ForfaitClasse existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param categoryClasseDtoRequest
	 * @return
	 */
	@PostMapping("/add") 
	@Operation(description = "Ajouter un Objet de ForfaitClasse", summary = "Ajouter un Objet de classe de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de ForfaitClasse existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de ForfaitClasse a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de ForfaitClasse n'a pa ete cree") })
	public ResponseEntity<ForfaitClassesResponse> saveForfaitClasse(@RequestBody ForfaitClassesDto categoryClasseDtoRequest) {
		return forfaitClasseService.saveForfaitClasse(categoryClasseDtoRequest);
	}
	
	
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter many Objets de ForfaitClasse", summary = "Ajouter plusieurs Objets de classe de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de ForfaitClasse existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de ForfaitClasse ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde ForfaitClasse n'ont pas ete crees") })
	public ResponseEntity<ForfaitClassesResponse> saveListForfaitClasses(@RequestBody List<ForfaitClassesDto> categoryClasseDtoRequests) {
		return forfaitClasseService.saveListForfaitClasses(categoryClasseDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de ForfaitClasse dans la BD. Si le Objet de ForfaitClasse n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param categoryClasseDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing ForfaitClasse", summary = "mettre à jour un Objet de classe de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de ForfaitClasse does not exist"),
			@ApiResponse(code = 200, message = "Ok: the ForfaitClasse is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the ForfaitClasse  is unsuccessfully updated") })
	public ResponseEntity<ForfaitClassesResponse> updateForfaitClasse(@RequestBody ForfaitClassesDto categoryClasseDtoRequest) {
		return forfaitClasseService.updateForfaitClasse(categoryClasseDtoRequest);
	}


	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de ForfaitClasse n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param ForfaitClasseId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a ForfaitClasse, if the ForfaitClasse does not exist, nothing is done", summary = "supprimer un Objet de classe de forfait d'un partenaire")
	@ApiResponse(code = 204, message = "No Content: ForfaitClasse sucessfully deleted")
	public ResponseEntity<ForfaitClassesResponse> deleteForfaitClasses(@PathVariable String id) {
		return forfaitClasseService.deleteForfaitClasse(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description = "Search a ForfaitClasse by it id", summary = "Rechercher un Objet de classe de forfait d'un partenaire à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitClassesResponse> findByIdForfaitClasse(@PathVariable String id) {
		return forfaitClasseService.findByIdForfaitClasse(id);
	}
	
	
	
	/**
	 * @param code
	 * @return
	 */
	@GetMapping("/findbycode/{code}")
	@Operation(description ="Search a ForfaitClasse by it code", summary = "Rechercher un Objet de classe de forfait d'un partenaire à l'aide son code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitClassesResponse> findByPartcode(@PathVariable("code") String code) {
		return forfaitClasseService.findByCode(code);
	}
	
	
	
	/**
	 * @param libelle
	 * @return
	 */
	@GetMapping("/findbylibelle/{libelle}")
	@Operation(description ="Search ForfaitClasses by libelle", summary = "Rechercher un Objet de classe de forfait d'un partenaire à l'aide du libelle")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitClassesResponse> findByLibelle(@PathVariable("libelle") String libelle) {
		return forfaitClasseService.findByLibelleLike(libelle);
	}
	
	
	
	/**
	 * @param partnercode
	 * @return
	 */
	@GetMapping("/findbypartnercode/{partnercode}")
	@Operation(description ="Search ForfaitClasses by libelle", summary = "Search many classes de forfait by libelle")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitClassesResponse> findByPartnerCode(@PathVariable("partnercode") String partnercode) {
		return forfaitClasseService.findByPartnerCode(partnercode);
	}
	
	
	
	/**
	 * Retourne la liste de tous les categoryClasses
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all categoryClasses", summary = "Get all categories Classe")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitClassesResponse> getAllCheques() {
		return forfaitClasseService.getAllForfaitClasses();
	}
	
	
	/**
	 * @param typeforfait
	 * @return
	 */
	@GetMapping("/findbytypeforfait/{typeforfait}")
	@Operation(description ="Search ForfaitClasses by type forfait", summary = "Search many classes de forfait by type de forfait")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitClassesResponse> findByTypeForfait(@PathVariable("typeforfait") String typeforfait) {
		return forfaitClasseService.findByTypeForfait(typeforfait);
	}
	
	
	/**
	 * @param partnercode
	 * @param typeforfait
	 * @return
	 */
	@GetMapping("/findbypartnercodetypeforfait/{partnercode}/{typeforfait}")
	@Operation(description ="Search ForfaitClasses by libelle", summary = "Search many classes de forfait by partner code and type de forfait")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitClassesResponse> findByPartnerCodeAndTypeForfait(@PathVariable("partnercode") String partnercode, @PathVariable("typeforfait") String typeforfait) {
		return forfaitClasseService.findByPartnerCodeAndTypeForfait(partnercode, typeforfait);
	}	
	

}