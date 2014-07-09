package vindbrein.manager.reclutador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.OfertaLaboral;
import vindbrein.domain.model.Postulante;
import vindbrein.service.PostulanteService;
import vindbrein.service.app.RecommenderService;
import vindbrein.util.RecommenderType;
import vindbrein.util.Util;

@Controller
@Scope("session")
public class RecomendarPostulanteManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(RecomendarPostulanteManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private int tipoRecomendacion;
	
	private OfertaLaboral selectedOfertaLaboral;
	
	private ArrayList<Postulante> postulantesRecomendados;
	
	@Autowired
	@Qualifier("recommenderServiceImpl")
	private RecommenderService recommenderService;
	
	@Autowired
	@Qualifier("postulanteServiceImpl")
	private PostulanteService postulanteService;
	
			
	@PostConstruct
	public void init() {		
		iniciarDatosMaestros();	
	}
	
	private void iniciarDatosMaestros(){
		
	}
	
	public void recomendarPostulantes(){	
		
		switch (tipoRecomendacion) {
		case 1:
			postulantesRecomendados = recommenderService.recomendarPostulanteToOfertaLboral(selectedOfertaLaboral, 2, RecommenderType.CONTENT_BASED);
			break;
		case 2:
			postulantesRecomendados = recommenderService.recomendarPostulanteToOfertaLboral(selectedOfertaLaboral, 2, RecommenderType.COLLABORATIVE_BASED);			
			break;
		case 3:
			postulantesRecomendados = recommenderService.recomendarPostulanteToOfertaLboral(selectedOfertaLaboral, 2, RecommenderType.RECIPROCITY_BASED);			
			break;
		case 4:
			postulantesRecomendados = recommenderService.recomendarPostulanteToOfertaLboral(selectedOfertaLaboral, 2, RecommenderType.FUSION_BASED);
			break;	

		default:
			System.out.println("que pasó default!!!");
			break;
		}
		
		Util.lanzarMensaje("INFO", "GLOBAL", "Se ha realizado exitosamente la recomendación");
	}
	
	//getters and setters	

	public int getTipoRecomendacion() {
		return tipoRecomendacion;
	}

	public ArrayList<Postulante> getPostulantesRecomendados() {
		return postulantesRecomendados;
	}

	public void setPostulantesRecomendados(
			ArrayList<Postulante> postulantesRecomendados) {
		this.postulantesRecomendados = postulantesRecomendados;
	}

	public void setTipoRecomendacion(int tipoRecomendacion) {
		this.tipoRecomendacion = tipoRecomendacion;
	}

	public OfertaLaboral getSelectedOfertaLaboral() {
		return selectedOfertaLaboral;
	}

	public void setSelectedOfertaLaboral(OfertaLaboral selectedOfertaLaboral) {
		this.selectedOfertaLaboral = selectedOfertaLaboral;
	}	
}
