package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.EstadoCivil;

@Repository
public class EstadoCivilDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addEstadoCivil(EstadoCivil EstadoCivil) {
		getSessionFactory().getCurrentSession().save(EstadoCivil);		
	}

	public void updateEstadoCivil(EstadoCivil EstadoCivil) {
		getSessionFactory().getCurrentSession().update(EstadoCivil);		
	}

	public void deleteEstadoCivil(EstadoCivil EstadoCivil) {
		getSessionFactory().getCurrentSession().delete(EstadoCivil);		
	}

	public EstadoCivil getEstadoCivilById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from EstadoCivil where esciId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (EstadoCivil)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<EstadoCivil> getEstadosCiviles() {
		ArrayList<EstadoCivil> list = (ArrayList<EstadoCivil>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from EstadoCivil order by esciNombre asc")
				.list();
		return list;
	}	
}
