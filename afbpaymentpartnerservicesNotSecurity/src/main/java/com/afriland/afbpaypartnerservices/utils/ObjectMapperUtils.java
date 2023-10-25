package com.afriland.afbpaypartnerservices.utils;

import org.modelmapper.ModelMapper;

import com.afriland.afbpaypartnerservices.dtos.AccountPartnersDto;
import com.afriland.afbpaypartnerservices.dtos.ForfaitClassesDto;
import com.afriland.afbpaypartnerservices.dtos.ForfaitPaymentsDto;
import com.afriland.afbpaypartnerservices.dtos.OperatorsDto;
import com.afriland.afbpaypartnerservices.dtos.PartnersDto;
import com.afriland.afbpaypartnerservices.dtos.PaymentDetails;
import com.afriland.afbpaypartnerservices.dtos.PropertyConfigsDto;
import com.afriland.afbpaypartnerservices.dtos.TransactionsDto;
import com.afriland.afbpaypartnerservices.dtos.TypeForfaitsDto;
import com.afriland.afbpaypartnerservices.dtos.UserExternsDto;
import com.afriland.afbpaypartnerservices.dtos.VentilationDto;
import com.afriland.afbpaypartnerservices.dtos.trusayway.TrustDetailsObject;
import com.afriland.afbpaypartnerservices.dtos.trusayway.TrustPaymentDetails;
import com.afriland.afbpaypartnerservices.jpa.AccountPartners;
import com.afriland.afbpaypartnerservices.jpa.ForfaitClasses;
import com.afriland.afbpaypartnerservices.jpa.ForfaitPayments;
import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.Partners;
import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.jpa.TypeForfaits;
import com.afriland.afbpaypartnerservices.jpa.UserExterns;
import com.afriland.afbpaypartnerservices.jpa.Ventilation;

public class ObjectMapperUtils {


	//********************************** Partner ***************************************

	/**
	 * Transforme une entity Partner en un POJO PartnerDto
	 *
	 * @param partners
	 * @return
	 */
	public static PartnersDto mapPartnerToPartnerDto(Partners partners) {
		ModelMapper mapper = new ModelMapper();
		PartnersDto partnersDto = mapper.map(partners, PartnersDto.class);
		return partnersDto;
	}


	/**
	 * Transforme un POJO PartnerDto en une entity Partner
	 *
	 * @param partnersDto
	 * @return
	 */
	public static Partners mapPartnerDtoToPartner(PartnersDto partnersDto) {
		ModelMapper mapper = new ModelMapper();
		Partners partners = mapper.map(partnersDto, Partners.class);
		return partners;
	}




	//********************************** Operator ***************************************

	/**
	 * Transforme une entity Operator en un POJO OperatorDto
	 *
	 * @param operators
	 * @return
	 */
	public static OperatorsDto mapOperatorToOperatorDto(Operators operators) {
		ModelMapper mapper = new ModelMapper();
		OperatorsDto operatorsDto = mapper.map(operators, OperatorsDto.class);
		return operatorsDto;
	}


	/**
	 * Transforme un POJO OperatorDto en une entity Operator
	 *
	 * @param operatorsDto
	 * @return
	 */
	public static Operators mapOperatorDtoToOperator(OperatorsDto operatorsDto) {
		ModelMapper mapper = new ModelMapper();
		Operators operators = mapper.map(operatorsDto, Operators.class);
		return operators;
	}




	//********************************** PropertyConfigs ***************************************

	/**
	 * Transforme une entity PropertyConfigs en un POJO PropertyConfigsDto
	 *
	 * @param propertyConfigs
	 * @return
	 */
	public static PropertyConfigsDto mapPropertyConfigsToPropertyConfigsDto(PropertyConfigs propertyConfigs) {
		ModelMapper mapper = new ModelMapper();
		PropertyConfigsDto propertyConfigsDto = mapper.map(propertyConfigs, PropertyConfigsDto.class);
		return propertyConfigsDto;
	}


