package ec.edu.uce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.service.IGestorClienteService;

@Controller
@RequestMapping("/clientes/")
public class ClienteController {

	@Autowired
	private IGestorClienteService iGestorClienteService;

	@GetMapping("clienteNuevo")
	private String paginaRegistroCliente(Cliente cliente) {
		return "nuevoCliente_c";
	}

	@PostMapping("insertarCliente")
	public String insertarEstudiante(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.iGestorClienteService.registrarCliente(cliente);
		redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado");
		return "redirect:/clientes/clienteNuevo";
	}

	@GetMapping("todos")
	public String buscarEstudianteTodos(Model modelo) {
		List<Vehiculo> listaVehiculos = this.iGestorClienteService.buscarVehiculosDisponibles("KIA", "HASH");
		modelo.addAttribute("listVehiculos", listaVehiculos);
		return "listaVehiculosCliente";
	}

}
