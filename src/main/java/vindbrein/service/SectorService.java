package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Sector;

public interface SectorService {
	
	public void addSector(Sector Sector);

	public void updateSector(Sector Sector);

	public void deleteSector(Sector Sector);

	public Sector getSectorById(int id);

	public ArrayList<Sector> getSectores();

}