	/**
	 * Transforme un POJO PropertyConfigsDto en une entity PropertyConfigs
	 *
	 * @param propertyConfigsDto
	 * @return
	 */
	public static PropertyConfigs mapPropertyConfigsDtoToPropertyConfigs(PropertyConfigsDto propertyConfigsDto) {
		ModelMapper mapper = new ModelMapper();
		PropertyConfigs propertyConfigs = mapper.map(propertyConfigsDto, PropertyConfigs.class);
		return propertyConfigs;
	}




	//********************************** ForfaitPayments ***************************************

	/**
	 * Transforme une entity ForfaitPayments en un POJO ForfaitPaymentsDto
	 *
	 * @param forfaitPayments
	 * @return
	 */
	public static ForfaitPaymentsDto mapForfaitPaymentsToForfaitPaymentsDto(ForfaitPayments forfaitPayments) {
		ModelMapper mapper = new ModelMapper();
		ForfaitPaymentsDto forfaitPaymentsDto = mapper.map(forfaitPayments, ForfaitPaymentsDto.class);
		return forfaitPaymentsDto;
	}


	/**
	 * Transforme un POJO ForfaitPaymentsDto en une entity ForfaitPayments
	 *
	 * @param forfaitPaymentsDto
	 * @return
	 */
	public static ForfaitPayments mapForfaitPaymentsDtoToForfaitPayments(ForfaitPaymentsDto forfaitPaymentsDto) {
		ModelMapper mapper = new ModelMapper();
		ForfaitPayments forfaitPayments = mapper.map(forfaitPaymentsDto, ForfaitPayments.class);
		return forfaitPayments;
	}




	//********************************** ForfaitClasses ***************************************

	/**
	 * Transforme une entity ForfaitClasses en un POJO ForfaitClassesDto
	 *
	 * @param forfaitClasses
	 * @return
	 */
	public static ForfaitClassesDto mapForfaitClassesToForfaitClassesDto(ForfaitClasses forfaitClasses) {
		ModelMapper mapper = new ModelMapper();
		ForfaitClassesDto forfaitClassesDto = mapper.map(forfaitClasses, ForfaitClassesDto.class);
		return forfaitClassesDto;
	}


	/**
	 * Transforme un POJO ForfaitClassesDto en une entity ForfaitClasses
	 *
	 * @param forfaitClassesDto
	 * @return
	 */
	public static ForfaitClasses mapForfaitClassesDtoToForfaitClasses(ForfaitClassesDto forfaitClassesDto) {
		ModelMapper mapper = new ModelMapper();
		ForfaitClasses forfaitClasses = mapper.map(forfaitClassesDto, ForfaitClasses.class);
		return forfaitClasses;
	}




	//********************************** TypeForfaits ***************************************

	/**
	 * Transforme une entity TypeForfaits en un POJO TypeForfaitsDto
	 *
	 * @param typeForfaits
	 * @return
	 */
	public static TypeForfaitsDto mapTypeForfaitsToTypeForfaitsDto(TypeForfaits typeForfaits) {
		ModelMapper mapper = new ModelMapper();
		TypeForfaitsDto typeForfaitsDto = mapper.map(typeForfaits, TypeForfaitsDto.class);
		return typeForfaitsDto;
	}


	/**
	 * Transforme un POJO TypeForfaitsDto en une entity TypeForfaits
	 *
	 * @param typeForfaitsDto
	 * @return
	 */
	public static TypeForfaits mapTypeForfaitsDtoToTypeForfaits(TypeForfaitsDto typeForfaitsDto) {
		ModelMapper mapper = new ModelMapper();
		TypeForfaits typeForfaits = mapper.map(typeForfaitsDto, TypeForfaits.class);
		return typeForfaits;
	}




	//********************************** Transactions ***************************************

	/**
	 * Transforme une entity Transactions en un POJO TransactionsDto
	 *
	 * @param transactions
	 * @returnO
	 */
	public static TransactionsDto mapTransactionsToTransactionsDto(Transactions transactions) {
		ModelMapper mapper = new ModelMapper();
		TransactionsDto transactionsDto = mapper.map(transactions, TransactionsDto.class);
		return transactionsDto;
	}


