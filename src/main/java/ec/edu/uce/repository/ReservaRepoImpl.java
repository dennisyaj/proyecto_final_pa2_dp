package ec.edu.uce.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ReporteReservas;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

@Transactional
@Repository
public class ReservaRepoImpl implements IReservaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Reserva reserva) {
		this.entityManager.persist(reserva);
	}

	@Override
	public void actualizar(Reserva reserva) {
		this.entityManager.merge(reserva);
	}

	@Override
	public Reserva buscar(Integer id) {
		return this.entityManager.find(Reserva.class, id);

	}

	@Override
	public void borrar(Integer id) {
		this.entityManager.remove(this.buscar(id));
	}

	@Override
	public Reserva buscarPorNumero(String numero) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r WHERE r.numero=: numero",
				Reserva.class);
		myQuery.setParameter("numero", numero);
		return myQuery.getSingleResult();
	}

	@Override
	public List<ReporteReservas> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		TypedQuery<ReporteReservas> myQuery = this.entityManager.createQuery(
				"SELECT NEW ec.edu.uce.modelo.ReporteReservas(r.id,r.numero,r.fechaInicio,r.fechaFinal,r.estado,c.apellido,c.cedula,v.placa,v.marca,v.valorPorDia)FROM Reserva r JOIN r.clienteReserva c JOIN r.vehiculoReservado v WHERE r.fechaInicio>=:fechaInicio AND  r.fechaFinal<=:fechaFinal",
				ReporteReservas.class);
		myQuery.setParameter("fechaInicio", fechaInicio);
		myQuery.setParameter("fechaFinal", fechaFinal);
		return myQuery.getResultList();
	}

}
