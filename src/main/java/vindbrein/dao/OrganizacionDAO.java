package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Organizacion;

@Repository
public class OrganizacionDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOrganizacion(Organizacion organizacion) {
		getSessionFactory().getCurrentSession().save(organizacion);		
	}

	public void updateOrganizacion(Organizacion organizacion) {
		getSessionFactory().getCurrentSession().update(organizacion);		
	}

	public void deleteOrganizacion(Organizacion organizacion) {
		getSessionFactory().getCurrentSession().delete(organizacion);		
	}

	public Organizacion getOrganizacionById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Organizacion where orgaId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Organizacion)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Organizacion> getOrganizaciones() {
		ArrayList<Organizacion> list = (ArrayList<Organizacion>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Organizacion order by orgaRazonSocial asc")
				.list();
		return list;
	}	
}
