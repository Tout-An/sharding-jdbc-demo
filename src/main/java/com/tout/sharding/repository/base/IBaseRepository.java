package com.tout.sharding.repository.base;

/**
 * @author xux
 * @date 2021-07-15 10:33
 **/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends
        JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}

