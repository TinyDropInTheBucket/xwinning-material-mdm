package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.OrganizationContactNameSetEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerContactNameRepository
 * @description 制造商联系人姓名
 * @date 2019/6/11
 */
@Repository
public interface ManufacturerContactNameSetRepository extends CommonRepository<OrganizationContactNameSetEntity> {
    List<OrganizationContactNameSetEntity> findByOrgContactIdAndIsDel(Long contactId, Integer isDel);
}
