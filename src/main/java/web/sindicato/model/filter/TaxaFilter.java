package web.sindicato.model.filter;

public class TaxaFilter {
	
	private Long codigo;
	private Long codigoSocio;
	private Boolean pago;
	
	
	
	public Long getCodigo() {
		return codigo;
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}



	public Long getCodigoSocio() {
		return codigoSocio;
	}



	public void setCodigoSocio(Long codigoSocio) {
		this.codigoSocio = codigoSocio;
	}



	public Boolean getPago() {
		return pago;
	}



	public void setPago(Boolean pago) {
		this.pago = pago;
	}



	@Override
	public String toString() {
		return "TaxaFilter [codigo=" + codigo + ", codigoSocio=" + codigoSocio + ", pago=" + pago + "]";
	}


	
}
