package com.afriland.afbpaypartnerservices.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.afriland.afbpaypartnerservices.dtos.Bkcom;
import com.afriland.afbpaypartnerservices.dtos.ResponseBkcom;
import com.afriland.afbpaypartnerservices.dtos.ResponseHolder;
import com.afriland.afbpaypartnerservices.dtos.VentilationDto;
import com.afriland.afbpaypartnerservices.enums.FpstatutFileTransaction;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.externservices.CoreBankingServices;
import com.afriland.afbpaypartnerservices.jpa.AccountPartners;
import com.afriland.afbpaypartnerservices.jpa.Bkeve;
import com.afriland.afbpaypartnerservices.jpa.BkeveTemplate;
import com.afriland.afbpaypartnerservices.jpa.Bkmvti;
import com.afriland.afbpaypartnerservices.jpa.DetailsVentilation;
import com.afriland.afbpaypartnerservices.jpa.DisplayBkeveTemplate;
import com.afriland.afbpaypartnerservices.jpa.ErreursVentilation;
import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.jpa.Ventilation;
import com.afriland.afbpaypartnerservices.repositories.AccountPartnerRepository;
import com.afriland.afbpaypartnerservices.repositories.BkeveRepository;
import com.afriland.afbpaypartnerservices.repositories.BkeveTemplateRepository;
import com.afriland.afbpaypartnerservices.repositories.DetailsVentilationRepository;
import com.afriland.afbpaypartnerservices.repositories.ErreursVentilationRepository;
import com.afriland.afbpaypartnerservices.repositories.PropertyConfigRepository;
import com.afriland.afbpaypartnerservices.repositories.TransactionsRepository;
import com.afriland.afbpaypartnerservices.repositories.VentilationRepository;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.VentilationResponse;
import com.afriland.afbpaypartnerservices.services.IVentilationService;
import com.afriland.afbpaypartnerservices.utils.DateUtil;
import com.afriland.afbpaypartnerservices.utils.FileHelper;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;
import com.afriland.afbpaypartnerservices.utils.ServerParameter;


@Service("ventilationservice")
@Transactional
public class VentilationServices implements IVentilationService{
	
	Logger logger = LoggerFactory.getLogger(VentilationServices.class);
	
	@Value("${jboss.server.data.dir}")
	private String serverDirectory;
	
	@Autowired
	private BkeveTemplateRepository bkeveTemplateRepository;
	
	@Autowired
	private VentilationRepository ventilationRepo;
	
	@Autowired
	private ErreursVentilationRepository errVentilationRepo;
	
	@Autowired
	private DetailsVentilationRepository detailsVentilationRepo;
	
	@Autowired
	private PropertyConfigRepository propertyConfigRepository;
	
	@Autowired
	private AccountPartnerRepository accountPartnerRepo;
	
	@Autowired
	private TransactionsRepository transactionRepo;
	
	@Autowired
	private BkeveRepository bkeveRepo;
	
	@Value("${application.afriland.cbsservice.baseurl}")
	private String host;

	@Value("${application.afriland.cbsservice.keysecurity}")
	private String keysecurity;
	
	@Value("${application.service.account.liaison}")
	private String ncpLiason;
	
	@Value("${application.dir.file}")
	private String dirFile;
	
	@Value("${application.dir.archfile}")
	private String dirFileArch;
	
	private  List<Bkmvti> listeEcritures;
	
	
	public String getValue(String code){
		Optional<PropertyConfigs> mers = propertyConfigRepository.findById(code);
		PropertyConfigs mer = null ; 
		if(mers.isPresent()){
			mer = mers.get();
			return mer.getValue();
		}
		return null;
	}
	public String getHost() {
		return getValue(host);
	}

	public String getKeysecurity() {
		return getValue(keysecurity);
	}
	
	public String getNcpLiason() {
		return getValue(ncpLiason);
	}
	
	private List<BkeveTemplate> templates = null;
	
	@PostConstruct
	private void init(){
		
		this.templates = this.bkeveTemplateRepository.findAll();
		host = getHost();
		keysecurity = getKeysecurity();		
		ncpLiason = getNcpLiason();
		
	}
	
