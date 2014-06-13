package vindbrein.domain.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the postulante database table.
 * 
 */
@Entity
@Table(name="postulante")
@NamedQuery(name="Postulante.findAll", query="SELECT p FROM Postulante p")
public class Postulante implements Serializable {
	private static final long serialVersionUID = 1L;
	private int postId;
	private String postApellidoMaterno;
	private String postApellidoPaterno;
	private BigInteger postCodigoEstudiante;
	private String postDni;
	private int postEdad;
	private Date postFechaNacimiento;
	private String postIdH;
	private String postIdP;
	private String postIdS;
	private String postNombres;
	private int postSalario;
	private String postSexo;
	private List<ActividadAcademica> actividadesAcademicas;
	private List<ExperienciaLaboral> experienciasLaborales;
	private List<MatchResult> matchResults;
	private DimensionOrganizacion dimensionOrganizacion;
	private EstadoCivil estadoCivil;
	private Sector sector;
	private TipoHorario tipoHorario;
	private List<PostulanteConocimiento> postulanteConocimientos;
	private List<PostulanteCurso> postulanteCursos;
	private List<PostulanteIdioma> postulanteIdiomas;
	private List<Residencia> residencias;
	private List<Telefono> telefonos;
	private Usuario usuario;
	private NivelPuesto nivelPuesto;
	private List<PostulanteBeneficio> postulanteBeneficios;
	private BigDecimal score;

	public Postulante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	public int getPostId() {
		return this.postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}


	@Column(name="post_apellido_materno")
	public String getPostApellidoMaterno() {
		return this.postApellidoMaterno;
	}

	public void setPostApellidoMaterno(String postApellidoMaterno) {
		this.postApellidoMaterno = postApellidoMaterno;
	}


	@Column(name="post_apellido_paterno")
	public String getPostApellidoPaterno() {
		return this.postApellidoPaterno;
	}

	public void setPostApellidoPaterno(String postApellidoPaterno) {
		this.postApellidoPaterno = postApellidoPaterno;
	}


	@Column(name="post_codigo_estudiante")
	public BigInteger getPostCodigoEstudiante() {
		return this.postCodigoEstudiante;
	}

	public void setPostCodigoEstudiante(BigInteger postCodigoEstudiante) {
		this.postCodigoEstudiante = postCodigoEstudiante;
	}


	@Column(name="post_dni")
	public String getPostDni() {
		return this.postDni;
	}

	public void setPostDni(String postDni) {
		this.postDni = postDni;
	}


	@Column(name="post_edad")
	public int getPostEdad() {
		return this.postEdad;
	}

	public void setPostEdad(int postEdad) {
		this.postEdad = postEdad;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="post_fecha_nacimiento")
	public Date getPostFechaNacimiento() {
		return this.postFechaNacimiento;
	}

	public void setPostFechaNacimiento(Date postFechaNacimiento) {
		this.postFechaNacimiento = postFechaNacimiento;
	}


	@Column(name="post_id_h")
	public String getPostIdH() {
		return this.postIdH;
	}

	public void setPostIdH(String postIdH) {
		this.postIdH = postIdH;
	}


	@Column(name="post_id_p")
	public String getPostIdP() {
		return this.postIdP;
	}

	public void setPostIdP(String postIdP) {
		this.postIdP = postIdP;
	}


	@Column(name="post_id_s")
	public String getPostIdS() {
		return this.postIdS;
	}

	public void setPostIdS(String postIdS) {
		this.postIdS = postIdS;
	}


	@Column(name="post_nombres")
	public String getPostNombres() {
		return this.postNombres;
	}

	public void setPostNombres(String postNombres) {
		this.postNombres = postNombres;
	}


	@Column(name="post_salario")
	public int getPostSalario() {
		return this.postSalario;
	}

	public void setPostSalario(int postSalario) {
		this.postSalario = postSalario;
	}


	@Column(name="post_sexo")
	public String getPostSexo() {
		return this.postSexo;
	}

