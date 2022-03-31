package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.IVA;
import ec.edu.uce.repository.IIVARepo;

@Service
public class IVAServiceImpl implements IIVAService {

	@Autowired
	private IIVARepo iIVARepo;

	@Override
	public void insertar(IVA iva) {
		this.iIVARepo.insertar(iva);
	}

	@Override
	public void actualizar(IVA iva) {
		this.iIVARepo.actualizar(iva);
	}

	@Override
	public IVA buscar(Integer id) {
		return this.iIVARepo.buscar(id);
	}

	@Override
	public void borrar(Integer id) {
		this.iIVARepo.borrar(id);
	}

}
