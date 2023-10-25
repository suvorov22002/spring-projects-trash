package com.afriland.afbpaypartnerservices.implementations;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.afriland.afbpaypartnerservices.dtos.AuthResponse;
import com.afriland.afbpaypartnerservices.dtos.Beneficiaire;
import com.afriland.afbpaypartnerservices.dtos.Bkcom;
import com.afriland.afbpaypartnerservices.dtos.BkeveDto;
import com.afriland.afbpaypartnerservices.dtos.BkeveDtoResponce;
import com.afriland.afbpaypartnerservices.dtos.CancelTransDto;
import com.afriland.afbpaypartnerservices.dtos.CashierDto;
import com.afriland.afbpaypartnerservices.dtos.ConfirmationTransDto;
import com.afriland.afbpaypartnerservices.dtos.Customer;
import com.afriland.afbpaypartnerservices.dtos.CustomerDetailsDto;
import com.afriland.afbpaypartnerservices.dtos.InfosCustomerVersementDto;
import com.afriland.afbpaypartnerservices.dtos.ObjectFile;
import com.afriland.afbpaypartnerservices.dtos.ParameterJasper;
import com.afriland.afbpaypartnerservices.dtos.PaymentDetails;
import com.afriland.afbpaypartnerservices.dtos.PaymentResponse;
import com.afriland.afbpaypartnerservices.dtos.ResponseBkcom;
import com.afriland.afbpaypartnerservices.dtos.ResponseCaisse;
import com.afriland.afbpaypartnerservices.dtos.ResponseCustomer;
import com.afriland.afbpaypartnerservices.dtos.ResponseData2;
import com.afriland.afbpaypartnerservices.dtos.ResponseData3;
import com.afriland.afbpaypartnerservices.dtos.ResponseHolder;
import com.afriland.afbpaypartnerservices.dtos.TransactionsDto;
import com.afriland.afbpaypartnerservices.dtos.UnpaidNotice;
import com.afriland.afbpaypartnerservices.dtos.VersementPrepayDTO;
import com.afriland.afbpaypartnerservices.dtos.trusayway.TrustBill;
import com.afriland.afbpaypartnerservices.dtos.trusayway.TrustDetailsObject;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.enums.PayPartnerExceptionCategory;
import com.afriland.afbpaypartnerservices.enums.PayPartnerExceptionCode;
import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.enums.PayPartnerTypeString;
import com.afriland.afbpaypartnerservices.enums.TransactionMode;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.exception.PayPartnerException;
import com.afriland.afbpaypartnerservices.externservices.AlerteServices;
import com.afriland.afbpaypartnerservices.externservices.CoreBankingServices;
import com.afriland.afbpaypartnerservices.jpa.Bkeve;
import com.afriland.afbpaypartnerservices.jpa.BkeveDTO;
import com.afriland.afbpaypartnerservices.jpa.BkeveTemplate;
import com.afriland.afbpaypartnerservices.jpa.Bkmvti;
import com.afriland.afbpaypartnerservices.jpa.DisplayBkeveTemplate;
import com.afriland.afbpaypartnerservices.jpa.FactMonth;
import com.afriland.afbpaypartnerservices.jpa.FactMonthDetails;
import com.afriland.afbpaypartnerservices.jpa.OperatorPhones;
import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.Partners;
import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.models.CollectionResponse;
import com.afriland.afbpaypartnerservices.models.CollectionResponse.StatusEnum;
import com.afriland.afbpaypartnerservices.repositories.BkeveRepository;
import com.afriland.afbpaypartnerservices.repositories.BkeveTemplateRepository;
import com.afriland.afbpaypartnerservices.repositories.FactMonthDetailRepository;
import com.afriland.afbpaypartnerservices.repositories.FactMonthRepository;
import com.afriland.afbpaypartnerservices.repositories.OperatorRepository;
import com.afriland.afbpaypartnerservices.repositories.PartnerRepository;
import com.afriland.afbpaypartnerservices.repositories.PhoneRepository;
import com.afriland.afbpaypartnerservices.repositories.PropertyConfigRepository;
import com.afriland.afbpaypartnerservices.repositories.TransactionsFullRepository;
import com.afriland.afbpaypartnerservices.repositories.TransactionsRepository;
import com.afriland.afbpaypartnerservices.response.CustomerInfosResponse;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.InfosCustomerVersementResponse;
import com.afriland.afbpaypartnerservices.response.TransactionsResponse;
import com.afriland.afbpaypartnerservices.services.GenerateReportTransactions;
import com.afriland.afbpaypartnerservices.services.ITransactionService;
import com.afriland.afbpaypartnerservices.utils.AfbEncryptionDecryption;
import com.afriland.afbpaypartnerservices.utils.DateUtil;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;
import com.afriland.afbpaypartnerservices.utils.StringUtil;
import com.afriland.afbpaypartnerservices.utils.ToolsUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;




/**
 * TransactionServices
 * @author yves_labo
 *  
 */
@SuppressWarnings("null")
@Service("transactionservice")
@Transactional
public class TransactionServices implements ITransactionService {

	Logger log = LoggerFactory.getLogger(TransactionServices.class);

	@Autowired
	private TransactionsRepository repository;

	@Autowired
	private TransactionsFullRepository repositoryfull;

	@Autowired
	private FactMonthRepository factMonthrepository;

	@Autowired
	private FactMonthDetailRepository factMonthDetailrepository;

	@Autowired
	private BkeveRepository bkeveRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private OperatorRepository operatorRepository;

	@Autowired
	private PropertyConfigRepository propertyConfigRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Autowired
	private BkeveTemplateRepository bkeveTemplateRepository;

	@Autowired
	private GenerateReportTransactions generateReportTransactions;

	@Autowired
	private PropertyConfigServices PropertyConfig;


	@Value("${partner.code.trustpayway}")
	private String partnerCodeTrustPayway;

	@Value("${partner.code.cimencam}")
	private String partnerCodeCimencam;

	@Value("${partner.code.rtc}")
	private String partnerCodeRtc;

	@Value("${partner.code.douane}")
	private String partnerCodeDouane;

	@Value("${partner.code.sprintpay}")
	private String partnerCodeSprintpay;

	@Value("${partner.code.minitrans}") 
	private String partnerCodeMinitrans;

	@Value("${application.service.actif}")
	private String applicationServiceActif;

	@Value("${application.service.transaction.opposition}")
	private String transactionopposition;

	@Value("${application.afriland.cbsservice.baseurl}")
	private String host;

	@Value("${application.afriland.cbsservice.keysecurity}")
	private String keysecurity;

	@Value("${application.afriland.url.sms}")
	private String urlSms;

	@Value("${application.afriland.cbsservice.linkcustomerinfo}")
	private String linkcustomerinfo;

	@Value("${application.service.account.minitrans}")
	private String applicationAccountMinitrans;

	@Value("${application.code.operation.virement}")
	private String applicationCodeOperationVirement;

	@Value("${application.service.transaction.ope.nivellement}")
	private String applicationServiceOpeNivellement;

	@Value("${application.service.transaction.nature}")
	private String applicationServiceTransactionNature;

	@Value("${application.service.account.commission}")
	private String applicationServiceAccountCommission;

	@Value("${application.service.account.tva}")
	private String applicationServiceAccountTva;

	@Value("${application.service.account.liaison}")
	private String applicationServiceAccountLiaison;

	@Value("${application.service.code.user}")
	private String applicationServiceCodeUser;

	@Value("${application.service.niveau.check.transaction}")
	private String applicationServiceNiveauCheckTrans;

	@Value("${application.service.validite.otp}")
	private String applicationServiceValiditeOtp;

	@Value("${application.service.size.otp}")
	private String applicationServiceSizeOtp;

	@Value("${application.service.type.otp}")
	private String applicationServiceTypeOtp;

	@Value("${application.service.default.pin}")
	private String applicationServiceDefaultPin;




	public String getValue(String code){
		Optional<PropertyConfigs> mers = propertyConfigRepository.findById(code);
		PropertyConfigs mer = null ; 
		if(mers.isPresent()){
			mer = mers.get();
			return mer.getValue();
		}
		return null;
	}


	public String gethost(){
		return getValue(host);
	}

	public String getkeysecurity(){
		return getValue(keysecurity);
	}

	public String getlinkcustomerinfo(){
		return getValue(linkcustomerinfo);
	}

	public String getpartnerCodeTrustPayway(){
		return getValue(partnerCodeTrustPayway);
	}

	public String getpartnerCodeCimencam(){
		return getValue(partnerCodeCimencam);
	}

	public String getpartnerCodeRtc(){
		return getValue(partnerCodeRtc);
	}

	public String getpartnerCodeDouane(){
		return getValue(partnerCodeDouane);
	}

	public String getpartnerCodeSprintpay(){
		return getValue(partnerCodeSprintpay);
	}

	public String getpartnerCodeMinitrans(){
		return getValue(partnerCodeMinitrans);
	}

	public String getapplicationServiceActif(){
		return getValue(applicationServiceActif);
	}

	public String gettransactionopposition(){
		return getValue(transactionopposition);
	}

	public String getapplicationCodeOperationVirement(){
		return getValue(applicationCodeOperationVirement);
	}

	public String getapplicationaccountminitrans(){
		return getValue(applicationAccountMinitrans);
	} 

	public String getapplicationServiceOpeNivellement(){
		return getValue(applicationServiceOpeNivellement);
	} 

	public String getapplicationServiceTransactionNature(){
		return getValue(applicationServiceTransactionNature);
	}

	public String getapplicationServiceAccountLiaison(){
		return getValue(applicationServiceAccountLiaison);
	} 

	public String getapplicationServiceAccountCommission(){
		return getValue(applicationServiceAccountCommission);
	} 

	public String getapplicationServiceAccountTva(){
		return getValue(applicationServiceAccountTva);
	} 

	public String getapplicationServiceCodeUser(){
		return getValue(applicationServiceCodeUser);
	} 

	public String getapplicationServiceNiveauCheckTrans(){
		return getValue(applicationServiceNiveauCheckTrans);
	}

	public String getapplicationServiceValiditeOtp(){
		return getValue(applicationServiceValiditeOtp);
	}

	public String getapplicationServiceSizeOtp(){
		return getValue(applicationServiceSizeOtp);
	}

	public String getapplicationServiceTypeOtp(){
		return getValue(applicationServiceTypeOtp);
	}

	public String geturlSms(){
		return getValue(urlSms);
	} 

	public String getapplicationServiceDefaultPin(){
		return getValue(applicationServiceDefaultPin);
	}


	@PersistenceContext
	private EntityManager entityManager;

	private List<BkeveTemplate> templates = null;

	//*** private String keysecurity = null;
	private String emaildefault = null;
	private String phonedefault = null;


	@PostConstruct
	private void init(){
		this.templates = this.bkeveTemplateRepository.findAll();
		emaildefault = PropertyConfig.getemaildefault();
		phonedefault = PropertyConfig.getphonedefault();	
		host = gethost();
		urlSms = geturlSms();
		keysecurity = getkeysecurity();			
		linkcustomerinfo = getlinkcustomerinfo();		
		applicationServiceValiditeOtp = getapplicationServiceValiditeOtp();
		applicationServiceSizeOtp = getapplicationServiceSizeOtp();
		applicationServiceTypeOtp = getapplicationServiceTypeOtp();
		applicationServiceDefaultPin = getapplicationServiceDefaultPin();
		applicationServiceCodeUser =  getapplicationServiceCodeUser();

		partnerCodeRtc = getpartnerCodeRtc();
		partnerCodeTrustPayway = getpartnerCodeTrustPayway();
		applicationServiceOpeNivellement = getapplicationServiceOpeNivellement();
		applicationCodeOperationVirement = getapplicationCodeOperationVirement();

		applicationServiceAccountLiaison = getapplicationServiceAccountLiaison();
		applicationServiceAccountCommission = getapplicationServiceAccountCommission();
		applicationServiceAccountTva = getapplicationServiceAccountTva();

		applicationServiceTransactionNature = getapplicationServiceTransactionNature();
		transactionopposition = gettransactionopposition();
	}


	private BkeveTemplate getBkeveTemplate(String typeOpe) {
		DisplayBkeveTemplate display = () -> {
			return this.templates.stream()
					.filter(t ->t.getTypeOpe().equalsIgnoreCase(typeOpe))
					.collect(Collectors.toList());    
		};
		List<BkeveTemplate> list = display.show();  
		return list.iterator().next();
	}


