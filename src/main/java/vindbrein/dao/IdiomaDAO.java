package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Idioma;

@Repository
public class IdiomaDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addIdioma(Idioma idioma) {
		getSessionFactory().getCurrentSession().save(idioma);		
	}

	public void updateIdioma(Idioma idioma) {
		getSessionFactory().getCurrentSession().update(idioma);		
	}

	public void deleteIdioma(Idioma idioma) {
		getSessionFactory().getCurrentSession().delete(idioma);		
	}

	public Idioma getIdiomaById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Idioma where idioId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Idioma)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Idioma> getIdiomas() {
		ArrayList<Idioma> list = (ArrayList<Idioma>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Idioma order by idioNombre asc")
				.list();
		return list;
	}
}
