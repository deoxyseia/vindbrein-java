package vindbrein.dao.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import vindbrein.domain.document.OfferSelfDescription;

@Repository
public class OfferSelfDescriptionDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
	public static final String COLLECTION_NAME = "offer_selfdescription";
	 
	public void addOfferSelfDescription(OfferSelfDescription offerSelfDescription) {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
		//offerSelfDescription.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(offerSelfDescription, COLLECTION_NAME);
	}

	public List<OfferSelfDescription> listOfferSelfDescription() {
		return mongoTemplate.findAll(OfferSelfDescription.class, COLLECTION_NAME);
	}

	public void deleteOfferSelfDescription(OfferSelfDescription offerSelfDescription) {
		mongoTemplate.remove(offerSelfDescription, COLLECTION_NAME);
	}

	public void updateOfferSelfDescription(OfferSelfDescription offerSelfDescription) {			
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(offerSelfDescription.getId())), 
						Update.update("values", offerSelfDescription.getValues()), OfferSelfDescription.class, COLLECTION_NAME);
	}
	
	public OfferSelfDescription getOfferSelfDescriptionById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		
		List<OfferSelfDescription> list = mongoTemplate.find(query, OfferSelfDescription.class, COLLECTION_NAME);
				
		if(list.size()!=0){
			return (OfferSelfDescription)list.get(0);
		} else {
			return null;
		}
	}

}
