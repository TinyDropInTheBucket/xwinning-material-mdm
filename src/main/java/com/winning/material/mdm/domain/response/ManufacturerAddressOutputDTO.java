package com.winning.material.mdm.domain.response;

import com.winning.material.mdm.domain.dto.BaseOutputDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerAddressOutputDTO
 * @description
 * @date 2019/6/11
 */
@Data
@ApiModel(value = "ManufacturerAddressOutputDTO", description = "查询生产厂家地址出参")
public class ManufacturerAddressOutputDTO extends BaseOutputDTO {

    /**
     * 组织地址标识
     */
    @ApiModelProperty(value = "组织地址标识", name = "orgAddressId")
    private Long orgAddressId;

    /**
     * 组织标识
     */
    @ApiModelProperty(value = "组织标识", name = "orgId")
    private Long orgId;

    /**
     * 地址用途代码
     */
    @ApiModelProperty(value = "地址用途代码", name = "addrUsageCode")
    private Long addrUsageCode;

    /**
     * 地址类型代码
     */
    @ApiModelProperty(value = "地址类型代码", name = "addrCategoryCode")
    private Long addrCategoryCode;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址", name = "addrDetail")
    private String addrDetail;

    /**
     * 地址-国家代码
     */
    @ApiModelProperty(value = "地址-国家代码", name = "addrCountryCode")
    private Long addrCountryCode;

    /**
     * 地址-省代码（自治区、直辖市）
     */
    @ApiModelProperty(value = "地址-省代码（自治区、直辖市）", name = "addrProvinceCode")
    private Long addrProvinceCode;

    /**
     * 地址-市代码（地区、州）
     */
    @ApiModelProperty(value = "地址-市代码（地区、州）", name = "addrCityCode")
    private Long addrCityCode;

    /**
     * 地址-县代码（区）
     */
    @ApiModelProperty(value = "地址-县代码（区）", name = "addrCountyCode")
    private Long addrCountyCode;

    /**
     * 地址-乡代码（镇、街道办事处）
     */
    @ApiModelProperty(value = "地址-乡代码（镇、街道办事处）", name = "addrTownCode")
    private Long addrTownCode;

    /**
     * 地址-村（街、路、弄等）
     */
    @ApiModelProperty(value = "地址-村（街、路、弄等）", name = "addrVillage")
    private String addrVillage;

    /**
     * 地址-门牌号码
     */
    @ApiModelProperty(value = "地址-门牌号码", name = "addrHouseNo")
    private String addrHouseNo;

    /**
     * 排序序号
     */
    @ApiModelProperty(value = "排序序号", name = "seqNo")
    private Integer seqNo;

    /**
     * 有效日期时间
     */
    @ApiModelProperty(value = "有效日期时间", name = "activatedAt")
    private Date activatedAt;

    /**
     * 失效日期时间
     */
    @ApiModelProperty(value = "失效日期时间", name = "deactivatedAt")
    private Date deactivatedAt;
}
