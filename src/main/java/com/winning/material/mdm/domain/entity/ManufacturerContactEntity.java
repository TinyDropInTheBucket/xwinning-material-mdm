package com.winning.material.mdm.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerContactEntity
 * @description
 * @date 2019/6/9
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_CONTACT")
public class ManufacturerContactEntity extends BaseEntity{
    /**
     * 组织特定联系人标识
     */
    @Id
    @Column(name = "ORG_CONTACT_ID")
    private Long orgContactId;

    /**
     * 组织标识
     */
    /*@Column(name = "ORG_ID")
    private Long orgId;*/

    /**
     * 联系目的类型代码
     */
    @Column(name = "CONTACT_PURPOSE_TYPE_CODE")
    private Long contactPurposeTypeCode;

    @ManyToOne(targetEntity = ManufacturerEntity.class)
    @JoinColumn(name = "ORG_ID", referencedColumnName = "ORG_ID")
    private ManufacturerEntity manufacturerEntity;

    @OneToMany(targetEntity = ManufacturerContactNameSetEntity.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORG_CONTACT_ID", referencedColumnName = "ORG_CONTACT_ID")
    private List<ManufacturerContactNameSetEntity> contactNameList;

    @Override
    public Long getId() {
        return this.orgContactId;
    }

    @Override
    public String toString(){
        return "ManufacturerContactEntity{" +
                "orgContactId" + orgContactId +
                "," +contactNameList.toString() +
                "}";
    }
}
