package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Estudio;
import vindbrein.domain.model.Provincia;

@Repository
public class EstudioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addEstudio(Estudio estudio) {
		getSessionFactory().getCurrentSession().save(estudio);		
	}

	public void updateEstudio(Estudio estudio) {
		getSessionFactory().getCurrentSession().update(estudio);		
	}

	public void deleteEstudio(Estudio estudio) {
		getSessionFactory().getCurrentSession().delete(estudio);		
	}

	public Estudio getEstudioById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Estudio where estuId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Estudio)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Estudio> getEstudios() {
		ArrayList<Estudio> list = (ArrayList<Estudio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Estudio order by estuNombre asc")
				.list();
		return list;
	}	
}
