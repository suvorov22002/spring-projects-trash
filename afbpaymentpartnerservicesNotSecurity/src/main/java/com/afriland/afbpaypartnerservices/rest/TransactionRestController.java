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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afriland.afbpaypartnerservices.dtos.CancelTransDto;
import com.afriland.afbpaypartnerservices.dtos.ConfirmationTransDto;
import com.afriland.afbpaypartnerservices.dtos.PaymentDetails;
import com.afriland.afbpaypartnerservices.dtos.PaymentResponse;
import com.afriland.afbpaypartnerservices.dtos.ResponseCaisse;
import com.afriland.afbpaypartnerservices.dtos.TransactionsDto;
import com.afriland.afbpaypartnerservices.dtos.VersementPrepayDTO;
import com.afriland.afbpaypartnerservices.dtos.trusayway.TrustPaymentDetails;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.response.CustomerInfosResponse;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.InfosCustomerVersementResponse;
import com.afriland.afbpaypartnerservices.response.TransactionsResponse;
import com.afriland.afbpaypartnerservices.services.ITransactionService;
import com.afriland.afbpaypartnerservices.services.ITransactionTrustPayWayService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


/**
 * TransactionsController
 * @author yves_labo
 *  
 */
@Slf4j
@RestController(value = "Manager Transactions Services")
@RequestMapping("/rest/api/paypartnerservices/transaction")
@Tag(name = "Manage Transactions Services")
@CrossOrigin
public class TransactionRestController {
	
	public static final String _Role="TransactionServices";
	public static final String _Url="/paypartnerservices/transaction/**";
	
	@Autowired
	private ITransactionService transactionService;
	
	@Autowired
	private ITransactionTrustPayWayService transactionTrustPayWayService;
	
	
	@Operation(description = "initialisation d'une operation de versement à la caisse", summary = "initialisation d'une operation de versement à la caisse")
	@PostMapping("/initcashin")
	public ResponseEntity<TransactionsResponse> initCashInTransactions(@RequestBody VersementPrepayDTO data) {
		return transactionService.initCashInTransactions(data);
	}
	
	@Operation(description = "Operation de virement pour le paiement de factures ou forfait", summary = "Operation de virement pour le paiement de factures ou forfait")
	@PostMapping("/pay")
	public ResponseEntity<PaymentResponse> virementTransactions(@RequestBody PaymentDetails data) {
		return transactionService.virementTransactions(data);
	}
	
	
	/**
	 * Payment service
	 * @param paymentDetails The payment details
	 * @return Completed payment details
	 */
	@PostMapping("/payments")
	public ResponseEntity<PaymentResponse> payments(@RequestBody TrustPaymentDetails trustPaymentDetails){
		return 	transactionTrustPayWayService.payments(trustPaymentDetails)	;
	}
	
	
	@PostMapping("/addmulti") 
	public ResponseEntity<TransactionsResponse> saveListForfaitPayments(@RequestBody List<Transactions> datas) {
		return transactionService.saveListTransactions(datas);
	}
	
	
	@Operation(description = "Valider une opération", summary = "Valider une opération de versement initiée à la caisse")
	@PutMapping("/validertransactions")
	public ResponseEntity<TransactionsResponse> saveTransactions(@RequestBody Transactions data) {
		return transactionService.saveTransactions(data);
	}
	
	
	@Operation(description = "Confirmer une transaction par le partenaire", summary = "Confirmer une transaction par le partenaire")
	@PutMapping("/confirmtransactionpartner")
	public ResponseEntity<DataResponseDTO> confirmationTransaction(@RequestBody ConfirmationTransDto data) {
		return transactionService.confirmationTransaction(data);
	}
	
	
	@Operation(description = "Annuler une transaction", summary = "CancelTransDto une transaction")
	@PutMapping("/canceltransactionpartner")
	public ResponseEntity<DataResponseDTO> cancelTransactions(@RequestBody CancelTransDto data) {
		return transactionService.cancelTransactions(data);
	}
		
	
	@Operation(description = "journal de l'utilisateur", summary = "journal des transactions de l'utilisateur")
	@GetMapping("/findalltransactionstoday/{user}/{date}")
	public ResponseEntity<TransactionsResponse> findAllTransactionsToday(@PathVariable(value = "date") String date,@PathVariable(value = "user") String user){
		return transactionService.findAllTransactionsToday(date, user);
	}
	

	@Operation(description = "liste les transactions de la journée de l'utilisateur", summary = "liste les transactions de la journée d'un agent de guichet")
	@GetMapping("/findPendingTransactions/{user}")
	public ResponseEntity<TransactionsResponse> findPendingTransactions(@PathVariable(value = "user") String user){
		return transactionService.findPendingTransactions(user);
	}
		

	@Operation(description = "liste les transactions suivants les critéres", summary = "lister les transactions suivants les dates de début et de fin")
	@PostMapping("/findTransactions/{debut}/{fin}")
	public ResponseEntity<TransactionsResponse> findTransactions(@RequestBody Transactions data,@PathVariable(value = "debut") String debut, @PathVariable(value = "fin") String fin){
		return transactionService.findTransactions(data, debut, fin);
	}
	
	
	@Operation(description = "liste les transactions suivants les critéres", summary = "lister les transactions suivants la reference du partner, les dates de début et de fin")
	@GetMapping("/findTransactionsbydate/{referencepartner}/{debut}/{fin}")
	public ResponseEntity<TransactionsResponse> findTransactionsByDate(@PathVariable(value = "referencepartner") String referencepartner, @PathVariable(value = "debut") String debut, @PathVariable(value = "fin") String fin){
		return transactionService.findTransactionsByDate(referencepartner, debut, fin);
	}
		

