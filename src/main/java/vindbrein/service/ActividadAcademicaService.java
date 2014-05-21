package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.ActividadAcademica;

public interface ActividadAcademicaService {
	
	public void addActividadAcademica(ActividadAcademica actividadAcademica);

	public void updateActividadAcademica(ActividadAcademica actividadAcademica);

	public void deleteActividadAcademica(ActividadAcademica actividadAcademica);

	public ActividadAcademica getActividadAcademicaById(int id);

	public ArrayList<ActividadAcademica> getActividadesAcademicas();	
}
