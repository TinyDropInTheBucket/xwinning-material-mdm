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
 * @className OrganizationContactAddressEntity
 * @description 组织特定联系人实体
 * @date 2019/6/3 19:22
 */
@Data
@Entity()
@Table(name = "ORGANIZATION_CONTACT_ADDRESS")
public class OrganizationContactAddressEntity extends BaseEntity {

    /**
     * 组织特定联系人地址标识
     */
    @Id
    @Column(name = "ORG_PURPOSE_ADDRESS_ID")
    private Long orgPurposeAddressId;

    /**
     * 组织特定联系人标识
     */
    @Column(name = "ORG_CONTACT_ID")
    private Long orgContactId;


    /**
     * 地址用途代码
     */
    @Column(name = "ADDR_USAGE_CODE")
    private Long addrUsageCode;

    /**
     * 地址类型代码
     */
    @Column(name = "ADDR_CATEGORY_CODE")
    private Long addrCategoryCode;

    /**
     * 详细地址
     */
    @Column(name = "ADDR_DETAIL")
    private String addrDetail;

    /**
     * 地址-国家代码
     */
    @Column(name = "ADDR_COUNTRY_CODE")
    private Long addrCountryCode;

    /**
     * 地址-省代码（自治区、直辖市）
     */
    @Column(name = "ADDR_PROVINCE_CODE")
    private Long addrProvinceCode;

    /**
     * 地址-市代码（地区、州）
     */
    @Column(name = "ADDR_CITY_CODE")
    private Long addrCityCode;

    /**
     * 地址-县代码（区）
     */
    @Column(name = "ADDR_COUNTY_CODE")
    private Long addrCountyCode;

    /**
     * 地址-乡代码（镇、街道办事处）
     */
    @Column(name = "ADDR_TOWN_CODE")
    private Long addrTownCode;

    /**
     * 地址-村（街、路、弄等）
     */
    @Column(name = "ADDR_VILLAGE")
    private String addrVillage;

    /**
     * 地址-门牌号码
     */
    @Column(name = "ADDR_HOUSE_NO")
    private String addrHouseNo;

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
        return this.orgPurposeAddressId;
    }
}
