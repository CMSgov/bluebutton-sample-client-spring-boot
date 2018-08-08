package com.example.jackson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Resource {

	private String status;
	private JsonNode insurance;
	private JsonNode patient;
	private JsonNode diagnosis;
	private JsonNode type;
	private JsonNode billablePeriod;
	private String resourceType;
	private Extension[] extension;
	private JsonNode payment;
	private String id;
	private JsonNode identifier;
	private JsonNode item;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JsonNode getInsurance() {
		return insurance;
	}

	public void setInsurance(JsonNode insurance) {
		this.insurance = insurance;
	}

	public JsonNode getPatient() {
		return patient;
	}

	public void setPatient(JsonNode patient) {
		this.patient = patient;
	}

	public JsonNode getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(JsonNode diagnosis) {
		this.diagnosis = diagnosis;
	}

	public JsonNode getType() {
		return type;
	}

	public void setType(JsonNode type) {
		this.type = type;
	}

	public JsonNode getBillablePeriod() {
		return billablePeriod;
	}

	public void setBillablePeriod(JsonNode billablePeriod) {
		this.billablePeriod = billablePeriod;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Extension[] getExtension() {
		return extension;
	}

	public void setExtension(Extension[] extension) {
		this.extension = extension;
	}

	public JsonNode getPayment() {
		return payment;
	}

	public void setPayment(JsonNode payment) {
		this.payment = payment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JsonNode getIdentifier() {
		return identifier;
	}

	public void setIdentifier(JsonNode identifier) {
		this.identifier = identifier;
	}

	public JsonNode getItem() {
		return item;
	}

	public void setItem(JsonNode item) {
		this.item = item;
	}

	private Map<String, Object> otherProperties = new HashMap<String, Object>();

	public Object get(String name) {
		return otherProperties.get(name);
	}
}
