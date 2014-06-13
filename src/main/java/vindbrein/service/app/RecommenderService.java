package vindbrein.service.app;

import java.util.ArrayList;

import vindbrein.domain.app.Profile;
import vindbrein.domain.app.Result;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
import vindbrein.util.RecommenderType;


public interface RecommenderService {
	
	public ArrayList<OfertaLaboral> recomendarOfertasLaboralesToPostulante(Postulante postulante, int size, RecommenderType recommenderType);
	
}
