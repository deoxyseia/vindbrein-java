package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.GradoDAO;
import vindbrein.domain.model.Grado;
import vindbrein.service.GradoService;

@Service
@Transactional(readOnly = true)
public class GradoServiceImpl implements GradoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	GradoDAO gradoDAO;
	
	@Transactional(readOnly = false)
	public void addGrado(Grado grado) {
		getGradoDAO().addGrado(grado);		
	}
	
	@Transactional(readOnly = false)
	public void updateGrado(Grado grado) {
		getGradoDAO().updateGrado(grado);		
	}
	
	@Transactional(readOnly = false)
	public void deleteGrado(Grado grado) {
		getGradoDAO().deleteGrado(grado);		
	}

	public Grado getGradoById(int id) {
		return getGradoDAO().getGradoById(id);
	}

	public ArrayList<Grado> getGrados() {
		return getGradoDAO().getGrados();
	}	
	

	//getters and setters
	
	public GradoDAO getGradoDAO() {
		return gradoDAO;
	}

	public void setGradoDAO(GradoDAO gradoDAO) {
		this.gradoDAO = gradoDAO;
	}	
}
