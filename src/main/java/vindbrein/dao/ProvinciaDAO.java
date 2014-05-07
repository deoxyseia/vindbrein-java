package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Provincia;

@Repository
public class ProvinciaDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addProvincia(Provincia provincia) {
		getSessionFactory().getCurrentSession().save(provincia);		
	}

	public void updateProvincia(Provincia provincia) {
		getSessionFactory().getCurrentSession().update(provincia);		
	}

	public void deleteProvincia(Provincia provincia) {
		getSessionFactory().getCurrentSession().delete(provincia);		
	}

	public Provincia getProvinciaById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Provincia where provId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Provincia)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Provincia> getProvincias() {
		ArrayList<Provincia> list = (ArrayList<Provincia>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Provincia order by provNombre asc")
				.list();
		return list;
	}	
	
	public ArrayList<Provincia> getProvinciasByDepartamento(Departamento departamento) {
		ArrayList list = (ArrayList<Provincia>) getSessionFactory().getCurrentSession()
				.createQuery("from Provincia p where p.departamento.depaId=?")
		        .setParameter(0, departamento.getDepaId()).list();
		
		return list;
	}	
}
