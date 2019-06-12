package com.winning.material.mdm.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerDetailInfoModifyInputDTO
 * @description 作为修改信息的入参
 * @date 2019/6/12
 */
@Data
@ApiModel(value = "ManufacturerDetailInfoModifyInputDTO", description = "修改生产厂家详细信息入参")
public class ManufacturerDetailInfoModifyInputDTO extends ManufacturerDetailInfoInputDTO{
    /**
     * 生产厂家标志
     */
    @ApiModelProperty(value = "生产厂家标志", name = "orgId")
    private Long orgId;

    /**
     * 生产厂家别名标志
     */
    @ApiModelProperty(value = "生产厂家别名标志", name = "orgAliasId")
    private Long orgAliasId;

    /**
     * 生产厂家联系人标志
     */
    @ApiModelProperty(value = "生产厂家联系人标志", name = "orgContactId")
    private Long orgContactId;

    /**
     * 生产厂家联系人名标志
     */
    @ApiModelProperty(value = "生产厂家联系人名标志", name = "purposeNameSetId")
    private Long purposeNameSetId;

    /**
     * 生产厂家地址标志
     */
    @ApiModelProperty(value = "生产厂家地址标志", name = "addressId")
    private Long addressId;
}
