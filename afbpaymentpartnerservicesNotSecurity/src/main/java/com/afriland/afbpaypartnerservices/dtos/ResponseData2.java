package com.afriland.afbpaypartnerservices.dtos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

/**
 * 
 * @author yves_labo
 *
 */
public class ResponseData2 implements Serializable {
	
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private String code; 

	private String error;

	private String message;
	
	private List<Map<String, Object>> data = new ArrayList<>();

	/**
	 * 
	 */
	public ResponseData2() {
		super();
	}

	/**
	 * @param code
	 * @param error
	 * @param message
	 * @param eve
	 */
	public ResponseData2(String code, String error, String message, Object data) {
		super();
		this.code = code;
		this.error = error;
		this.message = message;
		this.setData(data.toString());
	}

	public static ResponseData2 getIntance(){
		return new ResponseData2();
	}

	public ResponseData2(BkeveDtoResponce dto){
		super();
		this.code = dto.getCode();
		this.error = dto.getError();
		this.message = dto.getMessage();
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject(dto.getEve01()); 
		json.put(obj);
		obj = new JSONObject(dto.getEve02()); 
		json.put(obj);
		this.setData(json.toString());
	}


	public ResponseData2 event(JSONArray data) {
		this.setData(data.toString());
		return this;
	}

	public ResponseData2 error(String msg) {
		this.setCode(ResponseHolder.FAIL);
		this.setMessage("FAIL");
		this.setError(msg);
		this.setError(ResponseHolder.mapMessage.get(this.code));
		return this;
	}

	public ResponseData2 errorCode(String code) {
		this.setCode(code);
		this.setMessage("FAIL");
		this.setError(ResponseHolder.mapMessage.get(this.code));
		return this;
	}

	public ResponseData2 sucess(String msg) {
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
	public List<Map<String, Object>> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String jsonString) {
		
		List<Map<String, Object>> map = new Gson()
				.fromJson(jsonString, new TypeToken<List<HashMap<String, Object>>>() {
				}.getType());
		
		this.data = map;
	}


}
