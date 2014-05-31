package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Conocimiento;
import vindbrein.domain.model.NivelConocimiento;
import vindbrein.domain.model.OfertaConocimiento;
import vindbrein.domain.model.OfertaLaboral;

@Repository
public class OfertaConocimientoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getSessionFactory().getCurrentSession().save(ofertaConocimiento);		
	}

	public void updateOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getSessionFactory().getCurrentSession().update(ofertaConocimiento);		
	}

	public void deleteOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getSessionFactory().getCurrentSession().delete(ofertaConocimiento);		
	}

	public OfertaConocimiento getOfertaConocimientoById(OfertaLaboral ofertaLaboral, Conocimiento conocimiento, NivelConocimiento nivelConocimiento) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from OfertaConocimiento where distId=?");
		
		query.setParameter(0, ofertaLaboral.getOflaId());
		query.setParameter(1, conocimiento.getConoId());
		query.setParameter(2, nivelConocimiento.getNicoId());
		
		List<OfertaConocimiento> list = query.list();		        
		
		if(list.size()!=0){
			return (OfertaConocimiento)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<OfertaConocimiento> getOfertaConocimientos() {
		ArrayList<OfertaConocimiento> list = (ArrayList<OfertaConocimiento>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from OfertaConocimiento order by conocimiento.conoNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<OfertaConocimiento> getOfertaConocimientosByOfertaLaboral(OfertaLaboral ofertaLaboral) {
		ArrayList list = (ArrayList<OfertaConocimiento>) getSessionFactory().getCurrentSession()
				.createQuery("from OfertaConocimiento where ofertaLaboral.oflaId=? order by conocimiento.conoNombre asc")
		        .setParameter(0, ofertaLaboral.getOflaId()).list();
		
		return list;
	}
}
