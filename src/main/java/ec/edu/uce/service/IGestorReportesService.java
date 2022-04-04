package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ReporteReservas;
import ec.edu.uce.modelo.Reserva;

public interface IGestorReportesService {

	List<ReporteReservas> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

	List<Cliente> reporteClientesVIP();

	void reporteVehiculodVIP(String mes, String anio);
}
