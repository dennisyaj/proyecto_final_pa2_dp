package ec.edu.uce.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.repository.IClienteRepo;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepo iClienteRepo;

	@Override
	@Transactional
	public void insertar(Cliente cliente) {
		this.iClienteRepo.insertar(cliente);
	}

	@Override
	@Transactional
	public void actualizar(Cliente cliente) {
		this.iClienteRepo.actualizar(cliente);
	}

	@Override
	public Cliente buscar(Integer id) {
		return this.iClienteRepo.buscar(id);
	}

	@Override
	@Transactional
	public void borrar(Integer id) {
		this.iClienteRepo.borrar(id);
	}

	@Override
	public Cliente buscarCedula(String cedula) {
		return this.iClienteRepo.buscarCedula(cedula);
	}

}
