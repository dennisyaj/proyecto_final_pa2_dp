package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

public class ReporteClienteTO {

	private Integer id;

	private String nombre;

	private String apellido;

	private String cedula;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	private String genero;

	private Character tipoRegistro;

	private BigDecimal valorIVA;

	private BigDecimal valorTotalAPagar;

	public ReporteClienteTO() {
	}

	public ReporteClienteTO(Integer id, String nombre, String apellido, String cedula, LocalDate fechaNacimiento,
			String genero, Character tipoRegistro, BigDecimal valorIVA, BigDecimal valorTotalAPagar) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.tipoRegistro = tipoRegistro;
		this.valorIVA = valorIVA;
		this.valorTotalAPagar = valorTotalAPagar;
	}

	// gets and set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Character getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Character tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public BigDecimal getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(BigDecimal valorIVA) {
		this.valorIVA = valorIVA;
	}

	public BigDecimal getValorTotalAPagar() {
		return valorTotalAPagar;
	}

	public void setValorTotalAPagar(BigDecimal valorTotalAPagar) {
		this.valorTotalAPagar = valorTotalAPagar;
	}

}
