package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ReporteClienteTO;
import ec.edu.uce.modelo.ReporteReservas;
import ec.edu.uce.modelo.Reserva;

@Service
public class GestorReportesServiceImpl implements IGestorReportesService {

	@Autowired
	private IReservaService iReservaService;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<ReporteReservas> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		return this.iReservaService.reporteReservas(fechaInicio, fechaFinal);
	}

	@Override
	public List<Cliente> reporteClientesVIP() {
		Map<Cliente, Long> employeesByCity = this.iReservaService.todasReservas().stream()
				.collect(Collectors.groupingBy(Reserva::getClienteReserva, Collectors.counting()));
		return null;
	}

	@Override
	public void reporteVehiculodVIP(String mes, String anio) {
		// TODO Auto-generated method stub

	}

}
