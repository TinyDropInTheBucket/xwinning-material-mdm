package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.OrganizationTelecomEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerTelecomRepository
 * @description 制造商联系方式
 * @date 2019/6/11
 */
@Repository
public interface ManufacturerTelecomRepository extends CommonRepository<OrganizationTelecomEntity> {
    List<OrganizationTelecomEntity> findByOrgIdAndIsDel(Long orgId, Integer isDel);
}
