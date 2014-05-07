package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Sector;

@Repository
public class SectorDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addSector(Sector sector) {
		getSessionFactory().getCurrentSession().save(sector);		
	}

	public void updateSector(Sector sector) {
		getSessionFactory().getCurrentSession().update(sector);		
	}

	public void deleteSector(Sector sector) {
		getSessionFactory().getCurrentSession().delete(sector);		
	}

	public Sector getSectorById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Sector where sectId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Sector)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Sector> getSectores() {
		ArrayList<Sector> list = (ArrayList<Sector>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Sector order by sectNombre asc")
				.list();
		return list;
	}	
}
