package com.winning.material.mdm.domain.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author OF
 * @version v1.0
 * @className OrganizationAliasEntity
 * @description 组织别名实体
 * @date 2019/6/3 19:22
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_ALIAS")
public class OrganizationAliasEntity extends BaseEntity {

    /**
     * 组织别名标识
     */
    @Id
    @Column(name = "ORG_ALIAS_ID")
    private Long orgAliasId;

    /**
     * 组织标识
     */
    @Column(name = "ORG_ID")
    private Long orgId;

    /**
     * 组织别名
     */
    @Column(name = "ORG_ALIAS")
    private String orgAlias;

    @Override
    public Long getId() {
        return this.getOrgAliasId();
    }
}
