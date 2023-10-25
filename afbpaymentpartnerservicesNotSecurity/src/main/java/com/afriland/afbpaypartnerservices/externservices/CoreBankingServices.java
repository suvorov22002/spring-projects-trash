package com.afriland.afbpaypartnerservices.externservices;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.afriland.afbpaypartnerservices.dtos.BkeveDto;
import com.afriland.afbpaypartnerservices.dtos.BkeveDtoResponce;
import com.afriland.afbpaypartnerservices.dtos.CustomerDetailsDto;
import com.afriland.afbpaypartnerservices.dtos.GenericObject;
import com.afriland.afbpaypartnerservices.dtos.ResponseBkcom;
import com.afriland.afbpaypartnerservices.dtos.ResponseCaisse;
import com.afriland.afbpaypartnerservices.dtos.ResponseCustomer;
import com.afriland.afbpaypartnerservices.dtos.ResponseData2;
import com.afriland.afbpaypartnerservices.dtos.ResponseData3;
import com.afriland.afbpaypartnerservices.jpa.Bkeve;
import com.afriland.afbpaypartnerservices.jpa.BkeveDTO;
import com.afriland.afbpaypartnerservices.jpa.Bkmvti;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * CoreBankingServices
 * @author yves_labo
 *  
 */
@Slf4j
public class CoreBankingServices {

	private static Logger log = LogManager.getLogger("CoreBankingServices.class");

	//*** private static String host = "";
	//*** private static String keysecurity = null;
	private static HttpHeaders headers = new HttpHeaders();
	
	
	public static void setHeader(String keysecurity) {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if(!StringUtils.isBlank(keysecurity)){
			headers.set("Authorization", keysecurity);
		}
		headers.set("Content-Type", "application/json");
		headers.set("Accept", "application/json"); 
	}


	public static DataResponseDTO getDateComptable(String keysecurity, String host) {
		String uridco = host+"/formatteddco";
		log.info("=======================uridco======================== : "+ uridco);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate client = new RestTemplate();
		ResponseEntity<DataResponseDTO> responsefindDCO = client.exchange(uridco, HttpMethod.GET, entity, DataResponseDTO.class); 
		return responsefindDCO.getBody();		 
	}
	
	
	public static DataResponseDTO getDateValeurDebit(String keysecurity, String host, String dco, String pdr) {
		String uridco = host+"/formatteddvadebit/"+dco+"/"+pdr;
		log.info("=======================uridco======================== : "+ uridco);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate client = new RestTemplate();
		ResponseEntity<DataResponseDTO> responsefindDCO = client.exchange(uridco, HttpMethod.GET, entity, DataResponseDTO.class); 
		return responsefindDCO.getBody();		 
	}
	
	
	public static DataResponseDTO getDateValeurCredit(String keysecurity, String host, String dco, String pdr) {
		String uridco = host+"/formatteddvacredit/"+dco+"/"+pdr;
		log.info("=======================uridco======================== : "+ uridco);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate client = new RestTemplate();
		ResponseEntity<DataResponseDTO> responsefindDCO = client.exchange(uridco, HttpMethod.GET, entity, DataResponseDTO.class); 
		return responsefindDCO.getBody();		 
	}


