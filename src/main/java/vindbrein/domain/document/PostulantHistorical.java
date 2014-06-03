package vindbrein.domain.document;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PostulantHistorical {

	@Id
	private String id;

	private HashMap<String, Integer> values;
	
	//getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, Integer> getValues() {
		return values;
	}

	public void setValues(HashMap<String, Integer> values) {
		this.values = values;
	}

}
