package vindbrein.service.app.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vindbrein.dao.MatchResultDAO;
import vindbrein.dao.PostulanteDAO;
import vindbrein.dao.app.CoreDAO;
import vindbrein.dao.document.PostulantHistoricalDAO;
import vindbrein.domain.document.PostulantHistorical;
import vindbrein.domain.model.MatchResult;
import vindbrein.domain.model.MatchResultPK;
import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
import vindbrein.service.app.CoreService;

@Service
@Transactional(readOnly = true)
public class CoreServiceImpl implements CoreService {
	
	@Autowired	
	CoreDAO coreDAO;
	
	@Autowired	
	MatchResultDAO matchResultDAO;
	
	@Autowired	
	PostulantHistoricalDAO postulantHistoricalDAO;
	
	@Autowired	
	PostulanteDAO postulanteDAO;
	
	
	@Transactional(readOnly = false)
	public void visitarOfertaLaboral(OfertaLaboral ofertaLaboral, Postulante postulante){
		postulante = postulanteDAO.getPostulanteByCorreo(postulante.getUsuario().getUsuaCorreo());
		
		MatchResult matchResult = matchResultDAO.getMatchResultById(ofertaLaboral, postulante);
		
		if(matchResult == null){
			matchResult = new MatchResult();
			matchResult.setId(new MatchResultPK());
			
			matchResult.setPostulante(postulante);
			matchResult.setOfertaLaboral(ofertaLaboral);
			matchResult.getId().setFkPostId(postulante.getPostId());
			matchResult.getId().setFkOflaId(ofertaLaboral.getOflaId());
			
			matchResult.setMareFlagOfertaVisitada((byte)1);			
			
			matchResultDAO.addMatchResult(matchResult);		
		}else{
			matchResult.setMareFlagOfertaVisitada((byte)1);
			
			matchResultDAO.updateMatchResult(matchResult);
		}
		
		LinkedHashMap<String, Integer> vecHistorical = coreDAO.getVectorOfferSelfDescription(ofertaLaboral);
				
		if (postulante.getPostIdH() == null) {
			ObjectId  objectId = new ObjectId();
			
			PostulantHistorical postulantHistorical = new PostulantHistorical();
			postulantHistorical.setId(objectId.toString());
			postulantHistorical.setValues(vecHistorical);
			
			postulantHistoricalDAO.addPostulantHistorical(postulantHistorical);
			
			postulante.setPostIdH(objectId.toString());			
			postulanteDAO.updatePostulante(postulante);
		}else{
			PostulantHistorical postulantHistorical = postulantHistoricalDAO.getPostulantHistoricalById(postulante.getPostIdH());
			
			postulantHistorical.setValues(vecHistorical);
			
			postulantHistoricalDAO.updatePostulantHistorical(postulantHistorical);
		}
	}
	
	@Transactional(readOnly = false)
	public void postularOfertaLaboral(OfertaLaboral ofertaLaboral, Postulante postulante){
		postulante = postulanteDAO.getPostulanteByCorreo(postulante.getUsuario().getUsuaCorreo());
		
		MatchResult matchResult = matchResultDAO.getMatchResultById(ofertaLaboral, postulante);
		
		if(matchResult == null){
			matchResult = new MatchResult();
			matchResult.setId(new MatchResultPK());
			
			matchResult.setPostulante(postulante);
			matchResult.setOfertaLaboral(ofertaLaboral);
			matchResult.getId().setFkPostId(postulante.getPostId());
			matchResult.getId().setFkOflaId(ofertaLaboral.getOflaId());
			
			matchResult.setMareFlagOfertaSeleccionada((byte)1);	
			matchResult.setMareFechaOfertaSeleccionada(new Date());
			
			matchResultDAO.addMatchResult(matchResult);		
		}else{
			matchResult.setMareFlagOfertaSeleccionada((byte)1);
			matchResult.setMareFechaOfertaSeleccionada(new Date());
			
			matchResultDAO.updateMatchResult(matchResult);
		}
	}
	
}
