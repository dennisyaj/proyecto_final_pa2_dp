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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.RetirarVehiculoTO;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.service.IGestorEmpleadoService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/empleados/")
public class EmpleadoController {

	@Autowired
	private IGestorEmpleadoService iGestorEmpleadoService;
	@Autowired
	private IVehiculoService iVehiculoService;

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

	@GetMapping("buscarCliente/{cedulaCliente}")
	public String obtenerUsuario(@PathVariable("cedulaCliente") String cedula, Cliente cliente, Model modelo) {
		Cliente c = this.iGestorEmpleadoService.buscarCliente(cedula);
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

	@GetMapping("vehiculo/{idPlaca}")
	public String actualizarEstudiante(@PathVariable(name = "idPlaca") String placa, Vehiculo vehiculo, Model modelo) {
		modelo.addAttribute("vehiculo", this.iVehiculoService.buscarPorPlaca(placa));
		return "empleado/vehiculoBusquedaPlaca";

	}

//	@GetMapping("retirar/{idNumero}")
//	private String paginaRegistroVehiculo(Cliente cliente) {
//		return "buscarCliente";
//	}

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
}
