package web.sindicato.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sindicato.model.Socio;
import web.sindicato.model.Taxa;
import web.sindicato.repository.TaxaRepository;

@Service
public class TaxaService {

	@Autowired
	private TaxaRepository taxaRepository;
	
	@Transactional
	public void salvar(Taxa taxa) {
		taxaRepository.save(taxa);
	}
	
	@Transactional
	public void alterar(Taxa taxa) {
		taxaRepository.save(taxa);
	}
	
	@Transactional
	public void novaTaxa(Float valor, Socio socio) {
		Taxa t = new Taxa();
		t.setData(LocalDate.now());
		t.setValor(valor);
		t.setSocio(socio);
		t.setPago(false);
		taxaRepository.save(t);
	}
	
	public Boolean verificarTaxa(Socio socio) {
		return taxaRepository.verificar(socio);
	}
	
	public LocalDate ultimaTaxaGerada() {
		List<Taxa> taxas = taxaRepository.findAll();
		if (!taxas.isEmpty()) return taxas.get(taxas.size()-1).getData();
		else return null;
	}
	
}