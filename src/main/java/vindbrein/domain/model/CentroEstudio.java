package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the centro_estudios database table.
 * 
 */
@Entity
@Table(name="centro_estudios")
@NamedQuery(name="CentroEstudio.findAll", query="SELECT c FROM CentroEstudio c")
public class CentroEstudio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ceesId;
	private String ceesCode;
	private String ceesNombre;
	private CentroEstudio centroEstudio;
	private List<CentroEstudio> centroEstudios;
	private TipoCentroEstudio tipoCentroEstudio;
	private List<Estudio> estudios;

	public CentroEstudio() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cees_id")
	public int getCeesId() {
		return this.ceesId;
	}

	public void setCeesId(int ceesId) {
		this.ceesId = ceesId;
	}


	@Column(name="cees_code")
	public String getCeesCode() {
		return this.ceesCode;
	}

	public void setCeesCode(String ceesCode) {
		this.ceesCode = ceesCode;
	}


	@Column(name="cees_nombre")
	public String getCeesNombre() {
		return this.ceesNombre;
	}

	public void setCeesNombre(String ceesNombre) {
		this.ceesNombre = ceesNombre;
	}


	//bi-directional many-to-one association to CentroEstudio
	@ManyToOne
	@JoinColumn(name="fk_cees_padre")
	public CentroEstudio getCentroEstudio() {
		return this.centroEstudio;
	}

	public void setCentroEstudio(CentroEstudio centroEstudio) {
		this.centroEstudio = centroEstudio;
	}


	//bi-directional many-to-one association to CentroEstudio
	@OneToMany(mappedBy="centroEstudio")
	public List<CentroEstudio> getCentroEstudios() {
		return this.centroEstudios;
	}

	public void setCentroEstudios(List<CentroEstudio> centroEstudios) {
		this.centroEstudios = centroEstudios;
	}

	public CentroEstudio addCentroEstudio(CentroEstudio centroEstudio) {
		getCentroEstudios().add(centroEstudio);
		centroEstudio.setCentroEstudio(this);

		return centroEstudio;
	}

	public CentroEstudio removeCentroEstudio(CentroEstudio centroEstudio) {
		getCentroEstudios().remove(centroEstudio);
		centroEstudio.setCentroEstudio(null);

		return centroEstudio;
	}


	//bi-directional many-to-one association to TipoCentroEstudio
	@ManyToOne
	@JoinColumn(name="fk_tice_id")
	public TipoCentroEstudio getTipoCentroEstudio() {
		return this.tipoCentroEstudio;
	}

	public void setTipoCentroEstudio(TipoCentroEstudio tipoCentroEstudio) {
		this.tipoCentroEstudio = tipoCentroEstudio;
	}


	//bi-directional many-to-one association to Estudio
	@OneToMany(mappedBy="centroEstudio")
	public List<Estudio> getEstudios() {
		return this.estudios;
	}

	public void setEstudios(List<Estudio> estudios) {
		this.estudios = estudios;
	}

	public Estudio addEstudio(Estudio estudio) {
		getEstudios().add(estudio);
		estudio.setCentroEstudio(this);

		return estudio;
	}

	public Estudio removeEstudio(Estudio estudio) {
		getEstudios().remove(estudio);
		estudio.setCentroEstudio(null);

		return estudio;
	}

}