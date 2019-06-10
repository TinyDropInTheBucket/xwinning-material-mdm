package com.winning.material.mdm.domain.constant;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerApiPathConst
 * @description
 * @date 2019/6/4
 */
public class ManufacturerApiPathConst extends ApiBaseConst{
    /**
     * 分页查询生产厂家信息
     */
    public static final String QUERY_MANUFACTURER_INFO_LIST = BASE_TYPE_CONTEXT + BASE_VERSION + BASE_MODEL_CONTEXT  + "/manufacturer/query/by_example";

    /**
     * 分页查询生产厂家详细信息
     */
    public static final String QUERY_MANUFACTURER_DETAIL_INFO_LIST = BASE_TYPE_CONTEXT + BASE_VERSION + BASE_MODEL_CONTEXT  + "/manufacturer/query_detail/by_example";
}
