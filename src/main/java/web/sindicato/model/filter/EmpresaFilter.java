package web.sindicato.model.filter;

public class EmpresaFilter {
	
	private Long codigo;
	private String nome;
	private Float taxa;
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
	@Override
	public String toString() {
		return "EmpresaFilter [codigo=" + codigo + ", nome=" + nome + ", taxa=" + taxa + "]";
	}
	
	
}
