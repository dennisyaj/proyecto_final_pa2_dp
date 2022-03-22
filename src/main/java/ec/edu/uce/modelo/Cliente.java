package ec.edu.uce.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
	@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1)
	@Column(name = "clie_id")
	private Integer id;

	@Column(name = "clie_nombre")
	private String nombre;

	@Column(name = "clie_apellido")
	private String apellido;

	@Column(name = "clie_cedula")
	private String cedula;

	@Column(name = "clie_fecha_nacimiento", columnDefinition = "TIMESTAMP")
	private LocalDateTime fechaNacimiento;

	@Column(name = "clie_genero")
	private String genero;

	@Column(name = "clie_registro")
	private String tipoRegistro;

	@OneToMany(mappedBy = "clienteRenta", cascade = CascadeType.ALL)
	private List<Vehiculo> vehiculosRentados;

//	@ManyToOne
//	@JoinColumn(name = "memb_id")
//	private Membresia membresia ;

}
