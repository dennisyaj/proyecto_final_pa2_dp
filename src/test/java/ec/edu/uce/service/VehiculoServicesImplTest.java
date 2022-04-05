package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import ec.edu.uce.modelo.Vehiculo;

@SpringBootTest
@Rollback(true)
@Transactional
class VehiculoServicesImplTest {

	@Autowired
	private IVehiculoService iVehiculoService;

	@Test
	void testActualizar() {

		Vehiculo vehiculo = this.iVehiculoService.buscarPorPlaca("xxx-xxx");
		vehiculo.setMarca("HA");
		this.iVehiculoService.actualizar(vehiculo);

		assertEquals("HA", this.iVehiculoService.buscarPorPlaca("xxx-xxx").getMarca());
	}

	@Test
	void testBuscarMarcaModelo() {
		assertThat(this.iVehiculoService.buscarMarcaModelo("KIA", "HASH")).isNotNull();
	}

	@BeforeEach
	void datos() {

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("KIA");
		vehiculo.setModelo("HASH");
		vehiculo.setPlaca("xxx-xxx");

		this.iVehiculoService.insertar(vehiculo);
	}
}
