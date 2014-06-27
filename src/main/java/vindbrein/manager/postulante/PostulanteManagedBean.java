package vindbrein.manager.postulante;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import vindbrein.domain.model.ActividadAcademica;
import vindbrein.domain.model.Beneficio;
import vindbrein.domain.model.CategoriaConocimiento;
import vindbrein.domain.model.Conocimiento;
import vindbrein.domain.model.Departamento;
import vindbrein.domain.model.DimensionOrganizacion;
import vindbrein.domain.model.Distrito;
import vindbrein.domain.model.EstadoCivil;
import vindbrein.domain.model.Estudio;
import vindbrein.domain.model.ExperienciaLaboral;
import vindbrein.domain.model.Idioma;
import vindbrein.domain.model.NivelConocimiento;
import vindbrein.domain.model.NivelIdioma;
import vindbrein.domain.model.NivelPuesto;
import vindbrein.domain.model.OfertaBeneficio;
import vindbrein.domain.model.OfertaConocimiento;
import vindbrein.domain.model.Organizacion;
import vindbrein.domain.model.OrganizacionPuesto;
import vindbrein.domain.model.Postulante;
import vindbrein.domain.model.PostulanteBeneficio;
import vindbrein.domain.model.PostulanteBeneficioPK;
import vindbrein.domain.model.PostulanteConocimiento;
import vindbrein.domain.model.PostulanteConocimientoPK;
import vindbrein.domain.model.PostulanteIdioma;
import vindbrein.domain.model.PostulanteIdiomaPK;
import vindbrein.domain.model.Provincia;
import vindbrein.domain.model.Puesto;
import vindbrein.domain.model.Residencia;
import vindbrein.domain.model.Sector;
import vindbrein.domain.model.SubcategoriaConocimiento;
import vindbrein.domain.model.Telefono;
import vindbrein.domain.model.TipoHorario;
import vindbrein.service.ActividadAcademicaService;
import vindbrein.service.BeneficioService;
import vindbrein.service.ConocimientoService;
import vindbrein.service.DepartamentoService;
import vindbrein.service.DistritoService;
import vindbrein.service.EstadoCivilService;
import vindbrein.service.EstudioService;
import vindbrein.service.ExperienciaLaboralService;
import vindbrein.service.IdiomaService;
import vindbrein.service.MongoService;
import vindbrein.service.OrganizacionService;
import vindbrein.service.PostulanteService;
import vindbrein.service.ProvinciaService;
import vindbrein.service.PuestoService;
import vindbrein.service.ResidenciaService;
import vindbrein.service.SectorService;
import vindbrein.service.TelefonoService;
import vindbrein.service.TipoHorarioService;

@Controller
@Scope("session")
public class PostulanteManagedBean implements Serializable{
	
	private static final Logger logger = Logger
			.getLogger(PostulanteManagedBean.class);
	
	private static final long serialVersionUID = 1L;
	
	private Postulante postulante;
	
	//telefono	
	private Telefono newTelefono;
	private Residencia newResidencia;
	private PostulanteConocimiento newPostulanteConocimiento;
	private ExperienciaLaboral newExperienciaLaboral;
	private ActividadAcademica newActividadAcademica;
	private PostulanteIdioma newPostulanteIdioma;
	private PostulanteBeneficio newPostulanteBeneficio;
	
			
	@Autowired
	@Qualifier("postulanteServiceImpl")
	private PostulanteService postulanteService;
		
	@Autowired
	@Qualifier("residenciaServiceImpl")
	private ResidenciaService residenciaService;
	
	@Autowired
	@Qualifier("departamentoServiceImpl")
	private DepartamentoService departamentoService;
	
	@Autowired
	@Qualifier("provinciaServiceImpl")
	private ProvinciaService provinciaService;
	
	@Autowired
	@Qualifier("distritoServiceImpl")
	private DistritoService distritoService;
	
	@Autowired
	@Qualifier("conocimientoServiceImpl")
	private ConocimientoService conocimientoService;
		
	@Autowired
	@Qualifier("puestoServiceImpl")
	private PuestoService puestoService;
		
