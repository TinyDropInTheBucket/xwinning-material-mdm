package com.winning.material.mdm.domain.request;

import com.winning.base.akso.rpc.WinRpcRequest;
import com.winning.material.mdm.domain.response.ManufacturerCertificateOutputDTO;
import com.winning.material.mdm.domain.response.ManufacturerTelecomOutputDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerDetailInfoInputDTO
 * @description 作为新增信息的入参
 * @date 2019/6/12
 */
@Data
@ApiModel(value = "ManufacturerDetailInfoInputDTO", description = "新增生产厂家详细信息入参")
public class ManufacturerDetailInfoInputDTO extends WinRpcRequest {
    /**
     * 生产厂家编码
     */
    @NotNull(message = "生产厂家编码不能为空")
    @ApiModelProperty(value = "生产厂家编码", name = "orgNo")
    private String orgNo;

    /**
     * 生产厂家名称
     */
    @NotNull(message = "生产厂家名称不能为空")
    @ApiModelProperty(value = "生产厂家名称", name = "orgName")
    private String orgName;

    /**
     * 拼音码
     */
    @NotNull(message = "拼音码不能为空")
    @ApiModelProperty(value = "拼音码", name = "pinyin")
    private String pinyin;

    /**
     * 五笔码
     */
    @ApiModelProperty(value = "五笔码", name = "wubi")
    private String wubi;

    /**
     * 助记码
     */
    @ApiModelProperty(value = "助记码", name = "SHORTCUT")
    private String shortCut;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "orgDesc")
    private String orgDesc;

    /**
     * 组织状态
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态", name = "orgStatus")
    private Long orgStatus;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称", name = "orgAlias")
    private String orgAlias;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "fullName")
    private String fullName;

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
     * 详情地址
     */
    @ApiModelProperty(value = "详情地址", name = "addrDetail")
    private String addrDetail;

    /**
     * 许可证列表
     */
    @ApiModelProperty(value = "许可证号列表", name = "certificateList")
    private List<ManufacturerCertificateOutputDTO> certificateList;

    /**
     * 生产厂家联系方式
     */
    @ApiModelProperty(value = "联系方式", name = "contactList")
    private List<ManufacturerTelecomOutputDTO> contactList;

}
