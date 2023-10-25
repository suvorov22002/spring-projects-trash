package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.enums.FpstatutFileTransaction;
import com.afriland.afbpaypartnerservices.jpa.Ventilation;

@Repository
public interface VentilationRepository extends JpaRepository<Ventilation, String>{
	
	public Optional<Ventilation> findByFileName(String fileName);
	public Optional<Ventilation> findByFileNameAndIntegration(String fileName, Boolean integration);
	public Ventilation findByFileNameAndIntegrationAndStatus(String fileName, Boolean integration, FpstatutFileTransaction statut);
	public Ventilation findByFileNameAndStatus(String fileName, FpstatutFileTransaction statut);
	public Ventilation findByReference(String reference);
	public List<Ventilation> findByIntegrationAndStatus(Boolean integration, FpstatutFileTransaction statut);
	
}
