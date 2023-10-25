package com.afriland.afbpaypartnerservices.workers;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.afriland.afbpaypartnerservices.dtos.ResponseHolder;
import com.afriland.afbpaypartnerservices.dtos.VersementPrepayDTO;
import com.afriland.afbpaypartnerservices.enums.PayPartnerExceptionCode;
import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.enums.TransactionMode;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.externservices.AlerteServices;
import com.afriland.afbpaypartnerservices.externservices.CoreBankingServices;
import com.afriland.afbpaypartnerservices.implementations.PropertyConfigServices;
import com.afriland.afbpaypartnerservices.jpa.Bkeve;
import com.afriland.afbpaypartnerservices.jpa.EfirstTransactions;
import com.afriland.afbpaypartnerservices.jpa.ForfaitPayments;
import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.Partners;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.jpa.TypeForfaits;
import com.afriland.afbpaypartnerservices.repositories.BkeveRepository;
import com.afriland.afbpaypartnerservices.repositories.EfirstTransactionsRepository;
import com.afriland.afbpaypartnerservices.repositories.ForfaitPaymentRepository;
import com.afriland.afbpaypartnerservices.repositories.OperatorRepository;
import com.afriland.afbpaypartnerservices.repositories.PartnerRepository;
import com.afriland.afbpaypartnerservices.repositories.TransactionsRepository;
import com.afriland.afbpaypartnerservices.repositories.TypeForfaitRepository;
import com.afriland.afbpaypartnerservices.services.IPropertyConfigService;
import com.afriland.afbpaypartnerservices.utils.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author yves_labo
 *
 */
@Slf4j
@Service
public class TransactionEfirstProcessing {

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Autowired
	private TypeForfaitRepository typeForfaitRepository;
	
	@Autowired
	private ForfaitPaymentRepository forfaitPaymentRepository;
	
	@Autowired
	private BkeveRepository bkeveRepository;

	@Autowired
	private EfirstTransactionsRepository efirstTransactionsRepository;


	@Autowired
	private OperatorRepository operatorRepository;

	@Autowired
	private PartnerRepository partnerRepository;
	

	//*** @Autowired
	//*** private GenerateReportTransactions generateReportTransactions;

	@Autowired
	private IPropertyConfigService propertyConfigService;


	Logger log = LoggerFactory.getLogger(TransactionEfirstProcessing.class);


	private String host;
	private String keysecurity;
	private String emaildefault;
	private String ncp;
	private String phonedefault ;
	private String utiebanking ;


	//*** @Scheduled(fixedRate=24000000)
	public void doTask(){
		log.info("==================== BEGIN TransactionEfirstProcessing========================="+ new Date());
		processTransactionEfirst();
		log.info("====================END TransactionEfirstProcessing====================="+ new Date());
	}


	@PostConstruct
	private void init(){
		host = propertyConfigService.gethost();
		log.info("==================== TransactionEfirstProcessing host ========================="+ host);
		keysecurity = propertyConfigService.getkeysecurity();
		emaildefault = propertyConfigService.getemaildefault();
		phonedefault = propertyConfigService.getphonedefault();
		utiebanking = propertyConfigService.getutiebanking();
	}


