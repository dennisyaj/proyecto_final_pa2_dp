package ec.edu.uce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ReservarVehiculoTO;
import ec.edu.uce.modelo.RetirarVehiculoTO;
import ec.edu.uce.modelo.SinReservaTO;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.service.IGestorClienteService;
import ec.edu.uce.service.IGestorEmpleadoService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/empleados/")
public class EmpleadoController {

	@Autowired
	private IGestorEmpleadoService iGestorEmpleadoService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IGestorClienteService iGestorClienteService;

	@GetMapping("clienteNuevo")
	private String paginaRegistroCliente(Cliente cliente) {
		return "empleado/nuevoCliente";
	}

	@PostMapping("insertarCliente")
	public String insertarEstudiante(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.iGestorEmpleadoService.registrarCliente(cliente);
		redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado");
		return "redirect:/empleados/clienteNuevo";
	}

	/////////////////////// a
	@GetMapping("buscar")
	private String buscarCedulaF(Cliente cliente) {
		return "empleado/buscarCliente";
	}

	@PostMapping("buscarCliente")
	public String obtenerUsuario(@RequestParam(name = "cedula") String cedulaCliente, Cliente cliente, Model modelo) {
		Cliente c = this.iGestorEmpleadoService.buscarCliente(cedulaCliente);
		modelo.addAttribute("cliente", c);
		return "empleado/cliente";
	}

	@GetMapping("vehiculoNuevo")
	private String paginaRegistroVehiculo(Vehiculo vehiculo) {
		return "empleado/nuevoVehiculo";
	}

	@PostMapping("insertarVehiculo")
	public String insertarVehiculo(Vehiculo vehiculo, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.iGestorEmpleadoService.ingresarVehiculo(vehiculo);
		redirectAttributes.addFlashAttribute("mensaje", " Vehiculo guardado");
		return "redirect:/empleados/vehiculoNuevo";
	}

////////////funcionalidad d

	@GetMapping("buscarVehiculo")
	private String buscarVehiculo(Vehiculo vehiculo) {
		return "buscar";
	}

	@GetMapping("detalleVehiculo")
	public String actualizarEstudiante(@RequestParam(name = "placa") String placa, Model modelo) {
		modelo.addAttribute("vehiculo", this.iVehiculoService.buscarPorPlaca(placa));
		return "empleado/vehiculoBusquedaPlaca";

	}

////////////funcionalidad e
	@GetMapping("retirar/{idNumero}")
	private String buscarReservaNumero(@PathVariable(name = "idNumero") String numero, RetirarVehiculoTO retirar,
			Model modelo) {
		modelo.addAttribute("textoReserva", this.iGestorEmpleadoService.generarTexto(numero));
		return "empleado/retirarVehiculo";
	}

	@PutMapping("actualizar/{idNumero}")
	public String ejecutarReserva(@PathVariable("idNumero") String numero, Model modelo,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("mensaje", "Retiro de vehiculo exitoso");
		this.iGestorEmpleadoService.retirarVehiculoReservado(numero);
		return "redirect:/empleados/clienteNuevo";
	}

////////////funcionalidad f
	@GetMapping("retirar/sinReserva")
	private String retirarSinReserva(SinReservaTO sinReservaTO, ReservarVehiculoTO reservarVehiculoTO, Model modelo) {

		modelo.addAttribute("visible1", false);
		modelo.addAttribute("visible2", false);
		modelo.addAttribute("visible3", false);
		return "empleado/marcaModelo";
	}

	@PostMapping("disponiblidad")
	public String buscarVehiculosT(@RequestParam(name = "marca") String idMarca,
			@RequestParam(name = "modelo") String idModelo, Model modelo, SinReservaTO sinReservaTO,
			ReservarVehiculoTO reservarVehiculoTO) {

		modelo.addAttribute("listVehiculos", this.iGestorClienteService.buscarVehiculosDisponibles(idMarca, idModelo));
		modelo.addAttribute("visible1", true);
		modelo.addAttribute("visible2", true);
		modelo.addAttribute("visible3", false);
		return "empleado/marcaModelo";
	}

	@PostMapping("buscarReserva")
	public String insertarReserva(ReservarVehiculoTO reservarVehiculoTO, SinReservaTO sinReservaTO,
			BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {

		if (this.iGestorClienteService.verificarDisponibilidad(reservarVehiculoTO)) {
			redirectAttributes.addFlashAttribute("mensaje", "Vehiculo disponible");
			reservarVehiculoTO.setValorTotalAPagar(this.iGestorClienteService.generarPago(reservarVehiculoTO.getPlaca(),
					reservarVehiculoTO.getFechaInicio(), reservarVehiculoTO.getFechaFinal()).getValorTotalAPagar());

			modelo.addAttribute("reservarVehiculoTO", reservarVehiculoTO);
			modelo.addAttribute("visible1", false);
			modelo.addAttribute("visible2", false);
			modelo.addAttribute("visible3", true);
			return "empleado/marcaModelo";
		} else {
			redirectAttributes.addFlashAttribute("mensaje", "Vehiculo no disponible o Fechas incorrectas");
			return "redirect:/empleados/retirar/sinReserva";
		}

	}

	@PostMapping("insertarPago")
	public String insertarPago(ReservarVehiculoTO reservarVehiculoTO, SinReservaTO sinReservaTO, BindingResult result,
			Model modelo, RedirectAttributes redirectAttributes) {

		System.out.println(reservarVehiculoTO.getFechaInicio());
		System.out.println(reservarVehiculoTO.getFechaFinal());
		this.iGestorClienteService.crearReserva(reservarVehiculoTO);
		redirectAttributes.addFlashAttribute("mensaje", "Reservacion Creada");
		return "redirect:/empleados/retirar/sinReserva";
	}

}