	public static ResponseCaisse findcaisses(String keysecurity, String host, String userCaisse) {
		RestTemplate client = new RestTemplate();
		String urifindcaisses = host+"/findcaisses/"+userCaisse;
		log.info("=======================urifindcaisses======================== : "+ urifindcaisses);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<ResponseCaisse> responsefindcaisses = client.exchange(urifindcaisses, HttpMethod.GET, entity, ResponseCaisse.class); 
		return responsefindcaisses.getBody();
	}
	
	
	public static ResponseData3 getbalance(String keysecurity, String host, String account) {
		RestTemplate client = new RestTemplate();
		String urigetbalance = host+"/getbalance/"+account.split("-")[0]+"/"+account.split("-")[1];
		log.info("=======================urigetbalance======================== : "+ urigetbalance);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		try {
			ResponseEntity<ResponseData3> responsegetbalance = client.exchange(urigetbalance, HttpMethod.GET, entity, ResponseData3.class); 
			log.info("=======================responsegetbalance.getBody()======================== : "+ responsegetbalance.getBody());
			return responsegetbalance.getBody();
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ResponseCustomer getcustomerinfo(String keysecurity, String host, String codeClient) {
		RestTemplate client = new RestTemplate();
		String uriCustumerInfo = host+"/getcustomerinfo/"+codeClient; 
		log.info("=======================getcustomerinfo======================== : "+ uriCustumerInfo);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<ResponseCustomer> responseCustomer = client.exchange(uriCustumerInfo, HttpMethod.GET, entity, ResponseCustomer.class); 
		return responseCustomer.getBody();
	}
	
	
	public static ResponseBkcom getaccountinfo(String keysecurity, String host, String age, String ncp) {
		RestTemplate client = new RestTemplate();
		String uriAccountInfo = host+"/getaccountinfo/"+age+"/"+ncp;    
		log.info("=======================uriAccountInfo======================== : "+ uriAccountInfo);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<ResponseBkcom> responseBkcom = client.exchange(uriAccountInfo, HttpMethod.GET, entity, ResponseBkcom.class); 
		return responseBkcom.getBody();
	}

	
	public static ResponseData2 getoppoCompte(String keysecurity, String host, String account) {
		RestTemplate client = new RestTemplate();
		String urigetOppoCompte = host+"/oppos-compte/"+account.split("-")[0]+"/"+account.split("-")[1];
		log.info("=======================urigetOppoCompte======================== : "+ urigetOppoCompte);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<ResponseData2> responsegetoppoCompte = client.exchange(urigetOppoCompte, HttpMethod.GET, entity, ResponseData2.class); 
		return responsegetoppoCompte.getBody();
	}
	
	
	public static BkeveDTO postEvent(String keysecurity, String host, Bkeve eve) {
		RestTemplate client = new RestTemplate();
		String uriEvent = host+"/event";
		log.info("=======================uriEvent======================== : "+ uriEvent);
		setHeader(keysecurity);
		HttpEntity<Bkeve> entity = new HttpEntity<Bkeve>(eve,headers);
		ResponseEntity<BkeveDTO> responseevent = client.exchange(uriEvent, HttpMethod.POST, entity, BkeveDTO.class);     
		return responseevent.getBody();
	}
	
	
	public static DataResponseDTO postAccountingentries(String keysecurity, String host, List<Bkmvti> entries) {
		RestTemplate client = new RestTemplate();
		String uriaccountingentries = host+"/accountingentries";
		log.info("======================= uriAccountingentries ======================== : "+ uriaccountingentries);
		setHeader(keysecurity);
		HttpEntity<List<Bkmvti>> entity = new HttpEntity<List<Bkmvti>>(entries,headers);
		try {
			ResponseEntity<DataResponseDTO> responseaccountingentries = client.exchange(uriaccountingentries, HttpMethod.POST, entity, DataResponseDTO.class);     
			return responseaccountingentries.getBody();
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static BkeveDtoResponce depositEvent(String keysecurity, String host, BkeveDto dto) {
		RestTemplate client = new RestTemplate();
		String uridepositevent = host+"/depositevent";
		log.info("=======================uridepositevent======================== : "+ uridepositevent);
		setHeader(keysecurity);
		HttpEntity<BkeveDto> entity = new HttpEntity<BkeveDto>(dto,headers);
		ResponseEntity<BkeveDtoResponce> responseevent = client.exchange(uridepositevent, HttpMethod.POST, entity, BkeveDtoResponce.class);     
		return responseevent.getBody();
	}
	
	
	public static DataResponseDTO statutEvent(String keysecurity, String host, String agence, String eve, String ope, Date dco) {
		RestTemplate client = new RestTemplate();
		String uri = host+"/checkstatusevent/"+agence+"/"+eve+"/"+ope+"/"+DateUtil.format(dco, DateUtil.DATE_MINUS_FORMAT_SINGLE);
		setHeader(keysecurity);
		log.info("=======================statuseventuri======================== : "+uri);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<DataResponseDTO> response = client.exchange(uri, HttpMethod.GET, entity, DataResponseDTO.class);
		return response.getBody();
	}
	
		
	public static String checkebankingstatusevent(String keysecurity, String host, String age1, String ncp1, String clc1, String age2, String ncp2, String clc2, Integer mon2, String eta, String uti, String dsai) {
		RestTemplate client = new RestTemplate();
		String uri = host+"/checkebankingstatusevent/"+age1+"/"+ncp1+"/"+clc1+"/"+age2+"/"+ncp2+"/"+clc2+"/"+mon2+"/"+eta+"/"+uti+"/"+dsai;
		setHeader(keysecurity);
		log.info("=======================checkebankingstatuseventuri======================== : "+uri);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
		return response.getBody();
	}
	
	
	public static String getebankingtransactionsToday(String keysecurity, String host, String ncp) {
		RestTemplate client = new RestTemplate();
		String urigetebankingtransactionsToday = host+"/getebankingtransactionsToday/"+ncp;
		log.info("=======================urigetebankingtransactionsToday======================== : "+ urigetebankingtransactionsToday);
		setHeader(keysecurity);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responsegetoppoCompte = client.exchange(urigetebankingtransactionsToday, HttpMethod.GET, entity, String.class); 
		return responsegetoppoCompte.getBody();
	}
		
	
	public static CustomerDetailsDto findcustomerinfo(String keysecurity, String linkcustomerinfo, String codeclient) {
		RestTemplate client = new RestTemplate();
		String uri = linkcustomerinfo+"/findcustomercbs?subcode="+codeclient;
		
		setHeader(keysecurity);
		log.info("=======================findcustomerinfo======================== : "+uri);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<CustomerDetailsDto> response = client.exchange(uri, HttpMethod.GET, entity, CustomerDetailsDto.class);
		return response.getBody();
	}
	
	
	public static String liensig(String keysecurity, String linkcustomerinfo, String age, String ncp, String suf, String cli, String datecstring, String heurec, String utic) {
		RestTemplate client = new RestTemplate();
		String uri = linkcustomerinfo+"/liensig?age="+age+"&ncp="+ncp+"&suf="+suf+"&cli="+cli+"&datecstring="+datecstring+"&heurec="+heurec+"&utic="+utic;
		        
		setHeader(keysecurity);
		log.info("=======================liensig======================== : "+uri);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
		return response.getBody();
	}
	
	
	public static String checktransbkhis(String eve, String ope, String dco) {
		String result = "";
		RestTemplate client = new RestTemplate();
		final String uri = "192.168.11.75:9001/datasystem-service/rest/api/servicescbs/getdossierimpayes/" + eve + "/" + ope + "/" + dco;
		log.info("************** checktransbkhis uri ***************" + uri);

		GenericObject[] trans = client.getForObject(uri,GenericObject[].class);
		if(trans == null) return result;
				
		for(GenericObject t : trans) {
			result = t.getVal2();
		}
		
		return result;
	}
	
	
}
