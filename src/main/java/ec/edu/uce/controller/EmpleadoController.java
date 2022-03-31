package ec.edu.uce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.service.IGestorEmpleadoService;

@Controller
@RequestMapping("/empleados/")
public class EmpleadoController {

	@Autowired
	private IGestorEmpleadoService iGestorEmpleadoService;

	@GetMapping("clienteNuevo")
	private String paginaRegistroCliente(Cliente cliente) {
		return "nuevoCliente";
	}

	@PostMapping("insertarCliente")
	public String insertarEstudiante(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.iGestorEmpleadoService.registrarCliente(cliente);
		redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado");
		return "redirect:/empleados/clienteNuevo";
	}

	@GetMapping("buscar/{cedulaCliente}")
	public String obtenerUsuario(@PathVariable("cedulaCliente") String cedula, Cliente cliente,Model modelo) {
		Cliente c = this.iGestorEmpleadoService.buscarCliente(cedula);
		modelo.addAttribute("cliente", c);
		return "cliente";
	}

	@GetMapping("vehiculoNuevo")
	private String paginaRegistroVehiculo(Vehiculo vehiculo) {
		return "nuevoVehiculo";
	}

	@PostMapping("insertarVehiculo")
	public String insertarVehiculo(Vehiculo vehiculo, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.iGestorEmpleadoService.ingresarVehiculo(vehiculo);
		redirectAttributes.addFlashAttribute("mensaje", " Vehiculo guardado");
		return "redirect:/empleados/vehiculoNuevo";
	}

	@GetMapping("clienteBuscar")
	private String paginaRegistroVehiculo(Cliente cliente) {
		return "buscarCliente";
	}

	@GetMapping("b/{idCedula}")
	private String obtenerPaginaAtualizarDatos(@PathVariable("idCedula") String cedula, Cliente cliente, Model modelo) {

		Cliente c = this.iGestorEmpleadoService.buscarCliente(cedula);
		modelo.addAttribute("clie", c);
		return "cliente";
	}
}
