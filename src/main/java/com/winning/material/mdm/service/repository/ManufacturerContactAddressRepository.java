package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.OrganizationContactAddressEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerContactAddressRepository
 * @description 制造商联系地址
 * @date 2019/6/11
 */
@Repository
public interface ManufacturerContactAddressRepository extends CommonRepository<OrganizationContactAddressEntity> {
    List<OrganizationContactAddressEntity> findByOrgContactIdAndIsDel(Long contactId, Integer isDel);
}
