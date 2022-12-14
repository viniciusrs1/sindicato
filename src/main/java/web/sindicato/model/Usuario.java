package web.sindicato.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import web.sindicato.service.NomeUsuarioUnicoService;
import web.sindicato.validation.UniqueValueAttribute;

@Entity
@Table(name = "usuario")
@UniqueValueAttribute(attribute = "nomeUsuario", service = NomeUsuarioUnicoService.class, message = "Já existe um nome de usuário igual a este cadastrado")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="gerador55", sequenceName="usuario_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador55", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	@NotBlank(message = "O nome do usuário é obrigatório")
	private String nome;
	@NotBlank(message = "A senha do usuário é obrigatória")
	private String senha;
	@Column(name = "nome_usuario")
	@NotBlank(message = "O nome de usuário do usuário é obrigatório")
	private String nomeUsuario;
	private boolean ativo;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_papel", joinColumns = @JoinColumn(name = "codigo_usuario"), inverseJoinColumns = @JoinColumn(name = "codigo_papel"))
	@Size(min = 1, message = "O usuário deve ter ao menos um papel no sistema")
	private List<Papel> papeis = new ArrayList<>();

	public String isAdmin() {
		return this.papeis.get(0).getNome();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void adicionarPapel(Papel papel) {
		papeis.add(papel);
	}

	public void removerPapel(Papel papel) {
		papeis.remove(papel);
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "codigo: " + codigo + "\nnome: " + nome  + "\nsenha: " + senha + "\nusuario: " + nomeUsuario   + "\nativo: " + ativo;
	}

}