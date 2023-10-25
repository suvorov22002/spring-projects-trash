package com.afriland.afbpaypartnerservices.rest;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afriland.afbpaypartnerservices.dtos.FilenameDto;
import com.afriland.afbpaypartnerservices.response.VentilationResponse;
import com.afriland.afbpaypartnerservices.services.IVentilationService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/rest/api/paypartnerservices/ventilation")
@Tag(name = "Ventilation Services")
public class VentilationController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(VentilationController.class);
	
	@Autowired
	private IVentilationService ventilationtService;
	
	/**
	 * checknewfileventilation
	 * @return
	 */
	@GetMapping("/checknewfileventilation")
	@Operation(description="Recherche de fichier à integrer", summary="Recherche de fichier à integrer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded")
	})
	public ResponseEntity<VentilationResponse> checkNewFileVentilation(){
		return ventilationtService.checkNewFileVentilation();
	}
	
	
	/**
	 * processventilationlistfile
	 * @return
	 */
	@PostMapping("/processventilationlistfile/{partcode}/{login}")
	@Operation(description = "Proceder à la ventilation", summary = "Recherce du fichier de repartition et proceder à la ventilation")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: "),
			@ApiResponse(code = 201, message = "cree : le Objet de Operator a ete enregistre avec succes"),
			@ApiResponse(code = 304, message = "Erreur : le Objet de Operator n'a pa ete cree") })
	public ResponseEntity<VentilationResponse> decryptListFileTransaction(@RequestBody FilenameDto filenameDto, @PathVariable("login") String login, 
				@PathVariable("partcode") String partcode){
		return ventilationtService.processVentilationListFile(filenameDto.getListFile(), partcode, login);
	}
	
	
	/**
	 * processfileventilation
	 * @return
	 */
	@GetMapping("/processfileventilation/{partcode}/{login}")
	@Operation(description="Process Generate ecritures", summary="Generation ecritures")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded")
	})
	public ResponseEntity<VentilationResponse> processFileTransaction(@PathVariable("login") String login, @PathVariable("partcode") String partcode){
		return ventilationtService.processVentilation(null, partcode, login);
	}
	
	/**
	 * ventilation
	 * @return
	 */
	@GetMapping("/ventilation/{id}")
	@Operation(description="Process file by its name", summary="Rechercher un fichier à l'aide de l'ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded")
	})
	public ResponseEntity<VentilationResponse> ventilation(@PathVariable("id") String id){
		return ventilationtService.ventilation(id);
	}
	
	
	/**
	 * ventilationattente
	 * @return
	 */
	@GetMapping("/ventilationattente")
	@Operation(description="Process waiting file", summary="Rechercher fichier en attente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded")
	})
	public ResponseEntity<VentilationResponse> findVentilationAttente(){
		return ventilationtService.findVentilationAttente();
	}
	
	
	/**
	 * findFpDetailsVentilationByFileId
	 * @return
	 */
	@GetMapping("/getdetailsventilation/{ventilationId}")
	@Operation(description="Process details", summary="Rechercher details d'un fichier")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded")
	})
	public ResponseEntity<VentilationResponse> findFpDetailsVentilationByFileId(@PathVariable("ventilationid") String ventilationid){
		return ventilationtService.findFpDetailsVentilationByFileId(ventilationid);
	}
	
	
	/**
	 * geterreursventilation
	 * @return
	 */
	@GetMapping("/geterreursventilation/{ventilationId}")
	@Operation(description="Process details", summary="Rechercher details d'un fichier")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: successfull research"),
			@ApiResponse(code = 204, message = "No Content: no result founded")
	})
	public ResponseEntity<VentilationResponse> findFpErreursVentilationByFileId(@PathVariable("ventilationid") String ventilationid){
		return ventilationtService.findFpErreursVentilationByFileId(ventilationid);
	}
}