	public void setPostSexo(String postSexo) {
		this.postSexo = postSexo;
	}


	//bi-directional many-to-one association to ActividadAcademica
	@OneToMany(mappedBy="postulante")
	public List<ActividadAcademica> getActividadesAcademicas() {
		return this.actividadesAcademicas;
	}

	public void setActividadesAcademicas(List<ActividadAcademica> actividadesAcademicas) {
		this.actividadesAcademicas = actividadesAcademicas;
	}

	public ActividadAcademica addActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadesAcademicas().add(actividadAcademica);
		actividadAcademica.setPostulante(this);

		return actividadAcademica;
	}

	public ActividadAcademica removeActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadesAcademicas().remove(actividadAcademica);
		actividadAcademica.setPostulante(null);

		return actividadAcademica;
	}


	//bi-directional many-to-one association to ExperienciaLaboral
	@OneToMany(mappedBy="postulante")
	public List<ExperienciaLaboral> getExperienciasLaborales() {
		return this.experienciasLaborales;
	}

	public void setExperienciasLaborales(List<ExperienciaLaboral> experienciasLaborales) {
		this.experienciasLaborales = experienciasLaborales;
	}

	public ExperienciaLaboral addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciasLaborales().add(experienciaLaboral);
		experienciaLaboral.setPostulante(this);

		return experienciaLaboral;
	}

	public ExperienciaLaboral removeExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciasLaborales().remove(experienciaLaboral);
		experienciaLaboral.setPostulante(null);

		return experienciaLaboral;
	}


	//bi-directional many-to-one association to MatchResult
	@OneToMany(mappedBy="postulante")
	public List<MatchResult> getMatchResults() {
		return this.matchResults;
	}

	public void setMatchResults(List<MatchResult> matchResults) {
		this.matchResults = matchResults;
	}

	public MatchResult addMatchResult(MatchResult matchResult) {
		getMatchResults().add(matchResult);
		matchResult.setPostulante(this);

		return matchResult;
	}

	public MatchResult removeMatchResult(MatchResult matchResult) {
		getMatchResults().remove(matchResult);
		matchResult.setPostulante(null);

		return matchResult;
	}


	//bi-directional many-to-one association to DimensionOrganizacion
	@ManyToOne
	@JoinColumn(name="fk_dior_id")
	public DimensionOrganizacion getDimensionOrganizacion() {
		return this.dimensionOrganizacion;
	}

	public void setDimensionOrganizacion(DimensionOrganizacion dimensionOrganizacion) {
		this.dimensionOrganizacion = dimensionOrganizacion;
	}


	//bi-directional many-to-one association to EstadoCivil
	@ManyToOne
	@JoinColumn(name="fk_esci_id")
	public EstadoCivil getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	//bi-directional many-to-one association to Sector
	@ManyToOne
	@JoinColumn(name="fk_sect_id")
	public Sector getSector() {
		return this.sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}


	//bi-directional many-to-one association to TipoHorario
	@ManyToOne
	@JoinColumn(name="fk_tiho_id")
	public TipoHorario getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}


	//bi-directional many-to-one association to PostulanteConocimiento
	@OneToMany(mappedBy="postulante")
	public List<PostulanteConocimiento> getPostulanteConocimientos() {
		return this.postulanteConocimientos;
	}

	public void setPostulanteConocimientos(List<PostulanteConocimiento> postulanteConocimientos) {
		this.postulanteConocimientos = postulanteConocimientos;
	}

	public PostulanteConocimiento addPostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getPostulanteConocimientos().add(postulanteConocimiento);
		postulanteConocimiento.setPostulante(this);

		return postulanteConocimiento;
	}

	public PostulanteConocimiento removePostulanteConocimiento(PostulanteConocimiento postulanteConocimiento) {
		getPostulanteConocimientos().remove(postulanteConocimiento);
		postulanteConocimiento.setPostulante(null);

		return postulanteConocimiento;
	}


	//bi-directional many-to-one association to PostulanteCurso
	@OneToMany(mappedBy="postulante")
	public List<PostulanteCurso> getPostulanteCursos() {
		return this.postulanteCursos;
	}

	public void setPostulanteCursos(List<PostulanteCurso> postulanteCursos) {
		this.postulanteCursos = postulanteCursos;
	}

	public PostulanteCurso addPostulanteCurso(PostulanteCurso postulanteCurso) {
		getPostulanteCursos().add(postulanteCurso);
		postulanteCurso.setPostulante(this);

		return postulanteCurso;
	}

	public PostulanteCurso removePostulanteCurso(PostulanteCurso postulanteCurso) {
		getPostulanteCursos().remove(postulanteCurso);
		postulanteCurso.setPostulante(null);

		return postulanteCurso;
	}


	//bi-directional many-to-one association to PostulanteIdioma
	@OneToMany(mappedBy="postulante")
	public List<PostulanteIdioma> getPostulanteIdiomas() {
		return this.postulanteIdiomas;
	}

	public void setPostulanteIdiomas(List<PostulanteIdioma> postulanteIdiomas) {
		this.postulanteIdiomas = postulanteIdiomas;
	}

	public PostulanteIdioma addPostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomas().add(postulanteIdioma);
		postulanteIdioma.setPostulante(this);

		return postulanteIdioma;
	}

	public PostulanteIdioma removePostulanteIdioma(PostulanteIdioma postulanteIdioma) {
		getPostulanteIdiomas().remove(postulanteIdioma);
		postulanteIdioma.setPostulante(null);

		return postulanteIdioma;
	}


	//bi-directional many-to-one association to Residencia
	@OneToMany(mappedBy="postulante")
	public List<Residencia> getResidencias() {
		return this.residencias;
	}

	public void setResidencias(List<Residencia> residencias) {
		this.residencias = residencias;
	}

	public Residencia addResidencia(Residencia residencia) {
		getResidencias().add(residencia);
		residencia.setPostulante(this);

		return residencia;
	}

	public Residencia removeResidencia(Residencia residencia) {
		getResidencias().remove(residencia);
		residencia.setPostulante(null);

		return residencia;
	}


	//bi-directional many-to-one association to Telefono
	@OneToMany(mappedBy="postulante")
	public List<Telefono> getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public Telefono addTelefono(Telefono telefono) {
		getTelefonos().add(telefono);
		telefono.setPostulante(this);

		return telefono;
	}

	public Telefono removeTelefono(Telefono telefono) {
		getTelefonos().remove(telefono);
		telefono.setPostulante(null);

		return telefono;
	}


	//bi-directional many-to-one association to Usuario
	@OneToOne(mappedBy="postulante")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	//bi-directional many-to-one association to NivelPuesto
	@ManyToOne
	@JoinColumn(name="fk_nipu_id")
	public NivelPuesto getNivelPuesto() {
		return this.nivelPuesto;
	}

	public void setNivelPuesto(NivelPuesto nivelPuesto) {
		this.nivelPuesto = nivelPuesto;
	}

	//bi-directional many-to-one association to PostulanteBeneficio
	@OneToMany(mappedBy="postulante")
	public List<PostulanteBeneficio> getPostulanteBeneficios() {
		return this.postulanteBeneficios;
	}

	public void setPostulanteBeneficios(List<PostulanteBeneficio> postulanteBeneficios) {
		this.postulanteBeneficios = postulanteBeneficios;
	}

	public PostulanteBeneficio addPostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		getPostulanteBeneficios().add(postulanteBeneficio);
		postulanteBeneficio.setPostulante(this);

		return postulanteBeneficio;
	}

	public PostulanteBeneficio removePostulanteBeneficio(PostulanteBeneficio postulanteBeneficio) {
		getPostulanteBeneficios().remove(postulanteBeneficio);
		postulanteBeneficio.setPostulante(null);

		return postulanteBeneficio;
	}

	@Transient
	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	@Transient
	public String getScorePlain(){
		return score.toPlainString();
	}
}