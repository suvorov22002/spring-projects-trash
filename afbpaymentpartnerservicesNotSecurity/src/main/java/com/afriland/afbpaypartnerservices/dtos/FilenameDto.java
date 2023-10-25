package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;
import java.util.List;

public class FilenameDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> listFile;
	
	
	public FilenameDto() {
		super();
	}

	public FilenameDto(List<String> listFile) {
		super();
		this.listFile = listFile;
	}

	public List<String> getListFile() {
		return listFile;
	}

	public void setListFile(List<String> listFile) {
		this.listFile = listFile;
	}
	
	
}
