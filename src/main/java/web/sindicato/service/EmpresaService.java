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

	@Autowired
	private SocioService socioService;
	
	@Transactional
	public void salvar(Empresa empresa) {
		empresaRepository.save(empresa);
	}

	@Transactional
	public void alterar(Empresa empresa) {
		Empresa e = empresaRepository.findById(empresa.getCodigo()).orElse(empresa);
		e.setNome(empresa.getNome());
		e.setTaxa(empresa.getTaxa());
		if (empresa.getTaxasAtualizadas()) {
			e.setTaxasAtualizadas(true);
		}
		empresaRepository.save(e);
	}

	@Transactional
	public void remover(Long codigo) {
		empresaRepository.deleteById(codigo);
	}

	public void gerarTaxas(Empresa empresa) {
		socioService.gerarTaxa(empresa);
		
	}

}