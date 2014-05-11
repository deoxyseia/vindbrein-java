package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Conocimiento;
import vindbrein.domain.model.PostulanteConocimiento;

public interface ConocimientoService {
	
	public void addConocimiento(Conocimiento Conocimiento);

	public void updateConocimiento(Conocimiento Conocimiento);

	public void deleteConocimiento(Conocimiento Conocimiento);

	public Conocimiento getConocimientoById(int id);

	public ArrayList<Conocimiento> getConocimientos();
	
	public void addConocimientoToPostulante(PostulanteConocimiento postulanteConocimiento);
	
	public void updateConocimientoToPostulante(PostulanteConocimiento postulanteConocimiento);
	
	public void deleteConocimientoToPostulante(PostulanteConocimiento postulanteConocimiento);

}