	@Autowired
	@Qualifier("estadoCivilServiceImpl")
	private EstadoCivilService estadoCivilService;
	
	@Autowired
	@Qualifier("beneficioServiceImpl")
	private BeneficioService beneficioService;
	
	@Autowired
	@Qualifier("organizacionServiceImpl")
	private OrganizacionService organizacionService;
		
	@Autowired
	@Qualifier("estudioServiceImpl")
	private EstudioService estudioService;
	
	@Autowired
	@Qualifier("idiomaServiceImpl")
	private IdiomaService idiomaService;
	
	@Autowired
	@Qualifier("tipoHorarioServiceImpl")
	private TipoHorarioService tipoHorarioService;
	
	@Autowired
	@Qualifier("sectorServiceImpl")
	private SectorService sectorService;
	
	// datos maestros	
	private ArrayList<Departamento> departamentos;
	private ArrayList<Provincia> provincias;
	private ArrayList<Distrito> distritos;
	private ArrayList<Conocimiento> conocimientos;
	private ArrayList<Puesto> puestos;
	private ArrayList<EstadoCivil> estadosCiviles;
	private ArrayList<Beneficio> beneficios;
	private ArrayList<NivelConocimiento> nivelesConocimiento;
	private ArrayList<Organizacion> organizaciones;
	private ArrayList<Estudio> estudios;
	private ArrayList<Idioma> idiomas;
	private ArrayList<NivelIdioma> nivelesIdioma;
	private ArrayList<TipoHorario> tiposHorario;
	private ArrayList<NivelPuesto> nivelesPuesto;
	private ArrayList<DimensionOrganizacion> dimensionesOrganizacion;
	private ArrayList<Sector> sectores; 

	private Departamento selectedDepartamento;
	private Provincia selectedProvincia;
	private Distrito selectedDistrito;	
	
	///pivote
	private CategoriaConocimiento selectedCategoriaConocimiento;
	private SubcategoriaConocimiento selectedSubcategoriaConocimiento;
	
	private ArrayList<CategoriaConocimiento> categoriasConocimiento;
	private ArrayList<SubcategoriaConocimiento> subcategoriasConocimiento;
			
	///datos de mantenimiento
	private Conocimiento newConocimiento;
	private Puesto newPuesto;
	
		
	@PostConstruct
	public void init() {		
		iniciarPostulante();	
		
		iniciarDatosMaestros();			
	}
	
	private void iniciarPostulante(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		postulante = postulanteService.getPostulanteByCorreo(user.getUsername());
		
		recargarPostulante();		
	}
	
	private void recargarPostulante(){
		postulante = postulanteService.getPostulanteCompletoByPostulante(postulante);
		
		if(postulante.getEstadoCivil() == null){
			postulante.setEstadoCivil(new EstadoCivil());
		}
		
		if(postulante.getTipoHorario() == null){
			postulante.setTipoHorario(new TipoHorario());
		}
		
		if(postulante.getNivelPuesto() == null){
			postulante.setNivelPuesto(new NivelPuesto());
		}
		
		if(postulante.getDimensionOrganizacion() == null){
			postulante.setDimensionOrganizacion(new DimensionOrganizacion());
		}
		
		if(postulante.getSector() == null){
			postulante.setSector(new Sector());
		}
	}
	
	private void iniciarDatosMaestros(){
		selectedDepartamento = new Departamento();
		selectedProvincia = new Provincia();
		selectedDistrito = new Distrito();	
		
		departamentos = departamentoService.getDepartamentos();
		conocimientos = conocimientoService.getConocimientos();
		puestos = puestoService.getPuestos();
		estadosCiviles = estadoCivilService.getEstadosCiviles();
		nivelesConocimiento = conocimientoService.getNivelesConocimiento();
		organizaciones = organizacionService.getOrganizaciones();
		estudios = estudioService.getEstudios();
		idiomas = idiomaService.getIdiomas();
		nivelesIdioma = idiomaService.getNivelesIdioma();
		tiposHorario = tipoHorarioService.getTiposHorario();
		nivelesPuesto = puestoService.getNivelesPuesto();
		dimensionesOrganizacion = organizacionService.getDimensionesOrganizacion();
		sectores = sectorService.getSectores();
		beneficios = beneficioService.getBeneficios();
		
		/////
		selectedCategoriaConocimiento = new CategoriaConocimiento();
		selectedSubcategoriaConocimiento = new SubcategoriaConocimiento();
		
		categoriasConocimiento = conocimientoService.getCategoriasConocimiento();
		subcategoriasConocimiento= new ArrayList<SubcategoriaConocimiento>();
		
		reiniciarNewTelefono();
		reiniciarNewResidencia();
		reiniciarNewPostulanteConocimiento();
		reiniciarNewExperienciaLaboral();	
		reiniciarNewActividadAcademica();
		reiniciarNewPostulanteIdioma();
		reiniciarNewPostulanteBeneficio();
	}
	
