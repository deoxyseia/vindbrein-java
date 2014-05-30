package vindbrein.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.Beneficio;
import vindbrein.domain.model.OfertaBeneficio;
import vindbrein.domain.model.OfertaLaboral;

@Repository
public class OfertaBeneficioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
		
	public void addOfertaBeneficio(OfertaBeneficio ofertaBeneficio) {
		getSessionFactory().getCurrentSession().save(ofertaBeneficio);		
	}

	public void updateOfertaBeneficio(OfertaBeneficio ofertaBeneficio) {
		getSessionFactory().getCurrentSession().update(ofertaBeneficio);		
	}

	public void deleteOfertaBeneficio(OfertaBeneficio ofertaBeneficio) {
		getSessionFactory().getCurrentSession().delete(ofertaBeneficio);		
	}

	
	public OfertaBeneficio getOfertaBeneficioById(OfertaLaboral ofertaLaboral, Beneficio beneficio) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from OfertaBeneficio where ofertaLaboral.oflaId=? and beneficio.beneId=?");
		
		query.setParameter(0, ofertaLaboral.getOflaId());
		query.setParameter(1, beneficio.getBeneId());
				
		List<OfertaBeneficio> list = (ArrayList<OfertaBeneficio>)query.list();	
				
		if(list.size()!=0){
			return (OfertaBeneficio)list.get(0);
		} else {
			return null;
		}		
	}

	public ArrayList<OfertaBeneficio> getOfertaBeneficios() {
		ArrayList<OfertaBeneficio> list = (ArrayList<OfertaBeneficio>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from OfertaBeneficio order by beneficio.benNombre asc")
				.list();
		return list;
	}
}
