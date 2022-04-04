package ec.edu.uce.controller;

import java.math.BigDecimal;
import java.util.List;

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
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservarVehiculoTO;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.service.IGestorClienteService;

@Controller
@RequestMapping("/clientes/")
public class ClienteController {

	@Autowired
	private IGestorClienteService iGestorClienteService;

	@GetMapping("registrarse")
	private String paginaRegistroCliente(Cliente cliente) {
		return "cliente/nuevoCliente_c";
	}

	@PostMapping("insertarCliente")
	public String insertarCliente(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.iGestorClienteService.registrarCliente(cliente);
		redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado");
		return "redirect:/clientes/clienteNuevo";
	}

	@GetMapping("disponiblidad/{marca}/{modelo}")
	public String buscarVehiculosTodos(@PathVariable("marca") String idMarca, @PathVariable("modelo") String idModelo,
			Model modelo) {
		List<Vehiculo> listaVehiculos = this.iGestorClienteService.buscarVehiculosDisponibles(idMarca, idModelo);
		modelo.addAttribute("listVehiculos", listaVehiculos);
		return "cliente/listaVehiculosCliente";
	}

	///////// reservar Vehiculo//////////

	@GetMapping("reservar")
	private String paginaBusqueda(ReservarVehiculoTO reservarVehiculoTO) {
		return "cliente/nuevaReserva";
	}

	@PostMapping("buscarReserva")
	public String insertarReserva(ReservarVehiculoTO reservarVehiculoTO, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		if (this.iGestorClienteService.verificarDisponibilidad(reservarVehiculoTO)) {
			redirectAttributes.addFlashAttribute("mensaje", "Vehiculo disponible");
			modelo.addAttribute("reservarVehiculoTO", reservarVehiculoTO);

			reservarVehiculoTO.setValorTotalAPagar(this.iGestorClienteService.generarPago(reservarVehiculoTO.getPlaca(),
					reservarVehiculoTO.getFechaInicio(), reservarVehiculoTO.getFechaFinal()).getValorTotalAPagar());

			return "cliente/pago";
		} else {
			redirectAttributes.addFlashAttribute("mensaje", "Vehiculo no disponible o Fechas incorrectas");
			return "redirect:/clientes/reservar";
		}

	}

///////////////////////////////////////////////////////

	@PostMapping("insertarPago")
	public String insertarPago(ReservarVehiculoTO reservarVehiculoTO, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		System.out.println(reservarVehiculoTO.getFechaInicio());
		System.out.println(reservarVehiculoTO.getFechaFinal());
		this.iGestorClienteService.crearReserva(reservarVehiculoTO);
		redirectAttributes.addFlashAttribute("mensaje", "Reservacion Creada");
		return "redirect:/clientes/reservar";
	}
}
