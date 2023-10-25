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

import com.afriland.afbpaypartnerservices.dtos.UserExternsDto;
import com.afriland.afbpaypartnerservices.response.UserExternsResponse;
import com.afriland.afbpaypartnerservices.services.IUserExternService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/rest/api/paypartnerservices/userextern")
@Tag(name = "Users Externs Services")
@CrossOrigin
public class UserExternRestController {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserExternRestController.class);
	
	public static final String _Role="UserExternServices";
	public static final String _Url="/paypartnerservices/userextern/**";

	@Autowired
	private IUserExternService userExternService;
	


	/**
	 * Ajouter un nouveau Objet de UserExtern dans la BD. Si le Objet de UserExtern existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param userExternDtoRequest
	 * @return
	 */
	@PostMapping("/add")
	@Operation(description = "Ajouter un Objet de UserExtern", summary = "Souscription dun client pour des opérations pour un partenaire spécificque")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de UserExtern existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de UserExtern a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de UserExtern n'a pa ete cree") })
	public ResponseEntity<UserExternsResponse> saveUserExtern(@RequestBody UserExternsDto userExternDtoRequest) {
		return userExternService.saveUserExtern(userExternDtoRequest);
	}
	
	
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter plusieurs userExterns", summary = "Ajouter plusieurs Souscription")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de UserExtern existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de UserExtern ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde UserExtern n'ont pas ete crees") })
	public ResponseEntity<UserExternsResponse> saveListUserExterns(@RequestBody List<UserExternsDto> userExternDtoRequests) {
		return userExternService.saveListUserExterns(userExternDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de UserExtern dans la BD. Si le Objet de UserExtern n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param userExternDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing UserExtern", summary = "Modifier une souscription existante")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de UserExtern does not exist"),
			@ApiResponse(code = 200, message = "Ok: the UserExtern is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the UserExtern  is unsuccessfully updated") })
	public ResponseEntity<UserExternsResponse> updateUserExtern(@RequestBody UserExternsDto userExternDtoRequest) {
		return userExternService.updateUserExtern(userExternDtoRequest);
	}
	
	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de UserExtern n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param UserExternId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a UserExtern, if the UserExtern does not exist, nothing is done", summary = "Delete a UserExtern, if the UserExtern does not exist, nothing is done")
	@ApiResponse(code = 204, message = "No Content: UserExtern sucessfully deleted")
	public ResponseEntity<UserExternsResponse> deleteUserExterns(@PathVariable Long id) {
		return userExternService.deleteUserExtern(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description="Search a UserExtern by it id", summary="Rechercher une souscription à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<UserExternsResponse> findByIdUserExtern(@PathVariable Long id) {
		return userExternService.findByIdUserExtern(id);
	}
	
	
	
	/**
	 * @param login
	 * @return
	 */
	@GetMapping("/findbylogin/{login}")
	@Operation(description ="Search a UserExtern by it code", summary ="Rechercher une souscription à l'aide du code")
	public ResponseEntity<UserExternsResponse> findByCode(@PathVariable("login") String login) {
		return userExternService.findByLogin(login);
	}
	
	
	
	/**
	 * @param code
	 * @param valid
	 * @return
	 */
	@GetMapping("/findbyloginactif/{login}")
	@Operation(description = "Search a UserExtern by it code and boolean userExtern", summary = "Rechercher une souscription active à l'aide du code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<UserExternsResponse> findByLoginAndActif(@PathVariable("login") String login) {
		return userExternService.findByLoginAndActif(login);
	}
	
		
	
	/**
	 * Retourne la liste de tous les userExterns
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all userExterns", summary = "Obtenir toutes les souscriptions")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<UserExternsResponse> getAllCheques() {
		return userExternService.getAllUserExterns();
	}
	
	
	/**
	 * @param nom
	 * @return
	 */
	@GetMapping("/findbynom/{nom}")
	@Operation(description = "Search a UserExtern by it nom", summary = "Rechercher une souscription à l'aide du nom u=de l'utilisateur")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<UserExternsResponse> findByClientNameLike(@PathVariable("nom") String nom) {
		return userExternService.findByNomLike(nom);
	}
	
	
	/**
	 * @param partnercode
	 * @return
	 */
	@GetMapping("/findbypartnercode/{partnercode}")
	@Operation(description ="Search  UserExterns by it partnercode", summary ="Rechercher une souscription à l'aide du code du partenaire")
	public ResponseEntity<UserExternsResponse> findByPartnerCode(@PathVariable("partnercode") String partnercode){
		return userExternService.findByPartnerCode(partnercode);
	}
	

}
