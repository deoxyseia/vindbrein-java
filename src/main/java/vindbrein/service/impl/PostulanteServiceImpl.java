package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.PostulanteDAO;
import vindbrein.domain.model.Postulante;
import vindbrein.service.PostulanteService;

@Service
@Transactional(readOnly = true)
public class PostulanteServiceImpl implements PostulanteService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	PostulanteDAO postulanteDAO;
	

	public void addPostulante(Postulante postulante) {
		getPostulanteDAO().addPostulante(postulante);		
	}

	public void updatePostulante(Postulante postulante) {
		getPostulanteDAO().updatePostulante(postulante);		
	}

	public void deletePostulante(Postulante postulante) {
		getPostulanteDAO().deletePostulante(postulante);		
	}

	public Postulante getPostulanteById(int id) {
		return getPostulanteDAO().getPostulanteById(id);
	}

	public ArrayList<Postulante> getPostulantes() {
		return getPostulanteDAO().getPostulantes();
	}	
	
	//getters and setters
	
	public PostulanteDAO getPostulanteDAO() {
		return postulanteDAO;
	}

	public void setPostulanteDAO(PostulanteDAO postulanteDAO) {
		this.postulanteDAO = postulanteDAO;
	}	
}
