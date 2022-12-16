package web.sindicato.repository.helper.taxa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sindicato.model.Taxa;
import web.sindicato.model.filter.TaxaFilter;

public interface TaxaQueries {

	Page<Taxa> pesquisar(TaxaFilter filtro, Pageable pageable);
}