	private BkeveTemplate getBkeveTemplate(String typeOpe) {
		DisplayBkeveTemplate display = () -> {
			return this.templates.stream()
					.filter(t ->t.getTypeOpe().equalsIgnoreCase(typeOpe))
					.collect(Collectors.toList());    
		};
		List<BkeveTemplate> list = display.show();  
		return list.iterator().next();
	}

	
	
	@Override
	public ResponseEntity<VentilationResponse> checkNewFileVentilation() {
		
		Set<String> files = new HashSet<>();
		List<VentilationDto> datas = new ArrayList<VentilationDto>();
		Path payments = Paths.get(dirFile);
		
		try{
			
			ServerParameter sp = extractedRemoteParamFromBD();
			
			logger.info("REMOTE : "+sp.getHost()+" / "+sp.getFtpDir()+" / "+sp.getPort()+" / "+sp.getUser()+" / "+sp.getPassword() + " / " + serverDirectory);
		//	files = CSftp.findRemoteFiles(sp.getUser(), sp.getHost(), sp.getFtpDir(), sp.getPassword(), serverDirectory);
			Stream<Path> stream = Files.list(payments);
			files =  stream
					.filter(file -> !Files.isDirectory(file))
					.map(Path::getFileName)
					.map(Path::toString)
					.collect(Collectors.toSet());
			logger.info("NBRE: " + files.size());
			stream.close();
			
			VentilationDto ventilDto;
			
			for (String filename : files) {
				ventilDto = new VentilationDto();
				//ventilDto.setReference(filename);
				ventilDto.setFileName(filename);
				datas.add(ventilDto);
			}
	
		} catch(Exception e){
			logger.info("Global directory : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Global directory : "+e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
		return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<VentilationResponse> processVentilation(String filename, String partcode, String user) {
		
		List<VentilationDto> datas = new ArrayList<VentilationDto>();
		try {
	
			// Read file content and set file Ventilation details
			logger.info("Process Ventilation Event");
			List<DetailsVentilation> listDetailVentilation;
			List<Ventilation> dataVentilation = ventilationRepo.findByIntegrationAndStatus(Boolean.FALSE, FpstatutFileTransaction.CONTROLE);
			
			if(dataVentilation.isEmpty()) {
				return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.NO_CONTENT.value()),
						HttpStatus.NO_CONTENT.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.PRECONDITION_REQUIRED);
			}
			
			logger.info("Total Ventilation Event: " + dataVentilation.size());
			for(Ventilation ventil : dataVentilation) {
				
				listDetailVentilation = detailsVentilationRepo.findByParentAndStatus(ventil, FpstatutFileTransaction.ENCOURS);
				if(!listDetailVentilation.isEmpty()) {
					ventil.setPartenaire(partcode);
					ventil.setUser(user);
					executeVentilation(ventil);
					ventil.setIntegration(Boolean.TRUE);
					ventilationRepo.saveAndFlush(ventil);
					datas.add(ObjectMapperUtils.mapVentilationToVentilationDto(ventil));
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), 
					HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), null), HttpStatus.NOT_FOUND);
			
		}
	
