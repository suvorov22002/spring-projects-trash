package com.afriland.afbpaypartnerservices.utils;

import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import javax.servlet.http.HttpServletRequest;

import com.afriland.afbpaypartnerservices.enums.PayPartnerTypeString;

public class ToolsUtils {
	
	
	private static final String[] IP_HEADER_CANDIDATES = { 
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR" };
	
	
	public static final String FP_API_PAYMENT_SERVICE = "/payments/pay";
	
	public static String generateToken(int size, boolean alphabetic, boolean numeric){
		String result = "";
		
		if(Boolean.TRUE.equals(alphabetic) && Boolean.TRUE.equals(numeric)) result = RandomStringUtils.randomAlphanumeric(size).toUpperCase();
		if(Boolean.TRUE.equals(alphabetic) && Boolean.FALSE.equals(numeric)) result = RandomStringUtils.randomAlphabetic(size).toUpperCase();
		if(Boolean.FALSE.equals(alphabetic) && Boolean.TRUE.equals(numeric)) result = RandomStringUtils.randomNumeric(size).toUpperCase();
				
		return result;
	}
	
	
	public static String generateToken(int size, PayPartnerTypeString typeOTP){
		String result = "";
		
		System.out.println("---------- size ---------- : " + size);
		System.out.println("---------- typeOTP ---------- : " + typeOTP);
		switch (typeOTP) {
		case AN:
			result = RandomStringUtils.randomAlphanumeric(size).toUpperCase();
			break;

		case AL:
			result = RandomStringUtils.randomAlphabetic(size).toUpperCase();
			break;

		case NU:
			result = RandomStringUtils.randomNumeric(size).toUpperCase();
			break;

		default:
			result = RandomStringUtils.randomAlphanumeric(size).toUpperCase();
			break;
		}
		
		System.out.println("---------- result ---------- : " + result);
		return result;
	}
	
	
	public static final String TPW_API_KEY = "";
	public static final String TPW_VERIFY_SUBKEY_SERVICE = "/verifyActivationCode";
	public static final String TPW_DISABLE_SUBKEY_SERVICE = "/disableActivationCode";
	public static final String TPW_SEND_PAYMENT_KEY_SERVICE = "/setAfrilandPaymentCode";
	
	
		
	public static Boolean isPartnerAuthorized(HttpServletRequest request, String partnerCode){
		
		for (String header : IP_HEADER_CANDIDATES) {
	        String ip = request.getHeader(header);
//	        logger.info("REQUEST SOURCE IP BEFORE IF LOOP : "+ip);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
//	        	logger.info("REQUEST SOURCE IP IN IF LOOP : "+ip);
	        	ArrayList<String> listIP = new ArrayList<String>();
	        	listIP.add("172.21.240.9");
	        	listIP.add("172.21.240.10");
	        	listIP.add("172.21.240.12");
	        	if(listIP.contains(ip)) return Boolean.TRUE;
	            return Boolean.FALSE;
	        }
	    }
		
		return Boolean.FALSE;
	}
	
	
	public static String getUrl(String host, String port, String protocole, String path){
		return protocole+"://"+host+":"+port+path;
	}
	
	
}
