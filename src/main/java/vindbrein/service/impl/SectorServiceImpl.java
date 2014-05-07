package vindbrein.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.PostulanteDAO;
import vindbrein.dao.SectorDAO;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.Sector;
import vindbrein.service.PostulanteService;
import vindbrein.service.SectorService;

@Service
@Transactional(readOnly = true)
public class SectorServiceImpl implements SectorService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired	
	SectorDAO sectorDAO;	
	
	public void addSector(Sector sector) {
		getSectorDAO().addSector(sector);
		
	}
	public void updateSector(Sector sector) {
		getSectorDAO().updateSector(sector);
		
	}
	public void deleteSector(Sector sector) {
		getSectorDAO().deleteSector(sector);
		
	}
	public Sector getSectorById(int id) {
		return getSectorDAO().getSectorById(id);
	}
	public ArrayList<Sector> getSectores() {
		return getSectorDAO().getSectores();
	}
	
	//getters and setters
	
	public SectorDAO getSectorDAO() {
		return sectorDAO;
	}
	
	public void setSectorDAO(SectorDAO sectorDAO) {
		this.sectorDAO = sectorDAO;
	}	
}
