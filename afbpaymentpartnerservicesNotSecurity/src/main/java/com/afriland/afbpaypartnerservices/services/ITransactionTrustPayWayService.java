package com.afriland.afbpaypartnerservices.services;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.PaymentResponse;
import com.afriland.afbpaypartnerservices.dtos.trusayway.TrustPaymentDetails;


public interface ITransactionTrustPayWayService {

	public ResponseEntity<PaymentResponse>  payments(TrustPaymentDetails trustPaymentDetails);
		
}
