package vindbrein.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the oferta_curso database table.
 * 
 */
@Entity
@Table(name="oferta_curso")
@NamedQuery(name="OfertaCurso.findAll", query="SELECT o FROM OfertaCurso o")
public class OfertaCurso implements Serializable {
	private static final long serialVersionUID = 1L;
	private OfertaCursoPK id;
	private BigDecimal ofcuNota;
	private Curso curso;
	private OfertaLaboral ofertaLaboral;

	public OfertaCurso() {
	}


	@EmbeddedId
	public OfertaCursoPK getId() {
		return this.id;
	}

	public void setId(OfertaCursoPK id) {
		this.id = id;
	}


	@Column(name="ofcu_nota")
	public BigDecimal getOfcuNota() {
		return this.ofcuNota;
	}

	public void setOfcuNota(BigDecimal ofcuNota) {
		this.ofcuNota = ofcuNota;
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


	//bi-directional many-to-one association to OfertaLaboral
	@ManyToOne
	@JoinColumn(name="fk_ofla_id", insertable=false, updatable=false)
	public OfertaLaboral getOfertaLaboral() {
		return this.ofertaLaboral;
	}

	public void setOfertaLaboral(OfertaLaboral ofertaLaboral) {
		this.ofertaLaboral = ofertaLaboral;
	}

}