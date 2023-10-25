package com.afriland.afbpaypartnerservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.BkeveTemplate;

@Repository
public interface BkeveTemplateRepository extends JpaRepository<BkeveTemplate, String> {

	
}
