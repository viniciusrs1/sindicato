package web.sindicato.repository.helper.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sindicato.model.Empresa;
import web.sindicato.model.filter.EmpresaFilter;

public interface EmpresaQueries {

	Page<Empresa> pesquisar(EmpresaFilter filtro, Pageable pageable);
	
}

