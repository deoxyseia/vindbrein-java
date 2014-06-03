package vindbrein.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vindbrein.dao.document.PostulantHistoricalDAO;
import vindbrein.domain.document.PostulantHistorical;
import vindbrein.service.MongoService;

@Service
public class MongoServiceImpl implements MongoService {

	@Autowired
	PostulantHistoricalDAO postulantHistoricalDAO;

	public void addPostulantHistorical(PostulantHistorical postulantHistorical) {
		postulantHistoricalDAO.addPostulantHistorical(postulantHistorical);		
	}

	public List<PostulantHistorical> listPostulantHistorical(){
		return postulantHistoricalDAO.listPostulantHistorical();
	}

	public void deletePostulantHistorical(PostulantHistorical postulantHistorical){
		postulantHistoricalDAO.deletePostulantHistorical(postulantHistorical);		
	}

	public void updatePostulantHistorical(PostulantHistorical postulantHistorical) {
		postulantHistoricalDAO.updatePostulantHistorical(postulantHistorical);		
	}
}
