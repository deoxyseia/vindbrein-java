package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the preferencia_puesto_laboral database table.
 * 
 */
@Entity
@Table(name="preferencia_puesto_laboral")
@NamedQuery(name="PreferenciaPuestoLaboral.findAll", query="SELECT p FROM PreferenciaPuestoLaboral p")
public class PreferenciaPuestoLaboral implements Serializable {
	private static final long serialVersionUID = 1L;
	private int prpuId;
	private String prpuEdadMaxima;
	private String prpuEdadMinima;
	private String prpuSexo;
	private int prpuTiempoExperiencia;
	private Distrito distrito;
	private PuestoLaboral puestoLaboral;
	private TipoPuesto tipoPuesto;
	private List<PuestoPrefConocim> puestoPrefConocims;
	private List<PuestoPrefCurso> puestoPrefCursos;
	private List<Estudio> estudios;
	private List<PuestoPrefIdioma> puestoPrefIdiomas;

	public PreferenciaPuestoLaboral() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prpu_id")
	public int getPrpuId() {
		return this.prpuId;
	}

	public void setPrpuId(int prpuId) {
		this.prpuId = prpuId;
	}


	@Column(name="prpu_edad_maxima")
	public String getPrpuEdadMaxima() {
		return this.prpuEdadMaxima;
	}

	public void setPrpuEdadMaxima(String prpuEdadMaxima) {
		this.prpuEdadMaxima = prpuEdadMaxima;
	}


	@Column(name="prpu_edad_minima")
	public String getPrpuEdadMinima() {
		return this.prpuEdadMinima;
	}

	public void setPrpuEdadMinima(String prpuEdadMinima) {
		this.prpuEdadMinima = prpuEdadMinima;
	}


	@Column(name="prpu_sexo")
	public String getPrpuSexo() {
		return this.prpuSexo;
	}

	public void setPrpuSexo(String prpuSexo) {
		this.prpuSexo = prpuSexo;
	}


	@Column(name="prpu_tiempo_experiencia")
	public int getPrpuTiempoExperiencia() {
		return this.prpuTiempoExperiencia;
	}

	public void setPrpuTiempoExperiencia(int prpuTiempoExperiencia) {
		this.prpuTiempoExperiencia = prpuTiempoExperiencia;
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


	//bi-directional many-to-one association to PuestoLaboral
	@ManyToOne
	@JoinColumn(name="fk_pula_id")
	public PuestoLaboral getPuestoLaboral() {
		return this.puestoLaboral;
	}

	public void setPuestoLaboral(PuestoLaboral puestoLaboral) {
		this.puestoLaboral = puestoLaboral;
	}


	//bi-directional many-to-one association to TipoPuesto
	@ManyToOne
	@JoinColumn(name="fk_tipu_id")
	public TipoPuesto getTipoPuesto() {
		return this.tipoPuesto;
	}

	public void setTipoPuesto(TipoPuesto tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}


	//bi-directional many-to-one association to PuestoPrefConocim
	@OneToMany(mappedBy="preferenciaPuestoLaboral")
	public List<PuestoPrefConocim> getPuestoPrefConocims() {
		return this.puestoPrefConocims;
	}

	public void setPuestoPrefConocims(List<PuestoPrefConocim> puestoPrefConocims) {
		this.puestoPrefConocims = puestoPrefConocims;
	}

	public PuestoPrefConocim addPuestoPrefConocim(PuestoPrefConocim puestoPrefConocim) {
		getPuestoPrefConocims().add(puestoPrefConocim);
		puestoPrefConocim.setPreferenciaPuestoLaboral(this);

		return puestoPrefConocim;
	}

	public PuestoPrefConocim removePuestoPrefConocim(PuestoPrefConocim puestoPrefConocim) {
		getPuestoPrefConocims().remove(puestoPrefConocim);
		puestoPrefConocim.setPreferenciaPuestoLaboral(null);

		return puestoPrefConocim;
	}


	//bi-directional many-to-one association to PuestoPrefCurso
	@OneToMany(mappedBy="preferenciaPuestoLaboral")
	public List<PuestoPrefCurso> getPuestoPrefCursos() {
		return this.puestoPrefCursos;
	}

	public void setPuestoPrefCursos(List<PuestoPrefCurso> puestoPrefCursos) {
		this.puestoPrefCursos = puestoPrefCursos;
	}

	public PuestoPrefCurso addPuestoPrefCurso(PuestoPrefCurso puestoPrefCurso) {
		getPuestoPrefCursos().add(puestoPrefCurso);
		puestoPrefCurso.setPreferenciaPuestoLaboral(this);

		return puestoPrefCurso;
	}

	public PuestoPrefCurso removePuestoPrefCurso(PuestoPrefCurso puestoPrefCurso) {
		getPuestoPrefCursos().remove(puestoPrefCurso);
		puestoPrefCurso.setPreferenciaPuestoLaboral(null);

		return puestoPrefCurso;
	}


	//bi-directional many-to-many association to Estudio
	@ManyToMany
	@JoinTable(
		name="puesto_pref_estudio"
		, joinColumns={
			@JoinColumn(name="fk_prpu_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_estu_id")
			}
		)
	public List<Estudio> getEstudios() {
		return this.estudios;
	}

	public void setEstudios(List<Estudio> estudios) {
		this.estudios = estudios;
	}


	//bi-directional many-to-one association to PuestoPrefIdioma
	@OneToMany(mappedBy="preferenciaPuestoLaboral")
	public List<PuestoPrefIdioma> getPuestoPrefIdiomas() {
		return this.puestoPrefIdiomas;
	}

	public void setPuestoPrefIdiomas(List<PuestoPrefIdioma> puestoPrefIdiomas) {
		this.puestoPrefIdiomas = puestoPrefIdiomas;
	}

	public PuestoPrefIdioma addPuestoPrefIdioma(PuestoPrefIdioma puestoPrefIdioma) {
		getPuestoPrefIdiomas().add(puestoPrefIdioma);
		puestoPrefIdioma.setPreferenciaPuestoLaboral(this);

		return puestoPrefIdioma;
	}

	public PuestoPrefIdioma removePuestoPrefIdioma(PuestoPrefIdioma puestoPrefIdioma) {
		getPuestoPrefIdiomas().remove(puestoPrefIdioma);
		puestoPrefIdioma.setPreferenciaPuestoLaboral(null);

		return puestoPrefIdioma;
	}

}