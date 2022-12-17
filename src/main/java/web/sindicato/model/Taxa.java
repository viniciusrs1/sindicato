package web.sindicato.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "taxas")
public class Taxa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "gerador3", sequenceName = "taxas_codigo_seq", allocationSize = 1)
	@GeneratedValue(generator = "gerador3", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	private LocalDate data;
	private Float valor;
	@ManyToOne
	@JoinColumn(name = "codigo_socio")
		private Socio socio;
	private Boolean pago = false;
	
	
	
	public Long getCodigo() {
		return codigo;
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}



	public LocalDate getData() {
		return data;
	}



	public void setData(LocalDate data) {
		this.data = data;
	}



	public Float getValor() {
		return valor;
	}



	public void setValor(Float valor) {
		this.valor = valor;
	}



	public Socio getSocio() {
		return socio;
	}



	public void setSocio(Socio socio) {
		this.socio = socio;
	}



	public Boolean getPago() {
		return pago;
	}



	public void setPago(Boolean pago) {
		this.pago = pago;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codigo, data, pago, socio, valor);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Taxa other = (Taxa) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(data, other.data)
				&& Objects.equals(pago, other.pago) && Objects.equals(socio, other.socio)
				&& Objects.equals(valor, other.valor);
	}



	@Override
	public String toString() {
		return "Taxa [codigo=" + codigo + ", data=" + data + ", valor=" + valor + ", socio=" + socio + ", pago=" + pago
				+ "]";
	}


}
