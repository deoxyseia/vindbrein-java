package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Beneficio;

public interface BeneficioService {
	
	public void addBeneficio(Beneficio beneficio);

	public void updateBeneficio(Beneficio beneficio);

	public void deleteBeneficio(Beneficio beneficio);

	public Beneficio getBeneficioById(int id);

	public ArrayList<Beneficio> getBeneficios();

}
