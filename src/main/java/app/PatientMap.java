package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.dstu3.model.Patient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

public class PatientMap {

	private List<Map<String, String>> patient;
	private JSONObject resource;
	private FhirContext ctx = FhirContext.forDstu3();
	private IParser parser;

	public PatientMap(HttpEntity<String> response) throws JSONException {
		resource = getResources(response.getBody()).get(0);
		parser = ctx.newJsonParser();
		parser.setPrettyPrint(true);
		patient = new ArrayList<Map<String, String>>();

		addClaims();
	}

	public List<Map<String, String>> getClaimMap() {
		return patient;
	}

	private void addClaims() {
		Map<String, String> patientRecord = new HashMap<String, String>();
		Patient patientRes = parser.parseResource(Patient.class, resource.toString());

		patientRecord.put("id", patientRes.getId().substring(8, 22));
		patientRecord.put("name",
				patientRes.getName().get(0).getGivenAsSingleString() + " " + patientRes.getName().get(0).getFamily());
		patientRecord.put("gender", patientRes.getGender().getDisplay());
		patientRecord.put("birthDate", patientRes.getBirthDate().toString().substring(0, 10));
		patient.add(patientRecord);
	}

	private ArrayList<JSONObject> getResources(String response) throws JSONException {
		ArrayList<JSONObject> resources = new ArrayList<JSONObject>();

		JSONObject json = new JSONObject(response);
		JSONArray entries = json.getJSONArray("entry");
		for (int i = 0; i < entries.length(); i++) {
			JSONObject entry = entries.getJSONObject(i);
			JSONObject resource = entry.getJSONObject("resource");
			resources.add(resource);
		}

		return resources;
	}

}
