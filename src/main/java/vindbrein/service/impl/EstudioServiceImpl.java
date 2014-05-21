package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.EstudioDAO;
import vindbrein.domain.model.Estudio;
import vindbrein.service.EstudioService;

@Service
@Transactional(readOnly = true)
public class EstudioServiceImpl implements EstudioService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	EstudioDAO estudioDAO;
	
	@Transactional(readOnly = false)
	public void addEstudio(Estudio estudio) {
		getEstudioDAO().addEstudio(estudio);		
	}
	
	@Transactional(readOnly = false)
	public void updateEstudio(Estudio estudio) {
		getEstudioDAO().updateEstudio(estudio);		
	}
	
	@Transactional(readOnly = false)
	public void deleteEstudio(Estudio estudio) {
		getEstudioDAO().deleteEstudio(estudio);		
	}

	public Estudio getEstudioById(int id) {
		return getEstudioDAO().getEstudioById(id);
	}

	public ArrayList<Estudio> getEstudios() {
		return getEstudioDAO().getEstudios();
	}	
		
	//getters and setters
	
	public EstudioDAO getEstudioDAO() {
		return estudioDAO;
	}

	public void setEstudioDAO(EstudioDAO estudioDAO) {
		this.estudioDAO = estudioDAO;
	}	
}
