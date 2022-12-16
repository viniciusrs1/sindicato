package web.sindicato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Taxa;
import web.sindicato.repository.helper.taxa.TaxaQueries;


public interface TaxaRepository extends JpaRepository<Taxa, Long>, TaxaQueries {

}