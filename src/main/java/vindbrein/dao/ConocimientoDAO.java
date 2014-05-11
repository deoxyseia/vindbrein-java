package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Conocimiento;

@Repository
public class ConocimientoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addConocimiento(Conocimiento conocimiento) {
		getSessionFactory().getCurrentSession().save(conocimiento);		
	}

	public void updateConocimiento(Conocimiento conocimiento) {
		getSessionFactory().getCurrentSession().update(conocimiento);		
	}

	public void deleteConocimiento(Conocimiento conocimiento) {
		getSessionFactory().getCurrentSession().delete(conocimiento);		
	}

	public Conocimiento getConocimientoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Conocimiento where conoId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Conocimiento)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Conocimiento> getConocimientos() {
		ArrayList<Conocimiento> list = (ArrayList<Conocimiento>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Conocimiento order by conoNombre asc")
				.list();
		return list;
	}	
}
