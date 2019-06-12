package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.OrganizationAddressEntity;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerAddressRepository
 * @description
 * @date 2019/6/11
 */
public interface ManufacturerAddressRepository extends CommonRepository<OrganizationAddressEntity>{
    List<OrganizationAddressEntity> findByOrgIdAndIsDel(Long orgId, Integer isDel);
}
