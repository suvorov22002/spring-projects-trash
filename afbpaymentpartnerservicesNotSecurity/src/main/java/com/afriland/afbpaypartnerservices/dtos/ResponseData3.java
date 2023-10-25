package com.afriland.afbpaypartnerservices.dtos;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author yves_labo
 *
 */
public class ResponseData3 implements Serializable{

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private String code; 

	private String error;

	private String message;

	private Map<String, Object> data = new HashMap<>();

	/**
	 * 
	 */
	public ResponseData3() {
		super();
	}

	/**
	 * @param code
	 * @param error
	 * @param message
	 * @param eve
	 */
	public ResponseData3(String code, String error, String message, Object data) {
		super();
		this.code = code;
		this.error = error;
		this.message = message;
		this.setData(data.toString());
	}

	public static ResponseData3 getIntance(){
		return new ResponseData3();
	}

	public ResponseData3 event(JSONObject data) {
		this.setData(data.toString());
		return this;
	}

	public ResponseData3 event(String data) {
		this.setData(data);
		return this;
	}

	public ResponseData3 error(String msg) {
		this.setCode(ResponseHolder.FAIL);
		this.setMessage("FAIL");
		this.setError(msg);
		this.setError(ResponseHolder.mapMessage.get(this.code));
		return this;
	}

	public ResponseData3 errorCode(String code) {
		this.setCode(code);
		this.setMessage("FAIL");
		this.setError(ResponseHolder.mapMessage.get(this.code));
		return this;
	}

	public ResponseData3 sucess(String msg) {
		this.setCode(ResponseHolder.SUCESS);
		this.setMessage("SUCCESS");
		this.setError(msg);
		this.setMessage(ResponseHolder.mapMessage.get(this.code));
		this.setError(ResponseHolder.mapMessage.get(this.code));
		return this;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String jsonString) {
		// New HashMap obj
        Map<String, Object> hashMap
            = new HashMap<String, Object>();
  
        // split the String by a comma
        String parts[] = jsonString.split(",");
  
        // iterate the parts and add them to a HashMap
        for (String part : parts) {        	
        	if(part.contains(":")) {
        		//** System.out.println("*************** part ************** : "  + part);
            	 // split the student data by colon to get the
                // name and roll number
                String stuData[] = part.split(":");
                
                String stuRollNo = stuData[0].trim();
                String stuName = stuData[1].trim();
                // Add to map
                hashMap.put(stuRollNo, stuName);
        	}        	
        }
        this.data = hashMap;
	}

}
