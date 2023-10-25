package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.UserExternsDto;
import com.afriland.afbpaypartnerservices.response.UserExternsResponse;

public interface IUserExternService {

	public ResponseEntity<UserExternsResponse> saveUserExtern(UserExternsDto userExternDTORequest);

	public ResponseEntity<UserExternsResponse> saveListUserExterns(List<UserExternsDto> userExternDTORequests);

	public ResponseEntity<UserExternsResponse> updateUserExtern(UserExternsDto userExternDTORequest);

	public ResponseEntity<UserExternsResponse> deleteUserExtern(Long id);

	public ResponseEntity<UserExternsResponse> findByIdUserExtern(Long id);

	public ResponseEntity<UserExternsResponse> findByLogin(String login);
	
	public ResponseEntity<UserExternsResponse> findByLoginAndActif(String login);
	
	public ResponseEntity<UserExternsResponse> findByNomLike(String nom);

	public ResponseEntity<UserExternsResponse> getAllUserExterns();

	public ResponseEntity<UserExternsResponse> findByPartnerCode(String partnerCode);

}
