package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ConocimientoDAO;
import vindbrein.dao.PostulanteConocimientoDAO;
import vindbrein.domain.model.Conocimiento;
import vindbrein.domain.model.PostulanteConocimiento;
import vindbrein.service.ConocimientoService;

@Service
@Transactional(readOnly = true)
public class ConocimientoServiceImpl implements ConocimientoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	ConocimientoDAO conocimientoDAO;
	
	@Autowired	
	PostulanteConocimientoDAO postulanteConocimientoDAO;
	
	@Transactional(readOnly = false)
	public void addConocimiento(Conocimiento conocimiento) {
		getConocimientoDAO().addConocimiento(conocimiento);		
	}
	
	@Transactional(readOnly = false)
	public void updateConocimiento(Conocimiento conocimiento) {
		getConocimientoDAO().updateConocimiento(conocimiento);		
	}
	
	@Transactional(readOnly = false)
	public void deleteConocimiento(Conocimiento conocimiento) {
		getConocimientoDAO().deleteConocimiento(conocimiento);		
	}

	public Conocimiento getConocimientoById(int id) {
		return getConocimientoDAO().getConocimientoById(id);
	}

	public ArrayList<Conocimiento> getConocimientos() {
		return getConocimientoDAO().getConocimientos();
	}
	
	@Transactional(readOnly = false)
	public void addConocimientoToPostulante(PostulanteConocimiento postulanteConocimiento){
		postulanteConocimiento.getId().setFkConoId(postulanteConocimiento.getConocimiento().getConoId());
		postulanteConocimiento.getId().setFkPostId(postulanteConocimiento.getPostulante().getPostId());
		
		getPostulanteConocimientoDAO().addPostulanteConocimiento(postulanteConocimiento);
	}
	
	@Transactional(readOnly = false)
	public void updateConocimientoToPostulante(PostulanteConocimiento postulanteConocimiento){
		postulanteConocimiento.getId().setFkConoId(postulanteConocimiento.getConocimiento().getConoId());
		postulanteConocimiento.getId().setFkPostId(postulanteConocimiento.getPostulante().getPostId());
		
		getPostulanteConocimientoDAO().updatePostulanteConocimiento(postulanteConocimiento);
	}
	
	@Transactional(readOnly = false)
	public void deleteConocimientoToPostulante(PostulanteConocimiento postulanteConocimiento){
		getPostulanteConocimientoDAO().deletePostulanteConocimiento(postulanteConocimiento);
	}
	
	//getters and setters
	
	public ConocimientoDAO getConocimientoDAO() {
		return conocimientoDAO;
	}

	public void setConocimientoDAO(ConocimientoDAO conocimientoDAO) {
		this.conocimientoDAO = conocimientoDAO;
	}

	public PostulanteConocimientoDAO getPostulanteConocimientoDAO() {
		return postulanteConocimientoDAO;
	}

	public void setPostulanteConocimientoDAO(
			PostulanteConocimientoDAO postulanteConocimientoDAO) {
		this.postulanteConocimientoDAO = postulanteConocimientoDAO;
	}	
}
