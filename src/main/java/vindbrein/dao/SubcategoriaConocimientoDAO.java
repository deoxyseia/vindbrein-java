package vindbrein.dao;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.CategoriaConocimiento;
import vindbrein.domain.model.SubcategoriaConocimiento;

@Repository
public class SubcategoriaConocimientoDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

	public ArrayList<SubcategoriaConocimiento> getSubcategoriasConocimientoByCategoriaConocimiento(CategoriaConocimiento categoriaConocimiento) {
		ArrayList<SubcategoriaConocimiento> list = (ArrayList<SubcategoriaConocimiento>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from SubcategoriaConocimiento where categoriaConocimiento.cacoId = ? order by sucoNombre asc")
				.setParameter(0, categoriaConocimiento.getCacoId())
				.list();
		return list;
	}
}
