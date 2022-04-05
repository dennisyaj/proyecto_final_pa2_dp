package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Vehiculo;

@SpringBootTest
@Rollback(true)
@Transactional
class GestorEmpleadoServiceImplTest {

	@Autowired
	private IGestorEmpleadoService iGestorEmpleadoService;

	@Autowired
	private IClienteService iClienteService;

	@Test
	void testRegistrarCliente() {

		Cliente cliente = new Cliente();
		cliente.setApellido("Parra");
		cliente.setNombre("Pedro");
		cliente.setCedula("999999990");
		this.iGestorEmpleadoService.registrarCliente(cliente);

		assertEquals("E", this.iClienteService.buscarCedula("999999990").getTipoRegistro().toString());
	}

	@Test
	void testRegistrarCliente2() {

		Cliente cliente = new Cliente();
		cliente.setApellido("Parra");
		cliente.setNombre("Pedro");
		cliente.setCedula("999999990");
		this.iGestorEmpleadoService.registrarCliente(cliente);

		assertThat(this.iClienteService.buscarCedula("999999990")).isNotNull();
	}

	@Test
	void testBuscarCliente() {
		assertEquals("Pedro", this.iGestorEmpleadoService.buscarCliente("999999999").getNombre().toString());
	}

	@Test
	void testIngresarVehiculo() {

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("KIA");
		vehiculo.setModelo("HASH");
		vehiculo.setPlaca("xxx");

		this.iGestorEmpleadoService.ingresarVehiculo(vehiculo);
		Vehiculo v = this.iGestorEmpleadoService.buscarVehiculo("xxx");
		assertEquals("KIA", v.getMarca());
	}

	@Test
	void testBuscarVehiculo() {
		assertThat(this.iGestorEmpleadoService.buscarVehiculo("xxx-xxx")).isNotNull();
	}

	@BeforeEach
	void datos() {

		Cliente cliente = new Cliente();
		cliente.setApellido("Parra");
		cliente.setNombre("Pedro");
		cliente.setCedula("999999999");

		this.iGestorEmpleadoService.registrarCliente(cliente);

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("KIA");
		vehiculo.setModelo("HASH");
		vehiculo.setPlaca("xxx-xxx");

		this.iGestorEmpleadoService.ingresarVehiculo(vehiculo);

	}
}
