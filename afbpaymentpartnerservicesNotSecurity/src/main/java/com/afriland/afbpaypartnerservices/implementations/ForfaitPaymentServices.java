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

import com.afriland.afbpaypartnerservices.dtos.ForfaitPaymentsDto;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.jpa.ForfaitPayments;
import com.afriland.afbpaypartnerservices.repositories.ForfaitPaymentRepository;
import com.afriland.afbpaypartnerservices.response.ForfaitPaymentsResponse;
import com.afriland.afbpaypartnerservices.services.IForfaitPaymentService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;



@Service("forfaitpaymentservice")
@Transactional
public class ForfaitPaymentServices implements IForfaitPaymentService {

	@Autowired
	private ForfaitPaymentRepository forfaitPaymentRepository;


	@PersistenceContext
	private EntityManager entityManager;


	Logger logger = LoggerFactory.getLogger(ForfaitPaymentServices.class);


	@Override
	public ResponseEntity<ForfaitPaymentsResponse> saveForfaitPayment(ForfaitPaymentsDto categoryPaymentDtoRequest) {
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			if (categoryPaymentDtoRequest == null) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getCode())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getLibelle())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (categoryPaymentDtoRequest.getAmount() == null) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Amount)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getTypeForfait())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Type forfait)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getPartnerCode())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			ForfaitPayments existingForfaitPayment = forfaitPaymentRepository.findByCode(categoryPaymentDtoRequest.getCode()); 
			if (existingForfaitPayment != null) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}

			ForfaitPayments categoryPaymentResponse = ObjectMapperUtils.mapForfaitPaymentsDtoToForfaitPayments(categoryPaymentDtoRequest);
			categoryPaymentResponse = forfaitPaymentRepository.save(categoryPaymentResponse);
			if (categoryPaymentResponse != null) {
				datas.add(ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(categoryPaymentResponse));
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> saveListForfaitPayments(List<ForfaitPaymentsDto> categoryPaymentDtoRequests) {
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			for(ForfaitPaymentsDto categoryPaymentDtoRequest : categoryPaymentDtoRequests) {
				try {
					/*
					if (categoryPaymentDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(categoryPaymentDtoRequest.getCode())) {
						break;
					}
					if (StringUtils.isBlank(categoryPaymentDtoRequest.getLibelle())) {
						break;
					}
					if (StringUtils.isBlank(categoryPaymentDtoRequest.getTypeForfait())) {
						break;
					}
					if (StringUtils.isBlank(categoryPaymentDtoRequest.getPartnerCode())) {
						break;
					}
					if (categoryPaymentDtoRequest.getAmount() == null) {
						break;
					}
					*/

					ForfaitPayments existingForfaitPayment = forfaitPaymentRepository.findByCode(categoryPaymentDtoRequest.getCode()); 
					if (existingForfaitPayment != null) {
						break;
					}

					ForfaitPayments categoryPaymentResponse = ObjectMapperUtils.mapForfaitPaymentsDtoToForfaitPayments(categoryPaymentDtoRequest);
					categoryPaymentResponse = forfaitPaymentRepository.save(categoryPaymentResponse);

					if (categoryPaymentResponse != null) {
						datas.add(ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(categoryPaymentResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> updateForfaitPayment(ForfaitPaymentsDto categoryPaymentDtoRequest) {
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {					

			if (categoryPaymentDtoRequest == null) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getId())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + "(Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getCode())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getLibelle())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (categoryPaymentDtoRequest.getAmount() == null) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Amount)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getTypeForfait())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Type forfait)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryPaymentDtoRequest.getPartnerCode())) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Optional<ForfaitPayments> optionalForfaitPayment = forfaitPaymentRepository.findById(categoryPaymentDtoRequest.getId()); 
			if (optionalForfaitPayment == null) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			ForfaitPayments forfaitPayments = optionalForfaitPayment.get();
			if(StringUtils.isNotBlank(categoryPaymentDtoRequest.getLibelle()))  forfaitPayments.setLibelle(categoryPaymentDtoRequest.getLibelle());
			if(categoryPaymentDtoRequest.getAmount() != null) forfaitPayments.setAmount(categoryPaymentDtoRequest.getAmount());
			if(StringUtils.isNotBlank(categoryPaymentDtoRequest.getTypeForfait())) forfaitPayments.setTypeForfait(categoryPaymentDtoRequest.getTypeForfait());
			if(categoryPaymentDtoRequest.getActif() != null) forfaitPayments.setActif(categoryPaymentDtoRequest.getActif());
			forfaitPayments.setValidTo(new Date());

			forfaitPayments = forfaitPaymentRepository.save(forfaitPayments);
			if (forfaitPayments != null) {
				List<ForfaitPaymentsDto> parts = new ArrayList<ForfaitPaymentsDto>();
				parts.add(ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(forfaitPayments));
				datas = parts;
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> deleteForfaitPayment(String id) {
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			forfaitPaymentRepository.deleteById(id);
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> findByIdForfaitPayment(String id) {
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<ForfaitPayments> optionalForfaitPayment = forfaitPaymentRepository.findById(id);
			if (optionalForfaitPayment != null) {
				ForfaitPayments categoryPaymentResponse = optionalForfaitPayment.get();
				datas.add(ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(categoryPaymentResponse));
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> getAllForfaitPayments() {
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			List<ForfaitPayments> forfaitPayments = forfaitPaymentRepository.findAll();
			if (forfaitPayments != null && !CollectionUtils.isEmpty(forfaitPayments)) {
				datas = forfaitPayments.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> findByCode(String code) {
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			ForfaitPayments categoryPaymentResponse = forfaitPaymentRepository.findByCode(code);
			if (categoryPaymentResponse != null) {
				datas.add(ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(categoryPaymentResponse));
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> findByLibelleLike(String libelle){
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			List<ForfaitPayments> forfaitPayments = forfaitPaymentRepository.findByLibelleLikeOrderByLibelleAsc((new StringBuilder()).append("%").append(libelle).append("%").toString());
			if (forfaitPayments != null && !CollectionUtils.isEmpty(forfaitPayments)) {
				datas = forfaitPayments.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitPaymentsResponse> findByPartnerCode(String partnerCode){
		// TODO Auto-generated method stub
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			List<ForfaitPayments> forfaitPayments = forfaitPaymentRepository.findByPartnerCodeAndActifOrderByLibelleAsc(partnerCode, Boolean.TRUE);
			if (forfaitPayments != null && !CollectionUtils.isEmpty(forfaitPayments)) {
				datas = forfaitPayments.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<ForfaitPaymentsResponse> findByPartnerCodeAndTypeForfait(String partnerCode, String typeForfait){
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			List<ForfaitPayments> forfaitPayments = forfaitPaymentRepository.findByPartnerCodeAndTypeForfaitAndActifOrderByLibelleAsc(partnerCode, typeForfait, Boolean.TRUE);
			if (forfaitPayments != null && !CollectionUtils.isEmpty(forfaitPayments)) {
				datas = forfaitPayments.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<ForfaitPaymentsResponse> findByTypeForfait(String typeForfait){
		List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();

		try {
			List<ForfaitPayments> forfaitPayments = forfaitPaymentRepository.findByTypeForfaitAndActifOrderByLibelleAsc(typeForfait, Boolean.TRUE);
			if (forfaitPayments != null && !CollectionUtils.isEmpty(forfaitPayments)) {
				datas = forfaitPayments.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitPaymentsToForfaitPaymentsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitPaymentsResponse>(new ForfaitPaymentsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
