package ec.edu.uce.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.ReporteFechasTO;
import ec.edu.uce.modelo.ReporteVehiculosVIPTO;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IGestorReportesService;

@Controller
@RequestMapping("/reportes/")
public class ReporteController {

	private static final Logger LOG = Logger.getRootLogger();

	@Autowired
	private IGestorReportesService iGestorReportesService;

	@GetMapping("reservas")
	private String paginaReporte(ReporteFechasTO reporteFechasTO) {
		return "reportes/fechasReporteReservas";
	}

	@PostMapping("fechas")
	public String buscarReporteTodos(ReporteFechasTO reporteFechasTO, Model modelo,
			RedirectAttributes redirectAttributes) {
		modelo.addAttribute("listReporteReservas", this.iGestorReportesService.reporteReservas(
				reporteFechasTO.getFechaInicio().atStartOfDay(), reporteFechasTO.getFechaFinal().atStartOfDay()));
		return "reportes/reporteReservas";
	}

//////////////////////////////////// clientes vip

	@GetMapping("vip")
	public String buscarEstudianteTodos(Model modelo) {
		LOG.info(this.iGestorReportesService.reporteClientesVIP().size());
		modelo.addAttribute("listaVIP", this.iGestorReportesService.reporteClientesVIP());
		return "reportes/reporteVIP";
	}

	//////////////////////////////// vehiculos vip

	@GetMapping("vehiculoVip/{idMes}/{idAnio}")
	public String obtenerReporteMesAnio(@PathVariable("idMes") String mes, @PathVariable("idAnio") String anio,
			Model modelo) {
		List<ReporteVehiculosVIPTO> listaReporte = this.iGestorReportesService.reporteVehiculodVIP(mes, anio);
		modelo.addAttribute("listaVIP", listaReporte);
		return "reportes/reporteVehiculoVIP";
	}

}
