package com.afriland.afbpaypartnerservices.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.afriland.afbpaypartnerservices.dtos.PartnersDto;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.jpa.Partners;
import com.afriland.afbpaypartnerservices.repositories.PartnerRepository;
import com.afriland.afbpaypartnerservices.response.PartnersResponse;
import com.afriland.afbpaypartnerservices.services.IPartnerService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;



@Service("partnerservice")
@Transactional
public class PartnerServices implements IPartnerService {

	@Autowired
	private PartnerRepository partnerRepository;

	@PersistenceContext
	private EntityManager entityManager;


	Logger logger = LoggerFactory.getLogger(PartnerServices.class);


	@Override
	public ResponseEntity<PartnersResponse> savePartner(PartnersDto partnerDtoRequest) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			if (partnerDtoRequest == null) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(partnerDtoRequest.getPartcode())) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partcode)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(partnerDtoRequest.getPartname())) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partname)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(partnerDtoRequest.getAccount())) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (account)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Partners existingPartner = partnerRepository.findByPartcode(partnerDtoRequest.getPartcode()); 
			if (existingPartner != null) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}

			Partners partnerResponse = ObjectMapperUtils.mapPartnerDtoToPartner(partnerDtoRequest);
			if(partnerDtoRequest.getActif() == null) partnerResponse.setActif(Boolean.TRUE);			
			partnerResponse = partnerRepository.save(partnerResponse);
			if (partnerResponse != null) {
				datas.add(ObjectMapperUtils.mapPartnerToPartnerDto(partnerResponse));
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PartnersResponse> saveListPartners(List<PartnersDto> partnerDtoRequests) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			for(PartnersDto partnerDtoRequest : partnerDtoRequests) {
				try {
					if (partnerDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(partnerDtoRequest.getPartcode())) {
						break;
					}
					if (StringUtils.isBlank(partnerDtoRequest.getPartname())) {
						break;
					}
					if (StringUtils.isBlank(partnerDtoRequest.getAccount())) {
						break;
					}

					Partners existingPartner = partnerRepository.findByPartcode(partnerDtoRequest.getPartcode()); 
					if (existingPartner != null) {
						break;
					}

					Partners partnerResponse = ObjectMapperUtils.mapPartnerDtoToPartner(partnerDtoRequest);
					if(partnerDtoRequest.getActif() == null) partnerResponse.setActif(Boolean.TRUE);			
					partnerResponse = partnerRepository.save(partnerResponse);

					if (partnerResponse != null) {
						datas.add(ObjectMapperUtils.mapPartnerToPartnerDto(partnerResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<PartnersResponse> updatePartner(PartnersDto partnerDtoRequest) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {					
			
			if (partnerDtoRequest == null) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(partnerDtoRequest.getId())) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + "(Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(partnerDtoRequest.getPartcode())) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partcode)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(partnerDtoRequest.getPartname())) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partname)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(partnerDtoRequest.getAccount())) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (account)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Optional<Partners> optionalPartner = partnerRepository.findById(partnerDtoRequest.getId()); 
			if (optionalPartner == null) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			Partners partners = optionalPartner.get();
			partners.setActif(partnerDtoRequest.getActif() != null ? partnerDtoRequest.getActif() : Boolean.TRUE);	
			partners.setKycPartner(partnerDtoRequest.getKycPartner() != null ? partnerDtoRequest.getKycPartner() : Boolean.TRUE);	
			partners.setComptabilisationTransit(partnerDtoRequest.getComptabilisationTransit() != null ? partnerDtoRequest.getComptabilisationTransit() : Boolean.TRUE);	
			
			if(StringUtils.isNotBlank(partnerDtoRequest.getAccount())) partners.setAccount(partnerDtoRequest.getAccount());
			if(StringUtils.isNotBlank(partnerDtoRequest.getApiKey())) partners.setApiKey(partnerDtoRequest.getApiKey());
			if(StringUtils.isNotBlank(partnerDtoRequest.getEmails())) partners.setEmails(partnerDtoRequest.getEmails());
			if(StringUtils.isNotBlank(partnerDtoRequest.getHost())) partners.setHost(partnerDtoRequest.getHost());
			if(StringUtils.isNotBlank(partnerDtoRequest.getPath())) partners.setPath(partnerDtoRequest.getPath());
			if(StringUtils.isNotBlank(partnerDtoRequest.getPartnerNcpVersement())) partners.setPartnerNcpVersement(partnerDtoRequest.getPartnerNcpVersement());
			if(StringUtils.isNotBlank(partnerDtoRequest.getPhones())) partners.setPhones(partnerDtoRequest.getPhones());
			if(partnerDtoRequest.getPort() != null) partners.setPort(partnerDtoRequest.getPort());
			if(StringUtils.isNotBlank(partnerDtoRequest.getProtocole())) partners.setProtocole(partnerDtoRequest.getProtocole());
			if(StringUtils.isNotBlank(partnerDtoRequest.getPwd())) partners.setPwd(partnerDtoRequest.getPwd());
			if(partnerDtoRequest.getSizeKey() != null) partners.setSizeKey(partnerDtoRequest.getSizeKey());
			if(partnerDtoRequest.getTypekey() != null) partners.setTypekey(partnerDtoRequest.getTypekey());
			partners.setValidTo(new Date());
						
			partners = partnerRepository.save(partners);
			if (partners != null) {
				List<PartnersDto> parts = new ArrayList<PartnersDto>();
				parts.add(ObjectMapperUtils.mapPartnerToPartnerDto(partners));
				datas = parts;
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PartnersResponse> deletePartner(String id) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			partnerRepository.deleteById(id);
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PartnersResponse> findByIdPartner(String id) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<Partners> optionalPartner = partnerRepository.findById(id);
			if (optionalPartner != null) {
				Partners partnerResponse = optionalPartner.get();
				datas.add(ObjectMapperUtils.mapPartnerToPartnerDto(partnerResponse));
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PartnersResponse> getAllPartners() {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			List<Partners> partners = partnerRepository.findAll();
			if (partners != null && !CollectionUtils.isEmpty(partners)) {
				datas = partners.stream().map(response -> {
					return ObjectMapperUtils.mapPartnerToPartnerDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<PartnersResponse> findByPartcode(String partcode) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			if (StringUtils.isBlank(partcode)) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partcode)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Partners partnerResponse = partnerRepository.findByPartcode(partcode);
			if (partnerResponse != null) {
				datas.add(ObjectMapperUtils.mapPartnerToPartnerDto(partnerResponse));
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PartnersResponse> findByPartcodeAndActif(String partcode, Boolean actif) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			if (StringUtils.isBlank(partcode)) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partcode)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (actif == null) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (actif)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Partners partnerResponse = partnerRepository.findByPartcodeAndActif(partcode, actif);
			if (partnerResponse != null) {
				datas.add(ObjectMapperUtils.mapPartnerToPartnerDto(partnerResponse));
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PartnersResponse> findByPartnameLike(String partname) {

		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			List<Partners> partners = partnerRepository.findByPartnameLike((new StringBuilder()).append("%").append(partname).append("%").toString());
			if (partners != null && !CollectionUtils.isEmpty(partners)) {
				datas = partners.stream().map(response -> {
					return ObjectMapperUtils.mapPartnerToPartnerDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<PartnersResponse> findByPartcodeAndPwd(String partcode, String pwd) {

		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			if (StringUtils.isBlank(partcode)) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partcode)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(pwd)) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (pwd)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			
			Partners partnerResponse = partnerRepository.findByPartcodeAndPwd(partcode, pwd);
			if (partnerResponse != null) {
				datas.add(ObjectMapperUtils.mapPartnerToPartnerDto(partnerResponse));
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<PartnersResponse> findByPartcodeAndPwdAndActif(String partcode, String pwd, Boolean actif) {
		// TODO Auto-generated method stub
		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {
			if (StringUtils.isBlank(partcode)) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partcode)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (actif == null) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (actif)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Partners partnerResponse = partnerRepository.findByPartcodeAndPwdAndActif(partcode, pwd, actif);
			if (partnerResponse != null) {
				datas.add(ObjectMapperUtils.mapPartnerToPartnerDto(partnerResponse));
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


	@Override
	public ResponseEntity<PartnersResponse> findPartner(PartnersDto partnerDtoRequest) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Partners> cq = cb.createQuery(Partners.class);

		Root<Partners> partners = cq.from(Partners.class);
		List<Predicate> predicates = new ArrayList<>();

		List<PartnersDto> datas = new ArrayList<PartnersDto>();

		try {			
			if (partnerDtoRequest == null) {
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			if (StringUtils.isNotBlank(partnerDtoRequest.getPartcode())) {
				predicates.add(cb.equal(partners.get("partcode"), partnerDtoRequest.getPartcode()));
			}			
			if (StringUtils.isNotBlank(partnerDtoRequest.getPartname())) {
				predicates.add(cb.like(partners.get("partname"), "%" + partnerDtoRequest.getPartname() + "%"));
			}
			if (StringUtils.isNotBlank(partnerDtoRequest.getAccount())) {
				predicates.add(cb.equal(partners.get("account"), partnerDtoRequest.getAccount()));
			}			
			if (StringUtils.isNotBlank(partnerDtoRequest.getEmails())) {
				predicates.add(cb.like(partners.get("emails"), "%" + partnerDtoRequest.getEmails() + "%"));
			}
			if (StringUtils.isNotBlank(partnerDtoRequest.getPhones())) {
				predicates.add(cb.like(partners.get("phones"), "%" + partnerDtoRequest.getPhones() + "%"));
			}
			if (partnerDtoRequest.getActif() != null) {
				predicates.add(cb.equal(partners.get("actif"), partnerDtoRequest.getActif()));
			}
			if (partnerDtoRequest.getKycPartner() != null) {
				predicates.add(cb.equal(partners.get("kycPartner"), partnerDtoRequest.getKycPartner()));
			}

			cq.where(predicates.toArray(new Predicate[0]));

			List<Partners> partnersResponse = entityManager.createQuery(cq).getResultList();
			if (partnersResponse != null && !CollectionUtils.isEmpty(partnersResponse)) {
				datas = partnersResponse.stream().map(response -> {
					return ObjectMapperUtils.mapPartnerToPartnerDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PartnersResponse>(new PartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
}
