package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ResidenciaDAO;
import vindbrein.domain.model.Residencia;
import vindbrein.service.ResidenciaService;

@Service
@Transactional(readOnly = true)
public class ResidenciaServiceImpl implements ResidenciaService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	ResidenciaDAO residenciaDAO;	
	
	@Transactional(readOnly = false)
	public void addResidencia(Residencia residencia) {
		getResidenciaDAO().addResidencia(residencia);		
	}
	
	@Transactional(readOnly = false)
	public void updateResidencia(Residencia residencia) {
		getResidenciaDAO().updateResidencia(residencia);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteResidencia(Residencia residencia) {
		getResidenciaDAO().deleteResidencia(residencia);
		
	}
	
	public Residencia getResidenciaById(int id) {
		return getResidenciaDAO().getResidenciaById(id);
	}
	
	public ArrayList<Residencia> getResidencias() {
		return getResidenciaDAO().getResidencias();
	}
	
	//getters and setters
	
	public ResidenciaDAO getResidenciaDAO() {
		return residenciaDAO;
	}
	
	public void setResidenciaDAO(ResidenciaDAO residenciaDAO) {
		this.residenciaDAO = residenciaDAO;
	}	
}
