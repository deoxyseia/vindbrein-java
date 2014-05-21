package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Puesto;

@Repository
public class PuestoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addPuesto(Puesto puesto) {
		getSessionFactory().getCurrentSession().save(puesto);		
	}

	public void updatePuesto(Puesto puesto) {
		getSessionFactory().getCurrentSession().update(puesto);		
	}

	public void deletePuesto(Puesto puesto) {
		getSessionFactory().getCurrentSession().delete(puesto);		
	}

	public Puesto getPuestoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Puesto where puesId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Puesto)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Puesto> getPuestos() {
		ArrayList<Puesto> list = (ArrayList<Puesto>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Puesto order by puesNombre asc")
				.list();
		return list;
	}	
}
