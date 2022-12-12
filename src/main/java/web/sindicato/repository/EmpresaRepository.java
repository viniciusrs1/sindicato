package web.sindicato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import web.sindicato.model.Empresa;
import web.sindicato.model.Status;
import web.sindicato.repository.helper.empresa.EmpresaQueries;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, EmpresaQueries{

	List<Empresa> findByStatus(Status status);
}
