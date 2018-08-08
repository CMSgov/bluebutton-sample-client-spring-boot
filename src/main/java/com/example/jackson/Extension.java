package com.example.jackson;

import com.fasterxml.jackson.databind.JsonNode;

public class Extension {

	private JsonNode valueMoney;
	private JsonNode valueIdentifier;
	private JsonNode valueCoding;
	private String url;

	public JsonNode getValueMoney() {
		return valueMoney;
	}

	public void setValueMoney(JsonNode valueMoney) {
		this.valueMoney = valueMoney;
	}

	public JsonNode getValueIdentifier() {
		return valueIdentifier;
	}

	public void setValueIdentifier(JsonNode valueIdentifier) {
		this.valueIdentifier = valueIdentifier;
	}

	public JsonNode getValueCoding() {
		return valueCoding;
	}

	public void setValueCoding(JsonNode valueCoding) {
		this.valueCoding = valueCoding;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
