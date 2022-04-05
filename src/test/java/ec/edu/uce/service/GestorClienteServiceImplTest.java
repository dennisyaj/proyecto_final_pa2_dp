package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ReservarVehiculoTO;
import ec.edu.uce.modelo.Vehiculo;

@SpringBootTest
@Rollback(true)
@Transactional
class GestorClienteServiceImplTest {

	@Autowired
	private IGestorClienteService iGestorClienteService;

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IReservaService iReservaService;

	@Test
	void testBuscarVehiculosDisponibles() {

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("P");
		vehiculo.setModelo("P");
		vehiculo.setPlaca("A");
		vehiculo.setEstado("ND");
		vehiculo.setValorPorDia(new BigDecimal(80));

		this.iVehiculoService.insertar(vehiculo);
		List<String> lista = this.iGestorClienteService.buscarVehiculosDisponibles("P", "P");
		assertEquals("Placa: A - Modelo: P - Estado: No Disponible - Valor por dia: $80", lista.get(0));

	}

	@Test
	void testRegistrarCliente() {

		Cliente cliente = new Cliente();
		cliente.setApellido("Parra");
		cliente.setNombre("Pedro");
		cliente.setCedula("999999999");

		this.iGestorClienteService.registrarCliente(cliente);
		assertEquals("Pedro", this.iClienteService.buscarCedula("999999999").getNombre());
	}

	@Test
	void testRegistrarCliente2() {

		Cliente cliente = new Cliente();
		cliente.setApellido("Parra");
		cliente.setNombre("Pedro");
		cliente.setCedula("999999900");

		this.iGestorClienteService.registrarCliente(cliente);
		assertThat(this.iClienteService.buscarCedula("999999900").getNombre()).isNotNull();
	}

	@Test
	void testRegistrarCliente3() {

		Cliente cliente = new Cliente();
		cliente.setApellido("Parra");
		cliente.setNombre("Pedro");
		cliente.setCedula("999999000");

		this.iGestorClienteService.registrarCliente(cliente);
		assertEquals("C", this.iClienteService.buscarCedula("999999000").getTipoRegistro().toString());
	}
}
