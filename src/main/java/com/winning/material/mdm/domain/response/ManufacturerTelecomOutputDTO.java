package com.winning.material.mdm.domain.response;

import com.winning.material.mdm.domain.dto.BaseOutputDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerTelecomOutputDTO
 * @description 组织联系方式实体
 * @date 2019/6/11
 */
@Data
@ApiModel(value = "ManufacturerTelecomOutputDTO", description = "查询生产厂家联系方式出参")
public class ManufacturerTelecomOutputDTO extends BaseOutputDTO {
    /**
     * 生产厂家联系标识
     */
    @ApiModelProperty(value = "生产厂家联系标识", name = "orgTelecomId")
    private Long orgTelecomId;

    /**
     * 生产厂家标识
     */
    @ApiModelProperty(value = "生产厂家标识", name = "orgId")
    private Long orgId;

    /**
     * 联系方式代码
     */
    @ApiModelProperty(value = "联系方式代码", name = "contactCode")
    private Long contactCode;

    /**
     * 联系用途代码
     */
    @ApiModelProperty(value = "联系用途代码", name = "contactUsageCode")
    private Long contactUsageCode;

    /**
     * 联系号码
     */
    @ApiModelProperty(value = "联系号码", name = "contactNo")
    private String contactNo;

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
