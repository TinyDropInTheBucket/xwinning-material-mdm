package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.OrganizationCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerCertificateRepository
 * @description 制造商证件
 * @date 2019/6/11
 */
@Repository
public interface ManufacturerCertificateRepository extends CommonRepository<OrganizationCertificateEntity> {
    OrganizationCertificateEntity findByOrgIdAndCertificateTypeCodeAndIsDel(Long id, Long code, Integer isDel);
}