	private void processTransactionEfirst() {

		try {

			//Get all partners
			List<Partners> partners = partnerRepository.findAll();
			for(Partners pa : partners) {
				log.info("==================== TransactionEfirstProcessing pa.getPartname() ========================= : "+ pa.getPartname());

				List<EfirstTransactions> transactionsEbgs = new ArrayList<EfirstTransactions>();
				JsonObject convertedObject = null;
				JsonElement elem = null;
				JsonElement elemArray = null;
				String code = null;

				if(StringUtils.isNotBlank(pa.getPartcode()) && pa.isActif()) {
					ncp = pa.isComptabilisationTransit() ? (StringUtils.isNotBlank(pa.getPartnerNcpVersement()) ? pa.getPartnerNcpVersement() : pa.getAccount()) : pa.getAccount();
					log.info("==================== TransactionEfirstProcessing ncp ========================= : "+ ncp);

					String res = CoreBankingServices.getebankingtransactionsToday(keysecurity, host, ncp);

					convertedObject = new Gson().fromJson(res, JsonObject.class);
					elem = convertedObject.get("code");
					elemArray = convertedObject.get("data");
					code = elem.getAsString();
					if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), code) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), code)){
						Type listType = new TypeToken<List<EfirstTransactions>>() {}.getType();
						transactionsEbgs = new Gson().fromJson(elemArray, listType);
					}
				} 


				/// checkstatusevent
				Bkeve eve = null; 
				for(EfirstTransactions treb : transactionsEbgs) {

					// Check Double 
					Date date = DateUtil.parse(treb.getDreq(), DateUtil.DATE_HOUR_FORMAT_MOMO);
					List<EfirstTransactions> trcheck = efirstTransactionsRepository.findByRessourceValue(treb.getNord(), treb.getDeva(), treb.getCpro(), treb.getNcp2(), treb.getNcp1(), treb.getMnt1(), treb.getDreq(), treb.getDope());
					treb.prePersist();
					Optional<EfirstTransactions> chkop = efirstTransactionsRepository.findById(treb.getId());
					EfirstTransactions chk = null;
					if(chkop.isPresent()) {
						chk = chkop.get();
					}
					
					if(trcheck.isEmpty() && chk == null){

						String sourceNcp2 = treb.getNcp2().replace(" ","").trim();
						int lgt = sourceNcp2.length();
						String age2 = sourceNcp2.substring(0,5), ncp2 = sourceNcp2.substring(5,lgt-2), clc2 = sourceNcp2.substring(lgt-2,lgt),
								eta = "VA",
								uti = treb.getCpro(), 
								dsai = DateUtil.format(date, DateUtil.DATE_MINUS_FORMAT_CBS);
						Integer mon2 = treb.getMnt1().intValue();

						String sourceNcp1 = treb.getNcp1().replace(" ","").trim();
						lgt = sourceNcp1.length();
						String age1 = sourceNcp1.substring(0,5), ncp1 = sourceNcp1.substring(5,lgt-2), clc1 = sourceNcp1.substring(lgt-2,lgt);
						uti=utiebanking;

						String resCheck = CoreBankingServices.checkebankingstatusevent(keysecurity, host, age1, ncp1, clc1, age2, ncp2, clc2, mon2, eta, uti, dsai);

						//System.out.println("=======================checkebankingstatusevent==resCheck======================"+resCheck);
						convertedObject = new Gson().fromJson(resCheck, JsonObject.class);
						elem = convertedObject.get("code");
						elemArray = convertedObject.get("data");
						code = elem.getAsString();
						//log.info("----checkebankingstatusevent - code---"+code);
						if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), code) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), code)){
							Type listType = new TypeToken<List<Bkeve>>() {}.getType();
							List<Bkeve> eves = new Gson().fromJson(elemArray, listType);
							if(!eves.isEmpty()){
								eve = eves.iterator().next();
							}
						}

						//log.info("----eve---"+eve);
						String phone = "";
						String email = "";

						if(eve != null ) {

							log.info("----evetoString---"+eve.toString());
							//check code de la redevance
							
							if(StringUtils.equalsIgnoreCase(eve.getEta(), "VF") || StringUtils.equalsIgnoreCase(eve.getEta(), "VA") ){

								if(StringUtils.isNotBlank(eve.getAge1()) && StringUtils.isNotBlank(eve.getNcp1()) && StringUtils.isNotBlank(eve.getClc1())) {

									Operators operator = operatorRepository.findByPartnerCodeAndCodeClient(pa.getPartcode(), eve.getCli1());
									
									if(operator != null){

										String motif = StringUtils.isNotBlank(treb.getMotif()) ? treb.getMotif().trim() : "";
										ForfaitPayments forfait = forfaitPaymentRepository.findByCode(motif);
										
										VersementPrepayDTO data = new VersementPrepayDTO(eve);
										Transactions trans = new Transactions();
										trans.setOpe(eve.getOpe());
										trans.setTypeOperation(data.getTypeOperation());
										trans.setAccountCredit(data.getCreditAccount());
										trans.setAccountDebit(eve.getAge1().replace(" ","").trim()+"-"+eve.getNcp1().replace(" ","").trim()+"-"+eve.getClc1().replace(" ","").trim());
										trans.setAgeCaisse(eve.getAge1().replace(" ","").trim());
										trans.setAmount(data.getMontantVerse());
										trans.setUserCaisse(data.getUserCaisse());
										trans.setPartnerCode(operator.getPartnerCode());			
										trans.setReferenceOperator(operator.getCustomerReferencePartner());
										trans.setNomOperator(data.getNomOperator());
										trans.setTelephone(data.getTelephone());
										trans.setPartieVersante(data.getPartieVersante());
										trans.setReason(data.getReason());
										trans.setAmountReceived(data.getMontantPercu());
										trans.setUsermail(operator.getEmails());
										data.setStatus(TransactionStatus.INITIATE);
										trans.setStatusTrans(TransactionStatus.INITIATE);
										trans.setEveid(eve.getEve());
										trans.setReferencenumber(eve.getId());
										trans.setTransactionMode(TransactionMode.VIREMENT_EFIRST);
										trans.setId(DateUtil.now() + data.getPartnerCode() + RandomStringUtils.randomAlphanumeric(10).toUpperCase());
										trans.setDivisionAdministratif(operator.getDivisionAdministratif());
										trans.setActivity(operator.getActivity());
										trans.setAdresseOperator(operator.getAdresseOperator());
										//*** trans.setLibelleAgence(trans.getAgeCaisse());
										
										if(forfait != null) {
											TypeForfaits typeForfait = typeForfaitRepository.findByCode(forfait.getTypeForfait());
											
											trans.setSegment(typeForfait.getSegment().name());
											trans.setTypeJustificatif(forfait.getTypeForfait());
											trans.setCategorieJustificatif(forfait.getClasseForfait());
										}
										
										List<Transactions> results = this.transactionsRepository.findByTransactionsTodayOnline(trans.getAccountDebit(), trans.getAmount(), trans.getAccountCredit(), trans.getEveid(), TransactionStatus.INITIATE);
										if(results.isEmpty()){
											trans = this.transactionsRepository.save(trans);
										}else {
											trans = results.iterator().next();
										}

										phone = StringUtils.isNotBlank(operator.getPhones()) ? operator.getPhones().split("/")[0] : "" ;
										email = operator.getEmails();
										if(email != null ) emaildefault = email;

										if(StringUtils.isNotBlank(email)) emaildefault = email;
										if(StringUtils.isNotBlank(phone)) phonedefault = phone;
										emaildefault = emaildefault.trim().replace(" ","");
										phonedefault = phonedefault.trim().replace(" ","");
										trans.generateNewTri();

										//Confirmation auprès du partenaire
										if(Boolean.FALSE.equals(pa.isKycPartner())) {  
											if(StringUtils.isBlank(motif)) {
												trans.setStatusTrans(TransactionStatus.NONCONFORME);
												trans.setExceptionCode(PayPartnerExceptionCode.FORFAIT_NOT_IDENTIFIED);
												trans.setExceptionlib(PayPartnerExceptionCode.FORFAIT_NOT_IDENTIFIED.getValue() + " : Motif non renseignée ");
												trans = transactionsRepository.save(trans);
												eve.setId(trans.getId());
												eve = bkeveRepository.save(eve);
												treb = efirstTransactionsRepository.save(treb);
											}
											else if(forfait ==null) {
												trans.setStatusTrans(TransactionStatus.NONCONFORME);
												trans.setExceptionCode(PayPartnerExceptionCode.FORFAIT_NOT_IDENTIFIED);
												trans.setExceptionlib(PayPartnerExceptionCode.FORFAIT_NOT_IDENTIFIED.getValue() + " : " + treb.getMotif());
												trans = transactionsRepository.save(trans);
												eve.setId(trans.getId());
												eve = bkeveRepository.save(eve);
												treb = efirstTransactionsRepository.save(treb);
											}
											else if(Double.compare(forfait.getAmount(), treb.getMnt1()) == 0) {
												trans.setStatusTrans(TransactionStatus.NONCONFORME);
												trans.setExceptionCode(PayPartnerExceptionCode.TransactionInvalidAmount);
												trans.setExceptionlib(PayPartnerExceptionCode.TransactionInvalidAmount.getValue());
												trans = transactionsRepository.save(trans);
												eve.setId(trans.getId());
												eve = bkeveRepository.save(eve);
												treb = efirstTransactionsRepository.save(treb);
											}
											else if(StringUtils.isBlank(operator.getAccounts())) {
												trans.setStatusTrans(TransactionStatus.NONCONFORME);
												trans.setExceptionCode(PayPartnerExceptionCode.TransactionInvalidAccount);
												trans.setExceptionlib(PayPartnerExceptionCode.TransactionInvalidAccount.getValue() + " : Account partner");
												trans = transactionsRepository.save(trans);
												eve.setId(trans.getId());
												eve = bkeveRepository.save(eve);
												treb = efirstTransactionsRepository.save(treb);
											}
											else if(!operator.getAccounts().contains(eve.getNcp1())) {
												trans.setStatusTrans(TransactionStatus.NONCONFORME);
												trans.setExceptionCode(PayPartnerExceptionCode.TransactionInvalidAccount);
												trans.setExceptionlib(PayPartnerExceptionCode.TransactionInvalidAccount.getValue() + " : Compte non defini lors de souscription");
												trans = transactionsRepository.save(trans);
												eve.setId(trans.getId());
												eve = bkeveRepository.save(eve);
												treb = efirstTransactionsRepository.save(treb);
											}
											else if(!PayPartnerStatutSubscriber.ACTIF.equals(operator.getOperatorStatus())) {
												trans.setStatusTrans(TransactionStatus.NONCONFORME);
												trans.setExceptionCode(PayPartnerExceptionCode.SubscriberInvalid);
												trans.setExceptionlib(PayPartnerExceptionCode.SubscriberInvalid.getValue());
												trans = transactionsRepository.save(trans);
												eve.setId(trans.getId());
												eve = bkeveRepository.save(eve);
												treb = efirstTransactionsRepository.save(treb);
											}
											else {
												trans.setReferenceBill(trans.getPartnerCode()+"-"+eve.getEve()+"-"+ new SimpleDateFormat("ddMMyy").format(trans.getDco() != null ? trans.getDco() : new Date())+"-"+ (trans.getAgeCaisse() != null ? trans.getAgeCaisse() : "00006"));
												trans.setStatusTrans(TransactionStatus.TO_CONFIRM);												
												trans = transactionsRepository.save(trans);
												eve.setId(trans.getId());
												eve = bkeveRepository.save(eve);
												
												//SMS au client
												try {
													String urlSMS = propertyConfigService.geturlSms();
													if(StringUtils.isNotBlank(urlSMS)) {
														String message = "CHER CLIENT, NOUS ACCUSONS RECEPTION DU PAIEMENT N° " + trans.getReferenceBill() + " DE LA REDEVANCE MINTRANSPORT " + motif ;
														AlerteServices.sendSMS(keysecurity, urlSMS, phonedefault, message);
													}													
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												trans.setReferencenumber(eve.getId());
												trans = transactionsRepository.save(trans);
												treb = efirstTransactionsRepository.save(treb);
											}
										}
										else {
											/*
												TransactionsResponse deposit = mavianceProcessServices.processQuoteAndPaiement(trans, operator, phonedefault, emaildefault);

												if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), deposit.getCodeResponse())){

													CollectionResponse collectionResponse = deposit.getCollection();
													if(collectionResponse != null){

														if(collectionResponse.getStatus() != null){

															if(collectionResponse.getStatus().equals(CollectionResponse.StatusEnum.SUCCESS)) {
																trans.setStatusTrans(TransactionStatus.SUCCESS);
																trans.setErrorsMsg(deposit.getMessage());
															}else if(collectionResponse.getStatus().equals(StatusEnum.PENDING)) {
																trans.setStatusTrans(TransactionStatus.TO_CONFIRM);
																trans.setErrorsMsg(deposit.getMessage());
															}else if(collectionResponse.getStatus().equals(StatusEnum.ERRORED)) {
																trans.setStatusTrans(TransactionStatus.ERROR);
																trans.setErrorsMsg(deposit.getMessage());
															}else if(collectionResponse.getStatus().equals(StatusEnum.REVERSED)) {
																trans.setStatusTrans(TransactionStatus.REVERSED);
																trans.setErrorsMsg(deposit.getMessage());
															}else if(collectionResponse.getStatus().equals(StatusEnum.UNDERINVESTIGATION)) {
																trans.setStatusTrans(TransactionStatus.UNDERINVESTIGATION);
																trans.setErrorsMsg(deposit.getMessage());
															}			
															trans.setReference(collectionResponse.getPtn()+"-"+collectionResponse.getTrid()+"-"+collectionResponse.getPayItemId());
															trans.setTraceCollectionRequest(deposit.getCollectionRequest() != null ? deposit.getCollectionRequest().toString() : null );
															trans.setTraceCollectionResponse(collectionResponse.toString());
															trans.setTraceQuoteRequest(deposit.getQuoteRequest() != null ? deposit.getQuoteRequest().toString() : null);
															trans.setTraceQuoteResponse(deposit.getQuoteResponse() != null ? deposit.getQuoteResponse().toString() : null);

															trans.setPtn(collectionResponse.getPtn());
															trans.setTimestamp(collectionResponse.getTimestamp());
															trans.setAgentBalance(collectionResponse.getAgentBalance());
															trans.setReceiptNumber(collectionResponse.getReceiptNumber());
															trans.setVeriCode(collectionResponse.getVeriCode());
															trans.setPriceLocalCur(collectionResponse.getPriceLocalCur());
															trans.setPriceSystemCur(collectionResponse.getPriceSystemCur());
															trans.setLocalCur(collectionResponse.getLocalCur());
															trans.setSystemCur(collectionResponse.getSystemCur());
															trans.setTrid(collectionResponse.getTrid());
															trans.setPin(collectionResponse.getPin());
															trans.setStatus(collectionResponse.getStatus());
															trans.setPayItemId(collectionResponse.getPayItemId());
															trans.setPayItemDescr(collectionResponse.getPayItemDescr());

														}else {


														}

													}

													if(trans.getStatusTrans().equals(TransactionStatus.SUCCESS) || trans.getStatusTrans().equals(TransactionStatus.TO_CONFIRM)) {
														trans = transactionsRepository.save(trans);
														eve.setId(trans.getId());
														eve = bkeveRepository.save(eve);
														treb = efirstTransactionsRepository.save(treb);
													}

												}
											 */
										}

									}

								}

							}

						}

					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
		
}
