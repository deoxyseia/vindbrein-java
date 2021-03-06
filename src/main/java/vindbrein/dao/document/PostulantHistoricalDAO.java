package vindbrein.dao.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import vindbrein.domain.document.PostulantHistorical;

@Repository
public class PostulantHistoricalDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
	public static final String COLLECTION_NAME = "postulant_historical";
	 
	public void addPostulantHistorical(PostulantHistorical postulantHistorical) {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
		//postulantHistorical.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(postulantHistorical, COLLECTION_NAME);
	}

	public List<PostulantHistorical> listPostulantHistorical() {
		return mongoTemplate.findAll(PostulantHistorical.class, COLLECTION_NAME);
	}

	public void deletePostulantHistorical(PostulantHistorical postulantHistorical) {
		mongoTemplate.remove(postulantHistorical, COLLECTION_NAME);
	}

	public void updatePostulantHistorical(PostulantHistorical postulantHistorical) {			
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(postulantHistorical.getId())), 
						Update.update("values", postulantHistorical.getValues()), PostulantHistorical.class, COLLECTION_NAME);
	}
	
	public PostulantHistorical getPostulantHistoricalById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		
		List<PostulantHistorical> list = mongoTemplate.find(query, PostulantHistorical.class, COLLECTION_NAME);
				
		if(list.size()!=0){
			return (PostulantHistorical)list.get(0);
		} else {
			return null;
		}
	}
}
