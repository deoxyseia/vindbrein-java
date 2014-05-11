package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Grado;
import vindbrein.domain.model.Provincia;

@Repository
public class GradoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addGrado(Grado grado) {
		getSessionFactory().getCurrentSession().save(grado);		
	}

	public void updateGrado(Grado grado) {
		getSessionFactory().getCurrentSession().update(grado);		
	}

	public void deleteGrado(Grado grado) {
		getSessionFactory().getCurrentSession().delete(grado);		
	}

	public Grado getGradoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Grado where gradId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Grado)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Grado> getGrados() {
		ArrayList<Grado> list = (ArrayList<Grado>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Grado order by gradCode asc")
				.list();
		return list;
	}
}
