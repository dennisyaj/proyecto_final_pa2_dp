package ec.edu.uce;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ReporteClienteTO;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.service.IGestorClienteService;
import ec.edu.uce.service.IGestorEmpleadoService;
import ec.edu.uce.service.IGestorReportesService;
import ec.edu.uce.service.IReservaService;

@SpringBootApplication
public class ProyectoFinalPa2DpApplication implements CommandLineRunner {

	@Autowired
	private IGestorEmpleadoService iGestorEmpleadoService;

	@Autowired
	private IGestorClienteService iGestorClienteService;

	@Autowired
	private IGestorReportesService iGestorReportesService;

	@Autowired
	private IReservaService iReservaService;

	Function<List<Reserva>, List<ReporteClienteTO>> reserva = r -> {
		return null;
	};

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2DpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Cliente cliente = new Cliente();
//		cliente.setApellido("Dennis");
//		cliente.setNombre("Pilco");
//		
//		Cliente cliente2 = new Cliente();
//		cliente2.setApellido("Dennis");
//		cliente2.setNombre("Pilco");
//
//		this.iGestorEmpleadoService.registrarCliente(cliente);
//		this.iGestorClienteService.registrarCliente(cliente2);

//		System.out.println(this.iGestorEmpleadoService.buscarCliente("123456789"));

//		Vehiculo vehiculo = new Vehiculo();
//		vehiculo.setMarca("KIA");
//		vehiculo.setModelo("HashBack");
//		vehiculo.setPlaca("FBl-456");
//		this.iGestorEmpleadoService.ingresarVehiculo(vehiculo);

//		List<Vehiculo> lista = this.iGestorClienteService.buscarVehiculosDisponibles("KIA", "HashBack");
//		lista.forEach(System.out::println);

//		this.iGestorClienteService.reservarVehiculo("FBI-456", "1726868886", LocalDateTime.of(2022, Month.APRIL, 12, 0,0),
//				LocalDateTime.of(2022, Month.APRIL, 13, 0,0));

//		System.out.println(this.iGestorReportesService.reporteReservas(LocalDateTime.of(2021, Month.APRIL, 12, 0, 0),
//				LocalDateTime.of(2023, Month.APRIL, 12, 0, 0)).stream().count());

//		this.iReservaService.todasReservas().forEach(System.out::println);
//		Map<Cliente, Long> employeesByCity = this.iReservaService.todasReservas().stream()
//				.collect(Collectors.groupingBy(Reserva::getClienteReserva, Collectors.counting()));
//		;

//		System.out.println(this.iGestorEmpleadoService.retirarVehiculoReservado("Pilco-FBI-456-2022-4-3").getDato());

	}

}
