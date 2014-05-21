package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_centro_estudios database table.
 * 
 */
@Entity
@Table(name="tipo_centro_estudio")
@NamedQuery(name="TipoCentroEstudio.findAll", query="SELECT t FROM TipoCentroEstudio t")
public class TipoCentroEstudio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ticeId;
	private String ticeNombre;
	private List<CentroEstudio> centroEstudios;

	public TipoCentroEstudio() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tice_id")
	public int getTiceId() {
		return this.ticeId;
	}

	public void setTiceId(int ticeId) {
		this.ticeId = ticeId;
	}


	@Column(name="tice_nombre")
	public String getTiceNombre() {
		return this.ticeNombre;
	}

	public void setTiceNombre(String ticeNombre) {
		this.ticeNombre = ticeNombre;
	}


	//bi-directional many-to-one association to CentroEstudio
	@OneToMany(mappedBy="tipoCentroEstudio")
	public List<CentroEstudio> getCentroEstudios() {
		return this.centroEstudios;
	}

	public void setCentroEstudios(List<CentroEstudio> centroEstudios) {
		this.centroEstudios = centroEstudios;
	}

	public CentroEstudio addCentroEstudio(CentroEstudio centroEstudio) {
		getCentroEstudios().add(centroEstudio);
		centroEstudio.setTipoCentroEstudio(this);

		return centroEstudio;
	}

	public CentroEstudio removeCentroEstudio(CentroEstudio centroEstudio) {
		getCentroEstudios().remove(centroEstudio);
		centroEstudio.setTipoCentroEstudio(null);

		return centroEstudio;
	}

}