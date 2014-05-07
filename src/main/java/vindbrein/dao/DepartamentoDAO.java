package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Departamento;

@Repository
public class DepartamentoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addDepartamento(Departamento departamento) {
		getSessionFactory().getCurrentSession().save(departamento);		
	}

	public void updateDepartamento(Departamento departamento) {
		getSessionFactory().getCurrentSession().update(departamento);		
	}

	public void deleteDepartamento(Departamento departamento) {
		getSessionFactory().getCurrentSession().delete(departamento);		
	}

	public Departamento getDepartamentoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Departamento where depaId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Departamento)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Departamento> getDepartamentos() {
		ArrayList<Departamento> list = (ArrayList<Departamento>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Departamento order by depaNombre asc")
				.list();
		return list;
	}	
}
