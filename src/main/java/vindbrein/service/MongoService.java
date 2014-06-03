package vindbrein.service;

import java.util.List;

import vindbrein.domain.document.PostulantHistorical;


public interface MongoService {
	
	public void addPostulantHistorical(PostulantHistorical postulantHistorical);	

	public List<PostulantHistorical> listPostulantHistorical();

	public void deletePostulantHistorical(PostulantHistorical postulantHistorical);

	public void updatePostulantHistorical(PostulantHistorical postulantHistorical);
	
}
