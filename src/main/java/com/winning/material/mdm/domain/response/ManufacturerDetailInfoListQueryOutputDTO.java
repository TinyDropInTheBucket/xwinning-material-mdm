package com.winning.material.mdm.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerDetailInfoListQueryOutputDTO
 * @description
 * @date 2019/6/10
 */
@Data
@ApiModel(value = "ManufacturerDetailInfoListQueryOutputDTO", description = "分页查询生产厂家详细信息出参")
public class ManufacturerDetailInfoListQueryOutputDTO extends ManufacturerInfoListQueryOutputDTO{
    /**
     * 生产厂家简称
     */
    @ApiModelProperty(value = "生产厂家简称", name = "shortCut")
    private String shortCut;

    /**
     * 生产厂家五笔
     */
    @ApiModelProperty(value = "生产厂家五笔", name = "wubi")
    private String wubi;

    /**
     * 生产厂家描述
     */
    @ApiModelProperty(value = "生产厂家描述", name = "orgDesc")
    private String orgDesc;

    /**
     * 生产厂家别名
     */
    @ApiModelProperty(value = "生产厂家别名", name = "orgAlias")
    private String orgAlias;

    /**
     * 生产厂家联系电话
     */
    @ApiModelProperty(value = "生产厂家联系电话", name = "orgTelecomList")
    private List<ManufacturerTelecomOutputDTO> orgTelecomList;

    /**
     * 生产厂家联系地址
     */
    @ApiModelProperty(value = "生产厂家联系地址", name = "orgAddressList")
    private List<ManufacturerAddressOutputDTO> orgAddressList;



}
