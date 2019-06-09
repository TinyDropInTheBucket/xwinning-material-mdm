package com.winning.material.mdm.domain.constant;

/**
 * ClassName: ErrorConstants
 * Description: 收集了各模块通用的异常处代码常量类
 * date 2019/3/26 13:35
 *
 * @author liu_chao
 * @since JDK 1.8
 */
public class ErrorConstants extends MdmBaseConst {
    /**
     * ERROR级别异常是预期外的需要运维或开发关注的
     */
    private static final String ERROR_LEVEL = "E";
    /**
     * WARN级别异常是预计内的不需要人为干预的
     */
    private static final String WARN_LEVEL = "W";
    /**
     * System ,表示错误产生于系统级别
     */
    /**
     * 暂时没用到
     * private static final String ERROR_ORIGIN_SYSTEM = "S";
     */

    /**
     * BUSINESS,表示该错误产生于业务中
     */
    private static final String ERROR_ORIGIN_BUSINESS = "B";
    
    /**
     * 业务系统代码，表示该错误产生于哪个业务系统,业务系统列表详见：wiki 中台环境配置信息
     * 名称:Material主数据
     * 异常类别代码:161
     * 默认端口号:10100
     * 端口号范围:10100-10109
     * AppId:161
     * App名称:winning-material-mdm
     * 说明:
     */
    private static final String CODE_TYPE = "161";

    public static final String REPEATED_DATA = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100001";
    public static final String REPEATED_DATA_DESC = "数据重复";

    public static final String UPDATED_DATA_NOT_FOUND = WARN_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100002";
    public static final String UPDATED_DATA_NOT_FOUND_DESC = "找不到要更新的数据";

    public static final String DELETED_DATA_NOT_FOUND = WARN_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100003";
    public static final String DELETED_DATA_NOT_FOUND_DESC = "找不到要删除的数据";

    public static final String ANY_NOT_FOUND = WARN_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100004";
    public static final String ANY_NOT_FOUND_DESC = "获取不到数据";

    /**
     * Material主数据 异常错误码（1）
     */
    public static final String PARAM_ISNULL = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100005";
    public static final String PARAM_ISNULL_DESC = "必填参数为空";

    public static final String PARAM_MEDICINE = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100006";
    public static final String PARAM_MEDICINE_DESC = "参数药品商品ID集合、临床服务ID集合不能同时为空";

    public static final String PARAM_EXCEEDING_THE_LIMIT = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100007";
    public static final String PARAM_EXCEEDING_THE_LIMIT_DESC = "参数超过限制";
    
    public static final String MEDICATION_NOT_NULL = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100008";
    public static final String MEDICATION_NOT_NULL_DESC = "药品商品信息不能为空";

    public static final String CLINICAL_MEDICATION_NOT_FIND= ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100009";
    public static final String CLINICAL_MEDICATION_NOT_FIND_DESC = "未查询到指定对象";

    public static final String CLINICAL_MEDICATION_ENABLE= ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100010";
    public static final String CLINICAL_MEDICATION_ENABLE_DESC = "该服务已启动不允许重复启用";

    public static final String CLINICAL_MEDICATION_DISABLE= ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100011";
    public static final String CLINICAL_MEDICATION_DISABLE_DESC = "该服务已停用不允许重复停用";

    public static final String PARAM_NOT_LESS_THEN_OR_EQUAL_ZERO = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100012";
    public static final String PARAM_NOT_LESS_THEN_OR_EQUAL_ZERO_DESC = "参数不能小于等于0";

    public static final String PARAM_HOSPITAL_SOID_IS_NOT_VALID = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100013";
    public static final String PARAM_HOSPITAL_SOID_IS_NOT_VALID_DESC = "医院SOID无效";

    public static final String SYSTEM_EXCEPTION= ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100014";
    public static final String SYSTEM_EXCEPTION_DESC = "程序处理异常！";

    public static final String ADD_FAIL = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100015";
    public static final String ADD_FAIL_DESC = "新增失败";

    public static final String MODIFY_FAIL = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100016";
    public static final String MODIFY_FAIL_DESC = "修改失败";

    public static final String CADN_CHINESE_NOT_NULL  = ERROR_LEVEL + ERROR_ORIGIN_BUSINESS + CODE_TYPE + "100017";
    public static final String CADN_CHINESE_NOT_NULL_DESC = "通用名称不允许修改为空字符串";

}
