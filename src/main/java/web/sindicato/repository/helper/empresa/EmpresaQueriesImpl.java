package web.sindicato.repository.helper.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import web.sindicato.model.Empresa;
import web.sindicato.model.Status;
import web.sindicato.model.filter.EmpresaFilter;
import web.sindicato.repository.pagination.PaginacaoUtil;

public class EmpresaQueriesImpl implements EmpresaQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Empresa> pesquisar(EmpresaFilter filtro, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteriaQuery = builder.createQuery(Empresa.class);
		Root<Empresa> e = criteriaQuery.from(Empresa.class);
		TypedQuery<Empresa> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(e.<Long>get("codigo"), 
		                 filtro.getCodigo()));
		}
		if (StringUtils.hasText(filtro.getNome())) {
			predicateList.add(builder.like(	builder.lower(e.<String>get("nome")),
										"%" + filtro.getNome().toLowerCase() + "%"));
		}

		predicateList.add(builder.equal(e.<Status>get("status"), 
		                 Status.ATIVO));
				
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(e).where(predArray);
		PaginacaoUtil.prepararOrdem(e, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
								
		List<Empresa> empresas = typedQuery.getResultList();
		
		long totalEmpresas = PaginacaoUtil.getTotalRegistros(e, predArray, builder, manager);

		Page<Empresa> page = new PageImpl<>(empresas, pageable, totalEmpresas); 
		
		return page;
	}

}