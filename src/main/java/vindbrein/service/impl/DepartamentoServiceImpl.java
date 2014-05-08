package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.DepartamentoDAO;
import vindbrein.domain.model.Departamento;
import vindbrein.service.DepartamentoService;

@Service
@Transactional(readOnly = true)
public class DepartamentoServiceImpl implements DepartamentoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	DepartamentoDAO departamentoDAO;
	
	@Transactional(readOnly = false)
	public void addDepartamento(Departamento departamento) {
		getDepartamentoDAO().addDepartamento(departamento);		
	}
	
	@Transactional(readOnly = false)
	public void updateDepartamento(Departamento departamento) {
		getDepartamentoDAO().updateDepartamento(departamento);		
	}
	
	@Transactional(readOnly = false)
	public void deleteDepartamento(Departamento departamento) {
		getDepartamentoDAO().deleteDepartamento(departamento);		
	}

	public Departamento getDepartamentoById(int id) {
		return getDepartamentoDAO().getDepartamentoById(id);
	}

	public ArrayList<Departamento> getDepartamentos() {
		return getDepartamentoDAO().getDepartamentos();
	}	
	
	//getters and setters
	
	public DepartamentoDAO getDepartamentoDAO() {
		return departamentoDAO;
	}

	public void setDepartamentoDAO(DepartamentoDAO departamentoDAO) {
		this.departamentoDAO = departamentoDAO;
	}	
}
