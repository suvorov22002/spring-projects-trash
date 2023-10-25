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

import com.afriland.afbpaypartnerservices.dtos.ForfaitPaymentsDto;
import com.afriland.afbpaypartnerservices.response.ForfaitPaymentsResponse;
import com.afriland.afbpaypartnerservices.services.IForfaitPaymentService;

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
@RequestMapping("/rest/api/paypartnerservices/forfaitpayment")
@Tag(name = "Forfait payment Services")
@CrossOrigin
public class ForfaitPaymentRestController  {
			
	public static final Logger LOGGER = LoggerFactory.getLogger(ForfaitPaymentRestController.class);
	
	public static final String _Role="ForfaitPaymentServices";
	public static final String _Url="/paypartnerservices/forfaitpayment/**";

	@Autowired
	private IForfaitPaymentService forfaitPaymentService;
	


	/**
	 * Ajouter un nouveau Objet de ForfaitPayment dans la BD. Si le Objet de ForfaitPayment existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param categoryPaymentDtoRequest
	 * @return
	 */
	@PostMapping("/add") 
	@Operation(description = "Ajouter un Objet de ForfaitPayment", summary = "Ajouter un Objet de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de ForfaitPayment existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de ForfaitPayment a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de ForfaitPayment n'a pa ete cree") })
	public ResponseEntity<ForfaitPaymentsResponse> saveForfaitPayment(@RequestBody ForfaitPaymentsDto categoryPaymentDtoRequest) {
		return forfaitPaymentService.saveForfaitPayment(categoryPaymentDtoRequest);
	}
	
	
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter many Objets de ForfaitPayment", summary = "Ajouter plusieurs Objets de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de ForfaitPayment existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de ForfaitPayment ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde ForfaitPayment n'ont pas ete crees") })
	public ResponseEntity<ForfaitPaymentsResponse> saveListForfaitPayments(@RequestBody List<ForfaitPaymentsDto> categoryPaymentDtoRequests) {
		return forfaitPaymentService.saveListForfaitPayments(categoryPaymentDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de ForfaitPayment dans la BD. Si le Objet de ForfaitPayment n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param categoryPaymentDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing ForfaitPayment", summary = "mettre à jour un Objet de forfait d'un partenaire")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de ForfaitPayment does not exist"),
			@ApiResponse(code = 200, message = "Ok: the ForfaitPayment is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the ForfaitPayment  is unsuccessfully updated") })
	public ResponseEntity<ForfaitPaymentsResponse> updateForfaitPayment(@RequestBody ForfaitPaymentsDto categoryPaymentDtoRequest) {
		return forfaitPaymentService.updateForfaitPayment(categoryPaymentDtoRequest);
	}


	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de ForfaitPayment n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param ForfaitPaymentId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a ForfaitPayment, if the ForfaitPayment does not exist, nothing is done", summary = "supprimer un Objet de forfait d'un partenaire")
	@ApiResponse(code = 204, message = "No Content: ForfaitPayment sucessfully deleted")
	public ResponseEntity<ForfaitPaymentsResponse> deleteForfaitPayments(@PathVariable String id) {
		return forfaitPaymentService.deleteForfaitPayment(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description = "Search a ForfaitPayment by it id", summary = "Rechercher un Objet de forfait d'un partenaire à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitPaymentsResponse> findByIdForfaitPayment(@PathVariable String id) {
		return forfaitPaymentService.findByIdForfaitPayment(id);
	}
	
	
	
	/**
	 * @param code
	 * @return
	 */
	@GetMapping("/findbycode/{code}")
	@Operation(description ="Search a ForfaitPayment by it code", summary = "Rechercher un Objet de forfait d'un partenaire à l'aide son code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitPaymentsResponse> findByPartcode(@PathVariable("code") String code) {
		return forfaitPaymentService.findByCode(code);
	}
	
	
	
	/**
	 * @param libelle
	 * @return
	 */
	@GetMapping("/findbylibelle/{libelle}")
	@Operation(description ="Search ForfaitPayments by libelle", summary = "Rechercher un Objet de forfait d'un partenaire à l'aide du libelle")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitPaymentsResponse> findByLibelle(@PathVariable("libelle") String libelle) {
		return forfaitPaymentService.findByLibelleLike(libelle);
	}
	
	
	
	/**
	 * @param partnercode
	 * @return
	 */
	@GetMapping("/findbypartnercode/{partnercode}")
	@Operation(description ="Search ForfaitPayments by libelle", summary = "Search many forfaits by libelle")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitPaymentsResponse> findByPartnerCode(@PathVariable("partnercode") String partnercode) {
		return forfaitPaymentService.findByPartnerCode(partnercode);
	}
	
	
	
	/**
	 * Retourne la liste de tous les categoryPayments
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all categoryPayments", summary = "Get all categories Payment")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitPaymentsResponse> getAllCheques() {
		return forfaitPaymentService.getAllForfaitPayments();
	}
	
	
	/**
	 * @param typeforfait
	 * @return
	 */
	@GetMapping("/findbytypeforfait/{typeforfait}")
	@Operation(description ="Search ForfaitPayments by type forfait", summary = "Search many forfaits by type de forfait")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitPaymentsResponse> findByTypeForfait(@PathVariable("typeforfait") String typeforfait) {
		return forfaitPaymentService.findByTypeForfait(typeforfait);
	}
	
	
	/**
	 * @param partnercode
	 * @param typeforfait
	 * @return
	 */
	@GetMapping("/findbypartnercodetypeforfait/{partnercode}/{typeforfait}")
	@Operation(description ="Search ForfaitPayments by libelle", summary = "Search many forfaits by partner code and type de forfait")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<ForfaitPaymentsResponse> findByPartnerCodeAndTypeForfait(@PathVariable("partnercode") String partnercode, @PathVariable("typeforfait") String typeforfait) {
		return forfaitPaymentService.findByPartnerCodeAndTypeForfait(partnercode, typeforfait);
	}	
	

}