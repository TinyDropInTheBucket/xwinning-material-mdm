package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.OrganizationContactEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerContactRepository
 * @description 制造商联系人
 * @date 2019/6/11
 */
@Repository
public interface ManufacturerContactRepository extends CommonRepository<OrganizationContactEntity> {
    /**
     * 根据orgId查询联系人列表
     * @param orgId
     * @return
     */
    List<OrganizationContactEntity> findByOrgIdAndIsDel(Long orgId, Integer isDel);
}
