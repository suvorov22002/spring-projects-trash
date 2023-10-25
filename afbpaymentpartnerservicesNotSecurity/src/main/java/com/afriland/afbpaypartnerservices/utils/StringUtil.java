package com.afriland.afbpaypartnerservices.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static int TAILLE_CODE_EVE = 6;
	public static int LEFT = 1;
	public static int RIGHT = 2;

	/**
	 * Check if the given array contains the given value (with case-insensitive comparison).
	 *
	 * @param array The array
	 * @param value The value to search
	 * @return true if the array contains the value
	 */
	public static boolean containsIgnoreCase(String[] array, String value) {
		for (String str : array) {
			if (value == null && str == null) return true;
			if (value != null && value.equalsIgnoreCase(str)) return true;
		}
		return false;
	}

	/**
	 * Join an array of strings with the given separator.
	 * <p>
	 * Note: This might be replaced by utility method from commons-lang or guava someday
	 * if one of those libraries is added as dependency.
	 * </p>
	 *
	 * @param array     The array of strings
	 * @param separator The separator
	 * @return the resulting string
	 */
	public static String join(String[] array, String separator) {
		int len = array.length;
		if (len == 0) return "";

		StringBuilder out = new StringBuilder();
		out.append(array[0]);
		for (int i = 1; i < len; i++) {
			out.append(separator).append(array[i]);
		}
		return out.toString();
	}

	/**
	 * Generates a random string
	 * @return the result string
	 */
	@SuppressWarnings("deprecation")
	public static String stringGenerator() {
		long min = new Long("111111111"), max = new Long("99999999999");
		long v = (long)( Math.random()*( max - min + 1 )) + min;
		return String.valueOf(v);
	}

	/**
	 * Converts a float to string and remove the decimal part if it's zero.
	 * 145.00 => 145
	 * 78.36 => 78.36
	 * 0.4878 => 0.4878
	 * 82.0 => 82
	 * @param ft  the float to convert
	 * @return  the string result
	 */
	public static String floatConverter(Float ft) {
		if (ft == null) return null;
		if (ft % 1.0 != 0) {
			return String.format("%s", ft);
		} else {
			return String.format("%.0f", ft);
		}
	}

	public static String floatConverter(String s) {
		return s != null ? floatConverter(Float.valueOf(s)) : s;
	}



	public static String padText(String text, int direction, int lenght, String addString ){

		String s = text;

		if(s == null){

			for(int i=1; i<=lenght; i++) s += addString;

		}else{

			if(s.length() < lenght){

				while(s.length() < lenght){
					s = direction == LEFT ? addString + s : s + addString;
				}

			}

		}

		s = s.substring(0, lenght);

		return s;
	}



	public static String getCode(){
		return new SimpleDateFormat("ddMMyyhhmmss").format(new java.util.Date());
	}

	public static String getCode2(){
		return new SimpleDateFormat("ddMMyy").format(new java.util.Date());
	}



	public static String espacement(double montant){
		try{
			return espacement(new DecimalFormat("#0.###").format(new BigDecimal(montant)).replaceAll(",", "."));
		}catch(Exception e){
			return "0";
		}
	}


	public static String espacement(String mnt){
		try{
			if(mnt.isEmpty()) return "0";
			String partieDeci = ((mnt.indexOf(".")>=0)?(mnt.substring(mnt.indexOf("."), mnt.length()) ):(""));
			if(mnt.indexOf(".")>=0) mnt = mnt.substring(0, mnt.indexOf("."));
			if(mnt.indexOf(" ")>=0) mnt = mnt.replaceAll(" ", "");

			if(mnt.length()<=3){
				return mnt + partieDeci;
			}else{

				int nbreParties = ((mnt.length()%3)==0)?( (int)Math.ceil( (mnt.length()/3)  ) ):(  (int)Math.ceil(Double.parseDouble( String.valueOf( (mnt.length()/3) ).split(",")[0] + ".8" ))    );
				//System.out.println("Montant : " + mnt + ", nbre de parties = " + nbreParties);
				int i=0; String s = "";
				while(i<nbreParties && mnt.length()>0){
					s = " " + mnt.substring( ((mnt.length()>=3)?(mnt.length()-3):(0)) , mnt.length()) + s;
					mnt = mnt.substring(0, ((mnt.length()>=3)?(mnt.length()-3):(mnt.length())) );
					i++;
				}
				if(s.startsWith(" ")) s = s.substring(1, s.length());
				return s + partieDeci;
			}
		}catch(Exception e){
			e.printStackTrace();
			return mnt;
		}
	}

	public static String amountFormatter(double montant){
        try{
            return espacement(new DecimalFormat("#0.###").format(new BigDecimal(montant)).replaceAll(",", "."));
        }catch(Exception e){
            return "0";
        }
    }
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public static String Split2(String message){
		message = StringUtils.replace(message," ","+");
		message =  message.replace(" ","+").replace("  ","+").replace("   ","+").replace("    ","+").replace("     ","+").replace("      ","+");
		message =  message.replace(">","%3C");
		message =  message.replace("<","%3E");
		return message;
	}
	
	public static String Split(String message){
		try {
			return URIUtil.encodeQuery(message);
		} catch (URIException e) {
			// TODO Auto-generated catch block
			return Split2(message);
		}
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	public static String encodeQuery(String message){
		try {
			return URIUtil.encodeQuery(message);
		} catch (URIException e) {
			// TODO Auto-generated catch block
			return Split(message);
		}
	}
	
	
	

}
