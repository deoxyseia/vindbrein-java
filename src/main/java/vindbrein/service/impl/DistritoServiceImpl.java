package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.DistritoDAO;
import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.Provincia;
import vindbrein.service.DistritoService;

@Service
@Transactional(readOnly = true)
public class DistritoServiceImpl implements DistritoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	DistritoDAO distritoDAO;
	
	@Transactional(readOnly = false)
	public void addDistrito(Distrito distrito) {
		getDistritoDAO().addDistrito(distrito);		
	}
	
	@Transactional(readOnly = false)
	public void updateDistrito(Distrito distrito) {
		getDistritoDAO().updateDistrito(distrito);		
	}
	
	@Transactional(readOnly = false)
	public void deleteDistrito(Distrito distrito) {
		getDistritoDAO().deleteDistrito(distrito);		
	}

	public Distrito getDistritoById(int id) {
		return getDistritoDAO().getDistritoById(id);
	}

	public ArrayList<Distrito> getDistritos() {
		return getDistritoDAO().getDistritos();
	}	
	
	public ArrayList<Distrito> getDistritosByProvincia(Provincia provincia) {
		return getDistritoDAO().getDistritosByProvincia(provincia);
	}
	
	//getters and setters
	
	public DistritoDAO getDistritoDAO() {
		return distritoDAO;
	}

	public void setDistritoDAO(DistritoDAO distritoDAO) {
		this.distritoDAO = distritoDAO;
	}	
}
