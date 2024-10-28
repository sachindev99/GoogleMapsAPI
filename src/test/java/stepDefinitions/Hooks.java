package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//execute this code only if place id is null (when you want to execute deleteAPIPlace alone)
		//this code will create a place_id
		StepDefinition sd = new StepDefinition();
		if(StepDefinition.place_id==null) {
			sd.add_place_payload_with("TestABC123", "English", "India", "343241");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("TestABC123", "getPlaceAPI");
		}
	}

}
