package vindbrein.dao.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import vindbrein.domain.document.PostulantSelfDescription;

@Repository
public class PostulantSelfDescriptionDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
	public static final String COLLECTION_NAME = "postulant_selfdescription";
	 
	public void addPostulantSelfDescription(PostulantSelfDescription postulantSelfDescription) {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
		//postulantSelfDescription.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(postulantSelfDescription, COLLECTION_NAME);
	}

	public List<PostulantSelfDescription> listPostulantSelfDescription() {
		return mongoTemplate.findAll(PostulantSelfDescription.class, COLLECTION_NAME);
	}

	public void deletePostulantSelfDescription(PostulantSelfDescription postulantSelfDescription) {
		mongoTemplate.remove(postulantSelfDescription, COLLECTION_NAME);
	}

	public void updatePostulantSelfDescription(PostulantSelfDescription postulantSelfDescription) {			
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(postulantSelfDescription.getId())), 
						Update.update("values", postulantSelfDescription.getValues()), PostulantSelfDescription.class, COLLECTION_NAME);
	}
	
	public PostulantSelfDescription getPostulantSelfDescriptionById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		
		List<PostulantSelfDescription> list = mongoTemplate.find(query, PostulantSelfDescription.class, COLLECTION_NAME);
		
		if(list.size()!=0){
			return (PostulantSelfDescription)list.get(0);
		} else {
			return null;
		}
	}

}
