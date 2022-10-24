package com.example.myshop.common.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class RangeableRepositoryImpl<T, ID extends Serializable> extends
    SimpleJpaRepository<T, ID> implements RangeableRepository<T, ID> {

    public RangeableRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
        EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public List<T> getRange(Specification<T> spec, Rangeable rangeable) {
        TypedQuery<T> query = getQuery(spec, getDomainClass(), rangeable.getSort());
        
        query.setFirstResult(rangeable.getStart());
        query.setMaxResults(rangeable.getLimit());
        return query.getResultList();
    }
}
