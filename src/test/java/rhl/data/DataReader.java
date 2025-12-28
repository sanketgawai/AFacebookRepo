package rhl.data;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class DataReader {

	 public static List<HashMap<String, String>> getData() {
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            return mapper.readValue(
	                    new File(System.getProperty("user.dir")+"\\src\\test\\java\\rhl\\data\\SignUpCredential.json"),
	                    new TypeReference<List<HashMap<String, String>>>() {}
	            );
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to read JSON file", e);
	        }
	    }
	
}
