package com.winning.material.mdm.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author OF
 * @version v1.0
 * @className OrganizationContactNameSetEntity
 * @description 组织特定联系人姓名实体
 * @date 2019/6/3 19:22
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_CONTACT_NAME_SET")
public class OrganizationContactNameSetEntity extends BaseEntity {

    /**
     * 组织特定联系人联系方式标识
     */
    @Id
    @Column(name = "PURPOSE_NAME_ID")
    private Long purposeNameId;
    /**
     * 组织特定联系人标识
     */
    @Column(name = "ORG_CONTACT_ID")
    private Long orgContactId;

    /**
     * 人的名称用途代码
     */
    @Column(name = "NAME_USAGE_CODE")
    private Long nameUsageCode;

    /**
     * 姓名
     */
    @Column(name = "FULL_NAME")
    private String fullName;

    /**
     * 姓
     */
    @Column(name = "FAMILY_NAME")
    private String familyName;

    /**
     * 名
     */
    @Column(name = "GIVEN_NAME")
    private String givenName;

    /**
     * 前缀
     */
    @Column(name = "PREFIX")
    private String prefix;

    /**
     * 后缀
     */
    @Column(name = "SUFFIX")
    private String suffix;

    /**
     * 有效日期时间
     */
    @Column(name = "ACTIVATED_AT")
    private Date activatedAt;

    /**
     * 失效日期时间
     */
    @Column(name = "DEACTIVATED_AT")
    private Date deactivatedAt;

    @Override
    public Long getId() {
        return this.purposeNameId;
    }
}