	@Operation(description = "lister toutes les transactions", summary = "lister toutes les transactions")
	@GetMapping("/getalltransactions")
	public ResponseEntity<TransactionsResponse> getAllTransactions(){
		return transactionService.getAllTransactions();
	}
		

	@Operation(description = "get Transaction by id", summary = "Obtenir une transaction à l'aide de l'Id")
	@GetMapping("/gettransactionsbyid/{id}")
	public ResponseEntity<TransactionsResponse> getTransactionsById(@PathVariable(value = "id") String id) {
		return transactionService.getTransactionsById(id);
	}

		
	@Operation(description = "cancel Transactions", summary = "Annulation de Transactions")
	@PostMapping("/deletetransaction")
	public ResponseEntity<TransactionsResponse> deleteTransactions(@RequestBody Transactions data){
		return transactionService.deleteTransactions(data);
	}
	
	
	@Operation(description = "cancel Transactions with Id", summary = "Annulation de Transactions à partir de l'Id")
	@DeleteMapping("/deletetransactionwithid/{id}")
	public ResponseEntity<TransactionsResponse> deleteTransactionsWithId(@PathVariable String id,@RequestBody Transactions data){
		return transactionService.deleteTransactionsWithId(id, data);
	}

	
	@Operation(description = "find Transactions by many parameters", summary = "Recherche de transactions en fonction du code partner")
	@GetMapping("/findbypartnercode/{partnercode}")
	public ResponseEntity<TransactionsResponse> findByPartnerCode(@PathVariable String partnercode){
		return transactionService.findByPartnerCode(partnercode);
	}
	
	
	@Operation(description = "Recherche de transactions en fonction du code partner et d'un statut de transaction", summary = "Recherche de transactions en fonction du code partner et d'un statut de transaction")
	@GetMapping("/findbypartnercodestatus/{partnercode}/{statustrans}/{page}/{size}")
	public ResponseEntity<TransactionsResponse> findByPartnerCodeAndStatusTrans(@PathVariable String partnercode, @PathVariable TransactionStatus statustrans,
			@PathVariable Integer page, @PathVariable Integer size){
		if(page == null) page = 0;
		if(size == null) size = 10;
		return transactionService.findByPartnerCodeAndStatusTrans(partnercode, statustrans, page, size);
	}

	
	@Operation(description = "find Transactions by many parameters", summary = "Recherche de transactions en fonction de plusieurs critères")
	@PostMapping("/findtransactions")
	public ResponseEntity<TransactionsResponse> findTransactions(@RequestBody TransactionsDto transactionDtoRequest){
		return transactionService.findTransactions(transactionDtoRequest);
	}
	
	
	@Operation(description = "find infos customer", summary = "Recherche des informations détaillées d'un client")
	@GetMapping("/findcustomerinfo/{codeclient}")
	public ResponseEntity<CustomerInfosResponse> findcustomerinfo(@PathVariable String codeclient){
		return transactionService.findcustomerinfo(codeclient);
	}
	
	
	@Operation(description = "Obtenir la signture du compte d'un client", summary = "Obtenir la signture du compte d'un client")
	@GetMapping("/liensig")
	public ResponseEntity<DataResponseDTO> liensig(@RequestParam String age, @RequestParam String ncp, @RequestParam String cli){
		return transactionService.liensig(age, ncp, cli);
	}
			
	
	@Operation(description = "check situation teller", summary = "Verifier l'ouverture de la caisse")
	@GetMapping("/findcaisses/{usercaisse}")
	public ResponseEntity<ResponseCaisse> findcaisses(@PathVariable String usercaisse){
		return transactionService.findcaisses(usercaisse);
	}
	
	
	@Operation(description = "Recherche des informations d'un client lors de sa dernière opération", summary = "Recherche des informations d'un client lors de sa dernière opération")
	@GetMapping("/findlastinfosoperator/{referenceoperator}/{partnercode}")
	public ResponseEntity<InfosCustomerVersementResponse> findLastOperator(@PathVariable String referenceoperator, @PathVariable String partnercode){
		return transactionService.findLastOperator(referenceoperator, partnercode);
	}
	
	
	@Operation(description = "Nivellement du compte du partenaire", summary = "Nivellement du compte du partenaire")
	@GetMapping("/nivellementaccountpartner/{partnercode}/{user}")
	public ResponseEntity<DataResponseDTO> nivellementAccountPartner(@PathVariable String partnercode, @PathVariable String user){
		return transactionService.nivellementAccountPartner(partnercode, user);
	}
	
	
	@Operation(description = "Recherche de transactions pour un partner", summary = "Recherche de transactions pour un partner")
	@GetMapping("/findtransactionspartner/{partnercode}")
	public ResponseEntity<TransactionsResponse> findTransactionsPartner(@PathVariable String partnercode){
		return transactionService.findTransactionsPartner(partnercode);
	}
	
	
	@Operation(description = "Editer le bordereau de versement", summary = "Editer le bordereau de versement")
	@PostMapping("/editbordereauversement")
	public ResponseEntity<DataResponseDTO> editBordereauVersement(@RequestBody TransactionsDto data){
		return transactionService.editBordereauVersement(data);
	}
	
	
}
