package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Idioma;
import vindbrein.domain.model.NivelIdioma;
import vindbrein.domain.model.OfertaIdioma;
import vindbrein.domain.model.OfertaLaboral;

@Repository
public class OfertaIdiomaDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getSessionFactory().getCurrentSession().save(ofertaIdioma);		
	}

	public void updateOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getSessionFactory().getCurrentSession().update(ofertaIdioma);		
	}

	public void deleteOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getSessionFactory().getCurrentSession().delete(ofertaIdioma);		
	}

	public OfertaIdioma getOfertaIdiomaById(OfertaLaboral ofertaLaboral, Idioma idioma, NivelIdioma nivelIdioma) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from OfertaIdioma where ofertaLaboral.oflaId=? and idioma.idioId=? and nivelIdioma.niidId=?");
		
		query.setParameter(0, ofertaLaboral.getOflaId());
		query.setParameter(1, idioma.getIdioId());
		query.setParameter(2, nivelIdioma.getNiidId());
		
		List<OfertaIdioma> list = query.list();		   
		
		if(list.size()!=0){
			return (OfertaIdioma)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<OfertaIdioma> getOfertaIdiomas() {
		ArrayList<OfertaIdioma> list = (ArrayList<OfertaIdioma>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from OfertaIdioma order by idioma.idioNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<OfertaIdioma> getOfertaIdiomasByOfertaLaboral(OfertaLaboral ofertaLaboral) {
		ArrayList list = (ArrayList<OfertaIdioma>) getSessionFactory().getCurrentSession()
				.createQuery("from OfertaIdioma where ofertaLaboral.oflaId =? order by idioma.idioNombre asc")
		        .setParameter(0, ofertaLaboral.getOflaId()).list();
		
		return list;
	}
}
