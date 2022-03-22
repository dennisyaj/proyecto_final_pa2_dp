package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "registro")
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_registro")
	@SequenceGenerator(name = "seq_registro", sequenceName = "seq_registro", allocationSize = 1)
	@Column(name = "vehi_id")
	private Integer id;

	@Column(name = "regi_valor_subtotal")
	private BigDecimal valorSubTotal;

	@Column(name = "regi_valor_IVA")
	private BigDecimal valorIVA;
	
	@Column(name = "regi_valor_total_pagar")
	private BigDecimal valorTotalAPagar;
	
	@Column(name = "regi_fecha_cobro", columnDefinition = "TIMESTAMP")
	private LocalDateTime fechaCobro;

}
