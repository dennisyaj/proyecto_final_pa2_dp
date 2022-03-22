package ec.edu.uce.service;

import ec.edu.uce.modelo.Vehiculo;

public interface IVehiculoService {
	
	void insertar(Vehiculo vehiculo);

	void actualizar(Vehiculo vehiculo);

	Vehiculo buscar(Integer id);

	void borrar(Integer id);
}
