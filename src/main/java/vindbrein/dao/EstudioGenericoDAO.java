package vindbrein.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.EstudioGenerico;

@Repository
public class EstudioGenericoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addEstudioGenerico(EstudioGenerico estudioGenerico) {
		getSessionFactory().getCurrentSession().save(estudioGenerico);		
	}

	public void updateEstudioGenerico(EstudioGenerico estudioGenerico) {
		getSessionFactory().getCurrentSession().update(estudioGenerico);		
	}

	public void deleteEstudioGenerico(EstudioGenerico estudioGenerico) {
		getSessionFactory().getCurrentSession().delete(estudioGenerico);		
	}

	public EstudioGenerico getEstudioGenericoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from EstudioGenerico where esgeId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (EstudioGenerico)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<EstudioGenerico> getEstudiosGenericos() {
		ArrayList<EstudioGenerico> list = (ArrayList<EstudioGenerico>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from EstudioGenerico order by esgeNombre asc")
				.list();
		return list;
	}	
}
