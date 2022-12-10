package web.sindicato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
}
