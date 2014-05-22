package vindbrein.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.NivelPuesto;

@Repository
public class NivelPuestoDAO implements Serializable{
	
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
		
	public void addNivelPuesto(NivelPuesto nivelPuesto) {
		getSessionFactory().getCurrentSession().save(nivelPuesto);		
	}

	public void updateNivelPuesto(NivelPuesto nivelPuesto) {
		getSessionFactory().getCurrentSession().update(nivelPuesto);		
	}

	public void deleteNivelPuesto(NivelPuesto nivelPuesto) {
		getSessionFactory().getCurrentSession().delete(nivelPuesto);		
	}

	public NivelPuesto getNivelPuestoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from NivelPuesto where nipuId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (NivelPuesto)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<NivelPuesto> getNivelesPuesto() {
		ArrayList<NivelPuesto> list = (ArrayList<NivelPuesto>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from NivelPuesto order by nipuNombre asc")
				.list();
		return list;
	}	
}
