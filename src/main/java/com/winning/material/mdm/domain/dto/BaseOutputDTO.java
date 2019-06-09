package com.winning.material.mdm.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: BaseOutputDTO
 * Description:
 * date 2019/3/26 14:44
 *
 * @author liuchao
 * @since JDK 1.8
 */
@Data
@ApiModel(value = "BaseOutputDTO", description = "基础结果对象")
public abstract class BaseOutputDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "医院SOID", name = "hospitalSOID")
    private Long hospitalSOID;
    @ApiModelProperty(value = "创建日期", name = "createdAt")
    private Date createdAt;
    @ApiModelProperty(value = "最近一次修改日期", name = "modifiedAt")
    private Date modifiedAt;
}
