package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	//By making reqSpec static, we ensure that the request specification is created only once and shared by all instances of the class. 
	public static RequestSpecification reqSpec;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(reqSpec==null) {
			PrintStream log = new PrintStream(new FileOutputStream("loging.txt"));
			//Request Specification 
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", "qaclick123").build();
			return reqSpec;
		}
		
		return reqSpec;
		
	}
	
	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/himaniwadhawan/eclipse-workspace/APIFramework/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
