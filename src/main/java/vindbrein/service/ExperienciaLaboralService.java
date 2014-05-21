package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.ExperienciaLaboral;

public interface ExperienciaLaboralService {
	
	public void addExperienciaLaboral(ExperienciaLaboral ExperienciaLaboral);

	public void updateExperienciaLaboral(ExperienciaLaboral ExperienciaLaboral);

	public void deleteExperienciaLaboral(ExperienciaLaboral ExperienciaLaboral);

	public ExperienciaLaboral getExperienciaLaboralById(int id);

	public ArrayList<ExperienciaLaboral> getExperienciasLaborales();
}
