package ec.edu.uce.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Vehiculo;

@Repository
@Transactional
public class VehiculoRepoImpl implements IVehiculoRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		this.entityManager.merge(vehiculo);
	}

	@Override
	public void borrar(Integer id) {
		this.entityManager.remove(this.buscar(id));
	}

	@Override
	public Vehiculo buscar(Integer id) {
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public Vehiculo buscarPorPlaca(String placa) {
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.placa=: placa",
				Vehiculo.class);
		myQuery.setParameter("placa", placa);
		return null;
	}

}