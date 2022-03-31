package ec.edu.uce.service;

import ec.edu.uce.modelo.IVA;

public interface IIVAService {

	void insertar(IVA iva);

	void actualizar(IVA iva);

	IVA buscar(Integer id);

	void borrar(Integer id);

}
