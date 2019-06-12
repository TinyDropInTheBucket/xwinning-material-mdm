package com.winning.material.mdm.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author OF
 * @version v1.0
 * @className OrganizationEnterpriseEntity
 * @description 组织企业扩展信息实体
 * @date 2019/6/11 9:40
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_ENTERPRISE")
public class OrganizationEnterpriseEntity extends BaseEntity {

    /**
     * 组织标识
     */
    @Id
    @Column(name = "ORG_ID")
    private Long orgId;

    /**
     * 组织机构代码
     */
    @Column(name = "ORG_INSTI_NO")
    private Long orgInstiNo;

    /**
     * 统一社会信用码
     */
    @Column(name = "SOCIAL_CREDIT_NO")
    private String socialCreditNo;

    /**
     * 开户银行
     */
    @Column(name = "OPENING_BANK")
    private String openingBank;

    /**
     * 开户银行账号
     */
    @Column(name = "OPENING_BANK_ACCOUNT")
    private String openingBankAccount;

    @Override
    public Long getId() {
        return this.orgInstiNo;
    }
}
