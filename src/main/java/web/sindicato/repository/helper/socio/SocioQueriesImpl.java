package web.sindicato.repository.helper.socio;

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
import web.sindicato.model.Socio;
import web.sindicato.model.filter.SocioFilter;
import web.sindicato.repository.pagination.PaginacaoUtil;

public class SocioQueriesImpl implements SocioQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Socio> pesquisar(SocioFilter filtro, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Socio> criteriaQuery = builder.createQuery(Socio.class);
		Root<Socio> s = criteriaQuery.from(Socio.class);
		TypedQuery<Socio> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(s.<Long>get("codigo"), 
		                 filtro.getCodigo()));
		}
		if (StringUtils.hasText(filtro.getNome())) {
			predicateList.add(builder.like(	builder.lower(s.<String>get("nome")),
										"%" + filtro.getNome().toLowerCase() + "%"));
		}
		if (filtro.getPendencia() != null) {
			if (filtro.getPendencia()) {
				predicateList.add(builder.isTrue(s.get("pendente").as(Boolean.class)));
			}
			else {
				predicateList.add(builder.isFalse(s.get("pendente").as(Boolean.class)));
			}
		}
		
				
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(s).where(predArray);
		PaginacaoUtil.prepararOrdem(s, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
								
		List<Socio> socios = typedQuery.getResultList();
		
		long totalSocios = PaginacaoUtil.getTotalRegistros(s, predArray, builder, manager);

		Page<Socio> page = new PageImpl<>(socios, pageable, totalSocios); 
		
		return page;
	}

	public List<Socio> pesquisarPorEmpresa(Empresa empresa){
		TypedQuery<Socio> query = manager.createQuery("select s from Socio s where s.empresa.codigo =: emp", Socio.class);
		query.setParameter("emp", empresa.getCodigo());
		List<Socio> lista = query.getResultList();
		return lista;						
	}
	
}