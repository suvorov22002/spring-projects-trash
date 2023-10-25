package com.afriland.afbpaypartnerservices.rest;

import java.util.List;

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

import com.afriland.afbpaypartnerservices.dtos.BkeveTemplateListReponse;
import com.afriland.afbpaypartnerservices.dtos.BkeveTemplateReponse;
import com.afriland.afbpaypartnerservices.jpa.BkeveTemplate;
import com.afriland.afbpaypartnerservices.repositories.BkeveTemplateRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author yves_labo
 *
 */
@RestController
@RequestMapping("/rest/api/paypartnerservices/evetemplate")
@Tag(name = "Transactions event Services")
@CrossOrigin
public class BkeveTemplateController {
	
	public static final String _Role="BkeveTemplateServices";
	public static final String _Url="/paypartnerservices/evetemplate/**";
	
	@Autowired
	private BkeveTemplateRepository repository;
	
	@Operation(summary = "Obtenir l'ensemble des evenements de transactions initiés")
	@GetMapping("/getall")
	public ResponseEntity<BkeveTemplateListReponse> getBkeveTemplate(){  
		return convertresults(repository.findAll());
	}
	
	@Operation(summary = "Rechercher un evenement de transaction initié à l'aide de l'ID")
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<BkeveTemplateReponse> getSpecificWebServs(@PathVariable(value = "id") String id) {
		return convertresult(repository.findById(id).get());
	}
	
	@Operation(summary = "Enregistrer un evenement de transaction initié ")
	@PostMapping("/save")
	public ResponseEntity<BkeveTemplateReponse> saveBkeveTemplate(@RequestBody BkeveTemplate data){
		return convertresult(repository.save(data));
	}
	
	@PostMapping("/addmulti")
	@Operation(description = "Ajouter plusieurs evenements de transaction initiée", summary = "Ajouter plusieurs evenements de transaction initiée")
	public ResponseEntity<BkeveTemplateListReponse> saveListPartners(@RequestBody List<BkeveTemplate> datas) {
		return convertresults(repository.saveAll(datas));
	}

	
	@Operation(summary = "Mettre à jour un evenement de transaction initié à partir de l'ID")
	@PutMapping("/update/{id}")
	public ResponseEntity<BkeveTemplateReponse> updateSpecificWebServs(@PathVariable String id,@RequestBody BkeveTemplate data){
		repository.deleteById(id);
		return convertresult(repository.save(data));
	}
	
	@Operation(summary = "Supprimer un evenement de transaction initié à partir de l'ID")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BkeveTemplateReponse> deleteSpecificWebServs(@PathVariable String id){
		repository.deleteById(id);
		return ResponseEntity.ok().body(new BkeveTemplateReponse().ok());
	}
	
	public ResponseEntity<BkeveTemplateReponse> convertresult(BkeveTemplate data) {
		if(data == null) return ResponseEntity.ok().body(new BkeveTemplateReponse().ko());
		return ResponseEntity.ok().body(new BkeveTemplateReponse(data).ok());
	}
	
	
	public ResponseEntity<BkeveTemplateListReponse> convertresults(List<BkeveTemplate> data) {
		if(data == null) return ResponseEntity.ok().body(new BkeveTemplateListReponse().ko());
		return ResponseEntity.ok().body(new BkeveTemplateListReponse(data).ok());
	}

}