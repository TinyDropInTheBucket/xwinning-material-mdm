package com.winning.material.mdm.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerEntity
 * @description
 * @date 2019/6/6
 */
@Data
@Entity()
@Table(name = "ORGANIZATION")
public class ManufacturerEntity extends BaseEntity{
    /**
     * 组织标识
     */
    @Id
    @Column(name = "ORG_ID")
    private Long orgId;

    /**
     * 组织编码
     */
    @Column(name = "ORG_NO")
    private String orgNo;
    /**
     * 组织名称
     */
    @Column(name = "ORG_NAME")
    private String orgName;

    /**
     * 拼音码
     */
    @Column(name = "PINYIN")
    private String pinyin;

    /**
     * 五笔码
     */
    @Column(name = "WUBI")
    private String wubi;

    /**
     * 助记码
     */
    @Column(name = "SHORTCUT")
    private String shortCut;

    /**
     * 组织介绍
     */
    @Column(name = "ORG_DESC")
    private String orgDesc;

    /**
     * 组织类型代码
     */
    @Column(name = "ORG_TYPE_CODE")
    private Long orgTypeCode;

    /**
     * 上级组织标识
     */
    @Column(name = "PARENT_ORG_ID")
    private Long parentOrgId;

    /**
     * 组织状态
     */
    @Column(name = "ORG_STATUS")
    private Long orgStatus;

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

    /**
     *  联系人列表
     */
    @OneToMany(targetEntity = ManufacturerContactEntity.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORG_ID", referencedColumnName = "ORG_ID")
    private List<ManufacturerContactEntity> contactList;

    @Transient
    private String nameSet;

    @Transient
    private String district;

    @Override
    public Long getId() {
        return this.orgId;
    }

    @Override
    public String toString(){
        return "ManufacturerEntity{" +
                "orgId=" + orgId +
                ", orgNo=" + orgNo +
                ", orgName=" + orgName +
                ", pinyin=" + pinyin +
                "," + contactList.toString() +
                "}";
    }

}
