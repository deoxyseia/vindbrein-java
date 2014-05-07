package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.Sucursal;

@Repository
public class SucursalDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addSucursal(Sucursal sucursal) {
		getSessionFactory().getCurrentSession().save(sucursal);		
	}

	public void updateSucursal(Sucursal sucursal) {
		getSessionFactory().getCurrentSession().update(sucursal);		
	}

	public void deleteSucursal(Sucursal sucursal) {
		getSessionFactory().getCurrentSession().delete(sucursal);		
	}

	public Sucursal getSucursalById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Sucursal where sucuId=?")
		        .setParameter(0, id).list();
		
		if(list.size()!=0){
			return (Sucursal)list.get(0);
		} else {
			return null;
		}
	}

	public ArrayList<Sucursal> getSucursales() {
		ArrayList<Sucursal> list = (ArrayList<Sucursal>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Sucursal order by sucuDireccion asc")
				.list();
		return list;
	}
}
