package ec.edu.uce.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.ReporteFechasTO;
import ec.edu.uce.service.IGestorReportesService;

@Controller
@RequestMapping("/reportes/")
public class ReporteController {

	@Autowired
	private IGestorReportesService iGestorReportesService;

	@GetMapping("reservas")
	private String paginaRegistroCliente(ReporteFechasTO reporteFechasTO) {
		return "reportes/fechasReporteReservas";
	}

	@PostMapping("fechas")
	public String buscarEstudianteTodos(ReporteFechasTO reporteFechasTO, Model modelo,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("mensaje", "Estudiante guadardo");
		modelo.addAttribute("listReporteReservas", this.iGestorReportesService.reporteReservas(
				reporteFechasTO.getFechaInicio().atStartOfDay(), reporteFechasTO.getFechaFinal().atStartOfDay()));
		return "reportes/reporteReservas";
	}

////////////////////////////////////
	@GetMapping("buscar/{inicioF}/{finalF}")
	public String obtenerReporteReservas(@PathVariable("inicioF") LocalDate fechaInicia,
			@PathVariable("finalF") LocalDate fechaFinal, Model modelo) {
//		modelo.addAttribute("listReporteReservas",
//				this.iGestorReportesService.reporteReservas(fechaInicia, fechaInicia));
		return "reporteReservas";
	}
}
