package web.sindicato.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
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
import javax.validation.constraints.NotBlank;


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
	@OneToMany
	@JoinColumn(name = "codigo_socio")
	private Set<Taxa> taxas = new LinkedHashSet<>();
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	private Empresa empresa;
	
	
	public Boolean getPendente() {
		return pendente;
	}
	public void setPendente(Boolean pendente) {
		this.pendente = pendente;
	}
	
}
