package com.afriland.afbpaypartnerservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.Bkeve;

@Repository
public interface BkeveRepository extends JpaRepository<Bkeve, String> {
	
	public Bkeve findByEveAndEta(String eveid, String eta);
}