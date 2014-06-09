package vindbrein.dao.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import vindbrein.domain.document.PostulantPreference;

@Repository
public class PostulantPreferenceDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
	public static final String COLLECTION_NAME = "postulant_preference";
	 
	public void addPostulantPreference(PostulantPreference postulantPreference) {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
		//postulantPreference.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(postulantPreference, COLLECTION_NAME);
	}

	public List<PostulantPreference> listPostulantPreference() {
		return mongoTemplate.findAll(PostulantPreference.class, COLLECTION_NAME);
	}

	public void deletePostulantPreference(PostulantPreference postulantPreference) {
		mongoTemplate.remove(postulantPreference, COLLECTION_NAME);
	}

	public void updatePostulantPreference(PostulantPreference postulantPreference) {			
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(postulantPreference.getId())), 
						Update.update("values", postulantPreference.getValues()), PostulantPreference.class, COLLECTION_NAME);
	}
	
	public PostulantPreference getPostulantPreferenceById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		
		List<PostulantPreference> list = mongoTemplate.find(query, PostulantPreference.class, COLLECTION_NAME);
		
		if(list.size()!=0){
			return (PostulantPreference)list.get(0);
		} else {
			return null;
		}
	}

}
