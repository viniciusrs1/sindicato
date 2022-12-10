package web.sindicato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sindicato.model.Socio;
import web.sindicato.repository.SocioRepository;

@Service
public class SocioService {
	
	@Autowired
	private SocioRepository socioRepository;
	
	@Transactional
	public void salvar(Socio socio) {
		socioRepository.save(socio);
	}

}