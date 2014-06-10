package vindbrein.manager.administrador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import vindbrein.domain.app.Profile;
import vindbrein.domain.app.Result;
import vindbrein.domain.document.PostulantHistorical;
import vindbrein.service.MongoService;
import vindbrein.service.app.RecommenderService;



@Controller
@Scope("session")
public class MongoManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(MongoManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	
	
	@Autowired
	@Qualifier("mongoServiceImpl")
	private MongoService mongoService;
	
	@Autowired
	@Qualifier("recommenderServiceImpl")
	private RecommenderService recommenderService;
	
	@PostConstruct
	public void init() {		
		
	}
	
//	public void run(){
//		PostulantHistorical postulantHistorical = new PostulantHistorical();
//		
//		HashMap<String, Integer> valores = new HashMap<String, Integer>();
//		
//		valores.put("SABE", 1);
//		valores.put("MELASUDA", 0);
//		
//		postulantHistorical.setValues(valores);
//		
//		mongoService.addPostulantHistorical(postulantHistorical);
//		
//		List<PostulantHistorical> r = mongoService.listPostulantHistorical();
//		
//		for (int i = 0; i < r.size(); i++) {
//			System.out.println("id: "+r.get(i).getId());
//			System.out.println("Items");
//			
//			
//			for (Map.Entry<String, Integer> entry : r.get(i).getValues().entrySet()) {
//			   System.out.println("key: "+entry.getKey());
//			   System.out.print("value: "+entry.getValue());
//			}
//			
//		}
//		
//		postulantHistorical.getValues().put("SABE",3);
//		
//		mongoService.updatePostulantHistorical(postulantHistorical);
//		
//	}
	
	public void rec(){
		Profile perfil_puesto = new Profile();		
		
		BigDecimal[] vector = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE};
		perfil_puesto.setVecSelfDescription(vector);		
		BigDecimal[] vector2 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE};
		perfil_puesto.setVecPreference(vector2);		
		BigDecimal[] vector3 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE};
		perfil_puesto.setVecHistorical(vector3);
		
		perfil_puesto.setId(1);
		perfil_puesto.setRecNumber(1);
		
		Profile[] perfiles_profesionales =  new Profile[3];		
		
		//////////////
		Profile perfil = new Profile();		
		
		BigDecimal[] vector4 = {BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE};
		perfil.setVecSelfDescription(vector4);		
		BigDecimal[] vector5 = {BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE};
		perfil.setVecPreference(vector5);		
		BigDecimal[] vector6 = {BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE};
		perfil.setVecHistorical(vector6);
		
		perfil.setId(2);
		perfil.setRecNumber(1);
		
		perfiles_profesionales[0] = perfil;
		
		////////////
		
		perfil = new Profile();		
		
		BigDecimal[] vector7 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO};
		perfil.setVecSelfDescription(vector7);		
		BigDecimal[] vector8 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO};
		perfil.setVecPreference(vector8);		
		BigDecimal[] vector9 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO};
		perfil.setVecHistorical(vector9);
		
		perfil.setId(3);
		perfil.setRecNumber(1);
		
		perfiles_profesionales[1] = perfil;
		
		////////////
		
		perfil = new Profile();		
		
		BigDecimal[] vector10 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE};
		perfil.setVecSelfDescription(vector10);		
		BigDecimal[] vector11 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE};
		perfil.setVecPreference(vector11);		
		BigDecimal[] vector12 = {BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE};
		perfil.setVecHistorical(vector12);
		
		perfil.setId(4);
		perfil.setRecNumber(1);
		
		perfiles_profesionales[2] = perfil;
		
//		ArrayList<Result> results1 = recommenderService.recommenderContentBased(perfil_puesto, perfiles_profesionales, 2);
//		
//		System.out.println("RESULTADOS DE CONTENIDO");
//		for (int i = 0; i < results1.size(); i++) {
//			System.out.println("puesto: "+i);
//			System.out.println(results1.get(i).getScore());
//		}
//		
//		ArrayList<Result> results2 = recommenderService.recommenderCollaborativeBased(perfil_puesto, perfiles_profesionales, 2);
//		
//		System.out.println("RESULTADOS DE COLABORACION");
//		for (int i = 0; i < results2.size(); i++) {
//			System.out.println("puesto: "+i);			
//			System.out.println(results2.get(i).getScore());
//		}
//		
//		ArrayList<Result> results3 = recommenderService.recommenderReciprocityBased(perfil_puesto, perfiles_profesionales, 2);
//		
//		System.out.println("RESULTADOS DE RECIPROCIDAD");
//		for (int i = 0; i < results3.size(); i++) {
//			System.out.println("puesto: "+i);		
//			System.out.println(results3.get(i).getScore());
//		}	
	}
	
		
}
