package com.afriland.afbpaypartnerservices.jpa;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BasicSampleData {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne @JoinColumn(name="idoperator", nullable=false)
    private Operators operator;
    
    @Column
	private String datavalue;

    
    public BasicSampleData(Operators operator, String datavalue) {
		this.operator = operator;
		this.datavalue = datavalue;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the operator
	 */
	public Operators getOperator() {
		return operator;
	}


	/**
	 * @param operators the operator to set
	 */
	public void setOperator(Operators operator) {
		this.operator = operator;
	}


	/**
	 * @return the datavalue
	 */
	public String getDatavalue() {
		return datavalue;
	}


	/**
	 * @param datavalue the datavalue to set
	 */
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}
    
}
