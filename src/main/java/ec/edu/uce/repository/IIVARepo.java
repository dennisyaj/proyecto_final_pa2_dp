package ec.edu.uce.repository;

import ec.edu.uce.modelo.IVA;

public interface IIVARepo {

	void insertar(IVA iva);

	void actualizar(IVA iva);

	IVA buscar(Integer id);

	void borrar(Integer id);

}
