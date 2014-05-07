package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ProvinciaDAO;
import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Provincia;
import vindbrein.service.ProvinciaService;

@Service
@Transactional(readOnly = true)
public class ProvinciaServiceImpl implements ProvinciaService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	ProvinciaDAO provinciaDAO;
	

	public void addProvincia(Provincia provincia) {
		getProvinciaDAO().addProvincia(provincia);		
	}

	public void updateProvincia(Provincia provincia) {
		getProvinciaDAO().updateProvincia(provincia);		
	}

	public void deleteProvincia(Provincia provincia) {
		getProvinciaDAO().deleteProvincia(provincia);		
	}

	public Provincia getProvinciaById(int id) {
		return getProvinciaDAO().getProvinciaById(id);
	}

	public ArrayList<Provincia> getProvincias() {
		return getProvinciaDAO().getProvincias();
	}	
	
	public ArrayList<Provincia> getProvinciasByDepartamento(Departamento departamento){
		return getProvinciaDAO().getProvinciasByDepartamento(departamento);
	}
	
	//getters and setters
	
	public ProvinciaDAO getProvinciaDAO() {
		return provinciaDAO;
	}

	public void setProvinciaDAO(ProvinciaDAO provinciaDAO) {
		this.provinciaDAO = provinciaDAO;
	}	
}
