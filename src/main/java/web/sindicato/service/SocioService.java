package web.sindicato.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sindicato.model.Empresa;
import web.sindicato.model.Socio;
import web.sindicato.repository.SocioRepository;

@Service
public class SocioService {

	@Autowired
	private SocioRepository socioRepository;

	@Autowired
	private TaxaService taxaService;

	@Transactional
	public void salvar(Socio socio) {
		socioRepository.save(socio);
	}

	public void gerarTaxa(Empresa empresa) {
		List<Socio> socios = socioRepository.pesquisarPorEmpresa(empresa);
		ListIterator<Socio> socioslist = socios.listIterator();
		while (socioslist.hasNext()) {
			Socio socio = (Socio) socioslist.next();
			taxaService.novaTaxa(empresa.getTaxa(), socio);
		}
	}

	public void verificarTaxa() {
		ListIterator<Socio> socios = socioRepository.findAll().listIterator();
		while (socios.hasNext()) {
			Socio socio = (Socio) socios.next();
			Boolean naoPagou = taxaService.verificarTaxa(socio);
			socio.setPendente(naoPagou);
			this.salvar(socio);
		}
	}
}