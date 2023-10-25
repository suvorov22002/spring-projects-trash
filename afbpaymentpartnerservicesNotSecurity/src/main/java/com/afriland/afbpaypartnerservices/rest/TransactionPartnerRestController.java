package com.afriland.afbpaypartnerservices.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afriland.afbpaypartnerservices.dtos.ConfirmationTransDto;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.TransactionsResponse;
import com.afriland.afbpaypartnerservices.services.ITransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


/**
 * TransactionPartnerRestController
 * @author yves_labo
 *  
 */
@Slf4j
@RestController(value = "Manager Transactions Partner Services")
@RequestMapping("/rest/api/partner/paypartnerservices/transactionpartner")
@Tag(name = "Manage Transactions Partner Services")
@CrossOrigin
public class TransactionPartnerRestController {
	
	public static final String _Role="TransactionServices";
	public static final String _Url="/paypartnerservices/transactionpartner/**";
	
	@Autowired
	private ITransactionService transactionService;
	
	
	@Operation(description = "Confirmer une transaction par le partenaire", summary = "Confirmer une transaction par le partenaire")
	@PutMapping("/confirmtransactionpartner")
	public ResponseEntity<DataResponseDTO> confirmationTransaction(@RequestBody ConfirmationTransDto data) {
		return transactionService.confirmationTransaction(data);
	}
	
	
	@Operation(description = "Recherche de transactions en fonction du code partner et d'un statut de transaction", summary = "Recherche de transactions en fonction du code partner et d'un statut de transaction")
	@GetMapping("/findbypartnercodestatus/{partnercode}/{statustrans}")
	public ResponseEntity<TransactionsResponse> findByPartnerCodeAndStatusTrans(@PathVariable String partnercode, @PathVariable TransactionStatus statustrans){
		return transactionService.findByPartnerCodeAndStatusTransDay(partnercode, statustrans);
	}
	
	
	@Operation(description = "Recherche paginée de transactions en fonction du code partner et d'un statut de transaction", summary = "Recherche paginée de transactions en fonction du code partner et d'un statut de transaction")
	@GetMapping("/findbypartnercodestatus/{partnercode}/{statustrans}/{page}/{size}")
	public ResponseEntity<TransactionsResponse> findByPartnerCodeAndStatusTrans(@PathVariable String partnercode, @PathVariable TransactionStatus statustrans,
			@PathVariable Integer page, @PathVariable Integer size){
		if(page == null) page = 0;
		if(size == null) size = 10;
		return transactionService.findByPartnerCodeAndStatusTrans(partnercode, statustrans, page, size);
	}

	
	@Operation(description = "Recherche de transactions pour un partner", summary = "Recherche de transactions pour un partner")
	@GetMapping("/findtransactionspartner/{partnercode}")
	public ResponseEntity<TransactionsResponse> findTransactionsPartner(@PathVariable String partnercode){
		return transactionService.findTransactionsPartner(partnercode);
	}
	
	
}
