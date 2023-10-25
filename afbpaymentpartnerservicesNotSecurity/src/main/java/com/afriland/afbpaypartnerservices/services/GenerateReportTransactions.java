package com.afriland.afbpaypartnerservices.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.afriland.afbpaypartnerservices.bill.BillModel;
import com.afriland.afbpaypartnerservices.bill.PrintDocument;
import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.repositories.PropertyConfigRepository;
import com.afriland.afbpaypartnerservices.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GenerateReportTransactions {
	
	private static Logger log = LogManager.getLogger("GenerateReportTransactions.class");
	@Autowired
	private PropertyConfigRepository propertyConfigRepository;
	
	@Value("${partner.code.minitrans}") 
	private String partnerCodeMinitrans;
	
	public String getValue(String code){
		Optional<PropertyConfigs> mers = propertyConfigRepository.findById(code);
		PropertyConfigs mer = null ; 
		if(mers.isPresent()){
			mer = mers.get();
			return mer.getValue();
		}
		return null;
	}
	
	public String getpartnerCodeMinitrans(){
		return getValue(partnerCodeMinitrans);
	}
	

	public byte[] generateBill(Transactions trans, Operators operators) {

		log.info("-------generateBill----");
		byte[] reportdata = null;
		try {
			BillModel bill = new BillModel();
			bill.setCodeBar(trans.getPtn());
			bill.setDateOper(new Date());
			bill.setNomClient(operators.getCustomerNamePartner());
			bill.setAdressClient(operators.getCustomerReferencePartner());
			bill.setPays("CM");
			bill.setRegion("");
			bill.setTellerNumber(trans.getUserCaisse());
			bill.setTransid(trans.getTrid()+"-"+trans.getEveid());
			bill.setCompCode(operators.getPartnerCode());
			bill.setPayDoc(operators.getPartnerCode());
			bill.setFiscalYear(DateUtil.nowYears());
			bill.setStatus("Successful");
			bill.setMontant(trans.getAmount());
			bill.setCurrency("XAF");
			bill.setCodeClient(operators.getCustomerReferencePartner());
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String currDateString = dateFormat.format(new Date());
			reportdata = PrintDocument.generateBill(bill, "BillCustDownPay.jasper", "BillDeposit_" +operators.getCustomerReferencePartner()+ "_" + currDateString + ".pdf");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	

		log.info("-----------"+reportdata);
		return reportdata;
	}

}
