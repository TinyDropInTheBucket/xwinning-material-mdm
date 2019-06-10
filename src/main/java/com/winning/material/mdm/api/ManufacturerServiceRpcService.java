package com.winning.material.mdm.api;

import com.winning.base.akso.common.page.WinPagedList;
import com.winning.base.akso.rpc.WinRpcResponse;
import com.winning.base.akso.rpc.rest.annotation.WinPostMapping;
import com.winning.material.mdm.domain.constant.FeignBaseConst;
import com.winning.material.mdm.domain.constant.ManufacturerApiPathConst;
import com.winning.material.mdm.domain.request.ManufacturerInfoListQueryInputDTO;
import com.winning.material.mdm.domain.response.ManufacturerDetailInfoListQueryOutputDTO;
import com.winning.material.mdm.domain.response.ManufacturerInfoListQueryOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerServiceRpcService
 * @description 分页查询生产厂家信息
 * @date 2019/6/4
 */

@FeignClient(name = FeignBaseConst.REGISTRY_SERVICE_NAME)
public interface ManufacturerServiceRpcService {
    /**
     * 分页查询生产厂家信息接口
     * @param dto
     * @return WinRpcResponse列表
     */
    @WinPostMapping(path = ManufacturerApiPathConst.QUERY_MANUFACTURER_INFO_LIST)
    WinRpcResponse<WinPagedList<ManufacturerInfoListQueryOutputDTO>> queryManufacturerInfoList(ManufacturerInfoListQueryInputDTO dto);

    /**
     * 分页查询生产厂家详细信息接口
     * @param dto
     * @return WinRpcResponse列表
     */
    @WinPostMapping(path = ManufacturerApiPathConst.QUERY_MANUFACTURER_DETAIL_INFO_LIST)
    WinRpcResponse<WinPagedList<ManufacturerDetailInfoListQueryOutputDTO>> queryManufacturerDetailInfoList(ManufacturerInfoListQueryInputDTO dto);
}
