package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afriland.afbpaypartnerservices.enums.FpstatutFileTransaction;
import com.afriland.afbpaypartnerservices.jpa.DetailsVentilation;
import com.afriland.afbpaypartnerservices.jpa.Ventilation;

public interface DetailsVentilationRepository extends JpaRepository<DetailsVentilation, String> {
	
	public List<DetailsVentilation> findByParentAndStatus(Ventilation parent, FpstatutFileTransaction status);
	public List<DetailsVentilation> findByParent(Ventilation parent);
	public List<DetailsVentilation> findByReferenceTransactionAndSensAndStatus(String referenceTransaction, String sens, FpstatutFileTransaction status);
	public List<DetailsVentilation> findByLigneAndSensAndStatus(String ligne, String sens, FpstatutFileTransaction status);
}
