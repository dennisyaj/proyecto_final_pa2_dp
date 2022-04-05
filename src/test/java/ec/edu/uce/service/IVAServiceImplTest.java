package ec.edu.uce.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(true)
@Transactional
class IVAServiceImplTest {

	@Autowired
	private IIVAService iivaService;

	@Test
	void testBuscar() {
		assertEquals(new BigDecimal("12.00"), this.iivaService.buscar(1).getValor());
	}

}
