package web.sindicato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sindicato.model.Empresa;
import web.sindicato.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Transactional
	public void salvar(Empresa empresa) {
		empresaRepository.save(empresa);
	}
	
	@Transactional
	public void alterar(Empresa empresa) {
		empresaRepository.save(empresa);
	}
	
	@Transactional
	public void remover(Long codigo) {
		empresaRepository.deleteById(codigo);
	}
	
}