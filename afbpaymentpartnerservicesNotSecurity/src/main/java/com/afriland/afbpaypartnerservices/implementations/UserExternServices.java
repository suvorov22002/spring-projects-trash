package com.afriland.afbpaypartnerservices.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.afriland.afbpaypartnerservices.dtos.UserExternsDto;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.jpa.Partners;
import com.afriland.afbpaypartnerservices.jpa.UserExterns;
import com.afriland.afbpaypartnerservices.repositories.PartnerRepository;
import com.afriland.afbpaypartnerservices.repositories.UserExternRepository;
import com.afriland.afbpaypartnerservices.response.UserExternsResponse;
import com.afriland.afbpaypartnerservices.services.IUserExternService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;



@Service("userexternservice")
@Transactional
public class UserExternServices implements IUserExternService {

	@Autowired
	private UserExternRepository userExternRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@PersistenceContext
	private EntityManager entityManager;


	Logger logger = LoggerFactory.getLogger(UserExternServices.class);


	@Override
	public ResponseEntity<UserExternsResponse> saveUserExtern(UserExternsDto userExternDtoRequest) {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			if (userExternDtoRequest == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(userExternDtoRequest.getLogin())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " ( User name)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getNom())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " ( Nom)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getPassword())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Password)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (userExternDtoRequest.getRoles() == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Les roles)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getPartnerCode())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getUticreation())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Utilisateur de création)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			//Controle des infos de partenaire
			Partners partner = partnerRepository.findByPartcode(userExternDtoRequest.getPartnerCode());
			if (partner == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " Partner code", datas), HttpStatus.PRECONDITION_FAILED);
			}
			
			Optional<UserExterns> existingUserExtern = userExternRepository.findByLogin(userExternDtoRequest.getLogin()); 
			if (existingUserExtern.isPresent()) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}

			Boolean existUser = userExternRepository.existsByEmail(userExternDtoRequest.getEmail()); 
			if (existUser) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, " Code client et partner code", datas), HttpStatus.CONFLICT);
			}

			UserExterns userExternResponse = ObjectMapperUtils.mapUserExternDtoToUserExtern(userExternDtoRequest);
			userExternResponse.setActif(Boolean.TRUE);
			userExternResponse.setValidfrom(new Date());
			userExternResponse.setUticreation(userExternDtoRequest.getUticreation());
			userExternResponse = userExternRepository.save(userExternResponse);
			if (userExternResponse != null) {
				datas.add(ObjectMapperUtils.mapUserExternToUserExternDto(userExternResponse));
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<UserExternsResponse> saveListUserExterns(List<UserExternsDto> userExternDtoRequests) {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			for(UserExternsDto userExternDtoRequest : userExternDtoRequests) {
				try {
					if (userExternDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(userExternDtoRequest.getLogin())) {
						break;
					}
					if (StringUtils.isBlank(userExternDtoRequest.getNom())) {
						break;
					}
					if (StringUtils.isBlank(userExternDtoRequest.getPassword())) { 
						break;
					}
					if (userExternDtoRequest.getRoles() == null) {
						break;
					}
					if (StringUtils.isBlank(userExternDtoRequest.getPartnerCode())) {
						break;
					}
					if (StringUtils.isBlank(userExternDtoRequest.getPartnerCode())) {
						break;
					}
					if (StringUtils.isBlank(userExternDtoRequest.getUticreation())) {
						break;
					}

					//Controle des infos de partenaire
					Partners partner = partnerRepository.findByPartcode(userExternDtoRequest.getPartnerCode());
					if (partner == null) {
						break;
					}
					
					Optional<UserExterns> existingUserExtern = userExternRepository.findByLogin(userExternDtoRequest.getLogin()); 
					if (existingUserExtern.isPresent()) {
						break;
					}

					UserExterns userExternResponse = ObjectMapperUtils.mapUserExternDtoToUserExtern(userExternDtoRequest);					
					userExternResponse.setActif(Boolean.TRUE);
					userExternResponse.setValidfrom(new Date());
					userExternResponse.setUticreation(userExternDtoRequest.getUticreation());
					userExternResponse = userExternRepository.save(userExternResponse);

					if (userExternResponse != null) {
						datas.add(ObjectMapperUtils.mapUserExternToUserExternDto(userExternResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<UserExternsResponse> updateUserExtern(UserExternsDto userExternDtoRequest) {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {					

			if (userExternDtoRequest == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(userExternDtoRequest.getLogin())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " ( User name)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getNom())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " ( Nom)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getPassword())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Password)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (userExternDtoRequest.getRoles() == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Les roles)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getPartnerCode())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(userExternDtoRequest.getUticreation())) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Utilisateur de création)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Optional<UserExterns> optionalUserExtern = userExternRepository.findById(userExternDtoRequest.getId()); 
			if (optionalUserExtern == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			UserExterns userExterns = optionalUserExtern.get();
			userExterns.setActif(userExternDtoRequest.isActif());
			userExterns.setPassword(userExternDtoRequest.getPassword());
			userExterns.setRoles(userExternDtoRequest.getRoles());
			userExterns.setValidTo(new Date());
			
			userExterns = userExternRepository.save(userExterns);
			if (userExterns != null) {
				List<UserExternsDto> parts = new ArrayList<UserExternsDto>();
				parts.add(ObjectMapperUtils.mapUserExternToUserExternDto(userExterns));
				datas = parts;
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<UserExternsResponse> deleteUserExtern(Long id) {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			if (id == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			userExternRepository.deleteById(id);
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<UserExternsResponse> findByIdUserExtern(Long id) {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			if (id == null) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<UserExterns> optionalUserExtern = userExternRepository.findById(id);
			if (optionalUserExtern != null) {
				UserExterns userExternResponse = optionalUserExtern.get();
				datas.add(ObjectMapperUtils.mapUserExternToUserExternDto(userExternResponse));
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<UserExternsResponse> getAllUserExterns() {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			List<UserExterns> userExterns = userExternRepository.findAll();
			if (userExterns != null && !CollectionUtils.isEmpty(userExterns)) {
				datas = userExterns.stream().map(response -> {
					return ObjectMapperUtils.mapUserExternToUserExternDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<UserExternsResponse> findByLogin(String login) {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			if (StringUtils.isBlank(login)) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Login)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<UserExterns> userExternResponse = userExternRepository.findByLogin(login);
			if (userExternResponse.isPresent()) {
				datas.add(ObjectMapperUtils.mapUserExternToUserExternDto(userExternResponse.get()));
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<UserExternsResponse> findByLoginAndActif(String login) {
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			if (StringUtils.isBlank(login)) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Login)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<UserExterns> userExternResponse = userExternRepository.findByLoginAndActif(login, Boolean.TRUE);
			if (userExternResponse.isPresent()) {
				datas.add(ObjectMapperUtils.mapUserExternToUserExternDto(userExternResponse.get()));
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<UserExternsResponse> findByNomLike(String nom) {

		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			List<UserExterns> userExterns = userExternRepository.findByNomLikeOrderByNomAsc((new StringBuilder()).append("%").append(nom).append("%").toString());
			if (userExterns != null && !CollectionUtils.isEmpty(userExterns)) {
				datas = userExterns.stream().map(response -> {
					return ObjectMapperUtils.mapUserExternToUserExternDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<UserExternsResponse> findByPartnerCode(String partnerCode){
		// TODO Auto-generated method stub
		List<UserExternsDto> datas = new ArrayList<UserExternsDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}		
			List<UserExterns> userExterns = userExternRepository.findByPartnerCodeOrderByNomAsc(partnerCode);
			if (userExterns != null && !CollectionUtils.isEmpty(userExterns)) {
				datas = userExterns.stream().map(response -> {
					return ObjectMapperUtils.mapUserExternToUserExternDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<UserExternsResponse>(new UserExternsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