	public void chargeSubcategoriasConocimiento(){
		subcategoriasConocimiento = conocimientoService.getSubcategoriasConocimientoByCategoriaConocimiento(selectedCategoriaConocimiento);
	}
	
//	public void savePostulante(){
//		postulanteService.updatePostulante(postulante);		
//		
//		recargarPostulante();
//	}
	
	public void savePostulante(){
		postulanteService.saveOrUpdatePostulante(postulante);
		
		recargarPostulante();
	}
	
	public void cancelSavePostulante(){
		recargarPostulante();
	}
	
	public void chargeProvincias(){
		logger.info("Cargando pronvincias");
		
		provincias = provinciaService.getProvinciasByDepartamento(selectedDepartamento);
		distritos = null;
	}
	
	public void chargeDistritos(){
		logger.info("Cargando distritos");
		
		distritos = distritoService.getDistritosByProvincia(selectedProvincia);
	}
	
	//telefono
	public void reiniciarNewTelefono(){
		newTelefono = new Telefono();
		newTelefono.setPostulante(postulante);
	}	
	
	public void saveTelefono(){		
		logger.info("agregar telefono");
		
		postulante.getTelefonos().add(newTelefono);	
	}
		
	public void deleteTelefono(){		
		logger.info("quitando telefono");
		
		if(newTelefono != null){
			postulante.getTelefonos().remove(newTelefono);
		}	
	}
	
	//residencia
	public void reiniciarNewResidencia(){
		newResidencia = new Residencia();
		newResidencia.setPostulante(postulante);
		newResidencia.setResiActivo((byte)1);
		
		selectedDepartamento = new Departamento();
		selectedProvincia = new Provincia();
		selectedDistrito = new Distrito();
		
		provincias = null;
		distritos = null;		
	}
	
	public void saveResidencia(){		
		logger.info("agregando residencia");
		
		if(newResidencia != null){
			newResidencia.setDistrito(selectedDistrito);
			postulante.getResidencias().add(newResidencia);
		}		
	}
	
	public void loadResidencia(){
		
		newResidencia = residenciaService.getResidenciaById(newResidencia.getResiId());
		
		selectedDistrito = newResidencia.getDistrito();
		selectedProvincia = newResidencia.getDistrito().getProvincia();
		selectedDepartamento = newResidencia.getDistrito().getProvincia().getDepartamento();
		
		provincias = provinciaService.getProvinciasByDepartamento(selectedDepartamento);
		distritos = distritoService.getDistritosByProvincia(selectedProvincia);
	}
	
	public void deleteResidencia(){	
		logger.info("removiendo residencia");
		
		if(newResidencia != null){
			postulante.getResidencias().remove(newResidencia);
		}		
	}
	
	//conocimiento
	public void reiniciarNewPostulanteConocimiento(){
		newPostulanteConocimiento = new PostulanteConocimiento();
		newPostulanteConocimiento.setId(new PostulanteConocimientoPK());
		
		newPostulanteConocimiento.setPostulante(postulante);	
		
		newPostulanteConocimiento.setConocimiento(new Conocimiento());
		newPostulanteConocimiento.setNivelConocimiento(new NivelConocimiento());
	}
	
