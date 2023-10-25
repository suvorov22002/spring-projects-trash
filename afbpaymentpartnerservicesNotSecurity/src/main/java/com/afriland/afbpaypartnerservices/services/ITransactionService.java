package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.CancelTransDto;
import com.afriland.afbpaypartnerservices.dtos.ConfirmationTransDto;
import com.afriland.afbpaypartnerservices.dtos.PaymentDetails;
import com.afriland.afbpaypartnerservices.dtos.PaymentResponse;
import com.afriland.afbpaypartnerservices.dtos.ResponseCaisse;
import com.afriland.afbpaypartnerservices.dtos.TransactionsDto;
import com.afriland.afbpaypartnerservices.dtos.VersementPrepayDTO;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.response.CustomerInfosResponse;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.InfosCustomerVersementResponse;
import com.afriland.afbpaypartnerservices.response.TransactionsResponse;


public interface ITransactionService {

	public ResponseEntity<TransactionsResponse> initCashInTransactions(VersementPrepayDTO data);
	
	public ResponseEntity<TransactionsResponse> saveListTransactions(List<Transactions> datas);
	
	public ResponseEntity<TransactionsResponse> saveTransactions(Transactions data);
	
	public ResponseEntity<DataResponseDTO> confirmationTransaction(ConfirmationTransDto data);
	
	public ResponseEntity<DataResponseDTO> cancelTransactions(CancelTransDto data);
	
	public ResponseEntity<PaymentResponse> virementTransactions(PaymentDetails paymentDetails);
	
	public ResponseEntity<CustomerInfosResponse> findcustomerinfo(String codeclient);
	
	public ResponseEntity<DataResponseDTO> liensig(String age, String ncp, String cli);
	
	public ResponseEntity<ResponseCaisse> findcaisses(String userCaisse);
	
	public ResponseEntity<TransactionsResponse> findAllTransactionsToday(String date, String user);
	
	public ResponseEntity<TransactionsResponse> findPendingTransactions(String user);
	
	public ResponseEntity<TransactionsResponse> findTransactionsByDate(String referenceOperator, String debut, String fin);
	
	public ResponseEntity<TransactionsResponse> findTransactions(Transactions data, String debut, String fin);
	
	public ResponseEntity<TransactionsResponse> getAllTransactions();
	
	public ResponseEntity<TransactionsResponse> getTransactionsById(String id);
	
	public ResponseEntity<TransactionsResponse> deleteTransactions(Transactions data);	
	
	public ResponseEntity<TransactionsResponse> deleteTransactionsWithId(String id, Transactions data);	
	
	public ResponseEntity<TransactionsResponse> findByPartnerCode(String partnerCode);
	
	public ResponseEntity<TransactionsResponse> findTransactionsPartner(String partnerCode);
	
	public ResponseEntity<TransactionsResponse> findByPartnerCodeAndStatusTransDay(String partnerCode, TransactionStatus statusTrans);
	
	public ResponseEntity<TransactionsResponse> findByPartnerCodeAndStatusTrans(String partnerCode, TransactionStatus statusTrans, int page, int size);
	
	public ResponseEntity<TransactionsResponse> findTransactions(TransactionsDto data);
	
	public ResponseEntity<InfosCustomerVersementResponse> findLastOperator(String referenceOperator, String partnerCode);
	
	public ResponseEntity<DataResponseDTO> nivellementAccountPartner(String partnerCode, String user);
	
	public ResponseEntity<DataResponseDTO> editBordereauVersement(TransactionsDto transactionDtoRequest);
		
}
