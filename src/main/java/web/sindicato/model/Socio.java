package web.sindicato.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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



import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "socios")
@DynamicUpdate
public class Socio implements Serializable {

	private static final long serialVersionUID = -3935828642122652510L;

	@Id
	@SequenceGenerator(name = "gerador2", sequenceName = "socio_codigo_seq", allocationSize = 1)
	@GeneratedValue(generator = "gerador2", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	private String nome;
	private String cargo;
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	private Boolean pendente = false;
	@OneToMany(mappedBy = "socio")
	private Set<Taxa> taxas = new LinkedHashSet<>();
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	private Empresa empresa;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Boolean getPendente() {
		return pendente;
	}
	public void setPendente(Boolean pendente) {
		this.pendente = pendente;
	}
	public Set<Taxa> getTaxas() {
		return taxas;
	}
	public void setTaxas(Set<Taxa> taxas) {
		this.taxas = taxas;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cargo, codigo, dataCriacao, empresa, nome, pendente, taxas);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return Objects.equals(cargo, other.cargo) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(dataCriacao, other.dataCriacao) && Objects.equals(empresa, other.empresa)
				&& Objects.equals(nome, other.nome) && Objects.equals(pendente, other.pendente)
				&& Objects.equals(taxas, other.taxas);
	}
	@Override
	public String toString() {
		return "Socio [codigo=" + codigo + ", nome=" + nome + ", cargo=" + cargo + ", dataCriacao=" + dataCriacao
				+ ", pendente=" + pendente + ", taxas=" + taxas + ", empresa=" + empresa + "]";
	}
	
	

	
}