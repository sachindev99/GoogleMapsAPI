Feature: Validating Place APIs
	@AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>" "<phoneNumber>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And the "status" in response body is "OK"
    And the "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"
    
  Examples:
  	|name 				|language		| address						|	phoneNumber	|
		|AAHouse			|English		| Word cross center | 23132135		|
		|White House 	|French-CA 	| Montreal 					| 43743242		|
		|THouse				|English-CA	| Vancouver					| 64734432		|
		
	@DeletePlace
	Scenario: Verify if the Delete Place functionality is working
		Given DeletePlace Payload in created
		When user calls "deletePlaceAPI" with "POST" http request
		Then the API call is success with status code 200
		And the "status" in response body is "OK"
		
			
		
		
		 	
	  
