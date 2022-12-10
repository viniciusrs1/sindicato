package web.sindicato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sindicato.model.Empresa;
import web.sindicato.model.Status;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	List<Empresa> findByStatus(Status status);
}
