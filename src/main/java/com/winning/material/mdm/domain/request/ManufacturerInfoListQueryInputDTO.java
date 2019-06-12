package com.winning.material.mdm.domain.request;

import com.winning.base.akso.rpc.WinRpcQueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author f_zl
 * @version v1.0
 * @date 2019/6/3
 */
@Data
@ApiModel(value = "ManufacturerInfoListQueryInputDTO",description = "分页查询生产厂家信息入参")
public class ManufacturerInfoListQueryInputDTO extends WinRpcQueryRequest {
    private static final long serialVersionID = 1L;

    /**
     * 生产厂家关键字
     */
    @ApiModelProperty(value = "生产厂家关键字", name = "keyword")
    private String keyword;

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
     * 生产厂家类型代码
     */
    @ApiModelProperty(value = "生产厂家类型代码", name = "orgTypeCode")
    private Long orgTypeCode;

    /**
     * 上级组织标识
     */
    @ApiModelProperty(value = "上级组织标识", name = "parentOrgId")
    private Long parentOrgId;

    /**
     * 启用标志
     */
    @ApiModelProperty(value = "启用标志", name = "orgStatus")
    private Long orgStatus;
}
