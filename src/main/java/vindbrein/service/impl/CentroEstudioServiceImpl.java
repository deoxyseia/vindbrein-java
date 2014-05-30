package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.CentroEstudioDAO;
import vindbrein.domain.model.CentroEstudio;
import vindbrein.service.CentroEstudioService;

@Service
@Transactional(readOnly = true)
public class CentroEstudioServiceImpl implements CentroEstudioService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	CentroEstudioDAO centroEstudioDAO;
	
	@Transactional(readOnly = false)
	public void addCentroEstudio(CentroEstudio centroEstudio) {
		getCentroEstudioDAO().addCentroEstudio(centroEstudio);		
	}
	
	@Transactional(readOnly = false)
	public void updateCentroEstudio(CentroEstudio centroEstudio) {
		getCentroEstudioDAO().updateCentroEstudio(centroEstudio);		
	}
	
	@Transactional(readOnly = false)
	public void deleteCentroEstudio(CentroEstudio centroEstudio) {
		getCentroEstudioDAO().deleteCentroEstudio(centroEstudio);		
	}

	public CentroEstudio getCentroEstudioById(int id) {
		return getCentroEstudioDAO().getCentroEstudioById(id);
	}

	public ArrayList<CentroEstudio> getCentrosEstudio() {
		return getCentroEstudioDAO().getCentrosEstudio();
	}	
	
	//getters and setters
	
	public CentroEstudioDAO getCentroEstudioDAO() {
		return centroEstudioDAO;
	}

	public void setCentroEstudioDAO(CentroEstudioDAO centroEstudioDAO) {
		this.centroEstudioDAO = centroEstudioDAO;
	}	
}
