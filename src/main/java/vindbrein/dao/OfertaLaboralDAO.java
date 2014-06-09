package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Organizacion;

@Repository
public class OfertaLaboralDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getSessionFactory().getCurrentSession().save(ofertaLaboral);		
	}

	public void updateOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getSessionFactory().getCurrentSession().update(ofertaLaboral);		
	}
	
	public void saveOrUpdateOfertaLaboral(OfertaLaboral ofertaLaboral){
		getSessionFactory().getCurrentSession().saveOrUpdate(ofertaLaboral);
	}

	public void deleteOfertaLaboral(OfertaLaboral ofertaLaboral) {
		getSessionFactory().getCurrentSession().delete(ofertaLaboral);		
	}

	public OfertaLaboral getOfertaLaboralById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from OfertaLaboral where oflaId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (OfertaLaboral)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		ArrayList<OfertaLaboral> list = (ArrayList<OfertaLaboral>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from OfertaLaboral")
				.list();
		return list;
	}
	
	public ArrayList<OfertaLaboral> getOfertasLaboralesByOrganizacion(Organizacion organizacion) {
		ArrayList list = (ArrayList<OfertaLaboral>) getSessionFactory().getCurrentSession()
				.createQuery("from OfertaLaboral where organizacionPuesto.organizacion.orgaId=?")
		        .setParameter(0, organizacion.getOrgaId()).list();
		
		return list;
	}
}
