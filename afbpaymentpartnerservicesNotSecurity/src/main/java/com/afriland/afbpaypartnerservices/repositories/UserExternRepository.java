package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.UserExterns;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface UserExternRepository extends JpaRepository<UserExterns,Long>{
		
	Optional<UserExterns> findByLogin(String login);
	
	Optional<UserExterns> findByLoginAndActif(String login, boolean actif);

	Boolean existsByLogin(String login);

	Boolean existsByEmail(String email);
	
	public List<UserExterns> findByNomLikeOrderByNomAsc(String nom);
	
	public List<UserExterns> findByPartnerCodeOrderByNomAsc(String partnerCode);
							
}