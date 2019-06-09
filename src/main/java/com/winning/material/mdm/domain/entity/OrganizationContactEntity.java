package com.winning.material.mdm.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author OF
 * @version v1.0
 * @className OrganizationContactEntity
 * @description 组织特定联系人实体
 * @date 2019/6/3 19:22
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_CONTACT")
public class OrganizationContactEntity extends BaseEntity {

    /**
     * 组织特定联系人标识
     */
    @Id
    @Column(name = "ORG_CONTACT_ID")
    private Long orgContactId;

    /**
     * 组织标识
     */
    @Column(name = "ORG_ID")
    private Long orgId;

    /**
     * 联系目的类型代码
     */
    @Column(name = "CONTACT_PURPOSE_TYPE_CODE")
    private Long contactPurposeTypeCode;

    @Override
    public Long getId() {
        return this.orgContactId;
    }
}
