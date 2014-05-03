package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.Provincia;

@Repository
public class DistritoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addDistrito(Distrito distrito) {
		getSessionFactory().getCurrentSession().save(distrito);		
	}

	public void updateDistrito(Distrito distrito) {
		getSessionFactory().getCurrentSession().update(distrito);		
	}

	public void deleteDistrito(Distrito distrito) {
		getSessionFactory().getCurrentSession().delete(distrito);		
	}

	public Distrito getDistritoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Distrito where distId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Distrito)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Distrito> getDistritos() {
		ArrayList<Distrito> list = (ArrayList<Distrito>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Distrito order by distNombre asc")
				.list();
		return list;
	}
	
	public ArrayList<Distrito> getDistritosByProvincia(Provincia provincia) {
		ArrayList list = (ArrayList<Distrito>) getSessionFactory().getCurrentSession()
				.createQuery("from Distrito d where d.provincia.provId=?")
		        .setParameter(0, provincia.getProvId()).list();
		
		return list;
	}
}
