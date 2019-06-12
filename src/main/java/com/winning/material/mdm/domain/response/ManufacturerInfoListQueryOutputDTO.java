package com.winning.material.mdm.domain.response;

import com.winning.material.mdm.domain.dto.BaseOutputDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerInfoListQueryOutputDTO
 * @description 分页查询生产厂家信息出参
 * @date 2019/6/4
 */
@Data
@ApiModel(value = "ManufacturerInfoListQueryOutputDTO", description = "分页查询生产厂家信息出参")
public class ManufacturerInfoListQueryOutputDTO extends BaseOutputDTO {
    /**
     * 生产厂家标识
     */
    @ApiModelProperty(value = "生产厂家标识", name = "orgId")
    private Long orgId;

    /**
     * 生产厂家编码
     */
    @ApiModelProperty(value = "生产厂家编码", name = "orgNo")
    private String orgNo;

    /**
     * 生产厂家名称
     */
    @ApiModelProperty(value = "生产厂家名称", name = "orgName")
    private String orgName;

    /**
     * 拼音码
     */
    @ApiModelProperty(value = "拼音码", name = "pinyin")
    private String pinyin;

    /**
     * 联系人名单
     */
    @ApiModelProperty(value = "联系人名单", name = "nameSet")
    private String nameSet;

    /**
     * 许可证号
     */
    @ApiModelProperty(value = "许可证号", name = "certificateNo")
    private String certificateNo;

    /**
     * 启用标志
     */
    @ApiModelProperty(value = "启用标志", name = "orgStatus")
    private Integer orgStatus;

}
