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

import com.afriland.afbpaypartnerservices.dtos.PropertyConfigsDto;
import com.afriland.afbpaypartnerservices.response.PropertyConfigsResponse;
import com.afriland.afbpaypartnerservices.services.IPropertyConfigService;

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
@RequestMapping("/rest/api/paypartnerservices/propertyconfig")
@Tag(name = "Property Config Services")
@CrossOrigin
public class PropertyConfigRestController  {
			
	public static final Logger LOGGER = LoggerFactory.getLogger(PropertyConfigRestController.class);
	
	public static final String _Role="PropertyConfigServices";
	public static final String _Url="/paypartnerservices/propertyconfig/**";

	@Autowired
	private IPropertyConfigService propertyConfigService;
	


	/**
	 * Ajouter un nouveau Objet de PropertyConfig dans la BD. Si le Objet de PropertyConfig existe déjà, on
	retourne un code indiquant que la création n'a pas abouti.
	 * @param propertyConfigDtoRequest
	 * @return
	 */
	@PostMapping("/add") 
	@Operation(description = "Ajouter un Objet de PropertyConfig", summary = "Ajouter un paramètre de configuration")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le Objet de PropertyConfig existe deja"),
			@ApiResponse(code = 201, message = "cree : le Objet de PropertyConfig a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de PropertyConfig n'a pa ete cree") })
	public ResponseEntity<PropertyConfigsResponse> savePropertyConfig(@RequestBody PropertyConfigsDto propertyConfigDtoRequest) {
		return propertyConfigService.savePropertyConfig(propertyConfigDtoRequest);
	}
	
	
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter many Objets de PropertyConfig", summary = "Ajouter plusieurs paramètres de configuration")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: les Objets de PropertyConfig existent deja"),
			@ApiResponse(code = 201, message = "cree : les Objets de PropertyConfig ont ete enregistres avec succes"),
			@ApiResponse(code = 304, message = "Erreur : les Objet sde PropertyConfig n'ont pas ete crees") })
	public ResponseEntity<PropertyConfigsResponse> saveListPropertyConfigs(@RequestBody List<PropertyConfigsDto> propertyConfigDtoRequests) {
		return propertyConfigService.saveListPropertyConfigs(propertyConfigDtoRequests);
	}



	/**
	 * Met à jour les données d'un Objet de PropertyConfig dans la BD. Si le Objet de PropertyConfig n'est pas
	retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
	 * @param propertyConfigDtoRequest
	 * @return
	 */
	@PutMapping("/update")
	@Operation(description = "Update/Modify an existing PropertyConfig", summary = "Modifier un paramètre de configuration")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the Objet de PropertyConfig does not exist"),
			@ApiResponse(code = 200, message = "Ok: the PropertyConfig is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the PropertyConfig  is unsuccessfully updated") })
	public ResponseEntity<PropertyConfigsResponse> updatePropertyConfig(@RequestBody PropertyConfigsDto propertyConfigDtoRequest) {
		return propertyConfigService.updatePropertyConfig(propertyConfigDtoRequest);
	}


	
	/**
	 * Supprime une cheque dans la BD. Si le Objet de PropertyConfig n'est pas retrouvé, on
		retourne le Statut HTTP NO_CONTENT.
	 * @param PropertyConfigId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(description = "Delete a PropertyConfig, if the PropertyConfig does not exist, nothing is done", summary = "supprimer un paramètre de configuration à l'aide de l'ID")
	@ApiResponse(code = 204, message = "No Content: PropertyConfig sucessfully deleted")
	public ResponseEntity<PropertyConfigsResponse> deletePropertyConfigs(@PathVariable String id) {
		return propertyConfigService.deletePropertyConfig(id);
	}

	
	
	/**
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	@Operation(description = "Search a PropertyConfig by it id", summary = "Rechercher un paramètre de configuration à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PropertyConfigsResponse> findByIdPropertyConfig(@PathVariable String id) {
		return propertyConfigService.findByIdPropertyConfig(id);
	}
	
	
	
	/**
	 * @param code
	 * @return
	 */
	@GetMapping("/findbycode/{code}")
	@Operation(description ="Search a PropertyConfig by it code", summary = "Rechercher un paramètre de configuration à l'aide du code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PropertyConfigsResponse> findByPartcode(@PathVariable("code") String code) {
		return propertyConfigService.findByCode(code);
	}
	
	
	
	/**
	 * @param description
	 * @return
	 */
	@GetMapping("/findbydescription/{description}")
	@Operation(description ="Search PropertyConfigs by libelle", summary = "Recherche de paramètres de configuration à l'aide de la description")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PropertyConfigsResponse> findByDescriptionLike(@PathVariable("description") String description) {
		return propertyConfigService.findByDescriptionLike(description);
	}
	
	
	
	/**
	 * Retourne la liste de tous les propertyConfigs
	 * @param 
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(description = "Get all propertyConfigs", summary = "Obtenir l'ensemble des paramètres de configuration")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result founded"),
	})
	public ResponseEntity<PropertyConfigsResponse> getAllCheques() {
		return propertyConfigService.getAllPropertyConfigs();
	}
	

}