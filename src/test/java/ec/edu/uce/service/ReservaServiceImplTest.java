package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import ec.edu.uce.modelo.Pago;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

@SpringBootTest
@Rollback(true)
@Transactional
class ReservaServiceImplTest {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IReservaService iReservaService;

	@Test
	void testInsertar() {
		Reserva reserva = new Reserva();
		reserva.setEstado('G');
		reserva.setFechaInicio(LocalDateTime.now());
		reserva.setFechaFinal(LocalDateTime.now());
		reserva.setNumero("123456");
		this.iReservaService.insertar(reserva);
		assertThat(this.iReservaService.buscarPorNumero("123456")).isNotNull();
	}

	@Test
	void testActualizar() {
		Reserva reserva = this.iReservaService.buscarPorNumero("1234567");
		reserva.setEstado('E');
		this.iReservaService.actualizar(reserva);

		assertEquals('E', this.iReservaService.buscarPorNumero("1234567").getEstado());
	}

	@Test
	void testBuscarPorNumero() {
		assertThat(this.iReservaService.buscarPorNumero("1234567")).isNotNull();
	}

	@Test
	void testReporteReservas() {
		assertThat(this.iReservaService.reporteReservas(LocalDateTime.of(2022, Month.APRIL, 10, 0, 0),
				LocalDateTime.of(2022, Month.APRIL, 12, 0, 0))).isNotNull();
	}

	@Test
	void testBuscarPorVehiculo() {

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setId(1);
		vehiculo.setMarca("KIA");
		vehiculo.setModelo("HASH");
		vehiculo.setPlaca("xxx-xxx");

		assertThat(this.iReservaService.buscarPorVehiculo(vehiculo)).isNotNull();
	}

	@Test
	void testTodasReservas() {
		assertThat(this.iReservaService.todasReservas()).isNotNull();
	}

	@Test
	void testBuscarMesAnio() {
		assertTrue(this.iReservaService.buscarMesAnio("01", "2022").stream().count() > 0);
	}

	@BeforeEach
	void datos() {

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("KIA");
		vehiculo.setModelo("HASH");
		vehiculo.setPlaca("xxx-xxx");
		this.iVehiculoService.insertar(vehiculo);
		Pago pago = new Pago();
		pago.setValorIVA(new BigDecimal(50));
		pago.setValorTotalAPagar(new BigDecimal(50));

		Reserva reserva = new Reserva();
		reserva.setEstado('G');
		reserva.setFechaInicio(LocalDateTime.of(2022, Month.APRIL, 10, 0, 0));
		reserva.setFechaFinal(LocalDateTime.of(2022, Month.APRIL, 12, 0, 0));
		reserva.setNumero("1234567");
		reserva.setVehiculoReservado(vehiculo);
		reserva.setPagos(pago);
		this.iReservaService.insertar(reserva);
	}
}
