package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the criterio_seleccion database table.
 * 
 */
@Entity
@Table(name="criterio_seleccion")
@NamedQuery(name="CriterioSeleccion.findAll", query="SELECT c FROM CriterioSeleccion c")
public class CriterioSeleccion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int crseId;
	private String crseCode;
	private String crseRNombreColumna;
	private String crseRNombreTabla;
	private List<CategoriaValor> categoriaValors;
	private TipoCriterio tipoCriterio;
	private TipoPerfil tipoPerfil;

	public CriterioSeleccion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="crse_id")
	public int getCrseId() {
		return this.crseId;
	}

	public void setCrseId(int crseId) {
		this.crseId = crseId;
	}


	@Column(name="crse_code")
	public String getCrseCode() {
		return this.crseCode;
	}

	public void setCrseCode(String crseCode) {
		this.crseCode = crseCode;
	}


	@Column(name="crse_r_nombre_columna")
	public String getCrseRNombreColumna() {
		return this.crseRNombreColumna;
	}

	public void setCrseRNombreColumna(String crseRNombreColumna) {
		this.crseRNombreColumna = crseRNombreColumna;
	}


	@Column(name="crse_r_nombre_tabla")
	public String getCrseRNombreTabla() {
		return this.crseRNombreTabla;
	}

	public void setCrseRNombreTabla(String crseRNombreTabla) {
		this.crseRNombreTabla = crseRNombreTabla;
	}


	//bi-directional many-to-one association to CategoriaValor
	@OneToMany(mappedBy="criterioSeleccion")
	public List<CategoriaValor> getCategoriaValors() {
		return this.categoriaValors;
	}

	public void setCategoriaValors(List<CategoriaValor> categoriaValors) {
		this.categoriaValors = categoriaValors;
	}

	public CategoriaValor addCategoriaValor(CategoriaValor categoriaValor) {
		getCategoriaValors().add(categoriaValor);
		categoriaValor.setCriterioSeleccion(this);

		return categoriaValor;
	}

	public CategoriaValor removeCategoriaValor(CategoriaValor categoriaValor) {
		getCategoriaValors().remove(categoriaValor);
		categoriaValor.setCriterioSeleccion(null);

		return categoriaValor;
	}


	//bi-directional many-to-one association to TipoCriterio
	@ManyToOne
	@JoinColumn(name="fk_ticr_id")
	public TipoCriterio getTipoCriterio() {
		return this.tipoCriterio;
	}

	public void setTipoCriterio(TipoCriterio tipoCriterio) {
		this.tipoCriterio = tipoCriterio;
	}


	//bi-directional many-to-one association to TipoPerfil
	@ManyToOne
	@JoinColumn(name="fk_tipe_id")
	public TipoPerfil getTipoPerfil() {
		return this.tipoPerfil;
	}

	public void setTipoPerfil(TipoPerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}

}