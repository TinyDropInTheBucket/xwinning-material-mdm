package com.winning.material.mdm.domain.response;

import com.winning.material.mdm.domain.dto.BaseOutputDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerCertificateOutputDTO
 * @description 查询生产厂家证件出参
 * @date 2019/6/12
 */
@Data
@ApiModel(value = "ManufacturerCertificateOutputDTO", description = "查询生产厂家证件出参")
public class ManufacturerCertificateOutputDTO extends BaseOutputDTO {
    /**
     * 证件标识
     */
    @ApiModelProperty(value = "证件标识", name = "certificateId")
    private Long certificateId;

    /**
     * 组织标识
     */
    @ApiModelProperty(value = "组织标识", name = "orgId")
    private Long orgId;

    /**
     * 证件类型代码
     */
    @ApiModelProperty(value = "证件类型代码", name = "certificateTypeCode")
    private Long certificateTypeCode;

    /**
     * 证件编号
     */
    @ApiModelProperty(value = "证件编号", name = "certificateNo")
    private String certificateNo;

    /**
     * 证件有效期
     */
    @ApiModelProperty(value = "证件有效期", name = "certificateExpiryDate")
    private Date certificateExpiryDate;

    /**
     * 证件图片URL
     */
    @ApiModelProperty(value = "证件图片URL", name = "certificatePicUrl")
    private String certificatePicUrl;
}
