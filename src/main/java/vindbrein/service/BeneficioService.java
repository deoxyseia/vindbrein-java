package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Beneficio;
import vindbrein.domain.model.PostulanteBeneficio;

public interface BeneficioService {
	
	public void addBeneficio(Beneficio beneficio);

	public void updateBeneficio(Beneficio beneficio);

	public void deleteBeneficio(Beneficio beneficio);

	public Beneficio getBeneficioById(int id);

	public ArrayList<Beneficio> getBeneficios();
	
	public void addBeneficioToPostulante(PostulanteBeneficio postulanteBeneficio);

	public void updateBeneficioToPostulante(PostulanteBeneficio postulanteBeneficio);

	public void deleteBeneficioToPostulante(PostulanteBeneficio postulanteBeneficio);
}
