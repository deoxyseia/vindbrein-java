package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
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
	private String postDni;
	private int postEdad;
	private Date postFecNacimiento;
	private int postIdProfH;
	private int postIdProfP;
	private int postIdProfS;
	private String postNombres;
	private String postSexo;
	private List<ActividadAcademica> actividadAcademicas;	
	private List<ExperienciaLaboral> experienciaLaborals;
	private List<MatchResult> matchResults;
	private EstadoCivil estadoCivil;
	private List<PostulanteConocimiento> postulanteConocimientos;
	private List<PostulanteCurso> postulanteCursos;
	private List<PostulanteIdioma> postulanteIdiomas;
	private List<PreferenciaPostulante> preferenciaPostulantes;
	private List<Residencia> residencias;
	private List<Telefono> telefonos;
	private List<Usuario> usuarios;

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
	@Column(name="post_fec_nacimiento")
	public Date getPostFecNacimiento() {
		return this.postFecNacimiento;
	}

	public void setPostFecNacimiento(Date postFecNacimiento) {
		this.postFecNacimiento = postFecNacimiento;
	}


	@Column(name="post_id_prof_h")
	public int getPostIdProfH() {
		return this.postIdProfH;
	}

	public void setPostIdProfH(int postIdProfH) {
		this.postIdProfH = postIdProfH;
	}


	@Column(name="post_id_prof_p")
	public int getPostIdProfP() {
		return this.postIdProfP;
	}

	public void setPostIdProfP(int postIdProfP) {
		this.postIdProfP = postIdProfP;
	}


	@Column(name="post_id_prof_s")
	public int getPostIdProfS() {
		return this.postIdProfS;
	}

	public void setPostIdProfS(int postIdProfS) {
		this.postIdProfS = postIdProfS;
	}


	@Column(name="post_nombres")
	public String getPostNombres() {
		return this.postNombres;
	}

	public void setPostNombres(String postNombres) {
		this.postNombres = postNombres;
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
	public List<ActividadAcademica> getActividadAcademicas() {
		return this.actividadAcademicas;
	}

	public void setActividadAcademicas(List<ActividadAcademica> actividadAcademicas) {
		this.actividadAcademicas = actividadAcademicas;
	}

	public ActividadAcademica addActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadAcademicas().add(actividadAcademica);
		actividadAcademica.setPostulante(this);

		return actividadAcademica;
	}

	public ActividadAcademica removeActividadAcademica(ActividadAcademica actividadAcademica) {
		getActividadAcademicas().remove(actividadAcademica);
		actividadAcademica.setPostulante(null);

		return actividadAcademica;
	}
	
	//bi-directional many-to-one association to ExperienciaLaboral
	@OneToMany(mappedBy="postulante")
	public List<ExperienciaLaboral> getExperienciaLaborals() {
		return this.experienciaLaborals;
	}

	public void setExperienciaLaborals(List<ExperienciaLaboral> experienciaLaborals) {
		this.experienciaLaborals = experienciaLaborals;
	}

	public ExperienciaLaboral addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaborals().add(experienciaLaboral);
		experienciaLaboral.setPostulante(this);

		return experienciaLaboral;
	}

	public ExperienciaLaboral removeExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciaLaborals().remove(experienciaLaboral);
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


	//bi-directional many-to-one association to EstadoCivil
	@ManyToOne
	@JoinColumn(name="fk_esci_id")
	public EstadoCivil getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
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


	//bi-directional many-to-one association to PreferenciaPostulante
	@OneToMany(mappedBy="postulante")
	public List<PreferenciaPostulante> getPreferenciaPostulantes() {
		return this.preferenciaPostulantes;
	}

	public void setPreferenciaPostulantes(List<PreferenciaPostulante> preferenciaPostulantes) {
		this.preferenciaPostulantes = preferenciaPostulantes;
	}

	public PreferenciaPostulante addPreferenciaPostulante(PreferenciaPostulante preferenciaPostulante) {
		getPreferenciaPostulantes().add(preferenciaPostulante);
		preferenciaPostulante.setPostulante(this);

		return preferenciaPostulante;
	}

	public PreferenciaPostulante removePreferenciaPostulante(PreferenciaPostulante preferenciaPostulante) {
		getPreferenciaPostulantes().remove(preferenciaPostulante);
		preferenciaPostulante.setPostulante(null);

		return preferenciaPostulante;
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
	@OneToMany(mappedBy="postulante")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPostulante(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPostulante(null);

		return usuario;
	}

}