	@Override
	public ResponseEntity<CustomerInfosResponse> findcustomerinfo(String codeclient) {
		List<CustomerDetailsDto> datas = new ArrayList<CustomerDetailsDto>();

		try {
			if (StringUtils.isBlank(codeclient)) {
				return new ResponseEntity<CustomerInfosResponse>(new CustomerInfosResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Code client)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			CustomerDetailsDto customerInfos = CoreBankingServices.findcustomerinfo(keysecurity, linkcustomerinfo, codeclient);
			if (customerInfos != null) {
				datas.add(customerInfos);
				return new ResponseEntity<CustomerInfosResponse>(new CustomerInfosResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<CustomerInfosResponse>(new CustomerInfosResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<CustomerInfosResponse>(new CustomerInfosResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<DataResponseDTO> liensig(String age, String ncp, String cli){
		String signature = "";
		try {
			signature = CoreBankingServices.liensig(keysecurity, linkcustomerinfo, age, ncp, "RAS", cli, new SimpleDateFormat("dd/MM/yyyy").format(new Date()), new SimpleDateFormat("hhmmss").format(new Date()), "AUTO");
			if (StringUtils.isNotBlank(signature)) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", signature), HttpStatus.OK);
			}
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", signature), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), signature), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<ResponseCaisse> findcaisses(String userCaisse){
		ResponseCaisse caisses = null;
		try {
			caisses = CoreBankingServices.findcaisses(keysecurity, host, userCaisse);
			if (caisses == null) {
				return new ResponseEntity<ResponseCaisse>(caisses, HttpStatus.OK);
			}
			return new ResponseEntity<ResponseCaisse>(caisses, HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<ResponseCaisse>(caisses, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	private Date getDco() {
		DataResponseDTO dtoDCO = CoreBankingServices.getDateComptable(keysecurity, host);
		Date dco = new Date();
		if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), dtoDCO.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), dtoDCO.getCode()) ){
			if(!StringUtils.isBlank(dtoDCO.getData())){
				dco = DateUtil.parse(dtoDCO.getData(), DateUtil.DATE_MINUS_FORMAT_SINGLE);
			}
		}
		return dco;
	}


	private Date getDateValeurDebit(Date dco, String pdr) {
		DataResponseDTO dtoDva = CoreBankingServices.getDateValeurDebit(keysecurity, host, DateUtil.format(dco, DateUtil.DATE_MINUS_FORMAT_SINGLE), pdr);
		Date dva = new Date();
		if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), dtoDva.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), dtoDva.getCode()) ){
			if(!StringUtils.isBlank(dtoDva.getData())){
				dva = DateUtil.parse(dtoDva.getData(), DateUtil.DATE_MINUS_FORMAT_SINGLE);
			}
		}
		return dva;
	}


	private Date getDateValeurCredit(Date dco, String pdr ) {
		DataResponseDTO dtoDva = CoreBankingServices.getDateValeurCredit(keysecurity, host, DateUtil.format(dco, DateUtil.DATE_MINUS_FORMAT_SINGLE), pdr);
		Date dva = new Date();
		if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), dtoDva.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), dtoDva.getCode()) ){
			if(!StringUtils.isBlank(dtoDva.getData())){
				dva = DateUtil.parse(dtoDva.getData(), DateUtil.DATE_MINUS_FORMAT_SINGLE);
			}
		}
		return dva;
	}



	@Override
	public ResponseEntity<TransactionsResponse> initCashInTransactions(VersementPrepayDTO data){

		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
		Transactions trans = new Transactions();
		try {
			//init();

			log.info("Verifier l'etat du service ");
			String appServiceActif = getapplicationServiceActif();
			if(StringUtils.isBlank(appServiceActif) || !"UP".equals(appServiceActif))
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.DigitalFirstServiceSuspended, "", datas), HttpStatus.PRECONDITION_FAILED);

			if(StringUtils.isBlank(data.getCreditAccount())) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Account credit transit", datas), HttpStatus.PRECONDITION_FAILED);
			}
			String creditAccount = data.getCreditAccount();

			String ageOpe = creditAccount.split("-")[0];
			String ncpOpe = creditAccount.split("-")[1]; 
			System.out.println("=======================creditAccount========================"+ creditAccount);

			if(StringUtils.isBlank(data.getReferenceOperator())) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.CUSTOMER_NOT_IDENTIFIED, "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			if(StringUtils.isBlank(data.getCreditAccount())) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Account credit transit", datas), HttpStatus.PRECONDITION_FAILED);
			}
			Operators operator = operatorRepository.findByCustomerReferencePartnerAndPartnerCode(data.getReferenceOperator(), data.getPartnerCode());
			if(operator != null) {
				trans.setIdOperator(operator.getId());
			}

			trans.setOpe("001");
			trans.setTypeOperation("001");
			trans.setAccountCredit(creditAccount);
			trans.setAccountDebit(" --- ");
			trans.setAmount(data.getMontantVerse());
			trans.setUserCaisse(data.getUserCaisse());
			trans.setAgeCaisse(data.getAgeCaisse());
			trans.setLibelleAgence(data.getLibelleAgence());
			trans.setPartnerCode(data.getPartnerCode());			
			trans.setReferenceOperator(data.getReferenceOperator());
			trans.setNomOperator(data.getNomOperator());
			trans.setTelephone(data.getTelephone());
			trans.setPartieVersante(data.getPartieVersante());
			trans.setCodeReason(data.getCodeReason());
			trans.setReason(data.getReason());
			trans.setAmountReceived(data.getMontantPercu());
			trans.setUsermail(data.getUsermail());
			data.setStatus(TransactionStatus.INITIATE);
			trans.setStatusTrans(TransactionStatus.INITIATE);
			trans.setTransactionMode(TransactionMode.VERSEMENT_AFB);
			trans.setId(DateUtil.now() + data.getPartnerCode() + RandomStringUtils.randomAlphanumeric(10).toUpperCase());
			trans.setDetails(data.getDetails());
			trans.setSegment(data.getSegment());
			trans.setTypeJustificatif(data.getTypeJustificatif());
			trans.setCategorieJustificatif(data.getCategorieJustificatif());
			trans.setDivisionAdministratif(data.getDivisionAdministratif());
			trans.setActivity(data.getActivity());
			trans.setAdresseOperator(data.getAdresseOperator());

			trans = this.repository.save(trans);  

			String piece = RandomStringUtils.randomAlphanumeric(11).toUpperCase();

			DataResponseDTO dtoDCO = CoreBankingServices.getDateComptable(keysecurity, host);
			Date dco = new Date();
			if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), dtoDCO.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), dtoDCO.getCode()) ){
				if(!StringUtils.isBlank(dtoDCO.getData())){
					dco = DateUtil.parse(dtoDCO.getData(), DateUtil.DATE_MINUS_FORMAT_SINGLE);
					trans.setDco(dco);
				}else {
					data.setStatus(TransactionStatus.ERROR);
					trans.setStatusTrans(TransactionStatus.ERROR);
					trans.setExceptionCode(PayPartnerExceptionCode.DCO_NULL);
					trans.setExceptionlib(PayPartnerExceptionCode.DCO_NULL.getValue());
					trans = this.repository.save(trans); 
					datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.DCO_NULL, "", datas), HttpStatus.NOT_IMPLEMENTED);
				}
			}else {
				data.setStatus(TransactionStatus.ERROR);
				trans.setStatusTrans(TransactionStatus.ERROR);
				trans.setExceptionCode(PayPartnerExceptionCode.DCO_NULL);
				trans.setExceptionlib(PayPartnerExceptionCode.DCO_NULL.getValue());
				trans = this.repository.save(trans); 
				datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.DCO_NULL, "", datas), HttpStatus.NOT_IMPLEMENTED);
			}
			log.info("===================end====uridco========================"+ new Date());

			// Find Caisse
			ResponseCaisse caisses = CoreBankingServices.findcaisses(keysecurity, host, data.getUserCaisse());
			log.info("===================end====urifindcaisses========================"+ new Date());
			if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), caisses.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), caisses.getCode())){

				if( caisses.getData() == null) {
					data.setStatus(TransactionStatus.ERROR);
					trans.setStatusTrans(TransactionStatus.ERROR);
					trans.setExceptionCode(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE);
					trans.setExceptionlib(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE.getValue());
					trans = this.repository.save(trans); 
					datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.AUCUNE_CAISSE_OUVERTE, "", datas), HttpStatus.NOT_IMPLEMENTED);
				}

				CashierDto cashierDto = caisses.getData().iterator().next();
				if(cashierDto != null){
					if(!StringUtils.isBlank(cashierDto.getAge())){

						// Find detail compte 
						System.out.println("=======================ncpOpe========================"+ ncpOpe);
						ResponseBkcom res = CoreBankingServices.getaccountinfo(keysecurity, host, ageOpe, ncpOpe);
						Bkcom bkcom =  res.getData().isEmpty() ? null : res.getData().get(0);
						if(bkcom == null) {
							data.setStatus(TransactionStatus.ERROR);
							trans.setStatusTrans(TransactionStatus.ERROR);
							trans.setExceptionCode(PayPartnerExceptionCode.InvalidAccount);
							trans.setExceptionlib(PayPartnerExceptionCode.InvalidAccount.getValue());
							trans = this.repository.save(trans); 
							datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
							return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.InvalidAccount, "", datas), HttpStatus.NOT_IMPLEMENTED);
						}

						// Find detail Client 
						System.out.println("=======================bkcom.getCli()========================"+ bkcom.getCli());
						Customer customer = null;
						if(StringUtils.isNotBlank(bkcom.getCli())) {
							ResponseCustomer cust = CoreBankingServices.getcustomerinfo(keysecurity, host, bkcom.getCli());
							customer = cust.getData().isEmpty() ? null : cust.getData().get(0);
							System.out.println("===================end====getcustomerinfo========================"+ new Date());
						}

						if(StringUtils.equalsIgnoreCase(ageOpe, cashierDto.getAge())) {

							// meme Agence
							BkeveTemplate temp = getBkeveTemplate(BkeveTemplate.SIMPLE);
							Bkeve eve = new Bkeve(temp);
							if(StringUtils.isNotBlank(bkcom.getCli())) { eve.build(data, cashierDto,piece,bkcom,customer,trans);}
							else { eve.build(data, cashierDto,piece,bkcom,"240",trans); }
							trans.setAgeCaisse(cashierDto.getAge());

							System.out.println("=======================uriEvent========================"+ new Date());
							BkeveDTO dto = CoreBankingServices.postEvent(keysecurity, host, eve);
							System.out.println("=====================end==uriEvent========================"+ new Date());

							if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), dto.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), dto.getCode())){
								eve = dto.getEve();
								data.setStatus(TransactionStatus.PROCESSING);
								trans.setStatusTrans(TransactionStatus.PROCESSING);
								eve.setDco(dco);
								eve = this.bkeveRepository.save(eve);

								trans.setReferencenumber(eve.getId());
								trans.setEveid(eve.getEve());
								trans = this.repository.save(trans);        	

								data.setDateComptable(eve.getDco());
								data.setDateOpe(eve.getDsai());
								data.setNumEve(eve.getEve());  
								data.setReference(trans.getId());
							}else {
								data.setStatus(TransactionStatus.ERROR);
								trans.setStatusTrans(TransactionStatus.ERROR);
								trans.setExceptionCode(PayPartnerExceptionCode.POSTING_CBS_FAIL);
								trans.setExceptionlib(PayPartnerExceptionCode.POSTING_CBS_FAIL.getValue());
								trans = this.repository.save(trans);
							}

						}else {

							// deplace
							BkeveTemplate tempAGENT = getBkeveTemplate(BkeveTemplate.DEPLACE_AGENT);
							BkeveTemplate tempPARTNER = getBkeveTemplate(BkeveTemplate.DEPLACE_PARTNER);
							Bkeve eveAGENT = new Bkeve(tempAGENT);
							Bkeve evePARTNER = new Bkeve(tempPARTNER);

							if(StringUtils.isNotBlank(bkcom.getCli())) { eveAGENT.build2(data, cashierDto,piece,bkcom,customer,trans); }
							else { eveAGENT.build2(data, cashierDto,piece,bkcom, "240",trans); }

							// operation deplace							
							if(StringUtils.isNotBlank(bkcom.getCli())) { evePARTNER.buildDeplace(data, cashierDto,piece,bkcom,customer,trans);}
							else { evePARTNER.buildDeplace(data, cashierDto,piece,bkcom,"240",trans); }

							BkeveDto dto = new BkeveDto();
							dto.setEve01(eveAGENT);
							dto.setEve02(evePARTNER);

							System.out.println("=======================uridepositevent========================"+ new Date());
							BkeveDtoResponce response = CoreBankingServices.depositEvent(keysecurity, host, dto);
							System.out.println("=====================end==uridepositevent========================"+ new Date());

							if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), response.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), response.getCode())){

								eveAGENT = response.getEve01();
								evePARTNER = response.getEve02();
								data.setStatus(TransactionStatus.PROCESSING);
								trans.setStatusTrans(TransactionStatus.PROCESSING);
								evePARTNER.setDco(dco);
								eveAGENT.setDco(dco);
								evePARTNER = this.bkeveRepository.save(evePARTNER);
								eveAGENT = this.bkeveRepository.save(eveAGENT);

								trans.setReferencenumber(evePARTNER.getId()+"-"+eveAGENT.getId());
								trans.setEveid(evePARTNER.getEve());

								trans = this.repository.save(trans);        	

								data.setDateComptable(eveAGENT.getDco());
								data.setDateOpe(eveAGENT.getDsai());
								data.setNumEve(eveAGENT.getEve());  
								data.setReference(trans.getId());

							}else {
								data.setStatus(TransactionStatus.ERROR);
								trans.setStatusTrans(TransactionStatus.ERROR);
								trans.setExceptionCode(PayPartnerExceptionCode.POSTING_CBS_FAIL);
								trans.setExceptionlib(PayPartnerExceptionCode.POSTING_CBS_FAIL.getValue());
								trans = this.repository.save(trans);
								datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
								return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.POSTING_CBS_FAIL, "", datas), HttpStatus.NOT_IMPLEMENTED);
							}

						}
					}else {
						data.setStatus(TransactionStatus.ERROR);
						trans.setStatusTrans(TransactionStatus.ERROR);
						trans.setExceptionCode(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE);
						trans.setExceptionlib(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE.getValue());
						trans = this.repository.save(trans); 
						datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
						return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.AUCUNE_CAISSE_OUVERTE, "", datas), HttpStatus.NOT_IMPLEMENTED);
					}
				}else {
					data.setStatus(TransactionStatus.ERROR);
					trans.setStatusTrans(TransactionStatus.ERROR);
					trans.setExceptionCode(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE);
					trans.setExceptionlib(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE.getValue());
					trans = this.repository.save(trans); 
					datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.AUCUNE_CAISSE_OUVERTE, "", datas), HttpStatus.NOT_IMPLEMENTED);
				}

			}else {
				data.setStatus(TransactionStatus.ERROR);
				trans.setStatusTrans(TransactionStatus.ERROR);
				trans.setExceptionCode(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE);
				trans.setExceptionlib(PayPartnerExceptionCode.AUCUNE_CAISSE_OUVERTE.getValue());
				trans = this.repository.save(trans); 
				datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.AUCUNE_CAISSE_OUVERTE, "", datas), HttpStatus.NOT_IMPLEMENTED);
			}


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.setStatusTrans(TransactionStatus.ERROR);
			trans.setExceptionCode(PayPartnerExceptionCode.BankException);
			trans.setExceptionlib(e.getMessage());
			trans = this.repository.save(trans); 
			datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.AUCUNE_CAISSE_OUVERTE, "", datas), HttpStatus.NOT_IMPLEMENTED);
		}

		if (trans != null) {
			datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
		}
		return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

	}



	@Override
	public ResponseEntity<TransactionsResponse> saveTransactions(Transactions data){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();

		String eta = "";
		Bkeve eve = null;
		Bkeve eve2 = null;

		Optional<Transactions> trs = this.repository.findById(data.getId());
		Transactions trans = null;
		if(trs.isPresent()){
			trans = trs.get();
			if(TransactionStatus.ERROR.equals(trans.getStatusTrans()) || StringUtils.isBlank(trans.getReferencenumber())){
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.TRANSACTION_WRONG_STATUS, " (Transaction status ERROR)", datas), HttpStatus.PRECONDITION_FAILED);
			}

			String[] eves = trans.getReferencenumber().split("-");
			if(eves.length == 1){
				Optional<Bkeve> opeve = this.bkeveRepository.findById(trans.getReferencenumber());
				if(opeve.isPresent()){
					eve = opeve.get();
				}else {
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " (Transaction)", datas), HttpStatus.PRECONDITION_FAILED);
				}
			}else {
				Optional<Bkeve> opeve = this.bkeveRepository.findById(eves[0]);
				if(opeve.isPresent()){
					eve = opeve.get();
				}else {
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " (Transaction)", datas), HttpStatus.PRECONDITION_FAILED);
				}
				opeve = this.bkeveRepository.findById(eves[1]);
				if(opeve.isPresent()){
					eve2 = opeve.get();
				}else {
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " (Transaction)", datas), HttpStatus.PRECONDITION_FAILED);
				}
			}
		}else {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " (Transaction)", datas), HttpStatus.PRECONDITION_FAILED);
		}

		if(trans.getStatusTrans().equals(TransactionStatus.SUCCESS)){
			datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		}else if(trans.getStatusTrans().equals(TransactionStatus.ERROR) || trans.getStatusTrans().equals(TransactionStatus.CANCEL) || trans.getStatusTrans().equals(TransactionStatus.INITIATE) || trans.getStatusTrans().equals(TransactionStatus.FAILED) ) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.TRANSACTION_WRONG_STATUS, "", datas), HttpStatus.PRECONDITION_FAILED);
		}

		trans.setReferenceBill(trans.getPartnerCode()+"-"+trans.getEveid()+"-"+ new SimpleDateFormat("ddMMyy").format(trans.getDco() != null ? trans.getDco() : new Date())+"-"+ (trans.getAgeCaisse() != null ? trans.getAgeCaisse() : "00006"));
		if(trans.getStatusTrans().equals(TransactionStatus.PROCESSING)){

			DataResponseDTO res = CoreBankingServices.statutEvent(keysecurity, host, eve.getAge(), eve.getEve(), eve.getOpe(), eve.getDco());

			if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), res.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), res.getCode())){
				eta = res.getData();
				System.out.println("=======================statusevent========================"+eta);
				System.out.println("=======================eve2========================"+eve2);
				if(!StringUtils.equalsIgnoreCase(eta,"VF") && eve2 != null ){
					res = CoreBankingServices.statutEvent(keysecurity, host, eve2.getAge(), eve2.getEve(), eve2.getOpe(), eve2.getDco());
					eta = res.getData();
					System.out.println("=======================statusevent 222========================"+eta);
				}
			}
		}

		if(StringUtils.equalsIgnoreCase(eta, "VF") || trans.getStatusTrans().equals(TransactionStatus.ENCAISSEE) ){

			if(trans.getStatusTrans().equals(TransactionStatus.ENCAISSEE)) eta = "VF";
			//if(trans.getStatusTrans().equals(TransactionStatus.TO_CONFIRM)) eta = "VF";
			if(trans.getStatusTrans().equals(TransactionStatus.PROCESSING)) trans.setStatusTrans(TransactionStatus.ENCAISSEE);
			eve.setEta(eta);

			String phone = "";
			String email = "";
			Operators operators = operatorRepository.findByCustomerReferencePartnerAndPartnerCode(trans.getReferenceOperator(), trans.getPartnerCode());
			if(operators != null) {				
				phone = StringUtils.isNotBlank(operators.getPhones()) ? operators.getPhones().split("/")[0] : "" ;
				email = operators.getEmails();
				if(email != null ) emaildefault = email;

				List<OperatorPhones> phones = phoneRepository.findByRessource(operators);

				OperatorPhones mphone = null;
				if(!phones.isEmpty()) mphone = phones.get(0);
				if(mphone != null) phone = mphone.getDatavalue();
			}

			if(StringUtils.isNotBlank(email)) emaildefault = email;
			if(StringUtils.isNotBlank(phone)) phonedefault = phone;

			if(StringUtils.isNotBlank(trans.getTelephone())) phonedefault = trans.getTelephone();

			emaildefault = emaildefault.trim().replace(" ","");
			phonedefault = phonedefault.trim().replace(" ","");
			trans.generateNewTri();	
			if(getpartnerCodeMinitrans().equals(trans.getPartnerCode())) {
				log.info("----- SAVE getpartnerCodeMinitrans ------");
				trans.setStatusTrans(TransactionStatus.TO_CONFIRM);

				Partners partner = partnerRepository.findByPartcode(trans.getPartnerCode());
				try {
					trans.setReportdata(printRecu(partner.getPartname(), trans));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				trans = repository.save(trans);
				eve = bkeveRepository.save(eve);

				if(trans.getStatusTrans().equals(TransactionStatus.TO_CONFIRM) || trans.getStatusTrans().equals(TransactionStatus.SUCCESS)){
					datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
				}
			}
			else {
				//DECOMMENTER*** TransactionsResponse deposit = mavianceProcessServices.processQuoteAndPaiement(trans, operator, phonedefault, emaildefault);
				TransactionsResponse deposit = null;
				if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), deposit.getCodeResponse())){
					CollectionResponse collectionResponse = deposit.getCollection();
					if(collectionResponse != null){

						if(collectionResponse.getStatus() != null){

							if(CollectionResponse.StatusEnum.SUCCESS.equals(collectionResponse.getStatus())) {
								log.info("-----SUCCESS------");
								trans.setStatusTrans(TransactionStatus.SUCCESS);
								trans.setExceptionCode(PayPartnerExceptionCode.EXCEPTION_PARTNER);
								trans.setExceptionlib(deposit.getError());
								deposit.setReportdata(generateReportTransactions.generateBill(trans, operators));
								trans.setReportdata(Base64.getEncoder().encodeToString(deposit.getReportdata()));
							}else if(CollectionResponse.StatusEnum.PENDING.equals(collectionResponse.getStatus())) {
								log.info("-----TO_CONFIRM------");
								trans.setStatusTrans(TransactionStatus.TO_CONFIRM);
								trans.setExceptionCode(PayPartnerExceptionCode.EXCEPTION_PARTNER);
								trans.setExceptionlib(deposit.getError());
								deposit.setReportdata(generateReportTransactions.generateBill(trans, operators));
								trans.setReportdata(Base64.getEncoder().encodeToString(deposit.getReportdata()));
							}else if(CollectionResponse.StatusEnum.ERRORED.equals(collectionResponse.getStatus())) {
								trans.setStatusTrans(TransactionStatus.ERROR);
								trans.setExceptionCode(PayPartnerExceptionCode.EXCEPTION_PARTNER);
								trans.setExceptionlib(deposit.getError());
							}else if(CollectionResponse.StatusEnum.REVERSED.equals(collectionResponse.getStatus())) {
								trans.setStatusTrans(TransactionStatus.REVERSED);
								trans.setExceptionCode(PayPartnerExceptionCode.EXCEPTION_PARTNER);
								trans.setExceptionlib(deposit.getError());
							}else if(CollectionResponse.StatusEnum.UNDERINVESTIGATION.equals(collectionResponse.getStatus())) {
								trans.setStatusTrans(TransactionStatus.UNDERINVESTIGATION);
								trans.setExceptionCode(PayPartnerExceptionCode.EXCEPTION_PARTNER);
								trans.setExceptionlib(deposit.getError());
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

				}else if(StringUtils.equalsIgnoreCase(ResponseHolder.AUTHENTICATION_FAIL, deposit.getCodeResponse())){
					trans.setExceptionCode(PayPartnerExceptionCode.AUTHENTICATION_FAIL);
					trans.setExceptionlib(PayPartnerExceptionCode.AUTHENTICATION_FAIL.getValue());
				}else if(StringUtils.equalsIgnoreCase(ResponseHolder.SEND_TRANSFER_FAIL, deposit.getCodeResponse())){
					trans.setExceptionCode(PayPartnerExceptionCode.SEND_TRANSFER_FAIL);
					trans.setExceptionlib(PayPartnerExceptionCode.SEND_TRANSFER_FAIL.getValue());
				}

				log.info("-----SAVE------");
				trans = repository.save(trans);
				eve = bkeveRepository.save(eve);

				if(trans.getStatusTrans().equals(TransactionStatus.TO_CONFIRM) || trans.getStatusTrans().equals(TransactionStatus.SUCCESS)){
					trans.setReportdata(Base64.getEncoder().encodeToString(deposit.getReportdata()));
					datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
					return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
				}
			}	

		} else {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.VALIDATION_CBS_FAIL, "", datas), HttpStatus.PRECONDITION_FAILED);
		}

		datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
		return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

	}



	@Override
	public ResponseEntity<DataResponseDTO> confirmationTransaction(ConfirmationTransDto data){
		String response = "KO";
		try {
			log.info("Verifier l'etat du service ");
			String appServiceActif = getapplicationServiceActif();
			if(StringUtils.isBlank(appServiceActif) || !"UP".equals(appServiceActif))
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.DigitalFirstServiceSuspended, "", response), HttpStatus.NOT_IMPLEMENTED);

			if(data == null) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, " ", response), HttpStatus.PRECONDITION_FAILED);
			}
			if(StringUtils.isBlank(data.getId())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (ID Transaction)", response), HttpStatus.PRECONDITION_FAILED);
			}
			/*
			if(StringUtils.isBlank(data.getComment())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (Motif Confirmation)", response), HttpStatus.PRECONDITION_FAILED);
			}
			 */
			if(StringUtils.isBlank(data.getUserConfirmation())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (User Confirmation)", response), HttpStatus.PRECONDITION_FAILED);
			}
			if(StringUtils.isBlank(data.getPartnerCode())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (Partner code)", response), HttpStatus.PRECONDITION_FAILED);
			}			

			Optional<Transactions> trs = this.repository.findById(data.getId());
			Transactions trans = null;
			if(trs.isPresent()){
				trans = trs.get();
			}

			if(trans == null) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " Transaction", response), HttpStatus.OK);
			}
			if(TransactionStatus.SUCCESS.equals(trans.getStatusTrans()) || TransactionStatus.INVALID.equals(trans.getStatusTrans())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, " Statut incorrecte pour la confirmation", response), HttpStatus.OK);
			}

			if(StatusEnum.SUCCESS.equals(data.getStatusConfirmation())) {
				log.info("-----SUCCESS------");
				trans.setStatusTrans(TransactionStatus.SUCCESS);
				trans.setVeriCode(data.getUserConfirmation());
				trans.setUserConfirmation(data.getUserConfirmation());
				trans.setStatus(data.getStatusConfirmation());
				trans.setComment(data.getComment());
				trans.setDateConfirmation(new Date());
			}else {
				log.info("-----ERRORED------");
				trans.setStatusTrans(TransactionStatus.INVALID);

				if(TransactionMode.VIREMENT_EFIRST.equals(trans.getTransactionMode())) {
					log.info("----- TransactionMode.VIREMENT_EFIRST ------");					
					DataResponseDTO responsePostEntries = reverseTransaction(trans);
					if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), responsePostEntries.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), responsePostEntries.getCode()) ){
						log.info("----- reverseTransaction SUCESS ------");					
						trans.setVeriCode(data.getUserConfirmation());
						trans.setUserConfirmation(data.getUserConfirmation());
						trans.setStatus(data.getStatusConfirmation());
						trans.setComment(data.getComment());
						trans.setDateConfirmation(new Date());
						trans.setRessourcecancel("PARTNER");

						trans.setUsercancel(data.getUserConfirmation());
						trans.setCancelReason(data.getComment());
						trans.setDateCancel(new Date());					
					}
					else {
						log.info("----- reverseTransaction ECHEC ------");					
						return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, " (Posting des ecritures comptables)", response), HttpStatus.NOT_IMPLEMENTED);
					}
				}	
				else {
					log.info("----- ! TransactionMode.VIREMENT_EFIRST ------");					
					trans.setVeriCode(data.getUserConfirmation());
					trans.setUserConfirmation(data.getUserConfirmation());
					trans.setStatus(data.getStatusConfirmation());
					trans.setComment(data.getComment());
					trans.setDateConfirmation(new Date());
					trans.setRessourcecancel("PARTNER");

					trans.setUsercancel(data.getUserConfirmation());
					trans.setCancelReason(data.getComment());
					trans.setDateCancel(new Date());
				}
			}	

			log.info("-----SAVE------");
			trans = repository.save(trans);
			if (trans != null) {
				response = "OK";
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "OK", response), HttpStatus.OK);
			}
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", response), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), response), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}




	@Override
	public ResponseEntity<DataResponseDTO> cancelTransactions(CancelTransDto data){
		String response = "";

		if(data == null) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, " ", response), HttpStatus.PRECONDITION_FAILED);
		}
		if(StringUtils.isBlank(data.getId())) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (ID Transaction)", response), HttpStatus.PRECONDITION_FAILED);
		}
		if(StringUtils.isBlank(data.getCancelReason())) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (Cancel reason)", response), HttpStatus.PRECONDITION_FAILED);
		}
		if(StringUtils.isBlank(data.getUsercancel())) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (User cancel)", response), HttpStatus.PRECONDITION_FAILED);
		}
		if(StringUtils.isBlank(data.getPartnerCode())) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " (Partner code)", response), HttpStatus.PRECONDITION_FAILED);
		}

		Optional<Transactions> trs = this.repository.findById(data.getId());
		Transactions trans = null;

		if(trs.isPresent()){
			trans = trs.get();
			if(TransactionStatus.SUCCESS.equals(trans.getStatusTrans()) || TransactionStatus.INVALID.equals(trans.getStatusTrans()) || TransactionStatus.CANCEL.equals(trans.getStatusTrans()) || TransactionStatus.ERROR.equals(trans.getStatusTrans()) || TransactionStatus.EXTOURNE.equals(trans.getStatusTrans())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, " (Statut non conforme de la transaction)", response), HttpStatus.PRECONDITION_FAILED);
			}
			if(TransactionMode.VIREMENT_EFIRST.equals(trans.getTransactionMode())) {
				DataResponseDTO responsePostEntries = reverseTransaction(trans);
				if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), responsePostEntries.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), responsePostEntries.getCode()) ){
					trans.setStatusTrans(TransactionStatus.CANCEL);
					trans.setUsercancel(data.getUsercancel());
					trans.setCancelReason(data.getCancelReason());
					trans.setDateCancel(new Date());
					trans.setRessourcecancel("AFB");
					trans = repository.save(trans);
					response = "OK";
					return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "OK", response), HttpStatus.OK);
				}
				else {
					return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, " (Posting des ecritures comptables)", response), HttpStatus.NOT_IMPLEMENTED);
				}
			}	
			else {
				trans.setStatusTrans(TransactionStatus.CANCEL);
				trans.setUsercancel(data.getUsercancel());
				trans.setCancelReason(data.getCancelReason());
				trans.setDateCancel(new Date());
				trans.setRessourcecancel("AFB");
				trans = repository.save(trans);
				response = "OK";
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "OK", response), HttpStatus.OK);
			}

		}else {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " (Transaction)", response), HttpStatus.PRECONDITION_FAILED);
		}
	}



	public DataResponseDTO reverseTransaction(Transactions tx) {
		String response = "";
		List<Bkmvti> ecritures = new ArrayList<Bkmvti>();
		String ope = "";
		String accountLiaison = "";
		String codeUser = "";
		int numEc = 1;

		String debitAccount = tx.getAccountCredit();
		String creditAccount = tx.getAccountDebit();

		try {

			if(!TransactionMode.VIREMENT_EFIRST.equals(tx.getTransactionMode())) 
				return new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, " (Type d'operation non conforme)", response);

			accountLiaison = getapplicationServiceAccountLiaison();			
			if(StringUtils.isBlank(accountLiaison))
				return new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Compte de liaison", response);

			codeUser = getapplicationServiceCodeUser();
			if(StringUtils.isBlank(codeUser))
				return new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Code utilisateur", response);

			ope = getapplicationServiceOpeNivellement();
			if(StringUtils.isBlank(ope))
				return new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Code opération nivellement", response);

			Date dco = getDco();

			Bkcom bkcomLiaisonCredit = null;
			Bkcom bkcomLiaisonDebit = null;
			if(!debitAccount.split("-")[0].equals(creditAccount.split("-")[0])) {
				log.info("************* get details bkcomLiaisonDebit et bkcomLiaisonCredit *****************");
				ResponseBkcom resLiaisonCre = CoreBankingServices.getaccountinfo(keysecurity, host, debitAccount.split("-")[0], accountLiaison);
				bkcomLiaisonCredit = resLiaisonCre.getData().isEmpty() ? null : resLiaisonCre.getData().get(0);

				ResponseBkcom resLiaisonDeb = CoreBankingServices.getaccountinfo(keysecurity, host, creditAccount.split("-")[0], accountLiaison);
				bkcomLiaisonDebit = resLiaisonDeb.getData().isEmpty() ? null : resLiaisonDeb.getData().get(0);
				if(bkcomLiaisonDebit == null || bkcomLiaisonCredit == null) {
					return new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " Compte de liaison dans une agence", response);
				}
			}

			//Check eve dans le CBS
			Bkeve eve = null;

			String[] eves = tx.getReferencenumber().split("-");
			if(eves.length == 1){
				Optional<Bkeve> opeve = this.bkeveRepository.findById(tx.getReferencenumber());
				if(opeve.isPresent()){
					eve = opeve.get();
				}else {
					return new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " (Transaction)", response);
				}
			}else {
				Optional<Bkeve> opeve = this.bkeveRepository.findById(eves[0]);
				if(opeve.isPresent()){
					eve = opeve.get();
				}else {
					return new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " (Transaction)", response);
				}
			}

			String pie = "PP"+new SimpleDateFormat("ddMMyy").format(tx.getDco()) + RandomStringUtils.randomAlphanumeric(3);
			String lib = tx.getPartnerCode() + "/" + new SimpleDateFormat("ddMMyyHHmm").format(tx.getValidfrom()) + "/" + tx.getReferenceOperator();

			ResponseBkcom resDeb = CoreBankingServices.getaccountinfo(keysecurity, host, debitAccount.split("-")[0], debitAccount.split("-")[1]);
			Bkcom bkcomDeb =  resDeb.getData().isEmpty() ? null : resDeb.getData().get(0);
			log.info("************* niveau 0 bkcomDeb.getNcp() ***************** : " + bkcomDeb.getNcp());

			ResponseBkcom resCre = CoreBankingServices.getaccountinfo(keysecurity, host, creditAccount.split("-")[0], creditAccount.split("-")[1]);
			Bkcom bkcomCre =  resCre.getData().isEmpty() ? null : resCre.getData().get(0);
			log.info("************* niveau 0 bkcomCre.getNcp() ***************** : " + bkcomCre.getNcp());

			ecritures.add( new Bkmvti(bkcomDeb.getAge(), bkcomDeb.getDev(), bkcomDeb.getCha(), bkcomDeb.getNcp(), bkcomDeb.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomDeb.getClc(), dco, null, eve.getDva1(), tx.getAmount(), "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomDeb.getAge(), bkcomDeb.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
			ecritures.add( new Bkmvti(bkcomCre.getAge(), bkcomCre.getDev(), bkcomCre.getCha(), bkcomCre.getNcp(), bkcomCre.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomCre.getClc(), dco, null, eve.getDva2(), tx.getAmount(), "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomCre.getAge(), bkcomCre.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
			if(!debitAccount.split("-")[0].equals(creditAccount.split("-")[0])) {
				ecritures.add( new Bkmvti(bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), bkcomLiaisonCredit.getCha(), bkcomLiaisonCredit.getNcp(), bkcomLiaisonCredit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonCredit.getClc(), dco, null, eve.getDva2(), tx.getAmount(), "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
				ecritures.add( new Bkmvti(bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), bkcomLiaisonDebit.getCha(), bkcomLiaisonDebit.getNcp(), bkcomLiaisonDebit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonDebit.getClc(), dco, null, eve.getDva1(), tx.getAmount(), "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
			}   


			log.info("************* ecritures.size() ***************** : " + ecritures.size());
			try {
				DataResponseDTO responsePostEntries = CoreBankingServices.postAccountingentries(keysecurity, host, ecritures);				
				return responsePostEntries;
			} catch (Exception e) {
				return new DataResponseDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), response);
			}

		} catch (Exception e) {
			return new DataResponseDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), response);
		}

	}



	@SuppressWarnings("unused")
	@Override
	public ResponseEntity<PaymentResponse> virementTransactions(PaymentDetails paymentDetails){
		String accountDebit = "";

		log.info("payService : PaymentDetails ? "+paymentDetails);
		Transactions trans = new Transactions();
		Operators operator;

		// Rechercher abonnement
		if(paymentDetails.getDetails()!=null && paymentDetails.getPartnerCode().equalsIgnoreCase(partnerCodeMinitrans)){
			log.info("payService : MINITRANS true");
			operator = operatorRepository.findByCustomerReferencePartnerAndPartnerCode(paymentDetails.getKey(), partnerCodeMinitrans);
		}
		else if(paymentDetails.getDetails()!=null && paymentDetails.getPartnerCode().equalsIgnoreCase(partnerCodeTrustPayway)){
			log.info("payService : TRUPAY true");
			//*** operator = operatorRepository.findByCustomerReferencePartnerAndPartnerCode(paymentDetails.getKey(), partnerCodeTrustPayway);
			log.info("paymentDetails.getNiu() : " + paymentDetails.getNiu()); 
			log.info("paymentDetails.getKey() : " + paymentDetails.getKey());
			log.info("paymentDetails.getKey() crypté : " + AfbEncryptionDecryption.encrypt(paymentDetails.getKey(), "suscriber"));
			operator = operatorRepository.findByCustomerNiuPartnerAndKeyOperatorAndPartnerCode(paymentDetails.getNiu(), AfbEncryptionDecryption.encrypt(paymentDetails.getKey(), "suscriber"), partnerCodeTrustPayway);
			log.info("infos operator : " + operator.getClientName());

		}
		else{
			log.info("payService : else");
			operator = operatorRepository.findByCustomerReferencePartner(paymentDetails.getKey());
		}

		//*** if(operator != null) accountDebit = operator.getAccounts().get(0).getDatavalue();
		log.info("payService : operator Verifications");

		Partners partner = partnerRepository.findByPartcode(paymentDetails.getPartnerCode());
		try{ 			
			// Verifications
			operator = makeCheck(paymentDetails, operator, partner);
			accountDebit = operator.getSelectedAccount();

			// Traiter et enregistrer la transaction)
			trans = paymentTransaction(paymentDetails, operator, partner, accountDebit, TransactionMode.VIREMENT_PARTENAIRE, TransactionStatus.SUCCESS);
			log.info("payService : subspartner in service "+operator);
			log.info("PAYMENT OK ");

			// Envoyer OTP ou Infos Succes transaction
			try {
				String sms = "";
				if(partner.isConfirmTransaction()) {
					sms = AlerteServices.sendMessageOtp(keysecurity, urlSms, trans, operator, partner); 
				}
				else {
					sms = AlerteServices.sendMessageTrans(keysecurity, urlSms, trans, operator, partner);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}catch(PayPartnerException ppe){
			log.info("PayPartnerException : "+ppe.getCode()+" / "+ppe.getMessage());
			trans = failedTransaction(paymentDetails, operator, partner, accountDebit, applicationCodeOperationVirement, ppe);
			return new ResponseEntity<PaymentResponse>(new PaymentResponse(trans, Integer.toString(HttpStatus.PRECONDITION_FAILED.value())), HttpStatus.PRECONDITION_FAILED);			
		}catch(Exception e){
			log.info("Exception : "+e.getMessage()+" / "+e.getCause());
			e.printStackTrace();

			trans = failedTransactionException(paymentDetails, operator, partner, accountDebit, getapplicationCodeOperationVirement());
			return new ResponseEntity<PaymentResponse>(new PaymentResponse(trans, Integer.toString(HttpStatus.PRECONDITION_FAILED.value())), HttpStatus.PRECONDITION_FAILED);
		}

		// Retourner details transaction
		return new ResponseEntity<PaymentResponse>(new PaymentResponse(trans, Integer.toString(HttpStatus.OK.value())), HttpStatus.OK);

	}



	@SuppressWarnings("unlikely-arg-type")
	private Operators makeCheck(PaymentDetails paymentDetails, Operators operator, Partners partner) throws Exception{
		log.info("IN MAKECHECK ");

		log.info("Verifier l'etat du service ");
		if(!"UP".equals(getapplicationServiceActif()))
			throw new PayPartnerException(PayPartnerExceptionCode.DigitalFirstServiceSuspended, PayPartnerExceptionCode.DigitalFirstServiceSuspended.getValue(), PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceAccountLiaison))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Account Liaison", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceAccountCommission))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Account Commission", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceAccountTva))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Account TVA", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceCodeUser))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Code user", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationCodeOperationVirement))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Code operation", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceTransactionNature))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Nature operation", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(transactionopposition))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Code opposition sur les comptes", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceValiditeOtp))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Validite OTP", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceSizeOtp))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Taille OTP", PayPartnerExceptionCategory.METIER);
		if(StringUtils.isBlank(applicationServiceTypeOtp))
			throw new PayPartnerException(PayPartnerExceptionCode.NULL_PROPERTY, PayPartnerExceptionCode.NULL_PROPERTY.getValue()+" Type OTP", PayPartnerExceptionCategory.METIER);


		log.info("Verifier l'abonnement ");
		// Verifier l'abonnement
		if(operator == null) 
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberNotFound, PayPartnerExceptionCode.SubscriberNotFound.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);

		// Verifier statut abonne
		log.info("Verifier statut partenaire ");
		if(partner == null) 
			throw new PayPartnerException(PayPartnerExceptionCode.PartnerNotIdentified, PayPartnerExceptionCode.PartnerNotIdentified.getValue(), PayPartnerExceptionCategory.METIER);

		if(StringUtils.isBlank(operator.getPartnerCode())) 
			throw new PayPartnerException(PayPartnerExceptionCode.PartnerNotIdentified, PayPartnerExceptionCode.PartnerNotIdentified.getValue(), PayPartnerExceptionCategory.METIER);

		log.info("Verifier existence de la transaction paymentDetails.getTrxID() " + paymentDetails.getTrxID());
		// Verifier existence de la transaction
		List<Transactions> transactions = repository.findByPartnerTrxIDAndPartnerCode(paymentDetails.getTrxID(), partner.getPartcode());
		log.info("Verifier existence de la transaction transactions.get(0).getPartnerTrxID() " + transactions.size());
		if(!transactions.isEmpty())
			throw new PayPartnerException(PayPartnerExceptionCode.TransactionIDAlreadyExist, PayPartnerExceptionCode.TransactionIDAlreadyExist.getValue(), PayPartnerExceptionCategory.METIER);



		// Verifier statut abonne
		if(!partner.isActif()) 
			throw new PayPartnerException(PayPartnerExceptionCode.PartnerServiceSuspended, PayPartnerExceptionCode.PartnerServiceSuspended.getValue(), PayPartnerExceptionCategory.METIER);

		log.info("Verifier statut abonnement ");
		// Verifier statut abonne
		if(PayPartnerStatutSubscriber.SUSPENDU.equals(operator.getOperatorStatus())) 
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberSuspended, PayPartnerExceptionCode.SubscriberSuspended.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);

		if(PayPartnerStatutSubscriber.WAITING.equals(operator.getOperatorStatus()))  
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberWaitingValidation, PayPartnerExceptionCode.SubscriberWaitingValidation.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);

		log.info("Verifier le montant ");
		// Verifier solde
		if(paymentDetails.getAmount()==null || paymentDetails.getAmount() < 0d || (paymentDetails.getAmount() - paymentDetails.getAmount().longValue() != 0d))
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberInvalidAmount, PayPartnerExceptionCode.SubscriberInvalidAmount.getValue(), PayPartnerExceptionCategory.METIER);

		log.info("Verifier solde ");
		// Verifier solde
		String accountCustomer = "";
		Double sinAccount = 0d;
		int cptAccount = 0;
		List<String> accounts = new ArrayList<String>();
		for(String ac : operator.getAccounts().split("/")) {
			accounts.add(ac);
		}

		for(String ac : accounts) {
			cptAccount++;

			ResponseBkcom resLiaisonDeb = CoreBankingServices.getaccountinfo(keysecurity, host, ac.split("-")[0], ac.split("-")[1]);
			Bkcom bkcomOpe = resLiaisonDeb.getData().isEmpty() ? null : resLiaisonDeb.getData().get(0);
			if(bkcomOpe != null) {
				log.info("Verifier si compte ferme ");
				if("N".equals(bkcomOpe.getIfe()) && "N".equals(bkcomOpe.getCfe())) {
					ResponseData3 dsDTO = CoreBankingServices.getbalance(keysecurity, host, ac);
					if(ResponseHolder.SUCESS.equalsIgnoreCase(dsDTO.getCode())){
						Map<String, Object> data = dsDTO.getData();
						try {
							String soldeAccountTransitString = (String) data.get("sin");
							sinAccount = Double.valueOf(soldeAccountTransitString);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sinAccount = Double.valueOf(sinAccount.intValue());

						if(cptAccount == accounts.size() && (sinAccount - paymentDetails.getAmount()) < 0d)
							throw new PayPartnerException(PayPartnerExceptionCode.BankInsufficientBalance, PayPartnerExceptionCode.BankInsufficientBalance.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);
						else if((sinAccount - paymentDetails.getAmount()) >= 0d) {
							accountCustomer = ac;
							break;
						}
					}
				}
			}			
		}

		if(StringUtils.isBlank(accountCustomer))
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberInvalidAccount, PayPartnerExceptionCode.SubscriberInvalidAccount.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);
		operator.setSelectedAccount(accountCustomer);

		log.info("Verifier  opposition ");
		// Verifier  opposition
		ResponseData2 dsDTO = CoreBankingServices.getoppoCompte(keysecurity, host, accountCustomer);
		if(ResponseHolder.SUCESS.equalsIgnoreCase(dsDTO.getCode())){
			for(String o : transactionopposition.split(";")) {
				if(dsDTO.getData().contains(o)) {
					throw new PayPartnerException(PayPartnerExceptionCode.BankBlockingDebitAccount, PayPartnerExceptionCode.BankBlockingDebitAccount.getValue(), PayPartnerExceptionCategory.METIER);
				}
			}
		}

		/*		
		Fpprofil profil = subspartner.getProfil();

		log.info("Verifier le montant maximal autorise ");
		// Verifier le montant maximal autorise
		if(profil.getAmountLimitPayTrans() != 0d && paymentDetails.getAmount() > profil.getAmountLimitPayTrans()) 
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberAmountExceedMaxAllowed, PayPartnerExceptionCode.SubscriberAmountExceedMaxAllowed.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);

		log.info("Verifier les plafonds journaliers, hebdomadaires et mensuels ");
		// Verifier les plafonds journaliers, hebdomadaires et mensuels
		// Plafond Journalier
		if(Boolean.FALSE.equals(subspartner.islimit(TypeOperation.PAYMENT, paymentDetails.getAmount(), profil.getAmountLimitPayDay()))) 
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberLimitPaymentDayReached, PayPartnerExceptionCode.SubscriberLimitPaymentDayReached.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);

		// Plafond Hebdomadaire
		if(Boolean.FALSE.equals(subspartner.islimitWeekHebd(TypeOperation.PAYMENT, paymentDetails.getAmount(), profil.getAmountLimitPayWeek(), "W")))
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberLimitPaymentWeekReached, PayPartnerExceptionCode.SubscriberLimitPaymentWeekReached.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);

		// Plafond Mensuel
		if(Boolean.FALSE.equals(subspartner.islimitWeekHebd(TypeOperation.PAYMENT, paymentDetails.getAmount(), profil.getAmountLimitPayMonth(), "M")))
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberLimitPaymentMonthReached, PayPartnerExceptionCode.SubscriberLimitPaymentMonthReached.getValue(), PayPartnerExceptionCategory.METIER);
		log.info("payService : subspartner checked "+subspartner);
		log.info("CHECK OK ");
		 */

		return operator;
	}


	public Transactions paymentTransaction(PaymentDetails paymentDetails, Operators operator, Partners partner, String accountDebit, TransactionMode transactionMode, TransactionStatus status) throws Exception{

		Bkeve eve = null;
		String ope = "";
		String accountLiaison = "";
		String codeUser = "";          

		accountLiaison = applicationServiceAccountLiaison;
		codeUser = applicationServiceCodeUser;
		ope = applicationCodeOperationVirement;

		Transactions trans = new Transactions();
		List<Transactions> transactions = repository.findByPartnerTrxIDAndPartnerCode(paymentDetails.getTrxID(), partner.getPartcode());
		if(!transactions.isEmpty())
			throw new PayPartnerException(PayPartnerExceptionCode.TransactionIDAlreadyExist, PayPartnerExceptionCode.TransactionIDAlreadyExist.getValue(), PayPartnerExceptionCategory.METIER);

		// fabrication de la transaction
		trans = buildTransaction(paymentDetails, operator, partner, accountDebit, applicationCodeOperationVirement, status);	

		// Si le montant de la transaction est nul pour les transactions différentes de souscription
		if(((trans.getAmount()==0d && trans.getCommissions()==0d) || trans.getTtc()==0d) && !trans.getTransactionMode().name().equalsIgnoreCase(TransactionMode.SUBSCRIPTION.name())) 
			throw new PayPartnerException(PayPartnerExceptionCode.SubscriberInvalidAmount, PayPartnerExceptionCode.SubscriberInvalidAmount.getValue(), PayPartnerExceptionCategory.SUBSCRIBER);

		// Si la transaction reqiert l'OTP, le generer et definir sa validite
		if(partner.isConfirmTransaction()){
			trans = buildOTP(trans);
		}

		Object details = paymentDetails.getDetails();
		// Il faut prendre en compte les details de l'operation
		if(details!=null){
			log.info("DETAILS NOT NULL ");
			//***********************

			if(partner.getPartcode().equals(partnerCodeDouane)) {
				log.info("PARTNER = DOUANE " + partner.getPartcode());
				// Traitement de AP de la douane
				// log.info("DETAILS instance de AuthResponse ");
				String det;
				log.info("DETAILSTO STRING : "+details.toString());
				ModelMapper mapper = new ModelMapper();
				AuthResponse unpaidNotices = mapper.map(details, AuthResponse.class);
				log.info("OK : "+unpaidNotices.toString());
				//					AuthResponse unpaidNotices = ((AuthResponse) details);
				List<UnpaidNotice> resultData = unpaidNotices.getResultData();
				// Ajouter les details des AP après l'enregistrement de la transaction
				trans.setDetails(getDetailsAP(resultData));
				//					trans.setDetails(getNumAP(authR.getResultData()));
				// Generer la reference cote banque du paiement a transmettre a la douane (CODEBANK+DATE+N°SERIE)
				//					trans.setRef(generateRefPaiement(FpHelper.BANK_CODE));
				trans.setReference(getTaxPayerNumber(resultData));
				log.info("MAJ DETAILS + REFERENCE ");
				// Ne pas generer d'evenement
				//					eve = virementPayments.makeBkeve(trans, param.getCodeOperation(), param.getCodeUtil(), param.getNcpCommissions(), param.getNcpTVA(), param.getNumCompteLiaison(), param.getNat(), trans.getAbonne().getPartner().getPartcode());
			}
			if(partner.getPartcode().equals(partnerCodeSprintpay)) {
				log.info("PARTNER = SPRINT ");
				// Recuperation des donnees de ventillation
				log.info("Details du paiement SPRINT PAY ");
				String ben;
				log.info("DETAILSTO STRING : "+details.toString());
				ObjectMapper mapperBen = new ObjectMapper();
				List<Beneficiaire> beneficiaires = mapperBen.convertValue(details, new TypeReference<List<Beneficiaire>>(){});
				String benDetails = getDetailsBeneficiaires(beneficiaires);
				log.info("BENEFICIAIRES DETAILS : "+benDetails);
				// Ajouter les details des beneficiaires après l'enregistrement de la transaction
				trans.setDetails(benDetails);

				//					trans.setRef(getTaxPayerNumber(resultData));
				log.info("MAJ DETAILS + REFERENCE ");
				// Verifier que la somme des montants des beneficiares correspond au montant total de la transaction
				if(isVentillationAmountOK(trans.getAmount(), beneficiaires)){
					// 
				}
				else throw new PayPartnerException(PayPartnerExceptionCode.TransactionDetailsNotValid, PayPartnerExceptionCode.TransactionDetailsNotValid.getValue(), PayPartnerExceptionCategory.METIER);

			}
			if(partner.getPartcode().equals(partnerCodeTrustPayway)) {
				log.info("PARTNER = TRUPAY " + partner.getPartcode());
				// Traitement de bills de la douane
				log.info("DETAILSTO STRING : "+details.toString());
				ModelMapper tmapper = new ModelMapper();
				TrustDetailsObject tto = tmapper.map(details, TrustDetailsObject.class);
				log.info("OK : "+tto.toString());
				List<TrustBill> listBill = tto.getListBill();
				// Controle des montants des differentes factures et du montant total

				// Ajouter les details des bills après l'enregistrement de la transaction
				trans.setDetails(getTrustBillDetails(listBill));
				// Generer la reference cote banque du paiement
				//					trans.setRef(generateRefPaiement(FpHelper.BANK_CODE));
			}
		}

		if(!(trans.getTransactionMode()==TransactionMode.SUBSCRIPTION)){
			log.info("TRANS IN EVE : "+trans.toString());
			// Evenement
			eve = makeBkeve(trans, ope, codeUser, applicationServiceAccountCommission, applicationServiceAccountTva, accountLiaison, applicationServiceTransactionNature, operator, partner);
			if(eve != null) {

				try {
					log.info("-----SAVE------");
					//*** trans = repository.save(trans);
					//*** eve.setTransaction(trans);
					//*** eve = bkeveRepository.save(eve);

					log.info("eve.toString() " + eve.toString());
					System.out.println("=====================SAVING BKEVE========================"+ new Date());
					BkeveDTO dto = CoreBankingServices.postEvent(keysecurity, host, eve);
					System.out.println("=====================end==uriEvent========================"+ new Date());

					if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), dto.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), dto.getCode())){
						eve = dto.getEve();
						if(partner.getPartcode().equals(partnerCodeTrustPayway)) trans.setStatusTrans(TransactionStatus.SUCCESS);
						else trans.setStatusTrans(TransactionStatus.PROCESSING);
						eve.setDco(getDco());
						eve = this.bkeveRepository.save(eve);

						trans.setReferencenumber(eve.getId());
						trans.setEveid(eve.getEve());
						trans = this.repository.save(trans);   
					}else {
						trans.setStatusTrans(TransactionStatus.FAILED);
						trans.setExceptionCode(PayPartnerExceptionCode.POSTING_CBS_FAIL);
						trans.setExceptionlib(PayPartnerExceptionCode.POSTING_CBS_FAIL.getValue());
						trans = this.repository.save(trans);
					}
				} catch (Exception e) {
					e.printStackTrace();
					/*
					// TODO Auto-generated catch block
					trans.setStatusTrans(TransactionStatus.FAILED);
					trans.setExceptionCode(PayPartnerExceptionCode.BankException);
					trans.setExceptionlib(PayPartnerExceptionCode.BankException.getValue());
					trans = this.repository.save(trans); 
					 */
					throw new PayPartnerException(PayPartnerExceptionCode.BankException, PayPartnerExceptionCode.BankException.getValue(), PayPartnerExceptionCategory.METIER);					
				}
			}
		}

		return trans;

	}



	/**
	 * makeBkeve
	 */
	public Bkeve makeBkeve(Transactions trans, String codeOperation, String user, String ncpCommissions, String ncpTVA, String ncpLiaison, String nat, Operators operator, Partners partner) throws Exception {

		log.info("MAKING BKEVE ");
		log.info("ACCOUNTS ---> DEBIT : "+trans.getAccountDebit()+" / CREDIT : "+trans.getAccountCredit()+" / COMS : "+ncpCommissions+" / TVA : "+ncpTVA+" / LIAISON : "+ncpLiaison);
		String subName = operator.getClientName();
		String subKey = operator.getCustomerNiuPartner();

		Long numEc = 1l;
		Date dco = getDco();
		//String codeOperation = param.getCodeOperation();
		String pdr = trans.getAccountDebit().split("-")[1].substring(7,10);
		Date dva1 = getDateValeurDebit(dco, pdr); //dco; 
		pdr = trans.getAccountCredit().split("-")[1].substring(7,10);
		Date dva2 = getDateValeurCredit(dco, pdr); //dco; 
		//		String natMag = "VIRVERSAUTO";
		String datop = new SimpleDateFormat("ddMMyy").format(new Date());
		String pie = RandomStringUtils.randomNumeric(6);
		String ope = trans.getTypeOperation();
		String fact = TransactionMode.FACTURATION.name();
		String numEve = "";

		String nom = subName.toUpperCase().trim();
		if(nom.length() > 10){
			nom = nom.substring(0,10);
		}

		String lib = "PPAY/"+partner.getPartcode()+"/"+datop+"/"+subKey;


		// Initialisations
		Bkcom bkcomCommissions = null, bkcomTVA = null, bkcomAccountDebit = null, bkcomAccountCredit = null;


		// Si une commission a ete parametre pour l'operation
		if ( trans.getCommissions() > 0 ) {
			log.info("THERE IS COMMISSIONS!!! ");
			// Si le compte des commissions a ete parametre
			if(ncpCommissions != null && !ncpCommissions.isEmpty()){
				ResponseBkcom rsCommissions = CoreBankingServices.getaccountinfo(keysecurity, host, ncpCommissions.split("-")[0], ncpCommissions.split("-")[1]);
				if(rsCommissions == null) {
					throw new Exception("Impossible de trouver le compte des commissions");
				}			
				bkcomCommissions =  rsCommissions.getData().isEmpty() ? null : rsCommissions.getData().get(0);
				if(bkcomCommissions == null) {
					throw new Exception("Impossible de trouver le compte des commissions");
				}
			}


			// Si le numero de cpte TVA a ete parametre
			if(ncpTVA != null && !ncpTVA.isEmpty()){
				ResponseBkcom rsTVA = CoreBankingServices.getaccountinfo(keysecurity, host, ncpTVA.split("-")[0], ncpTVA.split("-")[1]);
				if(rsTVA == null) {
					throw new Exception("Impossible de trouver le compte des taxes");
				}			
				bkcomTVA = rsTVA.getData().isEmpty() ? null : rsTVA.getData().get(0);
				if(bkcomTVA == null) {
					throw new Exception("Impossible de trouver le compte des taxes");
				}
			}
		}

		// Recherche du cpte de l'abonne
		String accountDebit = trans.getAccountDebit();
		if(StringUtils.isBlank(accountDebit)) return new Bkeve();
		ResponseBkcom rsAccountDebit = CoreBankingServices.getaccountinfo(keysecurity, host, accountDebit.split("-")[0], accountDebit.split("-")[1]);
		if(rsAccountDebit == null) {
			throw new Exception("Impossible de trouver le compte du client a debiter");
		}			
		bkcomAccountDebit =  rsAccountDebit.getData().isEmpty() ? null : rsAccountDebit.getData().get(0);
		if(bkcomAccountDebit == null) {
			throw new Exception("Impossible de trouver le compte du client a debiter");
		}
		Customer customerAccountDebit = null;
		if(StringUtils.isNotBlank(bkcomAccountDebit.getCli())) {
			ResponseCustomer cust = CoreBankingServices.getcustomerinfo(keysecurity, host, bkcomAccountDebit.getCli());
			customerAccountDebit = cust.getData().isEmpty() ? null : cust.getData().get(0);
		}

		String accountCredit = trans.getAccountCredit();
		if(StringUtils.isBlank(accountCredit)) return new Bkeve();
		ResponseBkcom rsAccountCredit = CoreBankingServices.getaccountinfo(keysecurity, host, accountCredit.split("-")[0], accountCredit.split("-")[1]);
		if(rsAccountCredit == null) {
			throw new Exception("Impossible de trouver le compte du client a crediter");
		}			
		bkcomAccountCredit =  rsAccountCredit.getData().isEmpty() ? null : rsAccountCredit.getData().get(0);
		if(bkcomAccountCredit == null) {
			throw new Exception("Impossible de trouver le compte du client a crediter");
		}
		Customer customerAccountCredit = null;
		if(StringUtils.isNotBlank(bkcomAccountCredit.getCli())) {
			ResponseCustomer cust = CoreBankingServices.getcustomerinfo(keysecurity, host, bkcomAccountCredit.getCli());
			customerAccountCredit = cust.getData().isEmpty() ? null : cust.getData().get(0);
		}




		// Initialisation de l'evenement a retourner
		//		Double amount = trans.getAmount(); MAJ du montant commissions, ttc et taxes
		Bkeve eve = new Bkeve(trans, codeOperation, StringUtil.padText(String.valueOf(numEve), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), bkcomAccountDebit.getDev(), trans.getAmount(), nat, dco, user, trans.getCommissions(), trans.getCommissions(), trans.getTaxes(), trans.getTtc(), dva1, dva2);
		eve.setLib1(lib);
		eve.setLib2(subName);

		// Ajout du debiteur (montant = hors taxe + coms)
		eve.setDebiteur(bkcomAccountDebit.getAge(), bkcomAccountDebit.getDev(), bkcomAccountDebit.getNcp(), bkcomAccountDebit.getSuf(), bkcomAccountDebit.getClc(), bkcomAccountDebit.getCli(), customerAccountDebit.getNom(), customerAccountDebit.getGes(), trans.getAmount() + trans.getCommissions() , trans.getAmount() + trans.getCommissions(), dva1, bkcomAccountDebit.getSde());
		// Ajout du crediteur (montant = hors taxe)
		if(trans.getAmount() > 0d) eve.setCrediteur(bkcomAccountCredit.getAge(), bkcomAccountCredit.getDev(), bkcomAccountCredit.getNcp(), bkcomAccountCredit.getSuf(), bkcomAccountCredit.getClc(), bkcomAccountCredit.getCli(), customerAccountCredit.getNom(), customerAccountCredit.getGes(), trans.getAmount(), trans.getAmount(), dva2, bkcomAccountCredit.getSde());
		else if(bkcomCommissions != null && trans.getCommissions() > 0) eve.setCrediteur(bkcomCommissions.getAge(), bkcomCommissions.getDev(), bkcomCommissions.getNcp(), bkcomCommissions.getSuf(), bkcomCommissions.getClc(), bkcomCommissions.getCli(), bkcomCommissions.getInti(), "   ", trans.getCommissions(), trans.getCommissions(), dva1, bkcomCommissions.getSde());

		// Ecriture Comptable
		// Debit du cpte du client du montant de la transaction
		eve.getEcritures().add( new Bkmvti(bkcomAccountDebit.getAge(), bkcomAccountDebit.getDev(), bkcomAccountDebit.getCha(), bkcomAccountDebit.getNcp(), bkcomAccountDebit.getSuf(), codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountDebit.getClc(), dco, null, dva1, trans.getTtc(), "D",(ope.equals(fact) ? "FRAIS/"+lib : lib), "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountDebit.getAge(), bkcomAccountDebit.getDev(), trans.getTtc(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;
		// Credit du cpte du partenaire du montant de la transaction
		eve.getEcritures().add(new Bkmvti(bkcomAccountCredit.getAge(), bkcomAccountCredit.getDev(), bkcomAccountCredit.getCha(), bkcomAccountCredit.getNcp(), bkcomAccountCredit.getSuf(), codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountCredit.getClc(), dco, null, dva2, trans.getAmount(), "C",lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountCredit.getAge(), bkcomAccountCredit.getDev(), trans.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;

		// Comptes de debit (client) et credit (partenaire) dans des agences differentes
		if(trans.getAmount() > 0d && !bkcomAccountDebit.getAge().equalsIgnoreCase(bkcomAccountCredit.getAge())){

			ResponseBkcom rsAccountLiaisonDebit = CoreBankingServices.getaccountinfo(keysecurity, host, bkcomAccountDebit.getAge(), ncpLiaison);
			Bkcom bkcomAccountLiaisonDebit =  rsAccountLiaisonDebit.getData().isEmpty() ? null : rsAccountLiaisonDebit.getData().get(0);

			ResponseBkcom rsAccountLiaisonCredit = CoreBankingServices.getaccountinfo(keysecurity, host, bkcomAccountCredit.getAge(), ncpLiaison);
			Bkcom bkcomAccountLiaisonCredit =  rsAccountLiaisonCredit.getData().isEmpty() ? null : rsAccountLiaisonCredit.getData().get(0);

			// Credit de la liaison du client du montant
			if(bkcomAccountLiaisonDebit != null) eve.getEcritures().add( new Bkmvti(bkcomAccountLiaisonDebit.getAge(), bkcomAccountLiaisonDebit.getDev(), bkcomAccountLiaisonDebit.getCha(), bkcomAccountLiaisonDebit.getNcp(), bkcomAccountLiaisonDebit.getSuf(), codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountLiaisonDebit.getClc(), dco, null, dva2, trans.getAmount() , "C","LS/"+lib, "O", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountLiaisonDebit.getAge(), bkcomAccountLiaisonDebit.getDev(), trans.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;
			// Debit de la liaison du client du montant
			if(bkcomAccountLiaisonCredit != null) eve.getEcritures().add( new Bkmvti(bkcomAccountLiaisonCredit.getAge(), bkcomAccountLiaisonCredit.getDev(), bkcomAccountLiaisonCredit.getCha(), bkcomAccountLiaisonCredit.getNcp(), bkcomAccountLiaisonCredit.getSuf(),codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountLiaisonCredit.getClc(), dco, null, dva1, trans.getAmount() , "D","LS/"+lib, "O", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountLiaisonCredit.getAge(), bkcomAccountLiaisonCredit.getDev(), trans.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;

			bkcomAccountLiaisonDebit = null;
			bkcomAccountLiaisonCredit = null;
		}
		// Comptes de debit (client) et credit (commissions) dans des agences differentes
		if( trans.getCommissions() > 0 && !bkcomAccountDebit.getAge().equalsIgnoreCase(bkcomCommissions.getAge())){

			ResponseBkcom rsAccountLiaisonDebit = CoreBankingServices.getaccountinfo(keysecurity, host, bkcomAccountDebit.getAge(), ncpLiaison);
			Bkcom bkcomAccountLiaisonDebit =  rsAccountLiaisonDebit.getData().isEmpty() ? null : rsAccountLiaisonDebit.getData().get(0);

			ResponseBkcom rsAccountLiaisonCreditComms = CoreBankingServices.getaccountinfo(keysecurity, host, bkcomCommissions.getAge(), ncpLiaison);
			Bkcom bkcomAccountLiaisonCreditComms =  rsAccountLiaisonCreditComms.getData().isEmpty() ? null : rsAccountLiaisonCreditComms.getData().get(0);


			// Credit de la liaison du client du montant des commissions
			if(bkcomAccountLiaisonDebit != null) eve.getEcritures().add( new Bkmvti(bkcomAccountLiaisonDebit.getAge(), bkcomAccountLiaisonDebit.getDev(), bkcomAccountLiaisonDebit.getCha(), bkcomAccountLiaisonDebit.getNcp(), bkcomAccountLiaisonDebit.getSuf(), codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountLiaisonDebit.getClc(), dco, null,dva2, trans.getCommissions() , "C","LS/COM/"+lib, "O", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountLiaisonDebit.getAge(), bkcomAccountLiaisonDebit.getDev(), trans.getCommissions(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;
			// Debit de la liaison des commissions
			if(bkcomAccountLiaisonCreditComms != null) eve.getEcritures().add( new Bkmvti(bkcomAccountLiaisonCreditComms.getAge(), bkcomAccountLiaisonCreditComms.getDev(), bkcomAccountLiaisonCreditComms.getCha(), bkcomAccountLiaisonCreditComms.getNcp(), bkcomAccountLiaisonCreditComms.getSuf(),codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountLiaisonCreditComms.getClc(), dco, null, dva1, trans.getCommissions() , "D", "LS/COM/"+lib, "O", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountLiaisonCreditComms.getAge(), bkcomAccountLiaisonCreditComms.getDev(), trans.getCommissions(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;

			bkcomAccountLiaisonDebit = null;
			bkcomAccountLiaisonCreditComms = null;
		}
		// Comptes de debit (client) et credit (taxes) dans des agences differentes
		if( trans.getCommissions() > 0 && !bkcomAccountDebit.getAge().equalsIgnoreCase(bkcomTVA.getAge())){

			ResponseBkcom rsAccountLiaisonDebit = CoreBankingServices.getaccountinfo(keysecurity, host, bkcomAccountDebit.getAge(), ncpLiaison);
			Bkcom bkcomAccountLiaisonDebit =  rsAccountLiaisonDebit.getData().isEmpty() ? null : rsAccountLiaisonDebit.getData().get(0);

			ResponseBkcom rsAccountLiaisonCreditTaxes = CoreBankingServices.getaccountinfo(keysecurity, host, bkcomCommissions.getAge(), ncpLiaison);
			Bkcom bkcomAccountLiaisonCreditTaxes =  rsAccountLiaisonCreditTaxes.getData().isEmpty() ? null : rsAccountLiaisonCreditTaxes.getData().get(0);


			// Credit de la liaison du client du montant des commissions
			if(bkcomAccountLiaisonDebit != null) eve.getEcritures().add( new Bkmvti(bkcomAccountLiaisonDebit.getAge(), bkcomAccountLiaisonDebit.getDev(), bkcomAccountLiaisonDebit.getCha(), bkcomAccountLiaisonDebit.getNcp(), bkcomAccountLiaisonDebit.getSuf(), codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountLiaisonDebit.getClc(), dco, null, dva2, trans.getCommissions() , "C","LS/COM/"+lib, "O", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountLiaisonDebit.getAge(), bkcomAccountLiaisonDebit.getDev(), trans.getTaxes(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;
			// Debit de la liaison des commissions
			if(bkcomAccountLiaisonCreditTaxes != null) eve.getEcritures().add( new Bkmvti(bkcomAccountLiaisonCreditTaxes.getAge(), bkcomAccountLiaisonCreditTaxes.getDev(), bkcomAccountLiaisonCreditTaxes.getCha(), bkcomAccountLiaisonCreditTaxes.getNcp(), bkcomAccountLiaisonCreditTaxes.getSuf(),codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomAccountLiaisonCreditTaxes.getClc(), dco, null, dva1, trans.getCommissions() , "D", "LS/TAX/"+lib, "O", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomAccountLiaisonCreditTaxes.getAge(), bkcomAccountLiaisonCreditTaxes.getDev(), trans.getTaxes(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;

			bkcomAccountLiaisonDebit = null;
			bkcomAccountLiaisonCreditTaxes = null;
		}

		// Credit du cpte des commissions du montant de la commission s'il y'en a
		if(bkcomCommissions != null && trans.getCommissions() > 0) eve.getEcritures().add(new Bkmvti(bkcomCommissions.getAge(), bkcomCommissions.getDev(), bkcomCommissions.getCha(), bkcomCommissions.getNcp(), bkcomCommissions.getSuf(), codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomCommissions.getClc(), dco, null, dva2, trans.getCommissions(), "C", "COM/"+lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomCommissions.getAge(), bkcomCommissions.getDev(), trans.getCommissions(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;

		// Credit du cpte des taxes du montant de la taxe s'il y'en a
		if(bkcomTVA != null && trans.getTaxes() > 0) eve.getEcritures().add(new Bkmvti(bkcomTVA.getAge(), bkcomTVA.getDev(), bkcomTVA.getCha(), bkcomTVA.getNcp(), bkcomTVA.getSuf(), codeOperation, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, user, eve.getEve(), bkcomTVA.getClc(), dco, null, dva2, trans.getTaxes(), "C", "TAX/"+lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomTVA.getAge(), bkcomTVA.getDev(), trans.getTaxes(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) );  numEc++;

		// On libere les variables
		if(bkcomAccountCredit != null)  bkcomAccountCredit = null;
		if(bkcomAccountDebit != null) bkcomAccountDebit = null;
		if(bkcomCommissions != null)  bkcomCommissions = null;
		if(bkcomTVA != null) bkcomTVA = null;

		eve.setDco(dco);
		eve.setDva1(dva1);
		eve.setDva2(dva2);
		eve.setDou(dco);
		eve.setTransaction(trans);
		log.info("MAKING BKEVE OK");

		return eve;

	}



	public Transactions failedTransaction(PaymentDetails paymentDetails, Operators operator, Partners partner, String accountDebit, String ope, PayPartnerException ppe){
		try {
			// Creation de la transaction en echec		
			log.info("Creation de la transaction en echec");
			Transactions trans = buildTransaction(paymentDetails, operator, partner, accountDebit, ope, TransactionStatus.FAILED);	
			log.info("buildTransaction effectué");
			trans.setExceptionCode(ppe.getCode());
			trans.setExceptionlib(ppe.getMessage());
			if(StringUtils.isBlank(accountDebit)) trans.setAccountDebit("XXXXX-XXXXXXXXXXX-XX");
			//*** trans.setOtp(Boolean.FALSE);
			//*** operator.cancelLimit(transactionMode, trans.getAmount());

			// Enregistrement de la transaction en echec
			trans = this.repository.save(trans);
			log.info("Enregistrement de la transaction en echec");
			return  trans;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public Transactions failedTransactionException(PaymentDetails paymentDetails, Operators operator, Partners partner, String accountDebit, String ope){
		// Creation de la transaction en echec		
		Transactions trans = buildTransaction(paymentDetails, operator, partner, accountDebit, ope, TransactionStatus.FAILED);		
		trans.setExceptionCode(PayPartnerExceptionCode.BankException);
		trans.setExceptionlib(PayPartnerExceptionCode.BankException.getValue());
		if(StringUtils.isBlank(accountDebit)) trans.setAccountDebit("XXXXX-XXXXXXXXXXX-XX");
		//*** trans.setOtp(Boolean.FALSE);
		//*** operator.cancelLimit(transactionMode, trans.getAmount());

		// Enregistrement de la transaction en echec
		trans = this.repository.save(trans);
		return  trans;
	}


	public Transactions buildTransaction(PaymentDetails paymentDetails, Operators operator, Partners partner, String accountDebit, String ope, TransactionStatus status){
		Transactions trans = new Transactions();
		Double com = 0d; 
		Double tva = 0d;

		trans.setReason(paymentDetails.getLib());
		trans.setTransactionMode(TransactionMode.VIREMENT_PARTENAIRE);
		trans.setOpe(ope);
		trans.setTypeOperation(StringUtils.isNotBlank(ope) ? ope : TransactionMode.VIREMENT_PARTENAIRE.name());
		trans.setAmount(paymentDetails.getAmount());
		trans.setPartnerTrxID(paymentDetails.getTrxID());		
		trans.setPartnerCode(partner.getPartcode());
		trans.setAccountCredit(partner.getAccount());

		if(operator != null){
			trans.setReferenceOperator(operator.getId());
			trans.setNomOperator(operator.getClientName());
			trans.setDco(getDco());
			trans.setAccountDebit(accountDebit);

			/* les commissions
			log.info("MODE FACT SUB : "+subspartner.getProfil().getModeFacturation());
			log.info("MODE FACT TRX : "+FpmodeFacturation.TRANSACTION);
			log.info("RESULT : "+subspartner.getProfil().getModeFacturation().equals(FpmodeFacturation.TRANSACTION));
			// Si les commissions sont par transaction ou periodiques en focntion du type de transaction
			if((typeOperation == TypeOperation.PAYMENT && subspartner.getProfil().getModeFacturation()==FpmodeFacturation.TRANSACTION) || 
					((typeOperation == TypeOperation.SUBSCRIPTION || typeOperation == TypeOperation.FACTURATION) && subspartner.getProfil().getModeFacturation()==FpmodeFacturation.PERIODIQUE)){
				log.info("BUILDING COMMISSIONS ");
				// Ajout des commissions
				com = getFpTransactionCom(subspartner, trans.getAmount());				
			}
			 */
		}

		// 	
		//*** tva = FptoolsEjb.round(param.getTva() * trans.getCommissions() / 100.0, 0);
		trans.setCommissions(com);
		trans.setTtc(trans.getAmount() + trans.getCommissions() + tva);
		trans.setStatusTrans(status);
		trans.setValidfrom(new Date());
		trans.setOtp(Boolean.FALSE);
		log.info("BUILDING TRANSACTION OK");
		return trans;
	}



	/**
	 * 
	 * @param listAP
	 * @return
	 */
	public String getDetailsAP(List<UnpaidNotice> listAP){
		String num = "";
		if(listAP==null || listAP.isEmpty()) return num;
		for(UnpaidNotice unpaidNotice : listAP) {
			if(unpaidNotice!=null){
				num = num.concat( unpaidNotice.getNoticeNumber().concat("_").concat(String.valueOf(unpaidNotice.getNoticeAmount())).concat(";") );
			}
		}
		log.info("DETAILS = "+num);
		return num;
	}



	/**
	 * 
	 * @param bankCode
	 * @return
	 */
	public String getTaxPayerNumber(List<UnpaidNotice> listAP){
		if(listAP==null || listAP.isEmpty()) return null;
		for(UnpaidNotice unpaidNotice : listAP) {
			if(unpaidNotice!=null){
				return unpaidNotice.getTaxPayerNumber();
			}
		}
		return null;
	}


	/**
	 * 
	 * @param listBill
	 * @return
	 */
	public String getTrustBillDetails(List<TrustBill> listBill){
		String num = "";
		if(listBill==null || listBill.isEmpty()) return num;
		for(TrustBill bill : listBill) {
			if(bill!=null){
				num = num.concat( bill.getBillRef().concat("_").concat(bill.getCreateTime().toString()).concat("_").concat(bill.getDueTime().toString())
						.concat("_").concat(String.valueOf(bill.getDueAmount())).concat("_").concat(bill.getCurrency()).concat(";") );
			}
		}
		log.info("DETAILS = "+num);
		return num;
	}


	/**
	 * 
	 * @param listBeneficiaires
	 * @return
	 */
	public String getDetailsBeneficiaires(List<Beneficiaire> listBeneficiaires){
		String beneficiares = "";
		if(listBeneficiaires==null || listBeneficiaires.isEmpty()) return beneficiares;
		for(Beneficiaire ben : listBeneficiaires) {
			if(ben!=null){
				beneficiares = beneficiares.concat( ben.getBeneficiaryName().concat("_").concat(ben.getBeneficiaryCode())
						.concat("_").concat(ben.getBankCode()).concat("_").concat(ben.getAccountNumber())
						.concat("_").concat(String.valueOf(ben.getAmount())).concat(";") );
			}
		}
		log.info("BENEFICIARES = "+beneficiares);
		return beneficiares;
	}


	/**
	 * 
	 * @param details
	 * @return
	 */
	public String getBillsNumber(String details){
		String bills = "";
		if(StringUtils.isBlank(details)) return bills;
		details = details.replaceFirst("BILLS:", "").trim();
		String[] dets = details.split(";");
		dets[0] = "";
		String bill = "";
		for(String d : dets) {
			if(StringUtils.isNotBlank(d)){
				bill = d.split("_")[0];
				bills = bills.isEmpty() ? bill : bills.concat(";").concat(bill);
			}
		}
		log.info("DETAILS = "+bills);
		return bills;
	}


	/**
	 * 
	 * @param details
	 * @return
	 */
	public Map<String, Integer> getMapBillAmount(String details){
		Map<String, Integer> billsAmt = new HashMap<>();
		if(StringUtils.isBlank(details)) return billsAmt;
		details = details.replaceFirst("BILLS:", "").trim();
		String[] dets = details.split(";");
		// dets[0] = "";
		String bill = "";
		for(String d : dets) {
			if(StringUtils.isNotBlank(d)){
				bill = d.split("_")[0];
				int amt = Integer.valueOf(d.split("_")[1]);
				billsAmt.put(bill, amt);
			}
		}
		// log.info("DETAILS = "+num);
		return billsAmt;
	}



	/**
	 * 
	 * @param amt
	 * @param listBeneficiaires
	 * @return
	 */
	public Boolean isVentillationAmountOK(Double amt, List<Beneficiaire> listBeneficiaires){
		Double benAmt = 0d;
		if(listBeneficiaires==null || listBeneficiaires.isEmpty()) return Boolean.FALSE;
		for(Beneficiaire ben : listBeneficiaires) {
			if(ben!=null){
				benAmt = benAmt + ben.getAmount().doubleValue();
			}
		}
		if(benAmt == amt) return Boolean.TRUE;
		return Boolean.FALSE;
	}



	/**
	 * findUnpaidBill : recuperation de la (des) facture(s) a regler
	 * @param partnerCode
	 * @param code
	 * @return La liste des AP recuperes
	 */
	public Response findUnpaidBill(String partnerCode, String code){
		return null;

		/*
		Partners partner;
		try{
			log.info("findUnpaidBill API");
//			return UnpaidNoticeResponse.getFactisListeAP();
			// Verifier l'existence et la validite du partenaire
			partner = partnerRepository.findByPartcode(partnerCode);
			// Retourner une liste vide
			if(partner==null) return Response.ok(MessageResponse.ERROR+" : PARTNER NOT FOUND").status(Status.NOT_FOUND).build();
			if(!partner.isActif()) return Response.ok(MessageResponse.ERROR+" : PARTNER NOT ACTIVE").status(Status.NOT_FOUND).build();
			//
			log.info("findUnpaidBill API : partner = "+partnerCode);
			log.info("findUnpaidBill API : code = "+code);

			FpparametersGeneral param = parametersEJB.findById(new FpparametersGeneral().getCode());
			if(param==null) return Response.ok(ResponseCode.ERROR+" : PARAMETERS NOT INITIALIZED").status(Status.NOT_FOUND).build(); 

			// Recuperer la liste des APs non regles selectionnes par le client sur la plateforme du partenaire
			String serviceURL = FpHelper.getUrl(partner.getHost(), String.valueOf(partner.getPort()), partner.getProtocole(), partner.getPath());

			String url = FpHelper.getUrl(param.getIpMiddleware(), String.valueOf(param.getPortMiddleware()), param.getProtocolMiddleware(), param.getPathMiddleware());
			log.info("findUnpaidBill API : chemin de base = "+url);
			String service = "/".concat(partnerCode.toLowerCase()).concat("/findunpaidbill");
			log.info("findUnpaidBill API : service = "+service);
			url = url.concat(service);

			CloseableHttpClient client = HttpClients.createDefault();
		    HttpPost httpPost = new HttpPost(url);

		    String json = new Gson().toJson(new SubscriptionCodeAPI(serviceURL, code, null, partner.getApiKey()));

            // Displaying JSON String 
            log.info("findUnpaidBill API Body json : "+json);

		    StringEntity entity = new StringEntity(json);
		    httpPost.setEntity(entity);
		    httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-type", "application/json");
		    // Integrer le certificat si necessaire
		    CloseableHttpResponse response = client.execute(httpPost);
		    log.info("findUnpaidBill API After execute");

		    String content = EntityUtils.toString(response.getEntity());
		    log.info("findUnpaidBill API Response : "+content);
		    int respStatut = response.getStatusLine().getStatusCode();
		    log.info("findunpaidbill Response statut : "+respStatut);

		    client.close();

		    if(respStatut==200){
		    	return Response.ok(content).status(Status.OK).build();
		    }
		    else return  Response.ok(content).status(Status.NOT_FOUND).build();

		}catch(Exception e){
			log.info("UNPAID NOTICE EXCEPTION = "+e.getMessage());
			e.printStackTrace();
			return Response.ok(ResponseCode.ERROR+" : " + e.getMessage()).status(Status.NOT_FOUND).build(); 
		}
		 */
	}




	private Transactions buildOTP(Transactions trans){
		log.info("BUILDING OTP ");

		// Generation OTP
		String otp = ToolsUtils.generateToken(Integer.parseInt(applicationServiceSizeOtp), PayPartnerTypeString.getValue(applicationServiceTypeOtp));
		trans.setOtp(Boolean.TRUE);
		trans.setSecureOTP(otp);

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, Integer.parseInt(applicationServiceValiditeOtp));
		// Delai de validite de l'OTP de la transaction
		trans.setValidTo(c.getTime());

		log.info("BUILDING OTP OK ");
		return trans;
	}

	//generateToken

	@Override
	public ResponseEntity<TransactionsResponse> findAllTransactionsToday(String date, String user){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
		try {
			Date validfrom = new DateUtil(DateUtil.parse(date, DateUtil.DATE_MINUS_FORMAT_SINGLE)).beginOfDay();
			Date validTo = new DateUtil(DateUtil.parse(date, DateUtil.DATE_MINUS_FORMAT_SINGLE)).endOfDay();
			List<TransactionStatus> listes = List.of(TransactionStatus.PROCESSING, TransactionStatus.ENCAISSEE, TransactionStatus.TO_CONFIRM, TransactionStatus.SUCCESS, TransactionStatus.CLOSE, TransactionStatus.CANCEL, TransactionStatus.ERROR, TransactionStatus.INITIATE);
			List<Transactions> transactions = repository.findByTransactionsTodayBbyuserCaisse(user,listes, validfrom, validTo);

			if (transactions != null && !CollectionUtils.isEmpty(transactions)) {
				datas = transactions.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<TransactionsResponse> findPendingTransactions(String user){		
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
		try {
			Date validfrom = new DateUtil(new Date()).beginOfDay();
			Date validTo = new DateUtil(new Date()).endOfDay();
			List<TransactionStatus> listes = List.of(TransactionStatus.PROCESSING, TransactionStatus.ENCAISSEE);
			List<Transactions> transactions = repository.findByTransactionsTodayBbyuserCaisse(user,listes, validfrom, validTo);

			if (transactions != null && !CollectionUtils.isEmpty(transactions)) {
				datas = transactions.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TransactionsResponse> findTransactions(Transactions data, String debut, String fin){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
		try {
			Date validfrom = new DateUtil(DateUtil.parse(debut, DateUtil.DATE_MINUS_FORMAT_SINGLE)).beginOfDay();
			Date validTo = new DateUtil(DateUtil.parse(fin, DateUtil.DATE_MINUS_FORMAT_SINGLE)).endOfDay();
			List<Transactions> transactions = this.repositoryfull.findDataByCriteria(data, validfrom, validTo);
			for(Transactions t : transactions){
				if(t.getReportdata() == null) {
					Operators operators = operatorRepository.findById(t.getReferenceOperator()).get();
					t.setReportdata(Base64.getEncoder().encodeToString(generateReportTransactions.generateBill(t, operators)));
				}
			}

			if (transactions != null && !CollectionUtils.isEmpty(transactions)) {
				datas = transactions.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}



	@Override
	public ResponseEntity<TransactionsResponse> findTransactionsByDate(String referenceOperator, String debut, String fin){
		Transactions data = new Transactions();
		data.setReferenceOperator(referenceOperator);
		return findTransactions(data, debut, fin);
	}



	@Override
	public ResponseEntity<TransactionsResponse> getAllTransactions(){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
		try {
			List<Transactions> transactions = repository.findAll();

			if (transactions != null && !CollectionUtils.isEmpty(transactions)) {
				datas = transactions.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TransactionsResponse> getTransactionsById(String id) {
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<Transactions> optionalTransaction = repository.findById(id);
			if (optionalTransaction != null) {
				Transactions transactionResponse = optionalTransaction.get();
				datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(transactionResponse));
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<TransactionsResponse> deleteTransactions(Transactions data){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
		try {
			Transactions trans = this.repository.findById(data.getId()).get();
			if(trans != null) {
				trans.setStatusTrans(TransactionStatus.CANCEL);
				trans.setValidTo(new Date());
				trans.setCancelReason(data.getCancelReason());
				trans = this.repository.save(trans);
				datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TransactionsResponse> deleteTransactionsWithId(String id, Transactions data){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
		try {
			Transactions trans = this.repository.findById(id).get();
			if(trans != null) {
				trans.setStatusTrans(TransactionStatus.CANCEL);
				trans.setValidTo(new Date());
				trans.setCancelReason(data.getCancelReason());
				trans.setUsercancel(data.getUsercancel());
				trans = this.repository.save(trans);
				datas.add(ObjectMapperUtils.mapTransactionsToTransactionsDto(trans));
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<TransactionsResponse> findByPartnerCode(String partnerCode){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			List<Transactions> transactionsResponse = repository.findByPartnerCodeOrderByValidfromDesc(partnerCode);
			if (transactionsResponse != null && !CollectionUtils.isEmpty(transactionsResponse)) {
				datas = transactionsResponse.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<TransactionsResponse> findByPartnerCodeAndStatusTransDay(String partnerCode, TransactionStatus statusTrans){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (statusTrans == null) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Statut transaction)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			TransactionsDto transactionDtoRequest = new TransactionsDto();
			transactionDtoRequest.setStatusTrans(statusTrans);
			transactionDtoRequest.setPartnerCode(partnerCode);
			transactionDtoRequest.setValidfrom(new Date());
			transactionDtoRequest.setValidTo(new Date());
			List<Transactions> transactionsResponse = findTransactionsGeneric(transactionDtoRequest);

			if (transactionsResponse != null && !CollectionUtils.isEmpty(transactionsResponse)) {
				datas = transactionsResponse.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<TransactionsResponse> findByPartnerCodeAndStatusTrans(String partnerCode, TransactionStatus statusTrans, int page, int size){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (statusTrans == null) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Statut transaction)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Page<Transactions> pageTransactions;
			Pageable paging = PageRequest.of(page, size);
			pageTransactions = repository.findByPartnerCodeAndStatusTransOrderByValidfromDesc(partnerCode, statusTrans, paging);

			//String currentPage, String totalItems, String totalPages,
			List<Transactions> transactionsResponse = pageTransactions.getContent();
			if (transactionsResponse != null && !CollectionUtils.isEmpty(transactionsResponse)) {
				datas = transactionsResponse.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", pageTransactions.getNumber(), pageTransactions.getTotalElements(), pageTransactions.getTotalPages(), datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<TransactionsResponse> findTransactionsPartner(String partnerCode){
		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			List<Transactions> transactionsResponse = repository.findTransactionsPartner(partnerCode, TransactionStatus.getValuesAcceptPartner());
			if (transactionsResponse != null && !CollectionUtils.isEmpty(transactionsResponse)) {
				datas = transactionsResponse.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResponseEntity<TransactionsResponse> findTransactions(TransactionsDto transactionDtoRequest) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transactions> cq = cb.createQuery(Transactions.class);

		Root<Transactions> transactions = cq.from(Transactions.class);
		List<Predicate> predicates = new ArrayList<>();

		List<TransactionsDto> datas = new ArrayList<TransactionsDto>();

		try {			
			if (transactionDtoRequest == null) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			if (StringUtils.isNotBlank(transactionDtoRequest.getAccountCredit())) {
				predicates.add(cb.equal(transactions.get("accountCredit"), transactionDtoRequest.getAccountCredit()));
			}			
			if (StringUtils.isNotBlank(transactionDtoRequest.getAccountDebit())) {
				predicates.add(cb.equal(transactions.get("accountDebit"), transactionDtoRequest.getAccountDebit()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getAgeCaisse())) {
				predicates.add(cb.equal(transactions.get("ageCaisse"), transactionDtoRequest.getAgeCaisse()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getEveid())) {
				predicates.add(cb.equal(transactions.get("eveid"), "%" + transactionDtoRequest.getEveid() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getNomOperator())) {
				predicates.add(cb.like(transactions.get("nomOperator"), "%" + transactionDtoRequest.getNomOperator() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getPartieVersante())) {
				predicates.add(cb.like(transactions.get("partieVersante"), "%" + transactionDtoRequest.getPartieVersante() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getPartnerCode())) {
				predicates.add(cb.equal(transactions.get("partnerCode"), transactionDtoRequest.getPartnerCode()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getPartieVersante())) {
				predicates.add(cb.like(transactions.get("partieVersante"), "%" + transactionDtoRequest.getPartieVersante() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReason())) {
				predicates.add(cb.like(transactions.get("reason"), "%" + transactionDtoRequest.getReason() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReferenceBill())) {
				predicates.add(cb.like(transactions.get("referenceBill"), "%" + transactionDtoRequest.getReferenceBill() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReference())) {
				predicates.add(cb.equal(transactions.get("reference"), transactionDtoRequest.getReference()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReferencenumber())) {
				predicates.add(cb.equal(transactions.get("referencenumber"), transactionDtoRequest.getReferencenumber()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReferenceOperator())) {
				predicates.add(cb.equal(transactions.get("referenceOperator"), transactionDtoRequest.getReferenceOperator()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getTelephone())) {
				predicates.add(cb.like(transactions.get("telephone"), "%" + transactionDtoRequest.getTelephone() + "%"));
			}
			if (transactionDtoRequest.getTransactionMode() != null) {
				predicates.add(cb.equal(transactions.get("transactionMode"), transactionDtoRequest.getTransactionMode()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getTypeOperation())) {
				predicates.add(cb.equal(transactions.get("typeOperation"), transactionDtoRequest.getTypeOperation()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getUserCaisse())) {
				predicates.add(cb.equal(transactions.get("userCaisse"), transactionDtoRequest.getUserCaisse()));
			}
			if (transactionDtoRequest.getStatusTrans() != null) {
				predicates.add(cb.equal(transactions.get("statusTrans"), transactionDtoRequest.getStatusTrans()));
			}		
			if (transactionDtoRequest.getPostComplete() != null) {
				predicates.add(cb.equal(transactions.get("postComplete"), transactionDtoRequest.getPostComplete()));
			}

			try {
				if(transactionDtoRequest.getValidfrom() != null && transactionDtoRequest.getValidTo() != null){
					log.info("******************** operatorDtoRequest.getValidfrom() ******************** : " + transactionDtoRequest.getValidfrom());
					log.info("******************** operatorDtoRequest.getValidTo() ******************** : " + transactionDtoRequest.getValidTo());
					Date dateDebParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(transactionDtoRequest.getValidfrom()) + " 00:00:00", "dd-MM-yyyy hh:mm:ss");
					Date dateFinParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(transactionDtoRequest.getValidTo()) + " 23:59:59", "dd-MM-yyyy hh:mm:ss");

					if(transactionDtoRequest.getStatusTrans() != null && (TransactionStatus.SUCCESS.equals(transactionDtoRequest.getStatusTrans()) || TransactionStatus.INVALID.equals(transactionDtoRequest.getStatusTrans()))) {
						log.info("******************** operatorDtoRequest.getStatusTrans() ******************** : " + transactionDtoRequest.getStatusTrans());
						predicates.add(cb.between(transactions.get("dateConfirmation"), dateDebParse, dateFinParse));
					}
					else {
						predicates.add(cb.between(transactions.get("validfrom"), dateDebParse, dateFinParse));
					}
				}
			} catch (Exception e) {
			}

			cq.where(predicates.toArray(new Predicate[0]));

			List<Order> orderList = new ArrayList();			
			if(transactionDtoRequest.getStatusTrans() != null && (TransactionStatus.SUCCESS.equals(transactionDtoRequest.getStatusTrans()) || TransactionStatus.INVALID.equals(transactionDtoRequest.getStatusTrans()))) {
				log.info("******************** operatorDtoRequest.getStatusTrans() ******************** : " + transactionDtoRequest.getStatusTrans());
				orderList.add(cb.desc(transactions.get("dateConfirmation")));
			}
			else {
				orderList.add(cb.desc(transactions.get("validfrom")));
			}
			orderList.add(cb.asc(transactions.get("nomOperator")));
			cq.orderBy(orderList);

			List<Transactions> transactionsResponse = entityManager.createQuery(cq).getResultList();
			if (transactionsResponse != null && !CollectionUtils.isEmpty(transactionsResponse)) {
				datas = transactionsResponse.stream().map(response -> {
					return ObjectMapperUtils.mapTransactionsToTransactionsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Transactions> findTransactionsGeneric(TransactionsDto transactionDtoRequest) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transactions> cq = cb.createQuery(Transactions.class);

		Root<Transactions> transactions = cq.from(Transactions.class);
		List<Predicate> predicates = new ArrayList<>();

		try {	

			if (StringUtils.isNotBlank(transactionDtoRequest.getAccountCredit())) {
				predicates.add(cb.equal(transactions.get("accountCredit"), transactionDtoRequest.getAccountCredit()));
			}			
			if (StringUtils.isNotBlank(transactionDtoRequest.getAccountDebit())) {
				predicates.add(cb.equal(transactions.get("accountDebit"), transactionDtoRequest.getAccountDebit()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getAgeCaisse())) {
				predicates.add(cb.equal(transactions.get("ageCaisse"), transactionDtoRequest.getAgeCaisse()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getEveid())) {
				predicates.add(cb.equal(transactions.get("eveid"), "%" + transactionDtoRequest.getEveid() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getNomOperator())) {
				predicates.add(cb.like(transactions.get("nomOperator"), "%" + transactionDtoRequest.getNomOperator() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getPartieVersante())) {
				predicates.add(cb.like(transactions.get("partieVersante"), "%" + transactionDtoRequest.getPartieVersante() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getPartnerCode())) {
				predicates.add(cb.equal(transactions.get("partnerCode"), transactionDtoRequest.getPartnerCode()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getPartieVersante())) {
				predicates.add(cb.like(transactions.get("partieVersante"), "%" + transactionDtoRequest.getPartieVersante() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReason())) {
				predicates.add(cb.like(transactions.get("reason"), "%" + transactionDtoRequest.getReason() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReferenceBill())) {
				predicates.add(cb.like(transactions.get("referenceBill"), "%" + transactionDtoRequest.getReferenceBill() + "%"));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReference())) {
				predicates.add(cb.equal(transactions.get("reference"), transactionDtoRequest.getReference()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReferencenumber())) {
				predicates.add(cb.equal(transactions.get("referencenumber"), transactionDtoRequest.getReferencenumber()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getReferenceOperator())) {
				predicates.add(cb.equal(transactions.get("referenceOperator"), transactionDtoRequest.getReferenceOperator()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getTelephone())) {
				predicates.add(cb.like(transactions.get("telephone"), "%" + transactionDtoRequest.getTelephone() + "%"));
			}
			if (transactionDtoRequest.getTransactionMode() != null) {
				predicates.add(cb.equal(transactions.get("transactionMode"), transactionDtoRequest.getTransactionMode()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getTypeOperation())) {
				predicates.add(cb.equal(transactions.get("typeOperation"), transactionDtoRequest.getTypeOperation()));
			}
			if (StringUtils.isNotBlank(transactionDtoRequest.getUserCaisse())) {
				predicates.add(cb.equal(transactions.get("userCaisse"), transactionDtoRequest.getUserCaisse()));
			}
			if (transactionDtoRequest.getStatusTrans() != null) {
				predicates.add(cb.equal(transactions.get("statusTrans"), transactionDtoRequest.getStatusTrans()));
			}		
			if (transactionDtoRequest.getPostComplete() != null) {
				predicates.add(cb.equal(transactions.get("postComplete"), transactionDtoRequest.getPostComplete()));
			}

			try {
				if(transactionDtoRequest.getValidfrom() != null && transactionDtoRequest.getValidTo() != null){
					log.info("******************** operatorDtoRequest.getValidfrom() ******************** : " + transactionDtoRequest.getValidfrom());
					log.info("******************** operatorDtoRequest.getValidTo() ******************** : " + transactionDtoRequest.getValidTo());
					Date dateDebParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(transactionDtoRequest.getValidfrom()) + " 00:00:00", "dd-MM-yyyy hh:mm:ss");
					Date dateFinParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(transactionDtoRequest.getValidTo()) + " 23:59:59", "dd-MM-yyyy hh:mm:ss");
					predicates.add(cb.between(transactions.get("validfrom"), dateDebParse, dateFinParse));
				}
			} catch (Exception e) {
			}

			cq.where(predicates.toArray(new Predicate[0]));

			List<Order> orderList = new ArrayList();			
			if(transactionDtoRequest.getStatusTrans() != null && TransactionStatus.SUCCESS.equals(transactionDtoRequest.getStatusTrans()) && TransactionStatus.SUCCESS.equals(transactionDtoRequest.getStatusTrans())) {
				orderList.add(cb.desc(transactions.get("dateConfirmation")));
			}
			else {
				orderList.add(cb.desc(transactions.get("validfrom")));
			}
			orderList.add(cb.asc(transactions.get("nomOperator")));
			cq.orderBy(orderList);

			return entityManager.createQuery(cq).getResultList();

		} catch (Exception e) {

		}

		return null;
	}


	@Override
	public ResponseEntity<InfosCustomerVersementResponse> findLastOperator(String referenceOperator, String partnerCode){
		List<InfosCustomerVersementDto> datas = new ArrayList<InfosCustomerVersementDto>();
		try {
			List<Transactions> transactionsResponse = this.repository.findLastOperator("001", referenceOperator, partnerCode, TransactionStatus.getValuesAccept());
			if (transactionsResponse != null && !CollectionUtils.isEmpty(transactionsResponse)) {
				InfosCustomerVersementDto infos = new InfosCustomerVersementDto();
				Transactions trans = transactionsResponse.get(0);
				infos.setActivity(StringUtils.isNotBlank(trans.getActivity()) ? trans.getActivity() : "");
				infos.setAdresseOperator(StringUtils.isNotBlank(trans.getAdresseOperator()) ? trans.getAdresseOperator() : "");
				infos.setCategorieJustificatif(StringUtils.isNotBlank(trans.getCategorieJustificatif()) ? trans.getCategorieJustificatif() : "");
				infos.setDivisionAdministratif(StringUtils.isNotBlank(trans.getDivisionAdministratif()) ? trans.getDivisionAdministratif() : "");
				infos.setNomOperator(StringUtils.isNotBlank(trans.getNomOperator()) ? trans.getNomOperator() : "");
				infos.setPartnerCode(StringUtils.isNotBlank(trans.getPartnerCode()) ? trans.getPartnerCode() : "");
				infos.setReferenceOperator(StringUtils.isNotBlank(trans.getReferenceOperator()) ? trans.getReferenceOperator() : "");
				infos.setSegment(StringUtils.isNotBlank(trans.getSegment()) ? trans.getSegment() : "");
				infos.setTypeJustificatif(StringUtils.isNotBlank(trans.getTypeJustificatif()) ? trans.getTypeJustificatif() : "");

				datas.add(infos);
				return new ResponseEntity<InfosCustomerVersementResponse>(new InfosCustomerVersementResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<InfosCustomerVersementResponse>(new InfosCustomerVersementResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<InfosCustomerVersementResponse>(new InfosCustomerVersementResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<DataResponseDTO> nivellementAccountPartner(String partnerCode, String user){
		String response = "";
		boolean transExist = Boolean.FALSE; 
		String niveauCheckTrans;
		Double mntD = 0d;
		Double mntC = 0d;
		Integer nbrC = 0;
		Integer nbrD = 0;
		String dev = "";
		String ope = "";
		String accountLiaison = "";
		String codeUser = "";
		List<FactMonthDetails> resultats = new ArrayList<FactMonthDetails>();
		Map<String,String> mapCompte = new HashMap<String, String>();

		try { 
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", response), HttpStatus.PRECONDITION_FAILED);
			}

			List<Transactions> transactionsResponse = this.repository.findByPartnerCodeAndPostCompleteAndStatusTrans(partnerCode, Boolean.FALSE, TransactionStatus.SUCCESS);
			if (transactionsResponse == null || transactionsResponse.isEmpty()) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST + " ", "", response), HttpStatus.PRECONDITION_FAILED);
			}
			log.info("************* transactionsResponse.size() ***************** : " + transactionsResponse.size());

			Partners partner = partnerRepository.findByPartcode(partnerCode);
			if (partner == null) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.PartnerNotIdentified + " (Partner code)", "", response), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(partner.getAccount())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST + " (Partner account)", "", response), HttpStatus.PRECONDITION_FAILED);
			}			
			if(!partner.isComptabilisationTransit()) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION + " (Comptabilisation intermediaire non autorisée)", "", response), HttpStatus.PRECONDITION_FAILED);
			}

			accountLiaison = getapplicationServiceAccountLiaison();			
			if(StringUtils.isBlank(accountLiaison))
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Compte de liaison", response), HttpStatus.PRECONDITION_FAILED);

			codeUser = getapplicationServiceCodeUser();
			if(StringUtils.isBlank(codeUser))
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Code utilisateur", response), HttpStatus.PRECONDITION_FAILED);

			ope = getapplicationServiceOpeNivellement();
			if(StringUtils.isBlank(ope))
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Code opération nivellement", response), HttpStatus.PRECONDITION_FAILED);

			String creditAccount = partner.getAccount();
			String debitAccount = partner.getPartnerNcpVersement();
			if(StringUtils.isBlank(debitAccount))
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY, " Account debit transit", response), HttpStatus.PRECONDITION_FAILED);

			niveauCheckTrans = getapplicationServiceNiveauCheckTrans();
			log.info("************* get niveauCheckTrans ***************** : " + niveauCheckTrans);

			Bkcom bkcomLiaisonCredit = null;
			Bkcom bkcomLiaisonDebit = null;
			if(!debitAccount.split("-")[0].equals(creditAccount.split("-")[0])) {
				log.info("************* get details bkcomLiaisonDebit et bkcomLiaisonCredit *****************");
				ResponseBkcom resLiaisonCre = CoreBankingServices.getaccountinfo(keysecurity, host, debitAccount.split("-")[0], accountLiaison);
				bkcomLiaisonCredit = resLiaisonCre.getData().isEmpty() ? null : resLiaisonCre.getData().get(0);

				ResponseBkcom resLiaisonDeb = CoreBankingServices.getaccountinfo(keysecurity, host, creditAccount.split("-")[0], accountLiaison);
				bkcomLiaisonDebit = resLiaisonDeb.getData().isEmpty() ? null : resLiaisonDeb.getData().get(0);
				if(bkcomLiaisonDebit == null || bkcomLiaisonCredit == null) {
					return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " Compte de liaison dans une agence", response), HttpStatus.PRECONDITION_FAILED);
				}
			}

			Date dco = getDco();

			Double amountTotal = 0d;
			Double soldeAccountTransit = 0d;			
			Bkeve eve = null;
			//*** Bkeve eve2 = null;
			int numEc = 1; 
			int nombreTrans = 0;
			List<Bkmvti> ecritures = new ArrayList<Bkmvti>();
			for(Transactions tx : transactionsResponse) {
				//Check eve dans le CBS
				String[] eves = tx.getReferencenumber().split("-");
				if(eves.length == 1){
					Optional<Bkeve> opeve = this.bkeveRepository.findById(tx.getReferencenumber());
					if(opeve.isPresent()){
						eve = opeve.get();
					}else {
						break;
					}
				}else {
					Optional<Bkeve> opeve = this.bkeveRepository.findById(eves[0]);
					if(opeve.isPresent()){
						eve = opeve.get();
					}else {
						break;
					}
					opeve = this.bkeveRepository.findById(eves[1]);
					if(opeve.isPresent()){
						//*** eve2 = opeve.get();
					}else {
						break;
					}
				}

				if(StringUtils.isBlank(niveauCheckTrans) || "0".equals(niveauCheckTrans)) {
					amountTotal=amountTotal+tx.getAmount();
					nombreTrans++;
					log.info("************* niveau 0 amountTotal ***************** : " + amountTotal);
					String pie = "PP"+new SimpleDateFormat("ddMMyy").format(tx.getDco()) + RandomStringUtils.randomAlphanumeric(3);
					String lib = "PAY/" + new SimpleDateFormat("ddMMyy").format(tx.getValidfrom()) + "/" + tx.getReferenceOperator() + "/" + tx.getCodeReason();

					ResponseBkcom resDeb = CoreBankingServices.getaccountinfo(keysecurity, host, debitAccount.split("-")[0], debitAccount.split("-")[1]);
					Bkcom bkcomDeb =  resDeb.getData().isEmpty() ? null : resDeb.getData().get(0);
					log.info("************* niveau 0 bkcomDeb.getNcp() ***************** : " + bkcomDeb.getNcp());

					ResponseBkcom resCre = CoreBankingServices.getaccountinfo(keysecurity, host, creditAccount.split("-")[0], creditAccount.split("-")[1]);
					Bkcom bkcomCre =  resCre.getData().isEmpty() ? null : resCre.getData().get(0);
					log.info("************* niveau 0 bkcomCre.getNcp() ***************** : " + bkcomCre.getNcp());

					ecritures.add( new Bkmvti(bkcomDeb.getAge(), bkcomDeb.getDev(), bkcomDeb.getCha(), bkcomDeb.getNcp(), bkcomDeb.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomDeb.getClc(), dco, null, eve.getDva1(), tx.getAmount(), "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomDeb.getAge(), bkcomDeb.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
					ecritures.add( new Bkmvti(bkcomCre.getAge(), bkcomCre.getDev(), bkcomCre.getCha(), bkcomCre.getNcp(), bkcomCre.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomCre.getClc(), dco, null, eve.getDva2(), tx.getAmount(), "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomCre.getAge(), bkcomCre.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
					if(!debitAccount.split("-")[0].equals(creditAccount.split("-")[0])) {
						ecritures.add( new Bkmvti(bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), bkcomLiaisonCredit.getCha(), bkcomLiaisonCredit.getNcp(), bkcomLiaisonCredit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonCredit.getClc(), dco, null, eve.getDva2(), tx.getAmount(), "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
						ecritures.add( new Bkmvti(bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), bkcomLiaisonDebit.getCha(), bkcomLiaisonDebit.getNcp(), bkcomLiaisonDebit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonDebit.getClc(), dco, null, eve.getDva1(), tx.getAmount(), "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
					}
				}
				else {
					if("1".equals(niveauCheckTrans)) {
						String result = CoreBankingServices.checktransbkhis(eve.getEve(), eve.getOpe(), new SimpleDateFormat("dd-MM-yyyy").format(eve.getDco()));
						if(StringUtils.isNotBlank(result) && debitAccount.split("-")[1].equals(result)) {
							transExist = Boolean.TRUE;
						}
					}
					if("2".equals(niveauCheckTrans)) {
						DataResponseDTO res = CoreBankingServices.statutEvent(keysecurity, host, eve.getAge(), eve.getEve(), eve.getOpe(), eve.getDco());
						if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), res.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), res.getCode())){
							transExist = Boolean.TRUE;
						}
					}

					if(transExist == Boolean.TRUE){
						amountTotal=amountTotal+tx.getAmount();
						nombreTrans++;
						String pie = "PP"+new SimpleDateFormat("ddMMyy").format(tx.getDco()) + RandomStringUtils.randomAlphanumeric(3).toUpperCase();
						String lib = "PAY/" + new SimpleDateFormat("ddMMyy").format(tx.getValidfrom()) + "/" + tx.getReferenceOperator() + "/" + tx.getCodeReason();

						ResponseBkcom resDeb = CoreBankingServices.getaccountinfo(keysecurity, host, debitAccount.split("-")[0], debitAccount.split("-")[1]);
						Bkcom bkcomDeb =  resDeb.getData().isEmpty() ? null : resDeb.getData().get(0);
						log.info("************* niveau > 0 bkcomDeb.getNcp() ***************** : " + bkcomDeb.getNcp());

						ResponseBkcom resCre = CoreBankingServices.getaccountinfo(keysecurity, host, creditAccount.split("-")[0], creditAccount.split("-")[1]);
						Bkcom bkcomCre =  resCre.getData().isEmpty() ? null : resCre.getData().get(0);
						log.info("************* niveau > 0 bkcomCre.getNcp() ***************** : " + bkcomCre.getNcp());

						ecritures.add( new Bkmvti(bkcomDeb.getAge(), bkcomDeb.getDev(), bkcomDeb.getCha(), bkcomDeb.getNcp(), bkcomDeb.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomDeb.getClc(), dco, null, eve.getDva1(), tx.getAmount(), "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomDeb.getAge(), bkcomDeb.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
						ecritures.add( new Bkmvti(bkcomCre.getAge(), bkcomCre.getDev(), bkcomCre.getCha(), bkcomCre.getNcp(), bkcomCre.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomCre.getClc(), dco, null, eve.getDva2(), tx.getAmount(), "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomCre.getAge(), bkcomCre.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
						if(!debitAccount.split("-")[0].equals(creditAccount.split("-")[0])) {
							ecritures.add( new Bkmvti(bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), bkcomLiaisonCredit.getCha(), bkcomLiaisonCredit.getNcp(), bkcomLiaisonCredit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonCredit.getClc(), dco, null, eve.getDva2(), tx.getAmount(), "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
							ecritures.add( new Bkmvti(bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), bkcomLiaisonDebit.getCha(), bkcomLiaisonDebit.getNcp(), bkcomLiaisonDebit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonDebit.getClc(), dco, null, eve.getDva1(), tx.getAmount(), "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), tx.getAmount(), null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
						}
					}
				}

			}


			log.info("************* get amountTotal ***************** : " + amountTotal);	
			if(amountTotal <= 1) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Montant à niveler)", "", response), HttpStatus.PRECONDITION_FAILED);
			}

			//Vérification du solde  du compte
			ResponseData3 dsDTO = CoreBankingServices.getbalance(keysecurity, host, debitAccount);
			if(ResponseHolder.SUCESS.equalsIgnoreCase(dsDTO.getCode())){
				log.info("************* ResponseHolder.SUCESS debitAccount *****************");
				Map<String, Object> data = dsDTO.getData();
				log.info("************* get dsDTO.getData().toString() ***************** : " + dsDTO.getData().toString());
				try {
					String soldeAccountTransitString = (String) data.get("sin");
					log.info("************* debitAccount ***************** : " + debitAccount);
					log.info("************* soldeAccountTransitString ***************** : " + soldeAccountTransitString);
					soldeAccountTransit = Double.valueOf(soldeAccountTransitString);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				soldeAccountTransit = Double.valueOf(soldeAccountTransit.intValue());

				if((soldeAccountTransit - amountTotal) < 0) {
					return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.BankInsufficientBalance + " (Compte intermédiaire)", "", response), HttpStatus.PRECONDITION_FAILED);
				}
			} 
			else {
				log.info("************* ResponseHolder.NOT_FOUND debitAccount *****************");
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION + " (Impossible de consulter le solde du compte intermédiaire)", "", response), HttpStatus.PRECONDITION_FAILED);
			}


			/*  Nivellement en une seule transaction
			String pie = "PP"+new SimpleDateFormat("ddMMyy").format(dco) + RandomStringUtils.randomAlphanumeric(3).toUpperCase();
			String lib = partnerCode + "/" + new SimpleDateFormat("ddMMyyHHmm").format(new Date()) + "/NIVEL";

			ResponseBkcom resDeb = CoreBankingServices.getaccountinfo(keysecurity, host, debitAccount.split("-")[0], debitAccount.split("-")[1]);
			Bkcom bkcomDeb =  resDeb.getData().isEmpty() ? null : resDeb.getData().get(0);
			if(bkcomDeb == null) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.InvalidAccount, " Compte de débit", response), HttpStatus.NOT_IMPLEMENTED);
			}
			log.info("************* niveau > 0 bkcomDeb.getNcp() ***************** : " + bkcomDeb.getNcp());

			ResponseBkcom resCre = CoreBankingServices.getaccountinfo(keysecurity, host, creditAccount.split("-")[0], creditAccount.split("-")[1]);
			Bkcom bkcomCre =  resCre.getData().isEmpty() ? null : resCre.getData().get(0);
			if(bkcomCre == null) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.InvalidAccount, " Compte de crédit", response), HttpStatus.NOT_IMPLEMENTED);
			}
			log.info("************* niveau > 0 bkcomCre.getNcp() ***************** : " + bkcomCre.getNcp());

			ecritures.add( new Bkmvti(bkcomDeb.getAge(), bkcomDeb.getDev(), bkcomDeb.getCha(), bkcomDeb.getNcp(), bkcomDeb.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomDeb.getClc(), dco, null, eve.getDva1(), amountTotal, "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomDeb.getAge(), bkcomDeb.getDev(), amountTotal, null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
			ecritures.add( new Bkmvti(bkcomCre.getAge(), bkcomCre.getDev(), bkcomCre.getCha(), bkcomCre.getNcp(), bkcomCre.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomCre.getClc(), dco, null, eve.getDva2(), amountTotal, "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomCre.getAge(), bkcomCre.getDev(), amountTotal, null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
			if(!debitAccount.split("-")[0].equals(creditAccount.split("-")[0])) {
				ecritures.add( new Bkmvti(bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), bkcomLiaisonCredit.getCha(), bkcomLiaisonCredit.getNcp(), bkcomLiaisonCredit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonCredit.getClc(), dco, null, eve.getDva2(), amountTotal, "C", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonCredit.getAge(), bkcomLiaisonCredit.getDev(), amountTotal, null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
				ecritures.add( new Bkmvti(bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), bkcomLiaisonDebit.getCha(), bkcomLiaisonDebit.getNcp(), bkcomLiaisonDebit.getSuf(), ope, StringUtil.padText(String.valueOf(numEc), StringUtil.LEFT, StringUtil.TAILLE_CODE_EVE, "0"), null, codeUser, eve.getEve(), bkcomLiaisonDebit.getClc(), dco, null, eve.getDva1(), amountTotal, "D", lib, "N", pie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, bkcomLiaisonDebit.getAge(), bkcomLiaisonDebit.getDev(), amountTotal, null, null, null, null, null, null, null, null, eve.getNat(), "VA", null, null) ); numEc++;
			}    
			 */


			log.info("************* ecritures.size() ***************** : " + ecritures.size());

			DataResponseDTO responsePostEntries = CoreBankingServices.postAccountingentries(keysecurity, host, ecritures);
			if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), responsePostEntries.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), responsePostEntries.getCode()) ){
				for(Bkmvti mvt : ecritures){

					mvt.setDco(dco);
					mvt.setOpe(ope);
					mvt.setUti(user);

					// Ecriture de la ligne dans le fichier
					if(!mvt.getDev().equalsIgnoreCase("001")){
						mvt.setDev("001");
					}

					// Recuperation 
					FactMonthDetails det = new FactMonthDetails(Integer.valueOf(mvt.getAge()), mvt.getNcp()+"-"+mvt.getClc(),mvt.getIntitule(), mvt.getLib(),mvt.getInitDate(),mvt.getDateFact() ,mvt.getDatedebutFact(),mvt.getSen(),mvt.getMon());
					det.setLibage(mvt.getLibage());
					det.setTxtage(mvt.getAge());
					det.setDateAbon(new Date());
					det.setDev(mvt.getDev());
					String cle = mvt.getAge().trim()+mvt.getNcp().trim()+mvt.getClc().trim();
					if(mapCompte.containsKey(cle)){
						det.setIntitule(mapCompte.get(cle));
					}else{
						ResponseBkcom res = CoreBankingServices.getaccountinfo(keysecurity, host, mvt.getAge(), mvt.getNcp());
						Bkcom bkcom =  res.getData().isEmpty() ? null : res.getData().get(0);
						if(bkcom != null){
							det.setIntitule(bkcom.getInti());
							if(mapCompte.isEmpty()){
								mapCompte.put(cle, bkcom.getInti());
							}else if(!mapCompte.containsKey(cle)){
								mapCompte.put(cle, bkcom.getInti());
							}
						}else {
							res = CoreBankingServices.getaccountinfo(keysecurity, host, mvt.getAge(), mvt.getNcp());
							bkcom =  res.getData().isEmpty() ? null : res.getData().get(0);
							if(bkcom != null){
								det.setIntitule(bkcom.getInti());
								if(mapCompte.isEmpty()){
									mapCompte.put(cle, bkcom.getInti());
								}else if(!mapCompte.containsKey(cle)){
									mapCompte.put(cle, bkcom.getInti());
								}
							}
						}
					}
					resultats.add(det);
					if("C".equalsIgnoreCase(mvt.getSen())){
						mntC = mntC+mvt.getMon();
						nbrC++;
					}else{
						mntD = mntD+mvt.getMon();
						nbrD++;
					}
					dev = mvt.getDev();

				}

				String mois = DateFormatUtils.format(new Date(), "MMMM");
				//Construction du rapport
				FactMonth fac = new FactMonth(DateFormatUtils.format(dco, "yyyy-MM-dd"), "D", "C", mntD, mntC, nbrD, nbrC, user, new Date(), mois);
				Collections.sort(resultats);
				fac.setDev(dev);
				fac = factMonthrepository.save(fac);
				for(FactMonthDetails det : resultats) det.setParentFactMonth(fac.getId());

				if(resultats.size() > 1000 ){
					int i = 0;
					int cpt = 1000;
					int size = resultats.size();
					int reste = size % cpt;
					int nb = size / cpt;
					int j = 0;
					List<FactMonthDetails> results = new ArrayList<FactMonthDetails>();
					while(nb > 0){
						j = j + cpt;
						results = new ArrayList<FactMonthDetails>(resultats.subList(i,j));
						factMonthDetailrepository.saveAll(results);
						nb = nb - 1;
						i = j;
					}

					results = new ArrayList<FactMonthDetails>(resultats.subList(i,j+reste));
					factMonthDetailrepository.saveAll(results);

				}else{
					factMonthDetailrepository.saveAll(resultats);			
				}


				transactionsResponse = transactionsResponse.stream().map(trans -> {
					trans.setPostComplete(Boolean.TRUE);
					return this.repository.save(trans);
				}).collect(Collectors.toList());

				try {
					response = printEtatNivellement(fac, mois, partner.getPartcode(), partner.getPartname(), nombreTrans, amountTotal);
					return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", response), HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, " Rapport des traitements non généré", response), HttpStatus.ACCEPTED);
				}				
			}			

			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", response), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), response), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<DataResponseDTO> editBordereauVersement(TransactionsDto transactionDtoRequest){
		String response = "";
		try {
			if (transactionDtoRequest == null) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", response), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(transactionDtoRequest.getId())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " ID Transaction", response), HttpStatus.PRECONDITION_FAILED);
			}
			Optional<Transactions> transOptional = repository.findById(transactionDtoRequest.getId());
			if(!transOptional.isPresent()) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, " Transaction", response), HttpStatus.PRECONDITION_FAILED);
			}
			Transactions trans = transOptional.get();

			/*
			if (!TransactionMode.VERSEMENT_AFB.equals(trans.getTransactionMode()) && !TransactionMode.VERSEMENT_AFB.equals(trans.getTransactionMode())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, " Operation non conforme pour ce type de transaction", response), HttpStatus.PRECONDITION_FAILED);
			}
			 */
			if (!TransactionStatus.SUCCESS.equals(trans.getStatusTrans()) && !TransactionStatus.TO_CONFIRM.equals(trans.getStatusTrans()) && !TransactionStatus.ENCAISSEE.equals(trans.getStatusTrans())) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.InvalidStatus, "", response), HttpStatus.PRECONDITION_FAILED);
			}

			Partners partner = partnerRepository.findByPartcode(trans.getPartnerCode());
			response = printRecu(partner.getPartname(), trans);
			if (StringUtils.isNotBlank(response)) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", response), HttpStatus.OK);
			}
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", response), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), response), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public String printRecu(String partnerName, Transactions trans) {		

		try {

			DecimalFormat df = new DecimalFormat("#,###");
			List<Transactions> data = new ArrayList<Transactions>();
			data.add(trans);

			//Generation de l'etat PDF
			ParameterJasper parameterJasper = new ParameterJasper(); 
			parameterJasper.setReportName("bordereauVersement.jasper");
			parameterJasper.setMaCollection(data);

			List<ObjectFile> objects = new ArrayList<ObjectFile>();
			objects.add(new ObjectFile("IMAGE_AFB", "AFRILAND.jpg"));
			objects.add(new ObjectFile("IMAGE_ENTETEAFB", "ENTETE_AFB.png"));
			objects.add(new ObjectFile("partnerName", partnerName));
			objects.add(new ObjectFile("montant", df.format(trans.getAmount()) + " XAF"));
			parameterJasper.setObjects(objects);

			// TODO Auto-generated method stub
			//Construction de l'etat PDF	
			try {

				RestTemplate restTemplate = new RestTemplate();
				//*** final String uri = servicesystemUrl() + "/rest/api/generictool/editdocumentbyte";
				final String uri = "http://192.168.11.75:9001/system-service/rest/api/generictool/editdocumentbyte";

				byte[] bytes = restTemplate.postForObject(uri, parameterJasper, byte[].class); 

				log.info("" + Base64.getEncoder().encodeToString(bytes));
				if(bytes != null) return Base64.getEncoder().encodeToString(bytes);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}



	public String printEtatNivellement(FactMonth fac, String mois, String partnerCode, String partnerName, int nombre, Double amount) {		
		String result = "";

		try {

			DecimalFormat df = new DecimalFormat("#,###");
			List<FactMonth> data = new ArrayList<FactMonth>();
			data.add(fac);

			//Generation de l'etat PDF
			ParameterJasper parameterJasper = new ParameterJasper(); 
			parameterJasper.setReportName("RapportFact.jasper");
			parameterJasper.setMaCollection(data);

			List<ObjectFile> objects = new ArrayList<ObjectFile>();
			objects.add(new ObjectFile("IMAGE_AFB", "AFRILAND.jpg"));
			objects.add(new ObjectFile("IMAGE_PARTNER", partnerCode+".png"));
			objects.add(new ObjectFile("IMAGE_INTRA", "AFB_INTRA.png"));
			objects.add(new ObjectFile("partnerName", partnerName));
			objects.add(new ObjectFile("mois", mois));
			objects.add(new ObjectFile("titre", "Traitement des Transactions"));
			objects.add(new ObjectFile("nombreTrans", Integer.toString(nombre)));
			objects.add(new ObjectFile("amount", df.format(amount)));
			parameterJasper.setObjects(objects);

			// TODO Auto-generated method stub
			//Construction de l'etat PDF	
			try {

				RestTemplate restTemplate = new RestTemplate();
				//*** final String uri = servicesystemUrl() + "/rest/api/generictool/editdocumentbyte";
				final String uri = "http://192.168.11.75:9001/system-service/rest/api/generictool/editdocumentbyte";

				byte[] bytes = restTemplate.postForObject(uri, parameterJasper, byte[].class); 

				if(bytes != null) return Base64.getEncoder().encodeToString(bytes);
				result = "OK";

			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}



	public ResponseEntity<TransactionsResponse> saveListTransactions(List<Transactions> datas) {
		// TODO Auto-generated method stub
		try {
			for(Transactions typeForfaitDtoRequest : datas) {
				try {

					typeForfaitDtoRequest = repository.save(typeForfaitDtoRequest);


				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", null), HttpStatus.OK);
			}
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", null), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<TransactionsResponse>(new TransactionsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
