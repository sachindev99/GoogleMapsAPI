package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	RequestSpecification req;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	JsonPath js;
	

	@Given("Add Place Payload with {string} {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address, String phoneNumber) throws IOException {
		//req will be used to establish a connection b/w given() and when()
		req= given().log().all().spec(requestSpecification()).body(data.addPlacePayload(name,language,address,phoneNumber));   
	}	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource,String method) {
		//calling constructor
		//we cannot instantiate an enum using the new keyword
		//valueOf() method allows you to convert a string into an enum constant,
		APIResources apiResources=APIResources.valueOf(resource);
		
		resSpec= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		if(method.equalsIgnoreCase("POST")) {
			response =  req.when().post(apiResources.getResourse());
		}
		
		else if(method.equalsIgnoreCase("GET")) {
			response =  req.when().get(apiResources.getResourse());
		}

	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}
	@Then("the {string} in response body is {string}")
	public void the_in_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPath(response, keyValue),expectedValue);

	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id= getJsonPath(response, "place_id");
	    req= given().spec(requestSpecification()).queryParam("place_id",place_id);
	    //calling another method
	    user_calls_with_http_request(resource,"GET");
	    String actualName = getJsonPath(response, "name");
	    assertEquals(actualName, expectedName);
	    
	}
	
	@Given("DeletePlace Payload in created")
	public void delete_place_payload_in_created() throws IOException {
	    req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
