package vindbrein.domain.document;

import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OfferPreference {

	@Id
	private String id;
	
	
	private LinkedHashMap<String, Integer> values;	
	
	//getters and setters

	public String getId() {		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public LinkedHashMap<String, Integer> getValues() {
		return values;
	}

	public void setValues(LinkedHashMap<String, Integer> values) {
		this.values = values;
	}
}
