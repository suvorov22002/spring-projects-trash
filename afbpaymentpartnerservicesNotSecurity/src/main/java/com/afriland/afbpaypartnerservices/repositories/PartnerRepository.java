package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.Partners;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partners,String>{
		
	public Partners findByPartcode(String partcode);
	
	public Partners findByPartcodeAndActif(String partcode, boolean actif);
	
	public Partners findByPartcodeAndPwd(String partcode, String pwd);
	
	public Partners findByPartcodeAndPwdAndActif(String partcode, String pwd, boolean actif);
		
	public List<Partners> findByPartnameLike(String partname);
							
}