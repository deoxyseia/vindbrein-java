package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.NivelIdioma;
import vindbrein.domain.model.Provincia;

@Repository
public class NivelIdiomaDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addNivelIdioma(NivelIdioma nivelIdioma) {
		getSessionFactory().getCurrentSession().save(nivelIdioma);		
	}

	public void updateNivelIdioma(NivelIdioma nivelIdioma) {
		getSessionFactory().getCurrentSession().update(nivelIdioma);		
	}

	public void deleteNivelIdioma(NivelIdioma nivelIdioma) {
		getSessionFactory().getCurrentSession().delete(nivelIdioma);		
	}

	public NivelIdioma getNivelIdiomaById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from NivelIdioma where niidId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (NivelIdioma)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<NivelIdioma> getNivelesIdioma() {
		ArrayList<NivelIdioma> list = (ArrayList<NivelIdioma>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from NivelIdioma order by niidNombre asc")
				.list();
		return list;
	}
}
