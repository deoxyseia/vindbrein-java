package vindbrein.dao.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import vindbrein.domain.document.OfferHistorical;

@Repository
public class OfferHistoricalDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
	public static final String COLLECTION_NAME = "offer_historical";
	 
	public void addOfferHistorical(OfferHistorical offerHistorical) {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
		//offerHistorical.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(offerHistorical, COLLECTION_NAME);		
	}

	public List<OfferHistorical> listOfferHistorical() {
		return mongoTemplate.findAll(OfferHistorical.class, COLLECTION_NAME);
	}

	public void deleteOfferHistorical(OfferHistorical offerHistorical) {
		mongoTemplate.remove(offerHistorical, COLLECTION_NAME);
	}

	public void updateOfferHistorical(OfferHistorical offerHistorical) {			
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(offerHistorical.getId())), 
						Update.update("values", offerHistorical.getValues()), OfferHistorical.class, COLLECTION_NAME);
	}
	
	public OfferHistorical getOfferHistoricalById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		
		List<OfferHistorical> list = mongoTemplate.find(query, OfferHistorical.class, COLLECTION_NAME);
				
		if(list.size()!=0){
			return (OfferHistorical)list.get(0);
		} else {
			return null;
		}
	}

}
