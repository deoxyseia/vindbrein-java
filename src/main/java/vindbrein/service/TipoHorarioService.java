package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.TipoHorario;

public interface TipoHorarioService {
	
	public void addTipoHorario(TipoHorario tipoHorario);

	public void updateTipoHorario(TipoHorario tipoHorario);

	public void deleteTipoHorario(TipoHorario tipoHorario);

	public TipoHorario getTipoHorarioById(int id);

	public ArrayList<TipoHorario> getTiposHorario();

}
