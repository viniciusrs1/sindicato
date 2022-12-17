package web.sindicato.repository.helper.socio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sindicato.model.Empresa;
import web.sindicato.model.Socio;
import web.sindicato.model.filter.SocioFilter;

public interface SocioQueries {

	Page<Socio> pesquisar(SocioFilter filtro, Pageable pageable);
	
	List<Socio> pesquisarPorEmpresa(Empresa empresa);
}

