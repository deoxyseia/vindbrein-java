package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.EstudioGenericoDAO;
import vindbrein.domain.model.EstudioGenerico;
import vindbrein.service.EstudioGenericoService;

@Service
@Transactional(readOnly = true)
public class EstudioGenericoServiceImpl implements EstudioGenericoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	EstudioGenericoDAO estudioGenericoDAO;
	
	
	@Transactional(readOnly = false)
	public void addEstudioGenerico(EstudioGenerico estudioGenerico) {
		getEstudioGenericoDAO().addEstudioGenerico(estudioGenerico);		
	}
	
	@Transactional(readOnly = false)
	public void updateEstudioGenerico(EstudioGenerico estudioGenerico) {
		getEstudioGenericoDAO().updateEstudioGenerico(estudioGenerico);		
	}
	
	@Transactional(readOnly = false)
	public void deleteEstudioGenerico(EstudioGenerico estudioGenerico) {
		getEstudioGenericoDAO().deleteEstudioGenerico(estudioGenerico);		
	}

	public EstudioGenerico getEstudioGenericoById(int id) {
		return getEstudioGenericoDAO().getEstudioGenericoById(id);
	}

	public ArrayList<EstudioGenerico> getEstudiosGenericos() {
		return getEstudioGenericoDAO().getEstudiosGenericos();
	}	
	
	//getters and setters
	
	public EstudioGenericoDAO getEstudioGenericoDAO() {
		return estudioGenericoDAO;
	}

	public void setEstudioGenericoDAO(EstudioGenericoDAO estudioGenericoDAO) {
		this.estudioGenericoDAO = estudioGenericoDAO;
	}

}
