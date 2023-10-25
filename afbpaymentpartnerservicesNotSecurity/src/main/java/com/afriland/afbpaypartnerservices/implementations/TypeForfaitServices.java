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

import com.afriland.afbpaypartnerservices.dtos.TypeForfaitsDto;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.enums.Segment;
import com.afriland.afbpaypartnerservices.jpa.TypeForfaits;
import com.afriland.afbpaypartnerservices.repositories.TypeForfaitRepository;
import com.afriland.afbpaypartnerservices.response.TypeForfaitsResponse;
import com.afriland.afbpaypartnerservices.services.ITypeForfaitService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;



@Service("typeforfaitservice")
@Transactional
public class TypeForfaitServices implements ITypeForfaitService {

	@Autowired
	private TypeForfaitRepository typeForfaitRepository;


	@PersistenceContext
	private EntityManager entityManager;


	Logger logger = LoggerFactory.getLogger(TypeForfaitServices.class);


	@Override
	public ResponseEntity<TypeForfaitsResponse> saveTypeForfait(TypeForfaitsDto typeForfaitDtoRequest) {
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			if (typeForfaitDtoRequest == null) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(typeForfaitDtoRequest.getCode())) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(typeForfaitDtoRequest.getLibelle())) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (typeForfaitDtoRequest.getSegment() == null) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Segment)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(typeForfaitDtoRequest.getPartnerCode())) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			TypeForfaits existingTypeForfait = typeForfaitRepository.findByCode(typeForfaitDtoRequest.getCode()); 
			if (existingTypeForfait != null) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}

			TypeForfaits typeForfaitResponse = ObjectMapperUtils.mapTypeForfaitsDtoToTypeForfaits(typeForfaitDtoRequest);
			typeForfaitResponse = typeForfaitRepository.save(typeForfaitResponse);
			if (typeForfaitResponse != null) {
				datas.add(ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(typeForfaitResponse));
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> saveListTypeForfaits(List<TypeForfaitsDto> typeForfaitDtoRequests) {
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			for(TypeForfaitsDto typeForfaitDtoRequest : typeForfaitDtoRequests) {
				try {
					/*/
					if (typeForfaitDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(typeForfaitDtoRequest.getCode())) {
						break;
					}
					if (StringUtils.isBlank(typeForfaitDtoRequest.getLibelle())) {
						break;
					}
					if (typeForfaitDtoRequest.getSegment() == null) {
						break;
					}
					if (StringUtils.isBlank(typeForfaitDtoRequest.getPartnerCode())) {
						break;
					}
					*/

					TypeForfaits existingTypeForfait = typeForfaitRepository.findByCode(typeForfaitDtoRequest.getCode()); 
					if (existingTypeForfait != null) {
						break;
					}

					TypeForfaits typeForfaitResponse = ObjectMapperUtils.mapTypeForfaitsDtoToTypeForfaits(typeForfaitDtoRequest);
					typeForfaitResponse = typeForfaitRepository.save(typeForfaitResponse);

					if (typeForfaitResponse != null) {
						datas.add(ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(typeForfaitResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> updateTypeForfait(TypeForfaitsDto typeForfaitDtoRequest) {
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();
		
		try {					

			if (typeForfaitDtoRequest == null) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(typeForfaitDtoRequest.getId())) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + "(Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(typeForfaitDtoRequest.getCode())) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(typeForfaitDtoRequest.getLibelle())) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (typeForfaitDtoRequest.getSegment() == null) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Segment)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(typeForfaitDtoRequest.getPartnerCode())) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Optional<TypeForfaits> optionalTypeForfait = typeForfaitRepository.findById(typeForfaitDtoRequest.getId()); 
			if (optionalTypeForfait == null) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			TypeForfaits typeForfaits = optionalTypeForfait.get();
			if(StringUtils.isNotBlank(typeForfaitDtoRequest.getLibelle())) typeForfaits.setLibelle(typeForfaitDtoRequest.getLibelle());
			if(typeForfaitDtoRequest.getSegment() != null) typeForfaits.setSegment(typeForfaitDtoRequest.getSegment());
			if(typeForfaitDtoRequest.getActif() != null) typeForfaits.setActif(typeForfaitDtoRequest.getActif());

			typeForfaits = typeForfaitRepository.save(typeForfaits);
			if (typeForfaits != null) {
				List<TypeForfaitsDto> parts = new ArrayList<TypeForfaitsDto>();
				parts.add(ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(typeForfaits));
				datas = parts;
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> deleteTypeForfait(String id) {
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			typeForfaitRepository.deleteById(id);
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> findByIdTypeForfait(String id) {
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<TypeForfaits> optionalTypeForfait = typeForfaitRepository.findById(id);
			if (optionalTypeForfait != null) {
				TypeForfaits typeForfaitResponse = optionalTypeForfait.get();
				datas.add(ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(typeForfaitResponse));
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> getAllTypeForfaits() {
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			List<TypeForfaits> typeForfaits = typeForfaitRepository.findAll();
			if (typeForfaits != null && !CollectionUtils.isEmpty(typeForfaits)) {
				datas = typeForfaits.stream().map(response -> {
					return ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> findByCode(String code) {
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			TypeForfaits typeForfaitResponse = typeForfaitRepository.findByCode(code);
			if (typeForfaitResponse != null) {
				datas.add(ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(typeForfaitResponse));
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> findByLibelleLike(String libelle){
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			List<TypeForfaits> typeForfaits = typeForfaitRepository.findByLibelleLikeOrderByLibelleAsc((new StringBuilder()).append("%").append(libelle).append("%").toString());
			if (typeForfaits != null && !CollectionUtils.isEmpty(typeForfaits)) {
				datas = typeForfaits.stream().map(response -> {
					return ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<TypeForfaitsResponse> findByPartnerCode(String partnerCode){
		// TODO Auto-generated method stub
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			List<TypeForfaits> typeForfaits = typeForfaitRepository.findByPartnerCodeAndActifOrderByLibelleAsc(partnerCode, Boolean.TRUE);
			if (typeForfaits != null && !CollectionUtils.isEmpty(typeForfaits)) {
				datas = typeForfaits.stream().map(response -> {
					return ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	public ResponseEntity<TypeForfaitsResponse> findBySegment(Segment segment){
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			if (segment == null) {
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (segment)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			List<TypeForfaits> typeForfaits = typeForfaitRepository.findBySegmentAndActifOrderByLibelleAsc(segment, Boolean.TRUE);
			if (typeForfaits != null && !CollectionUtils.isEmpty(typeForfaits)) {
				datas = typeForfaits.stream().map(response -> {
					return ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	public ResponseEntity<TypeForfaitsResponse> findByPartnerCodeAndSegment(String partnerCode, Segment segment){
		List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();

		try {
			List<TypeForfaits> typeForfaits = typeForfaitRepository.findByPartnerCodeAndSegmentOrderByLibelleAsc(partnerCode, segment);
			if (typeForfaits != null && !CollectionUtils.isEmpty(typeForfaits)) {
				datas = typeForfaits.stream().map(response -> {
					return ObjectMapperUtils.mapTypeForfaitsToTypeForfaitsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TypeForfaitsResponse>(new TypeForfaitsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
