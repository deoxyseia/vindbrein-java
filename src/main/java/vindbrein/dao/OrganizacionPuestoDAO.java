package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.OrganizacionPuesto;
import vindbrein.domain.model.Puesto;

@Repository
public class OrganizacionPuestoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		getSessionFactory().getCurrentSession().save(organizacionPuesto);		
	}

	public void updateOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		getSessionFactory().getCurrentSession().update(organizacionPuesto);		
	}

	public void deleteOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		getSessionFactory().getCurrentSession().delete(organizacionPuesto);		
	}

	public OrganizacionPuesto getOrganizacionPuestoById(Organizacion organizacion, Puesto puesto) {
		
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from OrganizacionPuesto where organizacion.orgaId=? and puesto.puesId=?");
		
		query.setParameter(0, organizacion.getOrgaId());
		query.setParameter(1, puesto.getPuesId());
		
		ArrayList list = (ArrayList<OrganizacionPuesto>) query.list();
		
		if(list.size()!=0){
			return (OrganizacionPuesto)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<OrganizacionPuesto> getOrganizacionPuestos() {
		ArrayList<OrganizacionPuesto> list = (ArrayList<OrganizacionPuesto>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from OrganizacionPuesto order by conoNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<OrganizacionPuesto> getOrganizacionPuestosByOrganizacion(
			Organizacion organizacion) {
		ArrayList<OrganizacionPuesto> list = (ArrayList<OrganizacionPuesto>) getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from OrganizacionPuesto where organizacion.orgaId=? norder by conoNombre asc")
				.setParameter(0, organizacion.getOrgaId()).list();
		return list;
	}
}
