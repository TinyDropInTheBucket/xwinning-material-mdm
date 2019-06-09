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
 * @className OrganizationTelecomEntity
 * @description 组织联系方式实体
 * @date 2019/6/3 19:22
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_TELECOM")
public class OrganizationTelecomEntity extends BaseEntity {

    /**
     * 组织联系方式标识
     */
    @Id
    @Column(name = "ORG_TELECOM_ID")
    private Long orgTelecomId;

    /**
     * 组织标识
     */
    @Column(name = "ORG_ID")
    private Long orgId;

    /**
     * 联系方式代码
     */
    @Column(name = "CONTACT_CODE")
    private Long contactCode;

    /**
     * 联系用途代码
     */
    @Column(name = "CONTACT_USAGE_CODE")
    private Long contactUsageCode;

    /**
     * 联系号码
     */
    @Column(name = "CONTACT_NO")
    private String contactNo;

    /**
     * 排序序号
     */
    @Column(name = "SEQ_NO")
    private Integer seqNo;

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
        return this.orgTelecomId;
    }
}