	public void savePostulanteConocimiento(){		
		logger.info("agregando conocimiento");
		
		boolean agregar = true;
		
		for (PostulanteConocimiento postulanteConocimiento : postulante.getPostulanteConocimientos()) {
			if(postulanteConocimiento.getConocimiento().getConoId() == newPostulanteConocimiento.getConocimiento().getConoId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			postulante.addPostulanteConocimiento(newPostulanteConocimiento);		
		}
	}
	
	public void deletePostulanteConocimiento(){	
		logger.info("removiendo conocimiento");
		
		if(newPostulanteConocimiento != null){
			postulante.removePostulanteConocimiento(newPostulanteConocimiento);
		}		
	}
	
	//experiencia laboral
	public void reiniciarNewExperienciaLaboral(){
		OrganizacionPuesto organizacionPuesto = new OrganizacionPuesto();
		organizacionPuesto.setPuesto(new Puesto());
		organizacionPuesto.setOrganizacion(new Organizacion());
		
		newExperienciaLaboral = new ExperienciaLaboral();
		newExperienciaLaboral.setPostulante(postulante);
		newExperienciaLaboral.setOrganizacionPuesto(organizacionPuesto);
	}
	
	public void saveExperienciaLaboral(){		
		logger.info("agregando experiencia laboral");
		
		boolean agregar = true;
		
		for (ExperienciaLaboral experienciaLaboral : postulante.getExperienciasLaborales()) {
			if(experienciaLaboral.getOrganizacionPuesto().getOrganizacion().getOrgaId() == newExperienciaLaboral.getOrganizacionPuesto().getOrganizacion().getOrgaId()
				&& experienciaLaboral.getOrganizacionPuesto().getPuesto().getPuesId() == newExperienciaLaboral.getOrganizacionPuesto().getPuesto().getPuesId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			postulante.addExperienciaLaboral(newExperienciaLaboral);		
		}
	}
	
	public void deleteExperienciaLaboral(){
		logger.info("removiendo experiencia laboral");
		
		if(newExperienciaLaboral != null){
			postulante.removeExperienciaLaboral(newExperienciaLaboral);
		}
	}
	
	//actividad academica
	public void reiniciarNewActividadAcademica(){
		newActividadAcademica = new ActividadAcademica();
		newActividadAcademica.setPostulante(postulante);
		newActividadAcademica.setEstudio(new Estudio());		
	}
	
	public void saveActividadAcademica(){		
		logger.info("agregando actividad academica");
		
		boolean agregar = true;
		
		for (ActividadAcademica actividadAcademica: postulante.getActividadesAcademicas()) {
			if(actividadAcademica.getEstudio().getEstuId() == newActividadAcademica.getEstudio().getEstuId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			postulante.addActividadAcademica(newActividadAcademica);	
		}
	}
	
	public void deleteActividadAcademica(){
		logger.info("removiendo actividad academica");
		
		if(newActividadAcademica != null){
			postulante.getActividadesAcademicas().remove(newActividadAcademica);
		}
	}
	
	//idioma
	public void reiniciarNewPostulanteIdioma(){
		newPostulanteIdioma = new PostulanteIdioma();
		
		newPostulanteIdioma.setId(new PostulanteIdiomaPK());		
		newPostulanteIdioma.setPostulante(postulante);
		newPostulanteIdioma.setIdioma(new Idioma());
		newPostulanteIdioma.setNivelIdioma(new NivelIdioma());
	}
	
	public void savePostulanteIdioma(){		
		logger.info("agregando idioma");
		
		boolean agregar = true;
		
		for (PostulanteIdioma postulanteIdioma: postulante.getPostulanteIdiomas()) {
			if(postulanteIdioma.getIdioma().getIdioId() == newPostulanteIdioma.getIdioma().getIdioId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			postulante.addPostulanteIdioma(newPostulanteIdioma);
		}
	}
	
	public void deletePostulanteIdioma(){
		logger.info("removiendo idioma");
		
		if(newPostulanteIdioma != null){
			postulante.removePostulanteIdioma(newPostulanteIdioma);
		}
	}
	
	//beneficio
	public void reiniciarNewPostulanteBeneficio(){
		newPostulanteBeneficio = new PostulanteBeneficio();
		newPostulanteBeneficio.setId(new PostulanteBeneficioPK());
		
		newPostulanteBeneficio.setPostulante(postulante);
		newPostulanteBeneficio.setBeneficio(new Beneficio());
	}
	
	public void savePostulanteBeneficio(){		
		logger.info("agregando beneficio");
		
		boolean agregar = true;
		
		for (PostulanteBeneficio postulanteBeneficio: postulante.getPostulanteBeneficios()) {
			if(postulanteBeneficio.getBeneficio().getBeneId() == newPostulanteBeneficio.getBeneficio().getBeneId()){
				agregar = false;
				break;
			}
		}
		
		if(agregar){
			postulante.addPostulanteBeneficio(newPostulanteBeneficio);
		}
	}
	
	public void deletePostulanteBeneficio(){
		logger.info("removiendo beneficio");
		
		if(newPostulanteBeneficio != null){
			postulante.getPostulanteBeneficios().remove(newPostulanteBeneficio);
		}
	}
	
	///actividades de mantenimiento
	
	public void reiniciarConocimiento(){
		newConocimiento = new Conocimiento();
		newConocimiento.setConoEstado("B");		
	}
	
	public void saveConocimiento(){
		newConocimiento.setSubcategoriaConocimiento(selectedSubcategoriaConocimiento);
		conocimientoService.addConocimiento(newConocimiento);
		
		conocimientos = conocimientoService.getConocimientos();
	}
	
	public void reiniciarPuesto(){
		newPuesto = new Puesto();
		newPuesto.setPuesEstado("B");
		newPuesto.setNivelPuesto(new NivelPuesto());
	}
	
	public void savePuesto(){
		puestoService.addPuesto(newPuesto);
		
		puestos = puestoService.getPuestos();
	}

	public Postulante getPostulante() {
		return postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

	public Residencia getNewResidencia() {
		return newResidencia;
	}

	public void setNewResidencia(Residencia newResidencia) {
		this.newResidencia = newResidencia;
	}

	public Telefono getNewTelefono() {
		return newTelefono;
	}

	public void setNewTelefono(Telefono newTelefono) {
		this.newTelefono = newTelefono;
	}	

	public ArrayList<Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(ArrayList<Puesto> puestos) {
		this.puestos = puestos;
	}

	public ArrayList<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(ArrayList<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
	}

	public ArrayList<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(ArrayList<Distrito> distritos) {
		this.distritos = distritos;
	}

	public Departamento getSelectedDepartamento() {
		return selectedDepartamento;
	}

	public void setSelectedDepartamento(Departamento selectedDepartamento) {
		this.selectedDepartamento = selectedDepartamento;
	}

	public Provincia getSelectedProvincia() {
		return selectedProvincia;
	}

	public void setSelectedProvincia(Provincia selectedProvincia) {
		this.selectedProvincia = selectedProvincia;
	}

	public Distrito getSelectedDistrito() {
		return selectedDistrito;
	}

	public void setSelectedDistrito(Distrito selectedDistrito) {
		this.selectedDistrito = selectedDistrito;
	}

	public ArrayList<EstadoCivil> getEstadosCiviles() {
		return estadosCiviles;
	}

	public void setEstadosCiviles(ArrayList<EstadoCivil> estadosCiviles) {
		this.estadosCiviles = estadosCiviles;
	}

	public ArrayList<Conocimiento> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(ArrayList<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public PostulanteConocimiento getNewPostulanteConocimiento() {
		return newPostulanteConocimiento;
	}

	public void setNewPostulanteConocimiento(
			PostulanteConocimiento newPostulanteConocimiento) {
		this.newPostulanteConocimiento = newPostulanteConocimiento;
	}

	public ExperienciaLaboral getNewExperienciaLaboral() {
		return newExperienciaLaboral;
	}

	public void setNewExperienciaLaboral(ExperienciaLaboral newExperienciaLaboral) {
		this.newExperienciaLaboral = newExperienciaLaboral;
	}

	public ArrayList<Beneficio> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(ArrayList<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}

	public ArrayList<NivelConocimiento> getNivelesConocimiento() {
		return nivelesConocimiento;
	}

	public void setNivelesConocimiento(
			ArrayList<NivelConocimiento> nivelesConocimiento) {
		this.nivelesConocimiento = nivelesConocimiento;
	}

	public ArrayList<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(ArrayList<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public ActividadAcademica getNewActividadAcademica() {
		return newActividadAcademica;
	}

	public void setNewActividadAcademica(ActividadAcademica newActividadAcademica) {
		this.newActividadAcademica = newActividadAcademica;
	}

	public ArrayList<Estudio> getEstudios() {
		return estudios;
	}

	public void setEstudios(ArrayList<Estudio> estudios) {
		this.estudios = estudios;
	}

	public PostulanteIdioma getNewPostulanteIdioma() {
		return newPostulanteIdioma;
	}

	public void setNewPostulanteIdioma(PostulanteIdioma newPostulanteIdioma) {
		this.newPostulanteIdioma = newPostulanteIdioma;
	}

	public ArrayList<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(ArrayList<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public ArrayList<NivelIdioma> getNivelesIdioma() {
		return nivelesIdioma;
	}

	public void setNivelesIdioma(ArrayList<NivelIdioma> nivelesIdioma) {
		this.nivelesIdioma = nivelesIdioma;
	}

	public ArrayList<TipoHorario> getTiposHorario() {
		return tiposHorario;
	}

	public void setTiposHorario(ArrayList<TipoHorario> tiposHorario) {
		this.tiposHorario = tiposHorario;
	}

	public ArrayList<NivelPuesto> getNivelesPuesto() {
		return nivelesPuesto;
	}

	public void setNivelesPuesto(ArrayList<NivelPuesto> nivelesPuesto) {
		this.nivelesPuesto = nivelesPuesto;
	}

	public ArrayList<DimensionOrganizacion> getDimensionesOrganizacion() {
		return dimensionesOrganizacion;
	}

	public void setDimensionesOrganizacion(
			ArrayList<DimensionOrganizacion> dimensionesOrganizacion) {
		this.dimensionesOrganizacion = dimensionesOrganizacion;
	}

	public ArrayList<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(ArrayList<Sector> sectores) {
		this.sectores = sectores;
	}

	public PostulanteBeneficio getNewPostulanteBeneficio() {
		return newPostulanteBeneficio;
	}

	public void setNewPostulanteBeneficio(PostulanteBeneficio newPostulanteBeneficio) {
		this.newPostulanteBeneficio = newPostulanteBeneficio;
	}

	public Conocimiento getNewConocimiento() {
		return newConocimiento;
	}

	public void setNewConocimiento(Conocimiento newConocimiento) {
		this.newConocimiento = newConocimiento;
	}

	public CategoriaConocimiento getSelectedCategoriaConocimiento() {
		return selectedCategoriaConocimiento;
	}

	public void setSelectedCategoriaConocimiento(
			CategoriaConocimiento selectedCategoriaConocimiento) {
		this.selectedCategoriaConocimiento = selectedCategoriaConocimiento;
	}

	public SubcategoriaConocimiento getSelectedSubcategoriaConocimiento() {
		return selectedSubcategoriaConocimiento;
	}

	public void setSelectedSubcategoriaConocimiento(
			SubcategoriaConocimiento selectedSubcategoriaConocimiento) {
		this.selectedSubcategoriaConocimiento = selectedSubcategoriaConocimiento;
	}

	public ArrayList<CategoriaConocimiento> getCategoriasConocimiento() {
		return categoriasConocimiento;
	}

	public void setCategoriasConocimiento(
			ArrayList<CategoriaConocimiento> categoriasConocimiento) {
		this.categoriasConocimiento = categoriasConocimiento;
	}

	public ArrayList<SubcategoriaConocimiento> getSubcategoriasConocimiento() {
		return subcategoriasConocimiento;
	}

	public void setSubcategoriasConocimiento(
			ArrayList<SubcategoriaConocimiento> subcategoriasConocimiento) {
		this.subcategoriasConocimiento = subcategoriasConocimiento;
	}

	public Puesto getNewPuesto() {
		return newPuesto;
	}

	public void setNewPuesto(Puesto newPuesto) {
		this.newPuesto = newPuesto;
	}		
}
