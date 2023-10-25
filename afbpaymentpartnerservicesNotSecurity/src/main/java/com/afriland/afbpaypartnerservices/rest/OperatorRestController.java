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

import com.afriland.afbpaypartnerservices.dtos.OperatorsDto;
import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.OperatorsResponse;
import com.afriland.afbpaypartnerservices.services.IOperatorService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/rest/api/paypartnerservices/operator")
@Tag(name = "Operator Services")
@CrossOrigin
public class OperatorRestController {

	public static final Logger LOGGER = LoggerFactory.getLogger(OperatorRestController.class);
	
	public static final String _Role="OperatorServices";
	public static final String _Url="/paypartnerservices/operator/**";

	@Autowired
	private IOperatorService operatorService;
	


	/**
	 * Ajouter un nouveau Objet de Operator dans la BD. Si le Objet de Operator existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param operatorDtoRequest
	 * @return
	 */
	@PostMapping("/add")
	@Operation(description = "Ajouter un Objet de Operator", summary = "Souscription dun client pour des opérations pour un partenaire spécificque")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de Operator existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de Operator a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de Operator n'a pa ete cree") })
	public ResponseEntity<OperatorsResponse> saveOperator(@RequestBody OperatorsDto operatorDtoRequest) {
		return operatorService.saveOperator(operatorDtoRequest);
	}
	
	
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter plusieurs operators", summary = "Ajouter plusieurs Souscription")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de Operator existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de Operator ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde Operator n'ont pas ete crees") })
	public ResponseEntity<OperatorsResponse> saveListOperators(@RequestBody List<OperatorsDto> operatorDtoRequests) {
		return operatorService.saveListOperators(operatorDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de Operator dans la BD. Si le Objet de Operator n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param operatorDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing Operator", summary = "Modifier une souscription existante")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de Operator does not exist"),
			@ApiResponse(code = 200, message = "Ok: the Operator is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the Operator  is unsuccessfully updated") })
	public ResponseEntity<OperatorsResponse> updateOperator(@RequestBody OperatorsDto operatorDtoRequest) {
		return operatorService.updateOperator(operatorDtoRequest, "UPDATE");
	}
	
	
	/**
	 * Met à jour les données d'un Objet de Operator dans la BD. Si le Objet de Operator n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param operatorDtoRequest
	 * @return
	 */
	@PutMapping("/validate")
	@Operation(description = "validate an existing Operator", summary = "valider une souscription existante")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de Operator does not exist"),
			@ApiResponse(code = 200, message = "Ok: the Operator is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the Operator  is unsuccessfully updated") })
	public ResponseEntity<OperatorsResponse> validateOperator(@RequestBody OperatorsDto operatorDtoRequest) {
		return operatorService.updateOperator(operatorDtoRequest, "VALIDATE");
	}
	
	
	/**
	 * Met à jour les données d'un Objet de Operator dans la BD. Si le Objet de Operator n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param operatorDtoRequest
	 * @return
	 */
	@PutMapping("/cancel")
	@Operation(description = "Cancel an existing Operator", summary = "Annulation d'une souscription")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de Operator does not exist"),
			@ApiResponse(code = 200, message = "Ok: the Operator is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the Operator  is unsuccessfully updated") })
	public ResponseEntity<OperatorsResponse> cancelOperator(@RequestBody OperatorsDto operatorDtoRequest) {
		return operatorService.updateOperator(operatorDtoRequest, "CANCEL");
	}
	
	
	
	/**
	 * Met à jour les données d'un Objet de Operator dans la BD. Si le Objet de Operator n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param operatorDtoRequest
	 * @return
	 */
	@PutMapping("/resetpin")
	@Operation(description = "Cancel an existing Operator", summary = "Annulation d'une souscription")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de Operator does not exist"),
			@ApiResponse(code = 200, message = "Ok: the Operator is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the Operator  is unsuccessfully updated") })
	public ResponseEntity<OperatorsResponse> resetPinOperator(@RequestBody OperatorsDto operatorDtoRequest) {
		return operatorService.updateOperator(operatorDtoRequest, "RESETPIN");
	}


	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de Operator n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param OperatorId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a Operator, if the Operator does not exist, nothing is done", summary = "Delete a Operator, if the Operator does not exist, nothing is done")
	@ApiResponse(code = 204, message = "No Content: Operator sucessfully deleted")
	public ResponseEntity<OperatorsResponse> deleteOperators(@PathVariable String id) {
		return operatorService.deleteOperator(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description="Search a Operator by it id", summary="Rechercher une souscription à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<OperatorsResponse> findByIdOperator(@PathVariable String id) {
		return operatorService.findByIdOperator(id);
	}
	
	
	
	/**
	 * @param code
	 * @return
	 */
	@GetMapping("/findbycode/{code}")
	@Operation(description ="Search a Operator by it code", summary ="Rechercher une souscription à l'aide du code")
	public ResponseEntity<OperatorsResponse> findByCode(@PathVariable("code") String code) {
		return operatorService.findByCode(code);
	}
	
	
	
	/**
	 * @param code
	 * @param valid
	 * @return
	 */
	@GetMapping("/findbycodeactif/{code}/{valid}")
	@Operation(description = "Search a Operator by it code and boolean operator", summary = "Rechercher une souscription active à l'aide du code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<OperatorsResponse> findByCodeAndValid(@PathVariable("code") String code, @PathVariable("valid") Boolean valid) {
		return operatorService.findByCodeAndValid(code, valid);
	}
	
		
	
	/**
	 * Retourne la liste de tous les operators
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all operators", summary = "Obtenir toutes les souscriptions")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<OperatorsResponse> getAllCheques() {
		return operatorService.getAllOperators();
	}
	
	
	
	/**
	 * @param codeclient
	 * @return
	 */
	@GetMapping("/findbycodeclient/{codeclient}")
	@Operation(description ="Search a Operator by it codeclient", summary = "Rechercher une souscription à l'aide du codeclient")
	public ResponseEntity<OperatorsResponse> findByCodeClient(@PathVariable("codeclient") String codeclient) {
		return operatorService.findByCodeClient(codeclient);
	}
	
	
	
	/**
	 * @param clientname
	 * @return
	 */
	@GetMapping("/findbyclientname/{clientname}")
	@Operation(description = "Search a Operator by it clientname", summary = "Rechercher une souscription à l'aide du nom client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<OperatorsResponse> findByClientNameLike(@PathVariable("clientname") String clientname) {
		return operatorService.findByClientNameLike(clientname);
	}
		
	
	
	/**
	 * @param niu
	 * @return
	 */
	@GetMapping("/findbyniu/{niu}")
	@Operation(description ="Search a Operator by it niu", summary ="Rechercher une souscription à l'aide du NIU")
	public ResponseEntity<OperatorsResponse> findBCustomerNiuPartner(@PathVariable("niu") String niu) {
		return operatorService.findByCustomerNiuPartner(niu);
	}
	
	
	
	/**
	 * @param reference
	 * @param partnercode
	 * @return
	 */
	@GetMapping("/findbyreferencepartnercode/{reference}/{partnercode}")
	@Operation(description ="Search a Operator by it reference and partnercode", summary ="Rechercher une souscription à l'aide de la reference du client auprés du partenaire, et du code du partenaire")
	public ResponseEntity<OperatorsResponse> findByCustomerReferencePartnerAndPartnerCode(@PathVariable("reference") String reference, @PathVariable("partnercode") String partnercode) {
		return operatorService.findByCustomerReferencePartnerAndPartnerCode(reference, partnercode);
	}
	
	
	
	/**
	 * @param partnercode
	 * @return
	 */
	@GetMapping("/findbypartnercode/{partnercode}")
	@Operation(description ="Search  Operators by it partnercode", summary ="Rechercher une souscription à l'aide du code du partenaire")
	public ResponseEntity<OperatorsResponse> findByPartnerCode(@PathVariable("partnercode") String partnercode){
		return operatorService.findByPartnerCode(partnercode);
	}
	
	
	
	/**
	 * @param partnercode
	 * @param codeclient
	 * @return
	 */
	@GetMapping("/findbypartnercodecodeclient/{partnercode}/{codeclient}")
	@Operation(description ="Search  Operators by it partnercode and codeclient", summary ="Rechercher une souscription à l'aide du code du partenaire et du matricule client")
	public ResponseEntity<OperatorsResponse> findByPartnerCodeAndCodeClient(@PathVariable("partnercode") String partnercode, @PathVariable("codeclient") String codeclient){
		return operatorService.findByPartnerCodeAndCodeClient(partnercode, codeclient);
	}
	
	
	
	/**
	 * @param statut
	 * @param agence
	 * @return
	 */
	@GetMapping("/findbystatutagence/{statut}/{agence}")
	@Operation(description ="Search a Operator by it reference and partnercode", summary ="Rechercher les souscriptions à l'aide du satut de souscription, et de l'agence de sosucription")
	public ResponseEntity<OperatorsResponse> findByOperatorStatusAndAgenceSous(@PathVariable("statut") PayPartnerStatutSubscriber statut, @PathVariable("agence") String agence) {
		return operatorService.findByOperatorStatusAndAgenceSous(statut, agence);
	}
	
	
	
	/**
	 * @param keyoperator
	 * @param partnercode
	 * @return
	 */
	@GetMapping("/findbykeyoperatorpartnercode/{keyoperator}/{partnercode}")
	@Operation(description ="Search a Operator by it reference and partnercode", summary ="Rechercher d'une souscription à l'aide de la clé de souscription, et du code du partenaire")
	public ResponseEntity<OperatorsResponse> findByKeyOperatorAndPartnerCode(@PathVariable("keyoperator") String keyoperator, @PathVariable("partnercode") String partnercode) {
		return operatorService.findByKeyOperatorAndPartnerCode(keyoperator, partnercode);
	}
	
	
	
	/**
	 * @param keyoperator
	 * @return
	 */
	@GetMapping("/findbykeyoperator/{keyoperator}")
	@Operation(description ="Search a Operator by it reference and partnercode", summary ="Rechercher d'une souscription à l'aide de la clé de souscription")
	public ResponseEntity<OperatorsResponse> findByKeyOperator(@PathVariable("keyoperator") String keyoperator) {
		return operatorService.findByKeyOperator(keyoperator);
	}
	
	
	
	@PostMapping("/findoperator")
	@Operation(description = "Rechercher des operators en fonction de plusieurs parametres", summary = "Rechercher des operators en fonction de plusieurs parametres")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<OperatorsResponse> findOperator(@RequestBody OperatorsDto operatorDtoRequest) {
		return operatorService.findOperator(operatorDtoRequest);
	}
	
	
	@PostMapping("/editbordereausouscription")
	@Operation(description = "Editer un bordereau de souscription", summary = "Editer un bordereau de souscription")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<DataResponseDTO> editBordereauSouscription(@RequestBody OperatorsDto operatorDtoRequest) {
		return operatorService.editBordereauSouscription(operatorDtoRequest);
	}
	

}
