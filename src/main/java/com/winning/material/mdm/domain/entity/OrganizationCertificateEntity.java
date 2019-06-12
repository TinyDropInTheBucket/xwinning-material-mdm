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
 * @className OrganizationCertificateEntity
 * @description 组织组织实体
 * @date 2019/6/11 9:40
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_CERTIFICATE")
public class OrganizationCertificateEntity extends BaseEntity {

    /**
     * 证件标识
     */
    @Id
    @Column(name = "CERTIFICATE_ID")
    private Long certificateId;

    /**
     * 组织标识
     */
    @Column(name = "ORG_ID")
    private Long orgId;

    /**
     * 证件类型代码
     */
    @Column(name = "CERTIFICATE_TYPE_CODE")
    private Long certificateTypeCode;

    /**
     * 证件编号
     */
    @Column(name = "CERTIFICATE_NO")
    private String certificateNo;

    /**
     * 证件有效期
     */
    @Column(name = "CERTIFICATE_EXPIRY_DATE")
    private Date certificateExpiryDate;

    /**
     * 证件图片URL
     */
    @Column(name = "CERTIFICATE_PIC_URL")
    private String certificatePicUrl;

    @Override
    public Long getId() {
        return this.certificateId;
    }
}
