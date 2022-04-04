package ec.edu.uce.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ReporteFechasTO {

//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;

//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFinal;

	public ReporteFechasTO() {
	}

	public ReporteFechasTO(LocalDate fechaInicio, LocalDate fechaFinal) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}

///geta and sets
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

}
