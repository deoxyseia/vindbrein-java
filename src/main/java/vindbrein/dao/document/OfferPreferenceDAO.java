package vindbrein.dao.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import vindbrein.domain.document.OfferPreference;

@Repository
public class OfferPreferenceDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
	public static final String COLLECTION_NAME = "offer_preference";
	 
	public void addOfferPreference(OfferPreference offerPreference) {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
		//offerPreference.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(offerPreference, COLLECTION_NAME);
	}

	public List<OfferPreference> listOfferPreference() {
		return mongoTemplate.findAll(OfferPreference.class, COLLECTION_NAME);
	}

	public void deleteOfferPreference(OfferPreference offerPreference) {
		mongoTemplate.remove(offerPreference, COLLECTION_NAME);
	}

	public void updateOfferPreference(OfferPreference offerPreference) {			
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(offerPreference.getId())), 
						Update.update("values", offerPreference.getValues()), OfferPreference.class, COLLECTION_NAME);
	}
	
	public OfferPreference getOfferPreferenceById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		
		List<OfferPreference> list = mongoTemplate.find(query, OfferPreference.class, COLLECTION_NAME);
		
		if(list.size()!=0){
			return (OfferPreference)list.get(0);
		} else {
			return null;
		}
	}

}
