package com.afriland.afbpaypartnerservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.ErreursVentilation;

@Repository
public interface ErreursVentilationRepository extends JpaRepository<ErreursVentilation, String>{

}
