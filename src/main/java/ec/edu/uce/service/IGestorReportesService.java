package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.ReporteClienteVIPTO;
import ec.edu.uce.modelo.ReporteReservas;
import ec.edu.uce.modelo.ReporteVehiculosVIPD;

public interface IGestorReportesService {

	List<ReporteReservas> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

	List<ReporteClienteVIPTO> reporteClientesVIP();

	List<ReporteVehiculosVIPD> reporteVehiculodVIP(String mes, String anio);
}
