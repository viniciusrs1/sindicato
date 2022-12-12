package web.sindicato.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="empresas")
@DynamicUpdate
public class Empresa implements Serializable {

	private static final long serialVersionUID = 6462379392589216109L;
	
	@Id
	@SequenceGenerator(name="gerador", sequenceName="empresas_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	@NotNull(message = "A taxa é obrigatória.")
	@Min(value = 0, message = "O valor da taxa tem que ser maior ou igual a 0")
	private Float taxa;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	@Column(name = "taxas_atualizadas")
	private Boolean taxasAtualizadas = false;
	
	
	public Long getCodigo() {
		return codigo;
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Float getTaxa() {
		return taxa;
	}



	public void setTaxa(Float taxa) {
		this.taxa = taxa;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public LocalDate getDataCriacao() {
		return dataCriacao;
	}



	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	public Boolean getTaxasAtualizadas() {
		return taxasAtualizadas;
	}



	public void setTaxasAtualizadas(Boolean taxasAtualizadas) {
		this.taxasAtualizadas = taxasAtualizadas;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataCriacao, nome, status, taxa, taxasAtualizadas);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(dataCriacao, other.dataCriacao)
				&& Objects.equals(nome, other.nome) && status == other.status && Objects.equals(taxa, other.taxa)
				&& Objects.equals(taxasAtualizadas, other.taxasAtualizadas);
	}



	@Override
	public String toString() {
		return "Empresa [codigo=" + codigo + ", nome=" + nome + ", taxa=" + taxa + ", status=" + status
				+ ", dataCriacao=" + dataCriacao + ", taxasAtualizadas=" + taxasAtualizadas + "]";
	}

	
}