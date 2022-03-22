package ec.edu.uce.repository;

import ec.edu.uce.modelo.Vehiculo;

public interface IVehiculoRepo {

	void insertar(Vehiculo vehiculo);

	void actualizar(Vehiculo vehiculo);

	void borrar(Integer id);

	Vehiculo buscar(Integer id);

	Vehiculo buscarPorPlaca(String placa);
}
