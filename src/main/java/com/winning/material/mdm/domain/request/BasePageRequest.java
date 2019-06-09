package com.winning.material.mdm.domain.request;

import com.winning.base.akso.base.WinBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * ClassName: BaseQueryPageRequest
 * Description:
 *
 * @author wangchang
 * @date 2019-05-08 14:28
 */
@Data
@ApiModel(value = "BasePageRequest", description = "分页查询请求基类")
public class BasePageRequest extends WinBaseRequest {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "每页大小，默认100", required = false)
    private int pageSize = 100;
    
    @ApiModelProperty("当前页数，从0开始")
    private int pageNo = 0;

    @NotEmpty
    @ApiModelProperty(value = "SaaS模式或者总分院模式下医院的租户ID", example = "[0L,1L,2L]", allowableValues = "")
    private Long[] soid;
}