	/**
	 * Transforme un POJO TransactionsDto en une entity Transactions
	 *
	 * @param transactionsDto
	 * @return
	 */
	public static Transactions mapTransactionsDtoToTransactions(TransactionsDto transactionsDto) {
		ModelMapper mapper = new ModelMapper();
		Transactions transactions = mapper.map(transactionsDto, Transactions.class);
		return transactions;
	}



	//********************************** AccountPartners ***************************************

	/**
	 * Transforme une entity AccountPartners en un POJO AccountPartnersDto
	 *
	 * @param accountPartners
	 * @return
	 */
	public static AccountPartnersDto mapAccountPartnersToAccountPartnersDto(AccountPartners accountPartners) {
		ModelMapper mapper = new ModelMapper();
		AccountPartnersDto accountPartnersDto = mapper.map(accountPartners, AccountPartnersDto.class);
		return accountPartnersDto;
	}


	/**
	 * Transforme un POJO AccountPartnersDto en une entity AccountPartners
	 *
	 * @param accountPartnersDto
	 * @return
	 */
	public static AccountPartners mapAccountPartnersDtoToAccountPartners(AccountPartnersDto accountPartnersDto) {
		ModelMapper mapper = new ModelMapper();
		AccountPartners accountPartners = mapper.map(accountPartnersDto, AccountPartners.class);
		return accountPartners;
	}



	//********************************** UserExtern ***************************************

	/**
	 * Transforme une entity UserExtern en un POJO UserExternDto
	 *
	 * @param userExterns
	 * @return
	 */
	public static UserExternsDto mapUserExternToUserExternDto(UserExterns userExterns) {
		ModelMapper mapper = new ModelMapper();
		UserExternsDto userExternsDto = mapper.map(userExterns, UserExternsDto.class);
		return userExternsDto;
	}


	/**
	 * Transforme un POJO UserExternDto en une entity UserExtern
	 *
	 * @param userExternsDto
	 * @return
	 */
	public static UserExterns mapUserExternDtoToUserExtern(UserExternsDto userExternsDto) {
		ModelMapper mapper = new ModelMapper();
		UserExterns userExterns = mapper.map(userExternsDto, UserExterns.class);
		return userExterns;
	}




	//********************************** TrustPaymentDetails ***************************************

	public static PaymentDetails mapTrustPaymentDetails(TrustPaymentDetails tpd){

		PaymentDetails paymentDetails = new PaymentDetails();

		if(tpd != null){
			paymentDetails.setAmount(tpd.getAmount().doubleValue());
			paymentDetails.setDetails(new TrustDetailsObject(tpd.getIntent(), tpd.getBillList()));
			paymentDetails.setKey(tpd.getPayerinfo().getValue());
			paymentDetails.setLib(tpd.getTomember());
			paymentDetails.setNiu(tpd.getPayerinfo().getType());
			paymentDetails.setReason(tpd.getDescription());
			paymentDetails.setTrxID(tpd.getAcquirertrxref());
		}

		return paymentDetails;
	}
	
	//******************************** Ventilation ***************************************
	

		/**
		 * Transforme une entity Ventilation en un POJO VentilationDto
		 *
		 * @param ventilation
		 * @returnO
		 */
		public static VentilationDto mapVentilationToVentilationDto(Ventilation ventilation) {
			ModelMapper mapper = new ModelMapper();
			VentilationDto ventilationDto = mapper.map(ventilation, VentilationDto.class);
			return ventilationDto;
		}


		/**
		 * Transforme un POJO VentilationDto en une entity Ventilation
		 *
		 * @param ventilationDto
		 * @return
		 */
		public static Ventilation mapVentilationDtoToVentilation(VentilationDto ventilationDto) {
			ModelMapper mapper = new ModelMapper();
			Ventilation ventilation = mapper.map(ventilationDto, Ventilation.class);
			return ventilation;
		}


}
