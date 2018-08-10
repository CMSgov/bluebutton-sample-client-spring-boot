package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.dstu3.model.ExplanationOfBenefit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

public class EOBMap {
	
	private List<Map<String, String>> claims;
	private ArrayList<JSONObject> resources;
	private FhirContext ctx = FhirContext.forDstu3();
	private IParser parser;

	public EOBMap(HttpEntity<String> response) throws JSONException {
		resources = getResources(response.getBody());
		parser = ctx.newJsonParser();
		parser.setPrettyPrint(true);
		claims = new ArrayList<Map<String, String>>();

		addClaims();
	}
	
	public List<Map<String, String>> getClaimMap() {
		return claims;
	}
	
	private void addClaims() {
		for (JSONObject resource : resources) {
			Map<String, String> claim = new HashMap<String, String>();
			ExplanationOfBenefit eob = parser.parseResource(ExplanationOfBenefit.class, resource.toString());

			claim.put("claimId", eob.getId().substring(29, eob.getId().length()));
			claim.put("patient", eob.getPatient().getReference().toString().substring(8, 22));
			claim.put("diagnosis", eob.getDiagnosisFirstRep().getTypeFirstRep().getCodingFirstRep().getDisplay());
			claim.put("dates", eob.getBillablePeriod().getStart().toString().substring(0, 10));
			claims.add(claim);
		}
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
