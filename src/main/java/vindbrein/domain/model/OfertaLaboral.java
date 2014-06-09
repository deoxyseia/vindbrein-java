package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the oferta_laboral database table.
 * 
 */
@Entity
@Table(name="oferta_laboral")
@NamedQuery(name="OfertaLaboral.findAll", query="SELECT o FROM OfertaLaboral o")
public class OfertaLaboral implements Serializable {
	private static final long serialVersionUID = 1L;
	private int oflaId;
	private String oflaDescripcion;
	private int oflaEdad;
	private String oflaIdH;
	private String oflaIdP;
	private String oflaIdS;
	private int oflaSalario;
	private String oflaSexo;
	private int oflaTiempoExperiencia;
	private String oflaTitulo;
	private List<MatchResult> matchResults;
	private List<OfertaBeneficio> ofertaBeneficios;
	private List<OfertaCentroEstudio> ofertaCentroEstudios;
	private List<OfertaConocimiento> ofertaConocimientos;
	private List<OfertaCurso> ofertaCursos;
	private List<OfertaEstudio> ofertaEstudios;
	private List<OfertaIdioma> ofertaIdiomas;
	private TipoHorario tipoHorario;
	private Distrito distrito;
	private EstadoCivil estadoCivil;
	private OrganizacionPuesto organizacionPuesto;

	public OfertaLaboral() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ofla_id")
	public int getOflaId() {
		return this.oflaId;
	}

	public void setOflaId(int oflaId) {
		this.oflaId = oflaId;
	}


	@Column(name="ofla_descripcion")
	public String getOflaDescripcion() {
		return this.oflaDescripcion;
	}

	public void setOflaDescripcion(String oflaDescripcion) {
		this.oflaDescripcion = oflaDescripcion;
	}


	@Column(name="ofla_edad")
	public int getOflaEdad() {
		return this.oflaEdad;
	}

	public void setOflaEdad(int oflaEdad) {
		this.oflaEdad = oflaEdad;
	}


	@Column(name="ofla_id_h")
	public String getOflaIdH() {
		return this.oflaIdH;
	}

	public void setOflaIdH(String oflaIdH) {
		this.oflaIdH = oflaIdH;
	}


	@Column(name="ofla_id_p")
	public String getOflaIdP() {
		return this.oflaIdP;
	}

	public void setOflaIdP(String oflaIdP) {
		this.oflaIdP = oflaIdP;
	}


	@Column(name="ofla_id_s")
	public String getOflaIdS() {
		return this.oflaIdS;
	}

	public void setOflaIdS(String oflaIdS) {
		this.oflaIdS = oflaIdS;
	}


	@Column(name="ofla_salario")
	public int getOflaSalario() {
		return this.oflaSalario;
	}

	public void setOflaSalario(int oflaSalario) {
		this.oflaSalario = oflaSalario;
	}


	@Column(name="ofla_sexo")
	public String getOflaSexo() {
		return this.oflaSexo;
	}

	public void setOflaSexo(String oflaSexo) {
		this.oflaSexo = oflaSexo;
	}


	@Column(name="ofla_tiempo_experiencia")
	public int getOflaTiempoExperiencia() {
		return this.oflaTiempoExperiencia;
	}

	public void setOflaTiempoExperiencia(int oflaTiempoExperiencia) {
		this.oflaTiempoExperiencia = oflaTiempoExperiencia;
	}


	@Column(name="ofla_titulo")
	public String getOflaTitulo() {
		return this.oflaTitulo;
	}

	public void setOflaTitulo(String oflaTitulo) {
		this.oflaTitulo = oflaTitulo;
	}


	//bi-directional many-to-one association to MatchResult
	@OneToMany(mappedBy="ofertaLaboral")
	public List<MatchResult> getMatchResults() {
		return this.matchResults;
	}

	public void setMatchResults(List<MatchResult> matchResults) {
		this.matchResults = matchResults;
	}

	public MatchResult addMatchResult(MatchResult matchResult) {
		getMatchResults().add(matchResult);
		matchResult.setOfertaLaboral(this);

		return matchResult;
	}

	public MatchResult removeMatchResult(MatchResult matchResult) {
		getMatchResults().remove(matchResult);
		matchResult.setOfertaLaboral(null);

		return matchResult;
	}


	//bi-directional many-to-one association to OfertaBeneficio
	@OneToMany(mappedBy="ofertaLaboral")
	public List<OfertaBeneficio> getOfertaBeneficios() {
		return this.ofertaBeneficios;
	}

	public void setOfertaBeneficios(List<OfertaBeneficio> ofertaBeneficios) {
		this.ofertaBeneficios = ofertaBeneficios;
	}

	public OfertaBeneficio addOfertaBeneficio(OfertaBeneficio ofertaBeneficio) {
		getOfertaBeneficios().add(ofertaBeneficio);
		ofertaBeneficio.setOfertaLaboral(this);

		return ofertaBeneficio;
	}

	public OfertaBeneficio removeOfertaBeneficio(OfertaBeneficio ofertaBeneficio) {
		getOfertaBeneficios().remove(ofertaBeneficio);
		ofertaBeneficio.setOfertaLaboral(null);

		return ofertaBeneficio;
	}


