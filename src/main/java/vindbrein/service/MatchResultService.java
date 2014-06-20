package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.MatchResult;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.Postulante;

public interface MatchResultService {
	
	public ArrayList<MatchResult> getMatchResultsByPostulante(Postulante postulante);
	
	public ArrayList<MatchResult> getMatchResultsByOrganizacion(Organizacion organizacion);
}
