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

import com.afriland.afbpaypartnerservices.dtos.TypeForfaitsDto;
import com.afriland.afbpaypartnerservices.enums.Segment;
import com.afriland.afbpaypartnerservices.response.TypeForfaitsResponse;
import com.afriland.afbpaypartnerservices.services.ITypeForfaitService;

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
@RequestMapping("/rest/api/paypartnerservices/typeforfait")
@Tag(name = "Types de forfait Services")
@CrossOrigin
public class TypeForfaitRestController  {
			
	public static final Logger LOGGER = LoggerFactory.getLogger(TypeForfaitRestController.class);
	
	public static final String _Role="TypeForfaitServices";
	public static final String _Url="/paypartnerservices/typeforfait/**";

	@Autowired
	private ITypeForfaitService typeForfaitService;
	


	/**
	 * Ajouter un nouveau Objet de TypeForfait dans la BD. Si le Objet de TypeForfait existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param typeForfaitDtoRequest
	 * @return
	 */
	@PostMapping("/add") 
	@Operation(description = "Ajouter un Objet de TypeForfait", summary = "Ajouter un Objet de type de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de TypeForfait existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de TypeForfait a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de TypeForfait n'a pa ete cree") })
	public ResponseEntity<TypeForfaitsResponse> saveTypeForfait(@RequestBody TypeForfaitsDto typeForfaitDtoRequest) {
		return typeForfaitService.saveTypeForfait(typeForfaitDtoRequest);
	}
	
	
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter many Objets de TypeForfait", summary = "Ajouter plusieurs Objets de type de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de TypeForfait existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de TypeForfait ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde TypeForfait n'ont pas ete crees") })
	public ResponseEntity<TypeForfaitsResponse> saveListTypeForfaits(@RequestBody List<TypeForfaitsDto> typeForfaitDtoRequests) {
		return typeForfaitService.saveListTypeForfaits(typeForfaitDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de TypeForfait dans la BD. Si le Objet de TypeForfait n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param typeForfaitDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing TypeForfait", summary = "mettre à jour un Objet de type de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de TypeForfait does not exist"),
			@ApiResponse(code = 200, message = "Ok: the TypeForfait is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the TypeForfait  is unsuccessfully updated") })
	public ResponseEntity<TypeForfaitsResponse> updateTypeForfait(@RequestBody TypeForfaitsDto typeForfaitDtoRequest) {
		return typeForfaitService.updateTypeForfait(typeForfaitDtoRequest);
	}


	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de TypeForfait n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param TypeForfaitId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a TypeForfait, if the TypeForfait does not exist, nothing is done", summary = "supprimer un Objet de type de forfait d'un partenaire")
	@ApiResponse(code = 204, message = "No Content: TypeForfait sucessfully deleted")
	public ResponseEntity<TypeForfaitsResponse> deleteTypeForfaits(@PathVariable String id) {
		return typeForfaitService.deleteTypeForfait(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description = "Search a TypeForfait by it id", summary = "Rechercher un Objet de type de forfait d'un partenaire à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<TypeForfaitsResponse> findByIdTypeForfait(@PathVariable String id) {
		return typeForfaitService.findByIdTypeForfait(id);
	}
	
	
	
	/**
	 * @param code
	 * @return
	 */
	@GetMapping("/findbycode/{code}")
	@Operation(description ="Search a TypeForfait by it code", summary = "Rechercher un Objet de type de forfait d'un partenaire à l'aide son code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<TypeForfaitsResponse> findByPartcode(@PathVariable("code") String code) {
		return typeForfaitService.findByCode(code);
	}
	
	
	
	/**
	 * @param libelle
	 * @return
	 */
	@GetMapping("/findbylibelle/{libelle}")
	@Operation(description ="Search TypeForfaits by libelle", summary = "Rechercher un Objet de type de forfait d'un partenaire à l'aide du libelle")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<TypeForfaitsResponse> findByLibelle(@PathVariable("libelle") String libelle) {
		return typeForfaitService.findByLibelleLike(libelle);
	}
	
	
	
	/**
	 * @param partnercode
	 * @return
	 */
	@GetMapping("/findbypartnercode/{partnercode}")
	@Operation(description ="Search TypeForfaits by libelle", summary = "Search many type de forfait by libelle")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<TypeForfaitsResponse> findByPartnerCode(@PathVariable("partnercode") String partnercode) {
		return typeForfaitService.findByPartnerCode(partnercode);
	}
	
	
	
	/**
	 * Retourne la liste de tous les typeForfaits
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all typeForfaits", summary = "Get all types de forfait")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<TypeForfaitsResponse> getAllCheques() {
		return typeForfaitService.getAllTypeForfaits();
	}
	
	
	/**
	 * @param segment
	 * @return
	 */
	@GetMapping("/findbypartnercodecategoriegroupe/{segment}")
	@Operation(description ="Search TypeForfaits by segment", summary = "Search many types de forfait by segment")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<TypeForfaitsResponse> findBySegment(@PathVariable("segment") Segment segment) {
		return typeForfaitService.findBySegment(segment);
	}
	
	
	/**
	 * @param partnercode
	 * @param segment
	 * @return
	 */
	@GetMapping("/findbypartnercodecategoriegroupe/{partnercode}/{segment}")
	@Operation(description ="Search TypeForfaits by libelle", summary = "Search many types de forfait by partner code and segment")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<TypeForfaitsResponse> findByPartnerCodeAndSegment(@PathVariable("partnercode") String partnercode, @PathVariable("segment") Segment segment) {
		return typeForfaitService.findByPartnerCodeAndSegment(partnercode, segment);
	}	
	

}