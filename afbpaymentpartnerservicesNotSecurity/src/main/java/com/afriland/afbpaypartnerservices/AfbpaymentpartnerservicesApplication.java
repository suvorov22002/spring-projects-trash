package com.afriland.afbpaypartnerservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.afriland.afbpaypartnerservices.config.security.CustomClientHttpRequestFactory;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@RestController
@EnableWebMvc
@EnableScheduling
@SpringBootApplication
public class AfbpaymentpartnerservicesApplication {
	
	//private String xmlString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:amp=\"http://soprabanking.com/amplitude\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <amp:getAccountDetailRequestFlow>\r\n         <amp:requestHeader>\r\n            <amp:requestId>1</amp:requestId>\r\n            <amp:serviceName>getAccountDetail</amp:serviceName>\r\n            <amp:timestamp>2023-02-16T08:34:53</amp:timestamp>\r\n\t\t\t<amp:originalName>APPTEST</amp:originalName>\r\n            <amp:languageCode>001</amp:languageCode>\r\n\t\t\t<amp:userCode>SOPRA3</amp:userCode>\r\n         </amp:requestHeader>\r\n         <amp:getAccountDetailRequest>\r\n            <amp:accountIdentifier>\r\n               <amp:branch>00001</amp:branch>\r\n\t\t\t   <amp:currency>XAF</amp:currency>\r\n\t\t\t   <amp:account>05957051051</amp:account>\r\n            </amp:accountIdentifier>\r\n         </amp:getAccountDetailRequest>\r\n      </amp:getAccountDetailRequestFlow>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>";
	private String xmlString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:amp=\"http://soprabanking.com/amplitude\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <amp:getCustomerDetailRequestFlow>\r\n         <amp:requestHeader>\r\n            <amp:requestId>1</amp:requestId>\r\n            <amp:serviceName>getCustomerDetail</amp:serviceName>\r\n            <amp:timestamp>2023-02-16T08:34:53</amp:timestamp>\r\n\t\t\t<amp:originalName>APPTEST</amp:originalName>\r\n            <amp:languageCode>001</amp:languageCode>\r\n\t\t\t<amp:userCode>SOPRA3</amp:userCode>\r\n         </amp:requestHeader>\r\n         <amp:getCustomerDetailRequest>\r\n            <amp:customerIdentifier>\r\n               <amp:customerCode>0651140</amp:customerCode>\r\n            </amp:customerIdentifier>\r\n         </amp:getCustomerDetailRequest>\r\n      </amp:getCustomerDetailRequestFlow>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>";
	
	public static void main(String[] args) {
		SpringApplication.run(AfbpaymentpartnerservicesApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.afriland.afbpaypartnerservices"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Payment PARTNER API Service Spring Boot REST API Documentation")
				.description("Payment PARTNER API Service For Afriland First Bank Paiement Integration")
				.contact(new Contact("Yves FOKAM", "https://afrilandfirstbank.com/", "mailto:yves_labo@afrilandfirstbank.com"))
				.version("1.0")
				.build();
	}
	
	
	@RequestMapping(value = "/hello")
	public String helloWorld() {
		
		RestTemplate restTemplate =  new RestTemplate(new CustomClientHttpRequestFactory(1200, 1200, true));
	    //Create a list for the message converters
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    //Add the String Message converter
	    messageConverters.add(new StringHttpMessageConverter());
	    //Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);


	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.set("SOAPAction", "getCustomerDetail");
	   // headers.set("Authorization", "Bearer 3f4c95ad-5c72-a69f-ee13-664489a41d4d");
	    headers.setBearerAuth("3f4c95ad-5c72-a69f-ee13-664489a41d4d");
	   
	    
	    //System.out.println("xmlString: " + xmlString);
	    HttpEntity<String> request = new HttpEntity<String>(xmlString, headers);

	    final ResponseEntity<String> response = restTemplate.postForEntity("https://172.21.88.95:8095/getCustomerDetail", request, String.class);
	    return response.getBody();
		//return "AFB PAYMENT PARTNER API Service For Afriland First Bank Paiement Integration !";
	}
	
	
	@Value("${allowed.origins}")
	private String allowedOrigins;
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/rest/api/paypartnerservices").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*").allowedHeaders("*");
			} 
		};
	}
	
	
}
