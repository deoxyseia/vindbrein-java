package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.BeneficioDAO;
import vindbrein.domain.model.Beneficio;
import vindbrein.service.BeneficioService;

@Service
@Transactional(readOnly = true)
public class BeneficioServiceImpl implements BeneficioService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	BeneficioDAO beneficioDAO;
	
	@Transactional(readOnly = false)
	public void addBeneficio(Beneficio beneficio) {
		getBeneficioDAO().addBeneficio(beneficio);		
	}
	
	@Transactional(readOnly = false)
	public void updateBeneficio(Beneficio beneficio) {
		getBeneficioDAO().updateBeneficio(beneficio);		
	}
	
	@Transactional(readOnly = false)
	public void deleteBeneficio(Beneficio beneficio) {
		getBeneficioDAO().deleteBeneficio(beneficio);		
	}

	public Beneficio getBeneficioById(int id) {
		return getBeneficioDAO().getBeneficioById(id);
	}

	public ArrayList<Beneficio> getBeneficios() {
		return getBeneficioDAO().getBeneficios();
	}	
	
	//getters and setters
	
	public BeneficioDAO getBeneficioDAO() {
		return beneficioDAO;
	}

	public void setBeneficioDAO(BeneficioDAO beneficioLaboralDAO) {
		this.beneficioDAO = beneficioDAO;
	}
}
