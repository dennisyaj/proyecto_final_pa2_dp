package ec.edu.uce.service;

import ec.edu.uce.modelo.Cliente;

public interface IClienteService {

	void insertar(Cliente cliente);

	void actualizar(Cliente cliente);

	Cliente buscar(Integer id);

	Cliente buscarCedula(String cedula);

	void borrar(Integer id);
}
