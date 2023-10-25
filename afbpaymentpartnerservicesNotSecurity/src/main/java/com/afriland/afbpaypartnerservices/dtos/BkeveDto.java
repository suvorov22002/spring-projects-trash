package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

import com.afriland.afbpaypartnerservices.jpa.Bkeve;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BkeveDto implements Serializable{

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private Bkeve eve01;
	
	private Bkeve eve02;
	

	/**
	 * 
	 */
	public BkeveDto() {
		super();
	}

	/**
	 * @return the eve01
	 */
	public Bkeve getEve01() {
		return eve01;
	}

	/**
	 * @param eve01 the eve01 to set
	 */
	public void setEve01(Bkeve eve01) {
		this.eve01 = eve01;
	}

	/**
	 * @return the eve02
	 */
	public Bkeve getEve02() {
		return eve02;
	}

	/**
	 * @param eve02 the eve02 to set
	 */
	public void setEve02(Bkeve eve02) {
		this.eve02 = eve02;
	}
	
	
}