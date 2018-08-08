package com.example.jackson;

public class Bundle {

	private String id;
	private Entry[] entry;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Entry[] getEntry() {
		return entry;
	}

	public void setEntry(Entry[] entry) {
		this.entry = entry;
	}
}
