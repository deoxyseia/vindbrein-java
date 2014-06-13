package vindbrein.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import vindbrein.domain.model.MatchResult;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;

@Repository
public class MatchResultDAO implements Serializable{
	
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
		
	public void addMatchResult(MatchResult matchResult) {
		getSessionFactory().getCurrentSession().save(matchResult);		
	}

	public void updateMatchResult(MatchResult matchResult) {
		getSessionFactory().getCurrentSession().update(matchResult);		
	}

	public void deleteMatchResult(MatchResult matchResult) {
		getSessionFactory().getCurrentSession().delete(matchResult);		
	}

	public MatchResult getMatchResultById(OfertaLaboral ofertaLaboral, Postulante postulante) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from MatchResult where ofertaLaboral.oflaId=? and postulante.postId = ?");
		
		query.setParameter(0, ofertaLaboral.getOflaId());
		query.setParameter(1, postulante.getPostId());
		
		List<MatchResult> list = query.list();
		
		if(list.size()!=0){
			return (MatchResult)list.get(0);
		} else {
			return null;
		}
	}
	
	
	//obtiene el match donde de donde se hizo la ultima postulación (campo selección)
	public MatchResult getLastMatchResultByPostulant(Postulante postulante){
		ArrayList<MatchResult> list = (ArrayList<MatchResult>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from MatchResult where mareFlagOfertaSeleccionada = 1 and postulante.postId = ? order by mareFechaOfertaSeleccionada desc")
				.setParameter(0, postulante.getPostId())
				.list();
		
		
		//obtiene el último valor
		if(list.size()!=0){
			return (MatchResult)list.get(0);
		} else {
			return null;
		}
	}
	
	//obtiene el match donde de donde se hizo la ultima postulación (campo selección)
		public MatchResult getLastMatchResultByOffer(OfertaLaboral ofertaLaboral){
			ArrayList<MatchResult> list = (ArrayList<MatchResult>) getSessionFactory()
					.getCurrentSession()
					.createQuery("from MatchResult where mareFlagPostulanteSeleccionado = 1 and ofertaLaboral.oflaId = ? order by mareFechaPostulanteSeleccionado desc")
					.setParameter(0, ofertaLaboral.getOflaId())
					.list();
			
			
			//obtiene el último valor
			if(list.size()!=0){
				return (MatchResult)list.get(0);
			} else {
				return null;
			}
		}

	public ArrayList<MatchResult> getMatchResults() {
		ArrayList<MatchResult> list = (ArrayList<MatchResult>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from MatchResult")
				.list();
		return list;
	}
	
	//se está usando la selección en lugar de la recomendación
	public int getNumberRecomendationPostulant(Postulante postulante){
		ArrayList<MatchResult> list = (ArrayList<MatchResult>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from MatchResult where mareFlagPostulanteRecomendado = 1 and mareFlagPostulanteSeleccionado = 1 and postulante.postId = ?")
				.setParameter(0, postulante.getPostId())
				.list();
		
		return list.size();
	}
	
	//se está usando la selección en lugar de la recomendación
	public int getNumberRecomendationOffer(OfertaLaboral ofertaLaboral){
		ArrayList<MatchResult> list = (ArrayList<MatchResult>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from MatchResult where mareFlagOfertaRecomendada = 1 and mareFlagOfertaSeleccionada = 1 and ofertaLaboral.oflaId = ?")
				.setParameter(0, ofertaLaboral.getOflaId())
				.list();
		
		return list.size();
	}
	
}
