package resources;

public enum APIResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resources;
	
	//constructor to initialize the resource value
	APIResources(String resources) {
		this.resources = resources;
	}
	
	public String getResourse() {
		return resources;
	}	

}
