package com.winning.material.mdm.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author liu_chao
 * 通用的数据库接口
 * @param <T>
 */
@NoRepositoryBean
public interface CommonRepository<T>  extends JpaRepository<T, Long>,JpaSpecificationExecutor<T> {
}
