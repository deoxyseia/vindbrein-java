package vindbrein.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the match_result database table.
 * 
 */
@Entity
@Table(name="match_result")
@NamedQuery(name="MatchResult.findAll", query="SELECT m FROM MatchResult m")
public class MatchResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private MatchResultPK id;	
	private Date mareFechaOfertaSeleccionada;
	private Date mareFechaPostulanteSeleccionado;
	private byte mareFlagOfertaRecomendada;
	private byte mareFlagOfertaSeleccionada;
	private byte mareFlagOfertaVisitada;
	private byte mareFlagPostulanteRecomendado;
	private byte mareFlagPostulanteSeleccionado;
	private byte mareFlagPostulanteVisitado;
	private OfertaLaboral ofertaLaboral;
	private Postulante postulante;

	public MatchResult() {
	}


	@EmbeddedId
	public MatchResultPK getId() {
		return this.id;
	}

	public void setId(MatchResultPK id) {
		this.id = id;
	}
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mare_fecha_oferta_seleccionada")
	public Date getMareFechaOfertaSeleccionada() {
		return this.mareFechaOfertaSeleccionada;
	}

	public void setMareFechaOfertaSeleccionada(Date mareFechaOfertaSeleccionada) {
		this.mareFechaOfertaSeleccionada = mareFechaOfertaSeleccionada;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mare_fecha_postulante_seleccionado")
	public Date getMareFechaPostulanteSeleccionado() {
		return this.mareFechaPostulanteSeleccionado;
	}

	public void setMareFechaPostulanteSeleccionado(Date mareFechaPostulanteSeleccionado) {
		this.mareFechaPostulanteSeleccionado = mareFechaPostulanteSeleccionado;
	}


	@Column(name="mare_flag_oferta_recomendada")
	public byte getMareFlagOfertaRecomendada() {
		return this.mareFlagOfertaRecomendada;
	}

	public void setMareFlagOfertaRecomendada(byte mareFlagOfertaRecomendada) {
		this.mareFlagOfertaRecomendada = mareFlagOfertaRecomendada;
	}


	@Column(name="mare_flag_oferta_seleccionada")
	public byte getMareFlagOfertaSeleccionada() {
		return this.mareFlagOfertaSeleccionada;
	}

	public void setMareFlagOfertaSeleccionada(byte mareFlagOfertaSeleccionada) {
		this.mareFlagOfertaSeleccionada = mareFlagOfertaSeleccionada;
	}


	@Column(name="mare_flag_oferta_visitada")
	public byte getMareFlagOfertaVisitada() {
		return this.mareFlagOfertaVisitada;
	}

	public void setMareFlagOfertaVisitada(byte mareFlagOfertaVisitada) {
		this.mareFlagOfertaVisitada = mareFlagOfertaVisitada;
	}


	@Column(name="mare_flag_postulante_recomendado")
	public byte getMareFlagPostulanteRecomendado() {
		return this.mareFlagPostulanteRecomendado;
	}

	public void setMareFlagPostulanteRecomendado(byte mareFlagPostulanteRecomendado) {
		this.mareFlagPostulanteRecomendado = mareFlagPostulanteRecomendado;
	}


	@Column(name="mare_flag_postulante_seleccionado")
	public byte getMareFlagPostulanteSeleccionado() {
		return this.mareFlagPostulanteSeleccionado;
	}

	public void setMareFlagPostulanteSeleccionado(byte mareFlagPostulanteSeleccionado) {
		this.mareFlagPostulanteSeleccionado = mareFlagPostulanteSeleccionado;
	}


	@Column(name="mare_flag_postulante_visitado")
	public byte getMareFlagPostulanteVisitado() {
		return this.mareFlagPostulanteVisitado;
	}

	public void setMareFlagPostulanteVisitado(byte mareFlagPostulanteVisitado) {
		this.mareFlagPostulanteVisitado = mareFlagPostulanteVisitado;
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


	//bi-directional many-to-one association to Postulante
	@ManyToOne
	@JoinColumn(name="fk_post_id", insertable=false, updatable=false)
	public Postulante getPostulante() {
		return this.postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}
	
	@Transient
	public boolean getOfertaVisitada(){
		if(mareFlagOfertaVisitada == 1){
			return true;
		}else{
			return false;
		}
	}
	
	@Transient
	public boolean getOfertaRecomendada(){
		if(mareFlagOfertaRecomendada == 1){
			return true;
		}else{
			return false;
		}
	}
	
	@Transient
	public boolean getOfertaSeleccionada(){
		if(mareFlagOfertaSeleccionada == 1){
			return true;
		}else{
			return false;
		}
	}
	
	@Transient
	public boolean getPostulanteVisitado(){
		if(mareFlagPostulanteVisitado == 1){
			return true;
		}else{
			return false;
		}
	}
	
	@Transient
	public boolean getPostulanteRecomendado(){
		if(mareFlagPostulanteRecomendado == 1){
			return true;
		}else{
			return false;
		}
	}
	
	@Transient
	public boolean getPostulanteSeleccionado(){
		if(mareFlagPostulanteSeleccionado == 1){
			return true;
		}else{
			return false;
		}
	}

}