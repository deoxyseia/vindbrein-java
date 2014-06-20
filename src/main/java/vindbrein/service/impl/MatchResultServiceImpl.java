package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.MatchResultDAO;
import vindbrein.domain.model.MatchResult;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.Postulante;
import vindbrein.service.MatchResultService;

@Service
@Transactional(readOnly = true)
public class MatchResultServiceImpl implements MatchResultService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	MatchResultDAO matchResultDAO;
	
	public ArrayList<MatchResult> getMatchResultsByPostulante(Postulante postulante) {
		return matchResultDAO.getMatchResultsByPostulant(postulante);
	}
	
	public ArrayList<MatchResult> getMatchResultsByOrganizacion(Organizacion organizacion) {
		return matchResultDAO.getMatchResultsByOrganization(organizacion);
	}	
}
