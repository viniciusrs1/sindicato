package web.sindicato.model.filter;

public class SocioFilter {
	
	private Long codigo;
	private String nome;
	private Boolean pendencia;

	
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


	public Boolean getPendencia() {
		return pendencia;
	}


	public void setPendencia(Boolean pendencia) {
		this.pendencia = pendencia;
	}


	@Override
	public String toString() {
		return "SocioFilter [codigo=" + codigo + ", nome=" + nome + ", pendencia=" + pendencia + "]";
	}

	
}
