package app;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

	private String token_value;

	@RequestMapping("/")
	public ModelAndView index(Principal principal) {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping("/principal")
	public Principal principal(Authentication principal) {
		token_value = ((OAuth2AuthenticationDetails) principal.getDetails()).getTokenValue();
		return principal;
	}

	@RequestMapping("/patient")
	public ModelAndView getPatient() throws JSONException {

		ModelAndView mav = new ModelAndView("patient");
		String patientUrl = "https://sandbox.bluebutton.cms.gov/v1/fhir/Patient/";
		HttpEntity<String> response = getResponse(patientUrl);

		PatientMap patientMap = new PatientMap(response);
		List<Map<String, String>> patient = patientMap.getClaimMap();

		mav.addObject("patient", patient);

		return mav;
	}

	// @RequestMapping("/coverage")
	// public ModelAndView getCoverage() throws JSONException {
	// ModelAndView mav = new ModelAndView("coverage");
	// String coverageUrl = "https://sandbox.bluebutton.cms.gov/v1/fhir/Coverage/";
	// HttpEntity<String> response = getResponse(coverageUrl);
	//
	// ArrayList<JSONObject> resources = getResources(response.getBody());
	// IParser parser = ctx.newJsonParser();
	// parser.setPrettyPrint(true);
	// Coverage coverage = parser.parseResource(Coverage.class,
	// resources.get(0).toString());
	//
	// mav.addObject("status", coverage.getStatus());
	//
	// return mav;
	// }

	@RequestMapping("/eob")
	public ModelAndView getEoB() throws JSONException {
		ModelAndView mav = new ModelAndView("eob");
		String eobUrl = "https://sandbox.bluebutton.cms.gov/v1/fhir/ExplanationOfBenefit/";
		HttpEntity<String> response = getResponse(eobUrl);

		EOBMap claimMap = new EOBMap(response);
		List<Map<String, String>> claims = claimMap.getClaimMap();

		mav.addObject("claims", claims);

		return mav;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HttpEntity<String> getResponse(String URL) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token_value);

		HttpEntity entity = new HttpEntity(headers);
		HttpEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		return response;
	}
}
