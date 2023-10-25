package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.afriland.afbpaypartnerservices.utils.DateUtil;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author yves_labo
 *
 */
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "PAYPART_EFIRST_TRANS")
public class EfirstTransactions extends EfirstTransactionBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
	
	@PrePersist
	public void prePersist() {
		Date date = DateUtil.parse(getDreq(), DateUtil.DATE_HOUR_FORMAT_MOMO);
		this.id = DateUtil.now(date).toString()+getNcp1().replace(" ","").trim()+String.valueOf(getMnt1().intValue())+String.valueOf(getNord().intValue());
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
