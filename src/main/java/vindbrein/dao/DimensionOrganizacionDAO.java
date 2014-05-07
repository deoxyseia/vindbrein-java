package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.DimensionOrganizacion;

@Repository
public class DimensionOrganizacionDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addDimensionOrganizacion(DimensionOrganizacion dimensionOrganizacion) {
		getSessionFactory().getCurrentSession().save(dimensionOrganizacion);		
	}

	public void updateDimensionOrganizacion(DimensionOrganizacion dimensionOrganizacion) {
		getSessionFactory().getCurrentSession().update(dimensionOrganizacion);		
	}

	public void deleteDimensionOrganizacion(DimensionOrganizacion dimensionOrganizacion) {
		getSessionFactory().getCurrentSession().delete(dimensionOrganizacion);		
	}

	public DimensionOrganizacion getDimensionOrganizacionById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from DimensionOrganizacion where diorId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (DimensionOrganizacion)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<DimensionOrganizacion> getDimensionesOrganizacion() {
		ArrayList<DimensionOrganizacion> list = (ArrayList<DimensionOrganizacion>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from DimensionOrganizacion order by diorNombre asc")
				.list();
		return list;
	}	
}
