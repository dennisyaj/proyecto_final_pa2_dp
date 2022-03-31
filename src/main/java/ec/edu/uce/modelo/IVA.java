package ec.edu.uce.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "iva")
public class IVA {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_iva")
	@SequenceGenerator(name = "seq_iva", sequenceName = "seq_iva", allocationSize = 1)
	@Column(name = "iva_id")
	private Integer id;

	@Column(name = "iva_valor")
	private BigDecimal valor;

	// gets and sets
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
