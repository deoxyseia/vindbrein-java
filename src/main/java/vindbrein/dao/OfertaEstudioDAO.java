package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.EstudioGenerico;
import vindbrein.domain.model.OfertaEstudio;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Provincia;

@Repository
public class OfertaEstudioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOfertaEstudio(OfertaEstudio ofertaEstudio) {
		getSessionFactory().getCurrentSession().save(ofertaEstudio);		
	}

	public void updateOfertaEstudio(OfertaEstudio ofertaEstudio) {
		getSessionFactory().getCurrentSession().update(ofertaEstudio);		
	}

	public void deleteOfertaEstudio(OfertaEstudio ofertaEstudio) {
		getSessionFactory().getCurrentSession().delete(ofertaEstudio);		
	}

	public OfertaEstudio getOfertaEstudioById(OfertaLaboral ofertaLaboral, EstudioGenerico estudioGenerico) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from OfertaEstudio where ofertaLaboral.oflaId=? and estudioGenerico.esgeId=?");
		
		query.setParameter(0, ofertaLaboral.getOflaId());
		query.setParameter(1, estudioGenerico.getEsgeId());
		 
		List<OfertaEstudio> list = query.list();
		
		if(list.size()!=0){
			return (OfertaEstudio)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<OfertaEstudio> getOfertaEstudios() {
		ArrayList<OfertaEstudio> list = (ArrayList<OfertaEstudio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from OfertaEstudio order by estudioGenerico.esgeNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<OfertaEstudio> getOfertaEstudiosByOfertaLaboral(OfertaLaboral ofertaLaboral) {
		ArrayList list = (ArrayList<OfertaEstudio>) getSessionFactory().getCurrentSession()
				.createQuery("from OfertaEstudio where ofertaLaboral.oflaId=? order by estudioGenerico.esgeNombre asc")
		        .setParameter(0, ofertaLaboral.getOflaId()).list();
		
		return list;
	}
}
