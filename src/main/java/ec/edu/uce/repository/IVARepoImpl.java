package ec.edu.uce.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.IVA;

@Repository
@Transactional
public class IVARepoImpl implements IIVARepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(IVA iva) {
		this.entityManager.persist(iva);
	}

	@Override
	public void actualizar(IVA iva) {
		this.entityManager.merge(iva);
	}

	@Override
	public IVA buscar(Integer id) {
		return this.entityManager.find(IVA.class, id);
	}

	@Override
	public void borrar(Integer id) {
		this.entityManager.remove(this.buscar(id));
	}

}
