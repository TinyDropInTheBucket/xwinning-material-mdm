package com.winning.material.mdm.rest;

import com.winning.base.akso.common.page.WinPagedList;
import com.winning.base.akso.rpc.WinRpcResponse;
import com.winning.base.akso.rpc.rest.annotation.WinPostMapping;
import com.winning.material.mdm.api.ManufacturerServiceRpcService;
import com.winning.material.mdm.domain.constant.ErrorConstants;
import com.winning.material.mdm.domain.constant.ManufacturerApiPathConst;
import com.winning.material.mdm.domain.request.ManufacturerDetailInfoInputDTO;
import com.winning.material.mdm.domain.request.ManufacturerDetailInfoModifyInputDTO;
import com.winning.material.mdm.domain.request.ManufacturerInfoListQueryInputDTO;
import com.winning.material.mdm.domain.response.ManufacturerDetailInfoListQueryOutputDTO;
import com.winning.material.mdm.domain.response.ManufacturerInfoListQueryOutputDTO;
import com.winning.material.mdm.service.MdmManufacturerService;
import com.winning.pts.exception.WinningRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerEndPointImpl
 * @description
 * @date 2019/6/4
 */
@Api(value = "4000-药品组织Api接口", tags = {"主数据-药品组织"})
@RestController
public class ManufacturerEndPointImpl implements ManufacturerServiceRpcService {
    @Autowired
    private MdmManufacturerService mdmManufacturerService;

    @Override
    @ApiOperation("分页查询生产厂家信息列表，默认每页显示100条")
    //@WinningApiInfo(id = "161-4100-01", apiVersion = "v1", sinceVersion = "1.0.0", createdAt = "2019-06-04", updatedAt = "2019-06-04", apiStatus = ApiStatus.IN_PROCESS, requirementNo = "4786")
    @WinPostMapping(path = ManufacturerApiPathConst.QUERY_MANUFACTURER_INFO_LIST)
    public WinRpcResponse<WinPagedList<ManufacturerInfoListQueryOutputDTO>> queryManufacturerInfoList(ManufacturerInfoListQueryInputDTO queryDto) {
        WinPagedList<ManufacturerInfoListQueryOutputDTO> manufacturerInfoList = mdmManufacturerService.queryManufacturerInfoList(queryDto);
        if (manufacturerInfoList==null){
            return new WinRpcResponse<>(new WinningRuntimeException(ErrorConstants.ANY_NOT_FOUND, ErrorConstants.ANY_NOT_FOUND_DESC));
        }
        return new WinRpcResponse(manufacturerInfoList.getData(),manufacturerInfoList.getCount());
    }

    @Override
    @ApiOperation("分页查询生产厂家详细信息列表，默认每页显示100条")
    //@WinningApiInfo(id = "161-4100-02", apiVersion = "v1", sinceVersion = "1.0.0", createdAt = "2019-06-11", updatedAt = "2019-06-11", apiStatus = ApiStatus.IN_PROCESS, requirementNo = "4788")
    @WinPostMapping(path = ManufacturerApiPathConst.QUERY_MANUFACTURER_DETAIL_INFO_LIST)
    public WinRpcResponse<WinPagedList<ManufacturerDetailInfoListQueryOutputDTO>> queryManufacturerDetailInfoList(ManufacturerInfoListQueryInputDTO dto) {
        WinPagedList<ManufacturerDetailInfoListQueryOutputDTO> manufacturerInfoList = mdmManufacturerService.queryManufacturerDetailInfoList(dto);
        if (manufacturerInfoList==null){
            return new WinRpcResponse<>(new WinningRuntimeException(ErrorConstants.ANY_NOT_FOUND, ErrorConstants.ANY_NOT_FOUND_DESC));
        }
        return new WinRpcResponse(manufacturerInfoList.getData(),manufacturerInfoList.getCount());
    }

    @Override
    @ApiOperation("新增生产厂家信息")
    //@WinningApiInfo(id = "161-4100-03", apiVersion = "v1", sinceVersion = "1.0.0", createdAt = "2019-06-12", updatedAt = "2019-06-12", apiStatus = ApiStatus.IN_PROCESS, requirementNo = "4790")
    @WinPostMapping(path = ManufacturerApiPathConst.ADD_MANUFACTURER_DETAIL_INFO)
    public WinRpcResponse addManufacturerDetailInfo(@RequestBody ManufacturerDetailInfoInputDTO dto) {
        Boolean result = mdmManufacturerService.addManufacturerDetailInfo(dto);
        if (!result) {
            return new WinRpcResponse<>(new WinningRuntimeException(ErrorConstants.ADD_FAIL, ErrorConstants.ADD_FAIL_DESC));
        }
        return new WinRpcResponse(true);
    }

    @Override
    @ApiOperation("修改生产厂家信息")
    //@WinningApiInfo(id = "161-4100-04", apiVersion = "v1", sinceVersion = "1.0.0", createdAt = "2019-06-12", updatedAt = "2019-06-12", apiStatus = ApiStatus.IN_PROCESS, requirementNo = "4792")
    @WinPostMapping(path = ManufacturerApiPathConst.MODIFY_MANUFACTURER_DETAIL_INFO)
    public WinRpcResponse modifyManufacturerDetailInfo(ManufacturerDetailInfoModifyInputDTO dto) {
        Boolean result = mdmManufacturerService.modifyManufacturerDetailInfo(dto);
        if (!result) {
            return new WinRpcResponse<>(new WinningRuntimeException(ErrorConstants.MODIFY_FAIL, ErrorConstants.MODIFY_FAIL_DESC));
        }
        return new WinRpcResponse(true);
    }
}
