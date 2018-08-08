package com.example;

import java.io.IOException;
import java.security.Principal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.jackson.Bundle;
import com.example.jackson.Entry;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {

	private String token_value;

	@RequestMapping("/patient")
	public Principal getPatient(Authentication principal) {
		token_value = ((OAuth2AuthenticationDetails) principal.getDetails()).getTokenValue();

		return principal;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/coverage")
	public String getCoverage() {
		String coverageUrl = "https://sandbox.bluebutton.cms.gov/v1/fhir/Coverage/";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token_value);

		HttpEntity entity = new HttpEntity(headers);
		HttpEntity<String> response = restTemplate.exchange(coverageUrl, HttpMethod.GET, entity, String.class);

		// String response = restTemplate.getForObject(coverageUrl, String.class);
		// return response;
		return response.getBody();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/eob")
	public String getEoB() throws JsonParseException, JsonMappingException, IOException {
		String eobUrl = "https://sandbox.bluebutton.cms.gov/v1/fhir/ExplanationOfBenefit/";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token_value);

		HttpEntity entity = new HttpEntity(headers);
		HttpEntity<String> response = restTemplate.exchange(eobUrl, HttpMethod.GET, entity, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Bundle patient = objectMapper.readValue(response.getBody(), Bundle.class);

		System.out.println(patient.getId());
		Entry[] entries = patient.getEntry();
		for (Entry entry : entries) {
			System.out.println(entry.getFullUrl());
			System.out.println(entry.getResource().getId());
		}

		return response.getBody();
	}
}
