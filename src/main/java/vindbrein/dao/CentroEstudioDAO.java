package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.CentroEstudio;

@Repository
public class CentroEstudioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addCentroEstudio(CentroEstudio centroEstudio) {
		getSessionFactory().getCurrentSession().save(centroEstudio);		
	}

	public void updateCentroEstudio(CentroEstudio centroEstudio) {
		getSessionFactory().getCurrentSession().update(centroEstudio);		
	}

	public void deleteCentroEstudio(CentroEstudio centroEstudio) {
		getSessionFactory().getCurrentSession().delete(centroEstudio);		
	}

	public CentroEstudio getCentroEstudioById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from CentroEstudio where ceesId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (CentroEstudio)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<CentroEstudio> getCentrosEstudio() {
		ArrayList<CentroEstudio> list = (ArrayList<CentroEstudio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from CentroEstudio order by ceesNombre asc")
				.list();
		return list;
	}	
}
