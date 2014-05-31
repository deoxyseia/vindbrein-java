package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.CentroEstudio;
import vindbrein.domain.model.OfertaCentroEstudio;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Provincia;

@Repository
public class OfertaCentroEstudioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOfertaCentroEstudio(OfertaCentroEstudio ofertaCentroEstudio) {
		getSessionFactory().getCurrentSession().save(ofertaCentroEstudio);		
	}

	public void updateOfertaCentroEstudio(OfertaCentroEstudio ofertaCentroEstudio) {
		getSessionFactory().getCurrentSession().update(ofertaCentroEstudio);		
	}

	public void deleteOfertaCentroEstudio(OfertaCentroEstudio ofertaCentroEstudio) {
		getSessionFactory().getCurrentSession().delete(ofertaCentroEstudio);		
	}

	public OfertaCentroEstudio getOfertaCentroEstudioById(OfertaLaboral ofertaLaboral, CentroEstudio centroEstudio) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from OfertaCentroEstudio where ofertaLaboral.oflaId=? and centroEstudio.ceesId=?");
		
		query.setParameter(0, ofertaLaboral.getOflaId());
		query.setParameter(1, centroEstudio.getCeesId());
		
		List<OfertaCentroEstudio> list = query.list();		        
		
		if(list.size()!=0){
			return (OfertaCentroEstudio)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<OfertaCentroEstudio> getOfertaCentroEstudios() {
		ArrayList<OfertaCentroEstudio> list = (ArrayList<OfertaCentroEstudio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from OfertaCentroEstudio order by centroEstudio.ceesNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<OfertaCentroEstudio> getOfertaCentroEstudiosByOfertaLaboral(OfertaLaboral ofertaLaboral) {
		ArrayList list = (ArrayList<OfertaCentroEstudio>) getSessionFactory().getCurrentSession()
				.createQuery("from OfertaCentroEstudio where ofertaLaboral.oflaId=? order by centroEstudio.ceesNombre asc")
		        .setParameter(0, ofertaLaboral.getOflaId()).list();
		
		return list;
	}
}
