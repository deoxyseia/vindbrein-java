package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.TelefonoDAO;
import vindbrein.domain.model.Telefono;
import vindbrein.service.TelefonoService;

@Service
@Transactional(readOnly = true)
public class TelefonoServiceImpl implements TelefonoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	TelefonoDAO telefonoDAO;	
	
	@Transactional(readOnly = false)
	public void addTelefono(Telefono telefono) {
		getTelefonoDAO().addTelefono(telefono);		
	}
	
	@Transactional(readOnly = false)
	public void updateTelefono(Telefono telefono) {
		getTelefonoDAO().updateTelefono(telefono);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteTelefono(Telefono telefono) {
		getTelefonoDAO().deleteTelefono(telefono);
		
	}
	
	public Telefono getTelefonoById(int id) {
		return getTelefonoDAO().getTelefonoById(id);
	}
	
	public ArrayList<Telefono> getTelefonos() {
		return getTelefonoDAO().getTelefonos();
	}
	
	//getters and setters
	
	public TelefonoDAO getTelefonoDAO() {
		return telefonoDAO;
	}
	
	public void setTelefonoDAO(TelefonoDAO telefonoDAO) {
		this.telefonoDAO = telefonoDAO;
	}	
}
