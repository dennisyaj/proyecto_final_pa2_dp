package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.ReporteReservas;
import ec.edu.uce.modelo.Reserva;

public interface IReservaRepo {

	void insertar(Reserva reserva);

	void actualizar(Reserva reserva);

	Reserva buscar(Integer id);

	void borrar(Integer id);

	Reserva buscarPorNumero(String numero);

	List<ReporteReservas> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal);
}
