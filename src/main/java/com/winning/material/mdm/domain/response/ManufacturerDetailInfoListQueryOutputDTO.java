package com.winning.material.mdm.domain.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerDetailInfoListQueryOutputDTO
 * @description
 * @date 2019/6/10
 */
public class ManufacturerDetailInfoListQueryOutputDTO extends ManufacturerInfoListQueryOutputDTO{
    /**
     * 生产厂家简称
     */
    @ApiModelProperty(value = "生产厂家简称", name = "shortCut")
    private String shortCut;

    /**
     * 生产厂家描述
     */
    @ApiModelProperty(value = "生产厂家描述", name = "orgDesc")
    private String orgDesc;

    /**
     * 生产厂家联系人所在地区
     */
    @ApiModelProperty(value = "生产厂家联系人所在地区", name = "district")
    private String district;

    /**
     * 生产厂家联系人所在地区
     */
    /*@ApiModelProperty(value = "生产厂家联系人所在地区", name = "district")
    private String district;*/

    /**
     * 生产厂家联系人详细地址
     */
    @ApiModelProperty(value = "生产厂家联系人详细地址", name = "addrDetail")
    private String addrDetail;






}
