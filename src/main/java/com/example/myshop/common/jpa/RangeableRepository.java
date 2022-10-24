package com.example.myshop.common.jpa;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface RangeableRepository<T, ID extends Serializable> extends Repository<T, ID>,
    RangeableExecutor<T> {

}
