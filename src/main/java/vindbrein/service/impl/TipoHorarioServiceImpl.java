package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.TipoHorarioDAO;
import vindbrein.domain.model.TipoHorario;
import vindbrein.service.TipoHorarioService;

@Service
@Transactional(readOnly = true)
public class TipoHorarioServiceImpl implements TipoHorarioService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	TipoHorarioDAO tipoHorarioDAO;
	
	@Transactional(readOnly = false)
	public void addTipoHorario(TipoHorario tipoHorario) {
		getTipoHorarioDAO().addTipoHorario(tipoHorario);		
	}
	
	@Transactional(readOnly = false)
	public void updateTipoHorario(TipoHorario tipoHorario) {
		getTipoHorarioDAO().updateTipoHorario(tipoHorario);		
	}
	
	@Transactional(readOnly = false)
	public void deleteTipoHorario(TipoHorario tipoHorario) {
		getTipoHorarioDAO().deleteTipoHorario(tipoHorario);		
	}

	public TipoHorario getTipoHorarioById(int id) {
		return getTipoHorarioDAO().getTipoHorarioById(id);
	}

	public ArrayList<TipoHorario> getTiposHorario() {
		return getTipoHorarioDAO().getTiposHorario();
	}	
	
	//getters and setters
	
	public TipoHorarioDAO getTipoHorarioDAO() {
		return tipoHorarioDAO;
	}

	public void setTipoHorarioDAO(TipoHorarioDAO tipoHorarioDAO) {
		this.tipoHorarioDAO = tipoHorarioDAO;
	}
}
