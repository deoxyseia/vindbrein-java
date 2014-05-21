package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ActividadAcademicaDAO;
import vindbrein.domain.model.ActividadAcademica;
import vindbrein.service.ActividadAcademicaService;

@Service
@Transactional(readOnly = true)
public class ActividadAcademicaServiceImpl implements ActividadAcademicaService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	ActividadAcademicaDAO actividadAcademicaDAO;
	
	@Transactional(readOnly = false)
	public void addActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadAcademicaDAO().addActividadAcademica(actividadAcademica);		
	}
	
	@Transactional(readOnly = false)
	public void updateActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadAcademicaDAO().updateActividadAcademica(actividadAcademica);		
	}
	
	@Transactional(readOnly = false)
	public void deleteActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadAcademicaDAO().deleteActividadAcademica(actividadAcademica);		
	}

	public ActividadAcademica getActividadAcademicaById(int id) {
		return getActividadAcademicaDAO().getActividadAcademicaById(id);
	}

	public ArrayList<ActividadAcademica> getActividadesAcademicas() {
		return getActividadAcademicaDAO().getActividadesAcademicas();
	}	
	
	//getters and setters
	
	public ActividadAcademicaDAO getActividadAcademicaDAO() {
		return actividadAcademicaDAO;
	}

	public void setActividadAcademicaDAO(ActividadAcademicaDAO actividadAcademicaDAO) {
		this.actividadAcademicaDAO = actividadAcademicaDAO;
	}	
}