		return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.OK.value()),
				HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<VentilationResponse> processVentilationListFile(List<String> filenameList, String partcode, String login) {
		
		logger.info("LoginPortal: " + login);
		logger.info("FILE-VENTILAT[]: " + filenameList);
		List<Ventilation> listFileTx = new ArrayList<Ventilation>();
		List<VentilationDto> datas = new ArrayList<VentilationDto>();
		
		// Verifier s'il n'ya pas une ventilation non traitée
		listFileTx = ventilationRepo.findByIntegrationAndStatus(Boolean.FALSE, FpstatutFileTransaction.CONTROLE);
		if(!listFileTx.isEmpty()) {
			String message = "Fichier non ventillé en attente. Veuillez proceder à la comptabilisation.";
			return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, message, datas), HttpStatus.CONFLICT);
		}
	
		for(String filename:filenameList){
			try {

				listFileTx.add(processFileVentilation(filename, partcode, login));
				//logger.info("listFileTx: " + listFileTx.size());
				listFileTx = listFileTx.stream().filter(m -> m.getFileName() != null).collect(Collectors.toList());
				//logger.info("listFileTx2: " + listFileTx.size());
				
				for (Ventilation flv : listFileTx) {
					datas.add(ObjectMapperUtils.mapVentilationToVentilationDto(flv));
				}
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info("Exception when decrypting the file " + filename + " : " + e.getMessage());
				logger.info(e.toString());
				e.printStackTrace();
				return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), null), HttpStatus.NOT_FOUND);
				
			}
		}
		
		String message = "";
		if(datas.isEmpty()) {
			message = "Erreur lors du control.";
			return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, message, datas), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<VentilationResponse>(new VentilationResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<VentilationResponse> ventilation(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VentilationResponse> findVentilationAttente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VentilationResponse> findFpDetailsVentilationByFileId(String ventilationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VentilationResponse> findFpErreursVentilationByFileId(String ventilationId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Ventilation processFileVentilation(String filename, String partcode, String user) throws IOException{

		//ServerParameter sp = extractedRemoteParamFromBD();
		//logger.info("REMOTE : "+sp.getHost()+" / "+sp.getFtpDir()+" / "+sp.getPort()+" / "+sp.getUser()+" / "+sp.getPassword());
		
		Ventilation ftx = getOrCreateFileVentilation(filename, partcode, user);
	
		// Read file content and set file transaction details
		logger.info("Process file transaction : " + ftx.getFileName());
		if(ftx.getFileName() == null) {
			return ftx;
		}
		
		ftx = processFileVentilation(ftx);
		
		// Archive the encrypted and the decrypted file
		// Archive the decrypted file (destFilename) to the backup dir
		logger.info("Process file archivage : " + dirFileArch);
		Path archDirVenti = Paths.get(dirFile, dirFileArch);
		logger.info("Process file archivage : " + archDirVenti.toString());
		if(Files.notExists(archDirVenti)) {
			Files.createDirectories(archDirVenti);
		}
		logger.info("Process file archivage  ftx.getFileName() : " +  ftx.getFileName());
		Files.move(Paths.get(dirFile+"/"+ftx.getFileName()), Paths.get(dirFile, dirFileArch+"/"+DateUtil.now()+"_"+ftx.getFileName()));
		
		
		return ftx;
	}
	
	
	private Ventilation processFileVentilation(Ventilation ftx) {
		boolean trans = false;
		logger.info("In process file transaction");
		AccountPartners param;
		Path filePath;
		
		try {
			
			filePath = Paths.get(dirFile, ftx.getFileName());
			logger.info("In process uploads file transaction: " + filePath.toString());
			File f = new File(filePath.toString());
			
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";

			logger.info("Reading file using Buffered Reader");
			
			Integer nbrLigne = 0, cpt = 0;
			String reference = "VENTI-"+ftx.getPartenaire()+"-"+DateUtil.now();

			Date dco = getDco();
			
			// Verification de l'existence d'une ventilation identique
			Ventilation venti = ventilationRepo.findByReference(reference);
			if(venti != null) {
				throw new Exception("Impossible d'effectuer cette operation, duplication de ventilation pour la même date");
			}
			
			String referenceTransaction, codeCompte, montTransaction, sens, senderAccountNo, receiverAccountNo, nomBenef,devise, line, identifier, dateTrx, libelle;
			Double amount;
			boolean block = false;
			int counterBlock = 1;
			line  = "";
			
			while ((readLine = b.readLine()) != null) {
				if(StringUtils.isBlank(readLine)) {
					logger.info("Error - Line with only blank space");
					b.close();
					ftx.setStatus(FpstatutFileTransaction.CANCEL);
					ftx.setErrorMsg("Error - Line with only blank space");
					ftx = ventilationRepo.save(ftx);
					
					List<DetailsVentilation> detVenti = detailsVentilationRepo.findByParent(ftx);
					if(!detVenti.isEmpty()) {
						for(DetailsVentilation d : detVenti) {
							d.setStatus(FpstatutFileTransaction.ECHEC);
							//detailsVentilationRepo.saveAndFlush(d);
						}
						
						detailsVentilationRepo.saveAllAndFlush(detVenti);
					}
					return ftx;
				}
				else {
					
					
					nbrLigne++;
					String[] arrayCode = readLine.split("\\|");
					identifier = arrayCode[0];
					referenceTransaction = arrayCode[1];
					dateTrx = arrayCode[2];
					
					if(identifier.equalsIgnoreCase("1") && !block) {
						line = "block_" + referenceTransaction + "_" + dateTrx + "_" + (counterBlock++);
						block = true;
					}
					else if(identifier.equalsIgnoreCase("2")) {
						
						block = false;
					}
					
					codeCompte = arrayCode[3];
					libelle = arrayCode[4];
					reference = libelle;
					montTransaction = arrayCode[5];
					sens = arrayCode[6];
					senderAccountNo = "";
					receiverAccountNo = "";
					nomBenef = "";
					amount = Double.parseDouble(montTransaction);
					devise = "";
					//line = senderAccountNo+receiverAccountNo+nomBenef+amount;
				//	line = referenceTransaction+codeCompte+montTransaction;
					//System.out.println("CODE COMPTE: "+codeCompte);
					param = accountPartnerRepo.findByCode(codeCompte);// TODO: appeler le bon repo
					if(param == null || arrayCode.length != 7) {
						ErreursVentilation erreur = new ErreursVentilation(reference, ftx, amount, devise, nomBenef, 
								senderAccountNo, "", FpstatutFileTransaction.ECHEC, "COMPTE BENEFICIAIRE INEXISTANT", line, sens);
						errVentilationRepo.save(erreur);
					}
					else {
						
						if("D".equals(sens)) {
							senderAccountNo = param.getAccount();
						}
						else if("C".equals(sens)) {
							receiverAccountNo = param.getAccount();
						}
						else {
							ErreursVentilation erreur = new ErreursVentilation(reference, ftx, amount, devise, nomBenef, 
									senderAccountNo, "", FpstatutFileTransaction.ECHEC, "SENS DE LA TRANSACTION NON CONNU", line, sens);
							errVentilationRepo.save(erreur);
						}
						
						
						DetailsVentilation detail = new DetailsVentilation(reference, referenceTransaction, nomBenef, ftx, amount, devise, dco, senderAccountNo, 
								receiverAccountNo, FpstatutFileTransaction.ENCOURS, line, sens, "");

						detail = detailsVentilationRepo.save(detail);						
//						//Ventilation
//						Boolean result = ventilationDetail(detail);
//						if(result == Boolean.TRUE) cpt++;
					}

				}
			}
			b.close();
			
			ftx.setNbrLigne(nbrLigne);	
			ftx.setReference(reference);
			ftx.setDateComptable(dco);
			ftx.setDateCreation(new Date());
		//	ftx.setStatus(cpt == nbrLigne ? FpstatutFileTransaction.VALIDE : FpstatutFileTransaction.ENCOURS);
			ftx.setStatus(FpstatutFileTransaction.CONTROLE);
			ftx.setIntegration(Boolean.FALSE);
			ventilationRepo.saveAndFlush(ftx);
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("Exception in process file : "+e.getMessage());
			ftx.setStatus(FpstatutFileTransaction.CANCEL);
			ftx.setErrorMsg(e.getMessage());
			ventilationRepo.saveAndFlush(ftx);
			return ftx;
		}
		
		return ftx;
	}

	private Ventilation getOrCreateFileVentilation(String filename, String partcode, String user) {
		
		try {
			Optional<Ventilation> opt_ftx = ventilationRepo.findByFileNameAndIntegration(filename, Boolean.FALSE);
			//Ventilation ftx = ventilationRepo.findByFileNameAndStatus(filename, FpstatutFileTransaction.CONTROLE);
			Ventilation ftx;
			logger.info("Save opt_ftx.isEmpty() "+opt_ftx.isPresent());
			if(opt_ftx.isEmpty()) {
				ftx = new Ventilation();
				ftx.setFileName(filename);
				ftx.setUser(user);
				ftx.setPartenaire(partcode);
				ventilationRepo.save(ftx);
				logger.info("Save ventilationRepo");	
				return ftx;
			}
			
			
		}
		catch(Exception e) {
			return new Ventilation();
		}
		return new Ventilation();
	}
	

	private ServerParameter extractedRemoteParamFromBD() {
		ServerParameter sp = new ServerParameter();
		PropertyConfigs param;
		
		param = propertyConfigRepository.findByCode(FileHelper.PAYPART_RMT_HOST_VENTI);
		sp.setHost(param != null ? param.getValue() : "");
		
		param = propertyConfigRepository.findByCode(FileHelper.PAYPART_RMT_USER_VENTI);
		sp.setUser(param != null ? param.getValue() : "");
		
		param = propertyConfigRepository.findByCode(FileHelper.PAYPART_RMT_PWD_VENTI);
		sp.setPassword(param != null ? PropertyConfigs.decrypPassword(param) : "");
		
		param = propertyConfigRepository.findByCode(FileHelper.PAYPART_RMT_PORT_VENTI);
		String port = param != null ? param.getValue() : "";
		if(!port.isEmpty() && port != null)
			sp.setPort(Integer.parseInt(port));
		
		param = propertyConfigRepository.findByCode(FileHelper.PAYPART_RMT_REP_VENTI);
		sp.setFtpDir(param != null ? param.getValue() : "");
		
		param = propertyConfigRepository.findByCode(FileHelper.PAYPART_RMT_REP_ARCH_VENTI);
		sp.setFtpDirArch(param != null ? param.getValue() : "");
		return sp;
	}
	
	public Boolean ventilationDetail(DetailsVentilation d) throws Exception {

		Boolean resultat = Boolean.FALSE;

		try {
			
//			ParameterFile parameter = parameterFileEjb.findParameterFileWithCode("CODE_OPE_SYSTAC_SANSFRAIS");
//			if(parameter == null) return resultat;
//			String codeOperation = parameter.getValue();			
//				
//			Date dco = getDco();
//			FpparametersGeneral param = parametersEjb.findById(new FpparametersGeneral().getCode());
//
//			Boolean result = virementPayments.process(d.getNcpSender(), d.getNcpBeneficiaire(), d.getMontant(), d.getNomBeneficiaire(), codeOperation, param.getCodeUtil(), d.getReferenceVentilation(), param.getNumCompteLiaison());
//			if(!result) {
//				d.setStatus(FpstatutFileTransaction.ECHEC);
//
//				ErreursVentilation error = new ErreursVentilation(d.getReferenceVentilation(), d.getParent(), d.getMontant(), d.getDevise(),
//						d.getNomBeneficiaire(), d.getNcpSender(), d.getNcpBeneficiaire(), FpstatutFileTransaction.ECHEC,
//						"");
//				errVentilationRepo.save(error);
//				resultat = Boolean.FALSE;
//			}
//			else{
//				d.setStatus(FpstatutFileTransaction.VALIDE);
//				d.setDateComptable(dco);
//			}
//			detailsVentilationRepo.save(d); //.update(d);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultat;
	}
	
	private Boolean executeVentilation(Ventilation ftx) {
		
		List<DetailsVentilation> listDetailVentilation;
		List<DetailsVentilation> listDetailVentilationCredit;
		
		Map<String,List<DetailsVentilation>> mapDetailsVentilation = new HashMap<String, List<DetailsVentilation>>();
		
		
		listDetailVentilation = detailsVentilationRepo.findByParentAndStatus(ftx, FpstatutFileTransaction.ENCOURS);
		
		logger.info("listDetailVentilation "+listDetailVentilation.size());
		
		List<DetailsVentilation> _details = listDetailVentilation.stream().filter(m -> "D".equals(m.getSens())).collect(Collectors.toList());
		
		for(DetailsVentilation detail : _details) {
			
			//listDetailVentilationCredit = detailsVentilationRepo.findByReferenceTransactionAndSensAndStatus(detail.getReferenceTransaction(), "C", FpstatutFileTransaction.ENCOURS);
			listDetailVentilationCredit = detailsVentilationRepo.findByLigneAndSensAndStatus(detail.getLigne(), "C", FpstatutFileTransaction.ENCOURS);
			
			if(!listDetailVentilationCredit.isEmpty()) {
				
				logger.info("listDetailVentilationCredit "+listDetailVentilationCredit.size());
				
				// Verification total credit == total debit
				double sumCredit = listDetailVentilationCredit.stream().mapToDouble(d -> d.getMontant()).reduce(0, Double::sum);
				logger.info("Somme Credit =  "+sumCredit);
				logger.info("Somme Dedit =  "+detail.getMontant());
				
				if( Math.abs(detail.getMontant() - sumCredit) <= 1) {
					logger.info("Transaction equilibrée =  " + Math.abs(detail.getMontant() - sumCredit));
					listDetailVentilationCredit.add(detail);
					mapDetailsVentilation.put(detail.getLigne(), listDetailVentilationCredit);
					//comptabilisationTransactions(listDetailVentilationCredit, ftx.getUser());
				}
				else {
					listDetailVentilationCredit.stream().forEach(elem -> {
						elem.setStatus(FpstatutFileTransaction.CANCEL);
						elem.setMotif("TRANSACTION NON EQUILIBREE");
					});
					detail.setStatus(FpstatutFileTransaction.CANCEL);	
					detail.setMotif("TRANSACTION NON EQUILIBREE");
					
					listDetailVentilationCredit.add(detail);
					detailsVentilationRepo.saveAllAndFlush(listDetailVentilationCredit);
					
					listDetailVentilationCredit.clear();
					
					return Boolean.FALSE;
				}
			}
			
		}
		
		/*
		for(DetailsVentilation detail : listDetailVentilation) {
			
			if("D".equals(detail.getSens())) {
				// Recherche de toutes les transactions de credit liés
				listDetailVentilationCredit = detailsVentilationRepo.findByReferenceTransactionAndSensAndStatus(detail.getReferenceTransaction(), "C", FpstatutFileTransaction.ENCOURS);
				if(!listDetailVentilationCredit.isEmpty()) {
					
					logger.info("listDetailVentilationCredit "+listDetailVentilationCredit.size());
					
					// Verification total credit == total debit
					double sumCredit = listDetailVentilationCredit.stream().mapToDouble(d -> d.getMontant()).reduce(0, Double::sum);
					logger.info("Somme Credit =  "+sumCredit);
					logger.info("Somme Dedit =  "+detail.getMontant());
					
					if(detail.getMontant() == sumCredit) {
						logger.info("Transaction equilibrée =  ");
						listDetailVentilationCredit.add(detail);
						mapDetailsVentilation.put(detail.getReferenceTransaction(), listDetailVentilationCredit);
						//comptabilisationTransactions(listDetailVentilationCredit, ftx.getUser());
					}
					else {
						listDetailVentilationCredit.stream().forEach(elem -> {
							elem.setStatus(FpstatutFileTransaction.CANCEL);
							elem.setMotif("COMPTE BENEFICIAIRE INEXISTANT");
						});
						detail.setStatus(FpstatutFileTransaction.CANCEL);	
						detail.setMotif("COMPTE BENEFICIAIRE INEXISTANT");
						
						listDetailVentilationCredit.add(detail);
						detailsVentilationRepo.saveAllAndFlush(listDetailVentilationCredit);
						
						listDetailVentilationCredit.clear();
					}
				}
			}
		}
		*/
		return comptabilisationTransactions(mapDetailsVentilation, ftx.getPartenaire(), ftx.getUser());
		
	}
	
	private Boolean comptabilisationTransactions(Map<String,List<DetailsVentilation>> mapDetailsVentilation, String partner, String user) {
		
		logger.info("mapDetailsVentilation.size = " + mapDetailsVentilation.size());
		listeEcritures = new ArrayList<Bkmvti>();
		Map<String, Transactions> mapTransactions = new HashMap<String, Transactions>();
		Ventilation parentVentil = new Ventilation();
		
		
		// Recherche de toutes les transactions à comptabiliser
		List<Transactions> listTransactionAcomptabiliser = transactionRepo.findByPartnerCodeAndPostCompleteAndStatusTrans(partner, Boolean.FALSE, TransactionStatus.SUCCESS);
		logger.info("transactions à comptabiliser: " + listTransactionAcomptabiliser.size() + " Code Partenaire: " + partner);
	    listTransactionAcomptabiliser.forEach(trxCompta -> {
	    	mapTransactions.put(trxCompta.getPartnerTrxID(), trxCompta);
	    });
		
		mapDetailsVentilation.forEach((k, v) -> {
			//System.out.println((k + ":" + v));
			Bkeve bkeve = null;
			// Rechercher dans les transactions du jour ceux ayant la reference fourni
			//transactionRepo.findByPartnerTrxIDAndPartnerCode(k, partner);
			
			Transactions transactionCourante = mapTransactions.get(k.split("_")[1]);
			
			if(transactionCourante != null) {
				logger.info("Generer les ecriutres pour: " + v.get(0).getReferenceTransaction());
				
				logger.info("Recherche de EVE: " + transactionCourante.getEveid());
				bkeve = bkeveRepo.findByEveAndEta(transactionCourante.getEveid(), "VA");
				
				String ageD, compteD, _clcD;
				String senderAccountNo;
				String receiverAccountNo;
				String codeOperation;
				String ncpLiaison;
				String nomBenef;
				Double amount;
				String pie;
				String age;
				String compte;
				String clc;
				
				Bkmvti bkmvti = null;
				
				codeOperation = transactionCourante.getOpe();
			    pie = RandomStringUtils.randomNumeric(6);
			    // Agence du sender
			    ageD = v.get(v.size() - 1).getNcpSender().substring(0, 5);
			    compteD = v.get(v.size() - 1).getNcpSender().split("-")[1];
			    _clcD = v.get(v.size() - 1).getNcpSender().split("-")[2];
			    ResponseBkcom infoCompteD = CoreBankingServices.getaccountinfo(keysecurity, host, ageD, compteD);
				Bkcom _bkcomD = getCompte(infoCompteD);
			    
			    
			
			    for(DetailsVentilation ecritures : v) {
				//v.forEach(ecritures -> {
//					// Construit l'evenement
//					bkeve = makeBkeve(senderAccountNo, receiverAccountNo, amount, nomBenef, codeOperation, user, ecritures.getReferenceVentilation(), ncpLiaison);
//					
					// Construit les ecritures et jajoute à levenement
					
					age = "C".equals(ecritures.getSens()) ? ecritures.getNcpBeneficiaire().substring(0, 5) : ecritures.getNcpSender().substring(0, 5);
					compte = "C".equals(ecritures.getSens()) ? ecritures.getNcpBeneficiaire().substring(6, 17) : ecritures.getNcpSender().substring(6, 17);
					clc = "C".equals(ecritures.getSens()) ? ecritures.getNcpBeneficiaire().substring(18, 20) : ecritures.getNcpSender().substring(18, 20);
					
					ResponseBkcom infoCompte = CoreBankingServices.getaccountinfo(keysecurity, host, age, compte);
					Bkcom bkcom = getCompte(infoCompte);
				
					Bkcom bkcomD, bkcomC;
					
					logger.info("BKCOM = " + bkcom.getCha());
					if (bkcom != null) {
						
						logger.info("BKCOM.AGE = " +bkcom.getAge() + " | BKCOM.AGED = " +ageD);
						if (!bkcom.getAge().equalsIgnoreCase(ageD)) {
							
							infoCompte = CoreBankingServices.getaccountinfo(keysecurity, host, ageD, ncpLiason);
							bkcomD = getCompte(infoCompte);
							logger.info("BKCOMD.CLE = " + bkcomD.getClc());
							
							infoCompte = CoreBankingServices.getaccountinfo(keysecurity, host, age, ncpLiason);
							bkcomC = getCompte(infoCompte);
							logger.info("BKCOMC.CLE = " + bkcomC.getClc());
							
							// Credite le compte de liaison de l'agence de lexpediteur
							bkmvti = new Bkmvti(ageD, bkcomD.getDev(), bkcomD.getCha(), bkcomD.getNcp(), bkcomD.getSuf(), 
									codeOperation,"", user, transactionCourante.getEveid(), bkcomD.getClc(), getDco(), 
									"1103", null, ecritures.getMontant(),"C", 
									ecritures.getReferenceVentilation(), "", pie, 1.0, 0.0, "O", "00099", 0.0, "00099S");
							listeEcritures.add(bkmvti);	
							
							// Debite le compte de liason de l'agence de destiantion
							bkmvti = new Bkmvti(age, bkcomC.getDev(), bkcomC.getCha(), bkcomC.getNcp(), bkcomC.getSuf(), 
									codeOperation,"", user, transactionCourante.getEveid(), bkcomC.getClc(), getDco(), 
									"1103", null, ecritures.getMontant(),"D", 
									ecritures.getReferenceVentilation(), "", pie, 1.0, 0.0, "O", "00099", 0.0, "00099S");
							listeEcritures.add(bkmvti);	
						}
						
						
						bkmvti = new Bkmvti(age, bkcom.getDev(), bkcom.getCha(), bkcom.getNcp(), bkcom.getSuf(), 
								codeOperation,"", user, transactionCourante.getEveid(), bkcom.getClc(), getDco(), 
								"1103", null, ecritures.getMontant(),ecritures.getSens(), 
								ecritures.getReferenceVentilation(), "", pie, 1.0, 0.0, "O", "00099", 0.0, "00099S");
						
						listeEcritures.add(bkmvti);	
						
						// Debite le compte expediteur
						bkmvti = new Bkmvti(ageD, _bkcomD.getDev(), _bkcomD.getCha(), _bkcomD.getNcp(), _bkcomD.getSuf(), 
								codeOperation,"", user, transactionCourante.getEveid(), _bkcomD.getClc(), getDco(), 
								"1103", null, ecritures.getMontant(),"D", 
								ecritures.getReferenceVentilation(), "", pie, 1.0, 0.0, "O", "00099", 0.0, "00099S");
						listeEcritures.add(bkmvti);	
						
					}
					
				}
				
				bkeve.getEcritures().addAll(listeEcritures);
				bkeveRepo.saveAndFlush(bkeve);
				
				v.stream().forEach(elem -> {
					elem.setStatus(FpstatutFileTransaction.VALIDE);
					elem.setMotif("TRANSACTION OK");
				});
				detailsVentilationRepo.saveAllAndFlush(v);
				
				transactionCourante.setPostCBS(Boolean.TRUE);
				transactionRepo.saveAndFlush(transactionCourante);
				
			}
			else {
				logger.info("Transaction inexistante");
				v.stream().forEach(elem -> {
					elem.setStatus(FpstatutFileTransaction.CANCEL);
					elem.setMotif("TRANSACTION INEXISTANTE");
				});
				
				detailsVentilationRepo.saveAllAndFlush(v);
				
//				Optional<Ventilation> extractVentil = Optional.ofNullable(v.get(0).getParent());
//				if(extractVentil.isEmpty()) {
//					Ventilation parentVentil = extractVentil.get();
//					parentVentil.setStatus(FpstatutFileTransaction.ECHEC);
//					ventilationRepo.saveAndFlush(parentVentil);
//				}
				
			}
		    
		});
		
		logger.info("listeEcritures: " + listeEcritures.size());
		DataResponseDTO dataResponse;
		
		if(!listeEcritures.isEmpty()) {
			dataResponse =  CoreBankingServices.postAccountingentries(keysecurity, host, listeEcritures);
			logger.info("Entries response "+dataResponse);
			
			Optional<List<DetailsVentilation>> extractVentil = mapDetailsVentilation.values().stream().findFirst();
			logger.info("extractVentil isPresent "+extractVentil.isPresent());
			///Optional<Ventilation> extractVentil = Optional.ofNullable(v.get(0).getParent());
			if(!extractVentil.isEmpty()) {
				parentVentil = extractVentil.get().get(0).getParent();
				parentVentil.setIntegration(Boolean.TRUE);
				ventilationRepo.saveAndFlush(parentVentil);
			}
			
			return Boolean.TRUE;
		}
			 
		return Boolean.FALSE;
	}
	
	private Date getDco() {
		DataResponseDTO dtoDCO = CoreBankingServices.getDateComptable(keysecurity, host);
		Date dco = new Date();
		if(StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), dtoDCO.getCode()) || StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS2.toString(), dtoDCO.getCode()) ){
			if(!StringUtils.isBlank(dtoDCO.getData())){
				dco = DateUtil.parse(dtoDCO.getData(), DateUtil.DATE_MINUS_FORMAT_SINGLE);
			}
		}
		return dco;
	}
	
	private Bkcom getCompte(ResponseBkcom infoCompte)  {
		
		Bkcom bkcom = null;
		if(!StringUtils.equalsIgnoreCase(ResponseHolder.SUCESS.toString(), infoCompte.getCode())) {
			if(!infoCompte.getData().isEmpty()){
				bkcom = infoCompte.getData().get(0);
			}
		}
		
		return bkcom;
	}
	
	private Date getDateValeur() {
		return null;
	}
	
//	public Bkeve makeBkeve(String senderAccountNo, String receiverAccountNo, Double amount, String nomBenef, String codeOperation, String user, String refTrans, String ncpLiaison){
//		
//		
//	}

}
