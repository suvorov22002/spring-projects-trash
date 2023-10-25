package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "PAYPART_OPERATOR_ACCOUNTS")
public class OperatorAccounts extends BasicSampleData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public OperatorAccounts(Operators operator, String datavalue) {
		super(operator, datavalue);
	}

}
