package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language,String address,String phoneNumber) {
		//serialization(converting java object to JSON)
		AddPlace loc = new AddPlace();
		loc.setAccuracy(50);
		loc.setName(name);
		loc.setAddress(address);
		loc.setLangauage(language);
		loc.setPhonenumber(phoneNumber);
		loc.setWebsite("https://rahulshettyacademy.com");

		List<String> type = new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		loc.setTypes(type);

		Location l = new Location();
		l.setLat(-38.67563);
		l.setLng(33.56677);

		loc.setLocation(l);	
		return loc;
		
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
