package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.OrganizationAliasEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerAliasRepository
 * @description 制造商别名
 * @date 2019/6/11
 */
@Repository
public interface ManufacturerAliasRepository extends CommonRepository<OrganizationAliasEntity> {
    List<OrganizationAliasEntity> findByOrgIdAndIsDel(Long orgId, Integer isDel);
}
