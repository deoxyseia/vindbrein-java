package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.NivelPuestoDAO;
import vindbrein.dao.PuestoDAO;
import vindbrein.domain.model.NivelPuesto;
import vindbrein.domain.model.Puesto;
import vindbrein.service.PuestoService;

@Service
@Transactional(readOnly = true)
public class PuestoServiceImpl implements PuestoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	PuestoDAO puestoDAO;
	
	@Autowired	
	NivelPuestoDAO nivelPuestoDAO;
		
	@Transactional(readOnly = false)
	public void addPuesto(Puesto puesto) {
		getPuestoDAO().addPuesto(puesto);		
	}
	
	@Transactional(readOnly = false)
	public void updatePuesto(Puesto puesto) {
		getPuestoDAO().updatePuesto(puesto);		
	}
	
	@Transactional(readOnly = false)
	public void deletePuesto(Puesto puesto) {
		getPuestoDAO().deletePuesto(puesto);		
	}

	public Puesto getPuestoById(int id) {
		return getPuestoDAO().getPuestoById(id);
	}

	public ArrayList<Puesto> getPuestos() {
		return getPuestoDAO().getPuestos();
	}
	
	public void addNivelPuesto(NivelPuesto nivelPuesto) {
		getNivelPuestoDAO().addNivelPuesto(nivelPuesto);		
	}

	public void updateNivelPuesto(NivelPuesto nivelPuesto) {
		getNivelPuestoDAO().updateNivelPuesto(nivelPuesto);		
	}

	public void deleteNivelPuesto(NivelPuesto nivelPuesto) {
		getNivelPuestoDAO().deleteNivelPuesto(nivelPuesto);		
	}

	public NivelPuesto getNivelPuestoById(int id) {
		return getNivelPuestoDAO().getNivelPuestoById(id);
	}

	public ArrayList<NivelPuesto> getNivelesPuesto() {
		return getNivelPuestoDAO().getNivelesPuesto();
	}
		
	//getters and setters
	
	public PuestoDAO getPuestoDAO() {
		return puestoDAO;
	}

	public void setPuestoDAO(PuestoDAO puestoDAO) {
		this.puestoDAO = puestoDAO;
	}

	public NivelPuestoDAO getNivelPuestoDAO() {
		return nivelPuestoDAO;
	}

	public void setNivelPuestoDAO(NivelPuestoDAO nivelPuestoDAO) {
		this.nivelPuestoDAO = nivelPuestoDAO;
	}	
}
