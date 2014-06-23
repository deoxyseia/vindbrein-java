package vindbrein.dao;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.CategoriaConocimiento;

@Repository
public class CategoriaConocimientoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	

	public ArrayList<CategoriaConocimiento> getCategoriasConocimiento() {
		ArrayList<CategoriaConocimiento> list = (ArrayList<CategoriaConocimiento>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from CategoriaConocimiento order by cacoNombre asc")
				.list();
		return list;
	}
}
