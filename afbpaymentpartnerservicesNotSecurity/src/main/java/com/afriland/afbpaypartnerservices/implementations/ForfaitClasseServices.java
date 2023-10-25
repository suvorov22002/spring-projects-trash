package com.afriland.afbpaypartnerservices.implementations;

import java.util.ArrayList;
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

import com.afriland.afbpaypartnerservices.dtos.ForfaitClassesDto;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.jpa.ForfaitClasses;
import com.afriland.afbpaypartnerservices.repositories.ForfaitClasseRepository;
import com.afriland.afbpaypartnerservices.response.ForfaitClassesResponse;
import com.afriland.afbpaypartnerservices.services.IForfaitClasseService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;



@Service("forfaitclasseservice")
@Transactional
public class ForfaitClasseServices implements IForfaitClasseService {

	@Autowired
	private ForfaitClasseRepository forfaitClasseRepository;


	@PersistenceContext
	private EntityManager entityManager;


	Logger logger = LoggerFactory.getLogger(ForfaitClasseServices.class);


	@Override
	public ResponseEntity<ForfaitClassesResponse> saveForfaitClasse(ForfaitClassesDto categoryClasseDtoRequest) {
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			if (categoryClasseDtoRequest == null) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(categoryClasseDtoRequest.getCode())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryClasseDtoRequest.getLibelle())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryClasseDtoRequest.getTypeForfait())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Type forfait)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryClasseDtoRequest.getPartnerCode())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			ForfaitClasses existingForfaitClasse = forfaitClasseRepository.findByCode(categoryClasseDtoRequest.getCode()); 
			if (existingForfaitClasse != null) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}

			ForfaitClasses categoryClasseResponse = ObjectMapperUtils.mapForfaitClassesDtoToForfaitClasses(categoryClasseDtoRequest);
			categoryClasseResponse = forfaitClasseRepository.save(categoryClasseResponse);
			if (categoryClasseResponse != null) {
				datas.add(ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(categoryClasseResponse));
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> saveListForfaitClasses(List<ForfaitClassesDto> categoryClasseDtoRequests) {
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			for(ForfaitClassesDto categoryClasseDtoRequest : categoryClasseDtoRequests) {
				try {
					if (categoryClasseDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(categoryClasseDtoRequest.getCode())) {
						break;
					}
					if (StringUtils.isBlank(categoryClasseDtoRequest.getLibelle())) {
						break;
					}
					if (StringUtils.isBlank(categoryClasseDtoRequest.getTypeForfait())) {
						break;
					}
					if (StringUtils.isBlank(categoryClasseDtoRequest.getPartnerCode())) {
						break;
					}
					
					ForfaitClasses existingForfaitClasse = forfaitClasseRepository.findByCode(categoryClasseDtoRequest.getCode()); 
					if (existingForfaitClasse != null) {
						break;
					}

					ForfaitClasses categoryClasseResponse = ObjectMapperUtils.mapForfaitClassesDtoToForfaitClasses(categoryClasseDtoRequest);
					categoryClasseResponse = forfaitClasseRepository.save(categoryClasseResponse);

					if (categoryClasseResponse != null) {
						datas.add(ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(categoryClasseResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> updateForfaitClasse(ForfaitClassesDto categoryClasseDtoRequest) {
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {					

			if (categoryClasseDtoRequest == null) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(categoryClasseDtoRequest.getId())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + "(Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryClasseDtoRequest.getCode())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryClasseDtoRequest.getLibelle())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryClasseDtoRequest.getTypeForfait())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Type forfait)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(categoryClasseDtoRequest.getPartnerCode())) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Optional<ForfaitClasses> optionalForfaitClasse = forfaitClasseRepository.findById(categoryClasseDtoRequest.getId()); 
			if (optionalForfaitClasse == null) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			ForfaitClasses forfaitClasses = optionalForfaitClasse.get();
			if(StringUtils.isNotBlank(categoryClasseDtoRequest.getLibelle())) forfaitClasses.setLibelle(categoryClasseDtoRequest.getLibelle());
			if(StringUtils.isNotBlank(categoryClasseDtoRequest.getTypeForfait())) forfaitClasses.setTypeForfait(categoryClasseDtoRequest.getTypeForfait());
			if(categoryClasseDtoRequest.getActif() != null) forfaitClasses.setActif(categoryClasseDtoRequest.getActif());
			
			forfaitClasses = forfaitClasseRepository.save(forfaitClasses);
			if (forfaitClasses != null) {
				List<ForfaitClassesDto> parts = new ArrayList<ForfaitClassesDto>();
				parts.add(ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(forfaitClasses));
				datas = parts;
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> deleteForfaitClasse(String id) {
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			forfaitClasseRepository.deleteById(id);
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> findByIdForfaitClasse(String id) {
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<ForfaitClasses> optionalForfaitClasse = forfaitClasseRepository.findById(id);
			if (optionalForfaitClasse != null) {
				ForfaitClasses categoryClasseResponse = optionalForfaitClasse.get();
				datas.add(ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(categoryClasseResponse));
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> getAllForfaitClasses() {
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			List<ForfaitClasses> forfaitClasses = forfaitClasseRepository.findAll();
			if (forfaitClasses != null && !CollectionUtils.isEmpty(forfaitClasses)) {
				datas = forfaitClasses.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> findByCode(String code) {
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			ForfaitClasses categoryClasseResponse = forfaitClasseRepository.findByCode(code);
			if (categoryClasseResponse != null) {
				datas.add(ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(categoryClasseResponse));
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> findByLibelleLike(String libelle){
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			List<ForfaitClasses> forfaitClasses = forfaitClasseRepository.findByLibelleLikeOrderByLibelleAsc((new StringBuilder()).append("%").append(libelle).append("%").toString());
			if (forfaitClasses != null && !CollectionUtils.isEmpty(forfaitClasses)) {
				datas = forfaitClasses.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<ForfaitClassesResponse> findByPartnerCode(String partnerCode){
		// TODO Auto-generated method stub
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			List<ForfaitClasses> forfaitClasses = forfaitClasseRepository.findByPartnerCodeAndActifOrderByLibelleAsc(partnerCode, Boolean.TRUE);
			if (forfaitClasses != null && !CollectionUtils.isEmpty(forfaitClasses)) {
				datas = forfaitClasses.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<ForfaitClassesResponse> findByPartnerCodeAndTypeForfait(String partnerCode, String typeForfait){
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			List<ForfaitClasses> forfaitClasses = forfaitClasseRepository.findByPartnerCodeAndTypeForfaitAndActifOrderByLibelleAsc(partnerCode, typeForfait, Boolean.TRUE);
			if (forfaitClasses != null && !CollectionUtils.isEmpty(forfaitClasses)) {
				datas = forfaitClasses.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<ForfaitClassesResponse> findByTypeForfait(String typeForfait){
		List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();

		try {
			List<ForfaitClasses> forfaitClasses = forfaitClasseRepository.findByTypeForfaitAndActifOrderByLibelleAsc(typeForfait, Boolean.TRUE);
			if (forfaitClasses != null && !CollectionUtils.isEmpty(forfaitClasses)) {
				datas = forfaitClasses.stream().map(response -> {
					return ObjectMapperUtils.mapForfaitClassesToForfaitClassesDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ForfaitClassesResponse>(new ForfaitClassesResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
