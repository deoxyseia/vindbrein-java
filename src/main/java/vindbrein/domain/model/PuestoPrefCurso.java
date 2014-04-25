package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the puesto_pref_curso database table.
 * 
 */
@Entity
@Table(name="puesto_pref_curso")
@NamedQuery(name="PuestoPrefCurso.findAll", query="SELECT p FROM PuestoPrefCurso p")
public class PuestoPrefCurso implements Serializable {
	private static final long serialVersionUID = 1L;
	private PuestoPrefCursoPK id;
	private int notaMinima;
	private Curso curso;
	private PreferenciaPuestoLaboral preferenciaPuestoLaboral;

	public PuestoPrefCurso() {
	}


	@EmbeddedId
	public PuestoPrefCursoPK getId() {
		return this.id;
	}

	public void setId(PuestoPrefCursoPK id) {
		this.id = id;
	}


	@Column(name="nota_minima")
	public int getNotaMinima() {
		return this.notaMinima;
	}

	public void setNotaMinima(int notaMinima) {
		this.notaMinima = notaMinima;
	}


	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="fk_curs_id", insertable=false, updatable=false)
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	//bi-directional many-to-one association to PreferenciaPuestoLaboral
	@ManyToOne
	@JoinColumn(name="fk_prpu_id", insertable=false, updatable=false)
	public PreferenciaPuestoLaboral getPreferenciaPuestoLaboral() {
		return this.preferenciaPuestoLaboral;
	}

	public void setPreferenciaPuestoLaboral(PreferenciaPuestoLaboral preferenciaPuestoLaboral) {
		this.preferenciaPuestoLaboral = preferenciaPuestoLaboral;
	}

}