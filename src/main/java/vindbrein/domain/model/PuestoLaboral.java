package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the puesto_laboral database table.
 * 
 */
@Entity
@Table(name="puesto_laboral")
@NamedQuery(name="PuestoLaboral.findAll", query="SELECT p FROM PuestoLaboral p")
public class PuestoLaboral implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pulaId;
	private String pulaDescripcion;
	private int pulaIdPuestoH;
	private int pulaIdPuestoP;
	private int pulaIdPuestoS;
	private String pulaNombre;
	private int pulaSalario;
	private List<BeneficioLaboral> beneficioLaborals;
	private List<MatchResult> matchResults;
	private List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals;
	private TipoHorario tipoHorario;
	private SucursalPuesto sucursalPuesto;

	public PuestoLaboral() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pula_id")
	public int getPulaId() {
		return this.pulaId;
	}

	public void setPulaId(int pulaId) {
		this.pulaId = pulaId;
	}


	@Column(name="pula_descripcion")
	public String getPulaDescripcion() {
		return this.pulaDescripcion;
	}

	public void setPulaDescripcion(String pulaDescripcion) {
		this.pulaDescripcion = pulaDescripcion;
	}


	@Column(name="pula_id_puesto_h")
	public int getPulaIdPuestoH() {
		return this.pulaIdPuestoH;
	}

	public void setPulaIdPuestoH(int pulaIdPuestoH) {
		this.pulaIdPuestoH = pulaIdPuestoH;
	}


	@Column(name="pula_id_puesto_p")
	public int getPulaIdPuestoP() {
		return this.pulaIdPuestoP;
	}

	public void setPulaIdPuestoP(int pulaIdPuestoP) {
		this.pulaIdPuestoP = pulaIdPuestoP;
	}


	@Column(name="pula_id_puesto_s")
	public int getPulaIdPuestoS() {
		return this.pulaIdPuestoS;
	}

	public void setPulaIdPuestoS(int pulaIdPuestoS) {
		this.pulaIdPuestoS = pulaIdPuestoS;
	}


	@Column(name="pula_nombre")
	public String getPulaNombre() {
		return this.pulaNombre;
	}

	public void setPulaNombre(String pulaNombre) {
		this.pulaNombre = pulaNombre;
	}


	@Column(name="pula_salario")
	public int getPulaSalario() {
		return this.pulaSalario;
	}

	public void setPulaSalario(int pulaSalario) {
		this.pulaSalario = pulaSalario;
	}


	//bi-directional many-to-many association to BeneficioLaboral
	@ManyToMany(mappedBy="puestoLaborals")
	public List<BeneficioLaboral> getBeneficioLaborals() {
		return this.beneficioLaborals;
	}

	public void setBeneficioLaborals(List<BeneficioLaboral> beneficioLaborals) {
		this.beneficioLaborals = beneficioLaborals;
	}


	//bi-directional many-to-one association to MatchResult
	@OneToMany(mappedBy="puestoLaboral")
	public List<MatchResult> getMatchResults() {
		return this.matchResults;
	}

	public void setMatchResults(List<MatchResult> matchResults) {
		this.matchResults = matchResults;
	}

	public MatchResult addMatchResult(MatchResult matchResult) {
		getMatchResults().add(matchResult);
		matchResult.setPuestoLaboral(this);

		return matchResult;
	}

	public MatchResult removeMatchResult(MatchResult matchResult) {
		getMatchResults().remove(matchResult);
		matchResult.setPuestoLaboral(null);

		return matchResult;
	}


	//bi-directional many-to-one association to PreferenciaPuestoLaboral
	@OneToMany(mappedBy="puestoLaboral")
	public List<PreferenciaPuestoLaboral> getPreferenciaPuestoLaborals() {
		return this.preferenciaPuestoLaborals;
	}

	public void setPreferenciaPuestoLaborals(List<PreferenciaPuestoLaboral> preferenciaPuestoLaborals) {
		this.preferenciaPuestoLaborals = preferenciaPuestoLaborals;
	}

	public PreferenciaPuestoLaboral addPreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		getPreferenciaPuestoLaborals().add(preferenciaPuestoLaboral);
		preferenciaPuestoLaboral.setPuestoLaboral(this);

		return preferenciaPuestoLaboral;
	}

	public PreferenciaPuestoLaboral removePreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		getPreferenciaPuestoLaborals().remove(preferenciaPuestoLaboral);
		preferenciaPuestoLaboral.setPuestoLaboral(null);

		return preferenciaPuestoLaboral;
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


	//bi-directional many-to-one association to SucursalPuesto
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="fk_puesto_id", referencedColumnName="fk_pues_id"),
		@JoinColumn(name="fk_sucu_id", referencedColumnName="fk_sucu_id")
		})
	public SucursalPuesto getSucursalPuesto() {
		return this.sucursalPuesto;
	}

	public void setSucursalPuesto(SucursalPuesto sucursalPuesto) {
		this.sucursalPuesto = sucursalPuesto;
	}

}