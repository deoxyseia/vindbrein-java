package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.ReclutadorDAO;
import vindbrein.domain.model.Reclutador;
import vindbrein.domain.model.Provincia;
import vindbrein.service.ReclutadorService;

@Service
@Transactional(readOnly = true)
public class ReclutadorServiceImpl implements ReclutadorService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	ReclutadorDAO reclutadorDAO;
	
	@Transactional(readOnly = false)
	public void addReclutador(Reclutador reclutador) {
		getReclutadorDAO().addReclutador(reclutador);		
	}
	
	@Transactional(readOnly = false)
	public void updateReclutador(Reclutador reclutador) {
		getReclutadorDAO().updateReclutador(reclutador);		
	}
	
	@Transactional(readOnly = false)
	public void deleteReclutador(Reclutador reclutador) {
		getReclutadorDAO().deleteReclutador(reclutador);		
	}

	public Reclutador getReclutadorById(int id) {
		return getReclutadorDAO().getReclutadorById(id);
	}

	public ArrayList<Reclutador> getReclutadores() {
		return getReclutadorDAO().getReclutadores();
	}	
	
	public Reclutador getReclutadorByCorreo(String correo) {
		return getReclutadorDAO().getReclutadorByCorreo(correo);
	}
	
	//getters and setters
	
	public ReclutadorDAO getReclutadorDAO() {
		return reclutadorDAO;
	}

	public void setReclutadorDAO(ReclutadorDAO reclutadorDAO) {
		this.reclutadorDAO = reclutadorDAO;
	}	
}
