package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.EstadoCivilDAO;
import vindbrein.domain.model.EstadoCivil;
import vindbrein.service.EstadoCivilService;

@Service
@Transactional(readOnly = true)
public class EstadoCivilServiceImpl implements EstadoCivilService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	EstadoCivilDAO EstadoCivilDAO;
		
	@Transactional(readOnly = false)
	public void addEstadoCivil(EstadoCivil EstadoCivil) {
		getEstadoCivilDAO().addEstadoCivil(EstadoCivil);		
	}
	
	@Transactional(readOnly = false)
	public void updateEstadoCivil(EstadoCivil EstadoCivil) {
		getEstadoCivilDAO().updateEstadoCivil(EstadoCivil);		
	}
	
	@Transactional(readOnly = false)
	public void deleteEstadoCivil(EstadoCivil EstadoCivil) {
		getEstadoCivilDAO().deleteEstadoCivil(EstadoCivil);		
	}

	public EstadoCivil getEstadoCivilById(int id) {
		return getEstadoCivilDAO().getEstadoCivilById(id);
	}

	public ArrayList<EstadoCivil> getEstadosCiviles() {
		return getEstadoCivilDAO().getEstadosCiviles();
	}
	
	//getters and setters
	
	public EstadoCivilDAO getEstadoCivilDAO() {
		return EstadoCivilDAO;
	}

	public void setEstadoCivilDAO(EstadoCivilDAO EstadoCivilDAO) {
		this.EstadoCivilDAO = EstadoCivilDAO;
	}

	public void addestadoCivil(EstadoCivil estadoCivil) {
		// TODO Auto-generated method stub
		
	}

	public void updateestadoCivil(EstadoCivil estadoCivil) {
		// TODO Auto-generated method stub
		
	}

	public void deleteestadoCivil(EstadoCivil estadoCivil) {
		// TODO Auto-generated method stub
		
	}
}
