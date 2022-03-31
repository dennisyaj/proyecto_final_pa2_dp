package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

@Service
public class GestorClienteServiceImpl implements IGestorClienteService {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IIVAService iivaService;

	@Autowired
	private IReservaService iReservaService;

	@Autowired
	private IPagoService iPagoService;

	private static final Logger LOG = LoggerFactory.getLogger(GestorClienteServiceImpl.class);

	@Override
	public List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo) {
		List<Vehiculo> lista = this.iVehiculoService.buscarMarcaModelo(marca, modelo);
		return lista;
	}

	@Override
	@Transactional
	public void reservarVehiculo(String placa, String cedulaCliente, LocalDateTime fechaInicio,
			LocalDateTime fechaFinal) {

		Vehiculo vehiculo = this.iVehiculoService.buscarPorPlaca(placa);

		if (disponibilidad(vehiculo, fechaInicio)) {

			long diasAlquiler = Duration.between(fechaInicio, fechaFinal).toDays();
			Cliente cliente = this.iClienteService.buscarCedula(cedulaCliente);

			BigDecimal valorSubTotal = vehiculo.getValorPorDia().multiply((new BigDecimal(diasAlquiler)));
			BigDecimal valorIVA = (this.iivaService.buscar(1).getValor().multiply(valorSubTotal))
					.divide(new BigDecimal(100));
			BigDecimal valorTotalPagar = valorSubTotal.add(valorIVA);

			Reserva reserva = new Reserva();
			reserva.setClienteReserva(cliente);
			reserva.setEstado('G');
			reserva.setFechaFinal(fechaFinal);
			reserva.setFechaInicio(fechaInicio);
			reserva.setNumero(cliente.getApellido() + "-" + vehiculo.getPlaca() + "-" + fechaInicio.getYear() + "-"
					+ fechaInicio.getMonthValue() + "-" + fechaInicio.getDayOfMonth());

			List<Vehiculo> listaVehiculos = new ArrayList<>();
			vehiculo.setReservaVehiculo(reserva);
			listaVehiculos.add(vehiculo);
			reserva.setVehiculoReservado(listaVehiculos);

//
//			Pago pago = new Pago();
//			pago.setFechaCobro(LocalDateTime.now());
//			pago.setPagoReserva(reserva);
//			pago.setTarjeta("A123456");
//			pago.setValorIVA(valorIVA);
//			pago.setValorSubTotal(valorSubTotal);
//			pago.setValorTotalAPagar(valorTotalPagar);

//			this.iPagoService.insertar(pago);
			try {
				this.iReservaService.insertar(reserva);
			} catch (Exception e) {
				LOG.info("Aqui el" + e.getMessage());
				e.printStackTrace();
			}

			// this.iVehiculoService.actualizar(vehiculo);
		}

	}

	@Override
	@Transactional
	public void registrarCliente(Cliente cliente) {
		cliente.setTipoRegistro('C');
		this.iClienteService.insertar(cliente);
	}

	public boolean disponibilidad(Vehiculo vehiculo, LocalDateTime fechaInicio) {
		if (vehiculo.getReservaVehiculo() == null) {
			return true;
		} else {
			Reserva reserva = this.iReservaService.buscar(vehiculo.getReservaVehiculo().getId());
			if (fechaInicio.isAfter(reserva.getFechaFinal())) {
				return true;
			}
			return false;

		}
	}
}
