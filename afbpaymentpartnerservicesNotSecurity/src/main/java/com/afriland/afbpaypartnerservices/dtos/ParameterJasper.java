package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class ParameterJasper implements Serializable {


	private static final long serialVersionUID = 1L;

	private String reportName;
	
	private List<ObjectFile> objects;
	
	private Collection<?> maCollection;

	
	/**
	 * Constructeur par d�faut
	 */
	public ParameterJasper() {
		super();
	}


	/**
	 * @param reportName
	 * @param objects
	 * @param maCollection
	 */
	public ParameterJasper(String reportName, List<ObjectFile> objects, Collection<?> maCollection) {
		super();
		this.reportName = reportName;
		this.objects = objects;
		this.maCollection = maCollection;
	}



	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}




	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}




	/**
	 * @return the objects
	 */
	public List<ObjectFile> getObjects() {
		return objects;
	}




	/**
	 * @param objects the objects to set
	 */
	public void setObjects(List<ObjectFile> objects) {
		this.objects = objects;
	}




	/**
	 * @return the maCollection
	 */
	public Collection<?> getMaCollection() {
		return maCollection;
	}




	/**
	 * @param maCollection the maCollection to set
	 */
	public void setMaCollection(Collection<?> maCollection) {
		this.maCollection = maCollection;
	}


	@Override
	public String toString() {
		return "ParameterJasper [reportName=" + reportName + ", objects=" + objects + ", maCollection=" + maCollection
				+ "]";
	}



}