	//bi-directional many-to-one association to OfertaCentroEstudio
	@OneToMany(mappedBy="ofertaLaboral")
	public List<OfertaCentroEstudio> getOfertaCentroEstudios() {
		return this.ofertaCentroEstudios;
	}

	public void setOfertaCentroEstudios(List<OfertaCentroEstudio> ofertaCentroEstudios) {
		this.ofertaCentroEstudios = ofertaCentroEstudios;
	}

	public OfertaCentroEstudio addOfertaCentroEstudio(OfertaCentroEstudio ofertaCentroEstudio) {
		getOfertaCentroEstudios().add(ofertaCentroEstudio);
		ofertaCentroEstudio.setOfertaLaboral(this);

		return ofertaCentroEstudio;
	}

	public OfertaCentroEstudio removeOfertaCentroEstudio(OfertaCentroEstudio ofertaCentroEstudio) {
		getOfertaCentroEstudios().remove(ofertaCentroEstudio);
		ofertaCentroEstudio.setOfertaLaboral(null);

		return ofertaCentroEstudio;
	}


	//bi-directional many-to-one association to OfertaConocimiento
	@OneToMany(mappedBy="ofertaLaboral")
	public List<OfertaConocimiento> getOfertaConocimientos() {
		return this.ofertaConocimientos;
	}

	public void setOfertaConocimientos(List<OfertaConocimiento> ofertaConocimientos) {
		this.ofertaConocimientos = ofertaConocimientos;
	}

	public OfertaConocimiento addOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getOfertaConocimientos().add(ofertaConocimiento);
		ofertaConocimiento.setOfertaLaboral(this);

		return ofertaConocimiento;
	}

	public OfertaConocimiento removeOfertaConocimiento(OfertaConocimiento ofertaConocimiento) {
		getOfertaConocimientos().remove(ofertaConocimiento);
		ofertaConocimiento.setOfertaLaboral(null);

		return ofertaConocimiento;
	}


	//bi-directional many-to-one association to OfertaCurso
	@OneToMany(mappedBy="ofertaLaboral")
	public List<OfertaCurso> getOfertaCursos() {
		return this.ofertaCursos;
	}

	public void setOfertaCursos(List<OfertaCurso> ofertaCursos) {
		this.ofertaCursos = ofertaCursos;
	}

	public OfertaCurso addOfertaCurso(OfertaCurso ofertaCurso) {
		getOfertaCursos().add(ofertaCurso);
		ofertaCurso.setOfertaLaboral(this);

		return ofertaCurso;
	}

	public OfertaCurso removeOfertaCurso(OfertaCurso ofertaCurso) {
		getOfertaCursos().remove(ofertaCurso);
		ofertaCurso.setOfertaLaboral(null);

		return ofertaCurso;
	}


	//bi-directional many-to-one association to OfertaEstudio
	@OneToMany(mappedBy="ofertaLaboral")
	public List<OfertaEstudio> getOfertaEstudios() {
		return this.ofertaEstudios;
	}

	public void setOfertaEstudios(List<OfertaEstudio> ofertaEstudios) {
		this.ofertaEstudios = ofertaEstudios;
	}

	public OfertaEstudio addOfertaEstudio(OfertaEstudio ofertaEstudio) {
		getOfertaEstudios().add(ofertaEstudio);
		ofertaEstudio.setOfertaLaboral(this);

		return ofertaEstudio;
	}

	public OfertaEstudio removeOfertaEstudio(OfertaEstudio ofertaEstudio) {
		getOfertaEstudios().remove(ofertaEstudio);
		ofertaEstudio.setOfertaLaboral(null);

		return ofertaEstudio;
	}


	//bi-directional many-to-one association to OfertaIdioma
	@OneToMany(mappedBy="ofertaLaboral")
	public List<OfertaIdioma> getOfertaIdiomas() {
		return this.ofertaIdiomas;
	}

	public void setOfertaIdiomas(List<OfertaIdioma> ofertaIdiomas) {
		this.ofertaIdiomas = ofertaIdiomas;
	}

	public OfertaIdioma addOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getOfertaIdiomas().add(ofertaIdioma);
		ofertaIdioma.setOfertaLaboral(this);

		return ofertaIdioma;
	}

	public OfertaIdioma removeOfertaIdioma(OfertaIdioma ofertaIdioma) {
		getOfertaIdiomas().remove(ofertaIdioma);
		ofertaIdioma.setOfertaLaboral(null);

		return ofertaIdioma;
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


	//bi-directional many-to-one association to Distrito
	@ManyToOne
	@JoinColumn(name="fk_dist_id")
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
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


	//bi-directional many-to-one association to OrganizacionPuesto
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="fk_orga_id", referencedColumnName="fk_orga_id"),
		@JoinColumn(name="fk_pues_id", referencedColumnName="fk_pues_id")
		})
	public OrganizacionPuesto getOrganizacionPuesto() {
		return this.organizacionPuesto;
	}

	public void setOrganizacionPuesto(OrganizacionPuesto organizacionPuesto) {
		this.organizacionPuesto = organizacionPuesto;
	}

}