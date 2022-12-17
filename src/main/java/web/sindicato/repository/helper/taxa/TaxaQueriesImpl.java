package web.sindicato.repository.helper.taxa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import web.sindicato.model.Socio;
import web.sindicato.model.Taxa;
import web.sindicato.model.filter.TaxaFilter;
import web.sindicato.repository.pagination.PaginacaoUtil;

public class TaxaQueriesImpl implements TaxaQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Taxa> pesquisar(TaxaFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Taxa> criteriaQuery = builder.createQuery(Taxa.class);
		Root<Taxa> t = criteriaQuery.from(Taxa.class);
		t.fetch("socio", JoinType.INNER);
		TypedQuery<Taxa> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(t.<Long>get("codigo"), filtro.getCodigo()));
		}

		if (filtro.getCodigoSocio() != null) {
			predicateList.add(builder.equal(t.<Socio>get("socio").<Long>get("codigo"), filtro.getCodigoSocio()));
		}

		if (filtro.getPago() != null) {
			if (filtro.getPago()) {
				predicateList.add(builder.isTrue(t.get("pago").as(Boolean.class)));
			} else {
				predicateList.add(builder.isFalse(t.get("pago").as(Boolean.class)));
			}
		}

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(t).where(predArray);
		PaginacaoUtil.prepararOrdem(t, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Taxa> taxas = typedQuery.getResultList();

		long totalTaxas = PaginacaoUtil.getTotalRegistros(t, predArray, builder, manager);

		Page<Taxa> page = new PageImpl<>(taxas, pageable, totalTaxas);

		return page;
	}

	public Boolean verificar(Socio socio) {
		String query = "select case when exists(select true from taxas t where t.codigo_socio =? and t.pago = false)  then true else false end";
		Query booleanQuery = manager.createNativeQuery(query);
		booleanQuery.setParameter(1, socio.getCodigo());
		Boolean exists = (Boolean) booleanQuery.getSingleResult();
		return exists;
	}

}