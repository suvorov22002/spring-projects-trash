package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.response.VentilationResponse;

public interface IVentilationService {
	
	public ResponseEntity<VentilationResponse> checkNewFileVentilation();
	public ResponseEntity<VentilationResponse> processVentilation(String filename, String partcode, String user);
	public ResponseEntity<VentilationResponse> processVentilationListFile(List<String> filenameList, String partcode, String login);
	public ResponseEntity<VentilationResponse> ventilation(String id);
	public ResponseEntity<VentilationResponse> findVentilationAttente();
	public ResponseEntity<VentilationResponse> findFpDetailsVentilationByFileId(String ventilationId);
	public ResponseEntity<VentilationResponse> findFpErreursVentilationByFileId(String ventilationId);
}
