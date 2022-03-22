package ec.edu.uce.service;

import java.time.LocalDateTime;

public interface IGestorReportesService {

	void reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

	void reporteClientesVIP();

	void reporteVehiculodVIP(String mes, String anio);
}
