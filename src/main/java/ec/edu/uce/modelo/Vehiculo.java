package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehiculo")
	@SequenceGenerator(name = "seq_vehiculo", sequenceName = "seq_vehiculo", allocationSize = 1)
	@Column(name = "vehi_id")
	private Integer id;

	@Column(name = "vehi_placa")
	private String placa;

	@Column(name = "vehi_modelo")
	private String modelo;

	@Column(name = "vehi_marca")
	private String marca;

	@Column(name = "vehi_anio_fabricacion", columnDefinition = "TIMESTAMP")
	private LocalDateTime anioFabricacion;

	@Column(name = "vehi_pais_fabricacion")
	private String pais;

	@Column(name = "vehi_cilindraje")
	private String cilindraje;

	@Column(name = "vehi_avaluo")
	private BigDecimal avaluo;

	@Column(name = "vehi_valor_por_dia")
	private BigDecimal valorPorDia;

	@Column(name = "vehi_estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "clie_id")
	private Cliente clienteRenta;

}
