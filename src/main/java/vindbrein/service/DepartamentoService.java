package vindbrein.service;

import java.util.ArrayList;

import vindbrein.domain.model.Departamento;

public interface DepartamentoService {
	
	public void addDepartamento(Departamento Departamento);

	public void updateDepartamento(Departamento Departamento);

	public void deleteDepartamento(Departamento Departamento);

	public Departamento getDepartamentoById(int id);

	public ArrayList<Departamento> getDepartamentos();

}
