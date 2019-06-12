package com.winning.material.mdm.service;

import com.winning.base.akso.common.page.WinPagedList;
import com.winning.base.akso.duid.DuidAbility;
import com.winning.material.mdm.domain.entity.*;
import com.winning.material.mdm.domain.request.ManufacturerDetailInfoInputDTO;
import com.winning.material.mdm.domain.request.ManufacturerDetailInfoModifyInputDTO;
import com.winning.material.mdm.domain.request.ManufacturerInfoListQueryInputDTO;
import com.winning.material.mdm.domain.response.*;
import com.winning.material.mdm.service.repository.*;
import com.winning.material.mdm.util.CommonNativeSqlUtil;
import com.winning.pts.utils.mapper.BeanMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author f_zl
 * @version v1.0
 * @className MdmManufacturerService
 * @description 生产厂家服务类
 * @date 2019/6/4
 */
@Service
public class MdmManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ManufacturerAliasRepository manufacturerAliasRepository;
    @Autowired
    private ManufacturerContactRepository manufacturerContactRepository;
    @Autowired
    private ManufacturerCertificateRepository manufacturerCertificateRepository;
    @Autowired
    private ManufacturerTelecomRepository manufacturerTelecomRepository;
    @Autowired
    private ManufacturerAddressRepository manufacturerAddressRepository;
    @Autowired
    private ManufacturerContactNameSetRepository manufacturerContactNameSetRepository;
    @Autowired
    private ManufacturerContactAddressRepository manufacturerContactAddressRepository;
    @Autowired
    public DuidAbility duid;

    @PersistenceContext
    private EntityManager entityManager;

    private int getCorrectPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 100;
        }
        return pageSize;
    }

    /**
     * 获取Sql动态查询条件
     * @param dto 分页查询入参
     * @return Sql动态查询条件
     */
    private String getDynamicSqlString(ManufacturerInfoListQueryInputDTO dto, Map<String, Object> mapSqlParams) {
        StringBuffer dynamicSqlString = new StringBuffer();
        String baseString = " SELECT " +
                "a.* " +
                "FROM ORGANIZATION a " +
                "WHERE a.IS_DEL = 0 ";
        dynamicSqlString.append(baseString);
        if (dto.getSoid() != null && dto.getSoid().length > 0) {
            dynamicSqlString.append(" AND a.HOSPITAL_SOID = :sqlParamSOID ");
            mapSqlParams.put("sqlParamSOID", dto.getSoid()[0]);
        }
        String strKeyWord = dto.getKeyword();
        if (StringUtils.isNotBlank(strKeyWord)) {
            dynamicSqlString.append(" AND (");
            dynamicSqlString.append("a.ORG_NAME like :sqlParamKeyWord ");
            dynamicSqlString.append("OR a.PINYIN like :sqlParamKeyWord ");
            dynamicSqlString.append("OR a.WUBI like :sqlParamKeyWord ");
            dynamicSqlString.append("OR a.SHORTCUT like :sqlParamKeyWord ");
            dynamicSqlString.append("OR a.ORG_DESC like :sqlParamKeyWord ");
            dynamicSqlString.append(") ");
            mapSqlParams.put("sqlParamKeyWord", "%" + strKeyWord + "%");
        }
        if (dto.getOrgStatus() != null) {
            dynamicSqlString.append(" AND a.ORG_STATUS = :sqlParamOrgStatus ");
            mapSqlParams.put("sqlParamOrgStatus", dto.getOrgStatus());
        }
        if (dto.getOrgId() != null) {
            dynamicSqlString.append(" AND a.ORG_ID = :sqlParamOrgId ");
            mapSqlParams.put("sqlParamOrgId", dto.getOrgId());
        }
        if (dto.getOrgNo() != null) {
            dynamicSqlString.append(" AND a.ORG_NO = :sqlParamOrgNo ");
            mapSqlParams.put("sqlParamOrgNo", dto.getOrgNo());
        }
        if (dto.getOrgTypeCode() != null) {
            dynamicSqlString.append(" AND a.ORG_TYPE_CODE = :sqlParamOrgTypeCode ");
            mapSqlParams.put("sqlParamOrgTypeCode", dto.getOrgTypeCode());
        }
        return dynamicSqlString.toString();
    }

    /**
     * 分页查询公共部分
     * @param dto
     * @return WinPagedList<OrganizationEntity>
     */
    private WinPagedList<OrganizationEntity> getOrganizationPagedList(ManufacturerInfoListQueryInputDTO dto) {
        dto.setPageSize(getCorrectPageSize(dto.getPageSize()));
        Map<String, Object> mapSqlParams = new HashMap<>(16);
        String dynamicSqlString = getDynamicSqlString(dto, mapSqlParams);
        //获取分页数据
        WinPagedList<OrganizationEntity> pageList = CommonNativeSqlUtil.findPageListByNativeSql(dynamicSqlString,
                PageRequest.of(dto.getPageNo(), dto.getPageSize()), mapSqlParams, OrganizationEntity.class, entityManager);
        return pageList;
    }

    /**
     * 查询厂家信息service
     * @param dto
     * @return
     */
    public WinPagedList<ManufacturerInfoListQueryOutputDTO> queryManufacturerInfoList(ManufacturerInfoListQueryInputDTO dto) {
        WinPagedList<OrganizationEntity> resultPagedList = getOrganizationPagedList(dto);
        //处理后的分页数据
        if (resultPagedList.getData() == null || resultPagedList.getData().size() == 0) {
            return new WinPagedList<>(0L, Collections.emptyList());
        } else {
            List<ManufacturerInfoListQueryOutputDTO> convertPagedList = convertManufacturerInfo(resultPagedList.getData());
            return new WinPagedList<>(resultPagedList.getCount(), convertPagedList);
        }
    }

    /**
     * 对厂商信息数据进行处理
     * @param tempList
     * @return
     */
    private List<ManufacturerInfoListQueryOutputDTO> convertManufacturerInfo(List<OrganizationEntity> tempList) {
        List<ManufacturerInfoListQueryOutputDTO> resultOutputPageList = BeanMapper.mapList(tempList,
                OrganizationEntity.class, ManufacturerInfoListQueryOutputDTO.class);
        for (ManufacturerInfoListQueryOutputDTO dto : resultOutputPageList) {
            StringBuffer nameBuffer = new StringBuffer();
            int count = 0;
            Long orgId = dto.getOrgId();
            // 307241为许可证对应的类型号 isDel为0
            OrganizationCertificateEntity certificateEntity = manufacturerCertificateRepository.findByOrgIdAndCertificateTypeCodeAndIsDel(orgId,307241L, 0);
            if (null != certificateEntity && null != certificateEntity.getCertificateNo()) {
                dto.setCertificateNo(certificateEntity.getCertificateNo());
            }
            List<OrganizationContactEntity> orgContactList = manufacturerContactRepository.findByOrgIdAndIsDel(orgId, 0);
            for (OrganizationContactEntity contact : orgContactList) {
                List<OrganizationContactNameSetEntity> contactNameList = manufacturerContactNameSetRepository.findByOrgContactIdAndIsDel(contact.getOrgContactId(), 0);
                for (OrganizationContactNameSetEntity nameEntity : contactNameList) {
                    count++;
                    if (count != 1){
                        nameBuffer.append(", ");
                    }
                    nameBuffer.append(nameEntity.getFullName());
                }
            }
            dto.setNameSet(nameBuffer.toString());
        }
        return  resultOutputPageList;
    }

    /**
     * 查询厂家详细信息service
     * @param dto
     * @return
     */
    public WinPagedList<ManufacturerDetailInfoListQueryOutputDTO> queryManufacturerDetailInfoList(ManufacturerInfoListQueryInputDTO dto) {
        WinPagedList<OrganizationEntity> resultPagedList = getOrganizationPagedList(dto);
        //处理后的分页数据
        if (resultPagedList.getData() == null || resultPagedList.getData().size() == 0) {
            return new WinPagedList<>(0L, Collections.emptyList());
        } else {
            List<ManufacturerDetailInfoListQueryOutputDTO> convertDetailPagedList = convertManufacturerDetailInfo(resultPagedList.getData());
            return new WinPagedList<>(resultPagedList.getCount(), convertDetailPagedList);
        }
    }

    private List<ManufacturerDetailInfoListQueryOutputDTO> convertManufacturerDetailInfo(List<OrganizationEntity> tempList) {
        List<ManufacturerDetailInfoListQueryOutputDTO> resultOutputPageList =
                BeanMapper.mapList(convertManufacturerInfo(tempList), ManufacturerInfoListQueryOutputDTO.class,
                        ManufacturerDetailInfoListQueryOutputDTO.class);
        for (ManufacturerDetailInfoListQueryOutputDTO dto : resultOutputPageList) {
            Long orgId = dto.getOrgId();
            List<OrganizationAliasEntity> orgAliasList = manufacturerAliasRepository.findByOrgIdAndIsDel(orgId, 0);
            if (null != orgAliasList && orgAliasList.size() > 0) {
                dto.setOrgAlias(orgAliasList.get(0).getOrgAlias());
            }
            List<OrganizationTelecomEntity> orgTelecomEntityList = manufacturerTelecomRepository.findByOrgIdAndIsDel(orgId, 0);
            if (null != orgTelecomEntityList && orgTelecomEntityList.size() > 0) {
                List<ManufacturerTelecomOutputDTO> orgTelecomList = BeanMapper.mapList(orgTelecomEntityList, OrganizationTelecomEntity.class, ManufacturerTelecomOutputDTO.class);
                dto.setOrgTelecomList(orgTelecomList);
            }
            List<OrganizationAddressEntity> orgAddressEntityList = manufacturerAddressRepository.findByOrgIdAndIsDel(orgId, 0);
            if (null != orgAddressEntityList && orgAddressEntityList.size() > 0) {
                List<ManufacturerAddressOutputDTO> orgAddressList = BeanMapper.mapList(orgAddressEntityList, OrganizationAddressEntity.class, ManufacturerAddressOutputDTO.class);
                dto.setOrgAddressList(orgAddressList);
            }
        }
        return  resultOutputPageList;
    }

    /**
     * 新增厂家信息service
     * @param dto
     * @return 返回成功/失败信息
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean addManufacturerDetailInfo(ManufacturerDetailInfoInputDTO dto) {
        // 给OrganizationEntity的属性赋值并保存
        Long orgId = duid.getUID();//全局ID， 下同
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setOrgId(orgId);
        organizationEntity.setOrgName(dto.getOrgName());
        organizationEntity.setOrgNo(dto.getOrgNo());
        organizationEntity.setPinyin(dto.getPinyin());
        organizationEntity.setWubi(dto.getWubi());
        organizationEntity.setShortCut(dto.getShortCut());
        organizationEntity.setOrgStatus(dto.getOrgStatus());
        organizationEntity.setOrgDesc(dto.getOrgDesc());
        organizationEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerRepository.save(organizationEntity);

        // 给OrganizationAliasEntity的属性赋值并保存
        Long orgAliasId= duid.getUID();
        OrganizationAliasEntity organizationAliasEntity = new OrganizationAliasEntity();
        organizationAliasEntity.setOrgAliasId(orgAliasId);
        organizationAliasEntity.setOrgId(orgId);
        organizationAliasEntity.setOrgAlias(dto.getOrgAlias());
        organizationAliasEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerAliasRepository.save(organizationAliasEntity);

        // 给OrganizationContactEntity和OrganizationContactNameSetEntity的属性赋值并保存
        Long orgContactId= duid.getUID();
        OrganizationContactEntity organizationContactEntity = new OrganizationContactEntity();
        organizationContactEntity.setOrgContactId(orgContactId);
        organizationContactEntity.setOrgId(orgId);
        organizationContactEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerContactRepository.save(organizationContactEntity);
        Long purposeNameSetId= duid.getUID();
        OrganizationContactNameSetEntity organizationContactNameSetEntity = new OrganizationContactNameSetEntity();
        organizationContactNameSetEntity.setPurposeNameId(purposeNameSetId);
        organizationContactNameSetEntity.setOrgContactId(orgContactId);
        organizationContactNameSetEntity.setFullName(dto.getFullName());
        organizationContactNameSetEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerContactNameSetRepository.save(organizationContactNameSetEntity);

        //给OrganizationTelecomEntity的属性赋值并保存
        List<ManufacturerTelecomOutputDTO> contactList = dto.getContactList();
        for (ManufacturerTelecomOutputDTO contact : contactList) {
            Long orgTelecomId= duid.getUID();
            OrganizationTelecomEntity organizationTelecomEntity = new OrganizationTelecomEntity();
            organizationTelecomEntity.setOrgTelecomId(orgTelecomId);
            organizationTelecomEntity.setOrgId(orgId);
            organizationTelecomEntity.setContactUsageCode(contact.getContactUsageCode());
            organizationTelecomEntity.setContactCode(contact.getContactCode());
            organizationTelecomEntity.setContactNo(contact.getContactNo());
            organizationTelecomEntity.setHospitalSOID(dto.getSoid()[0]);
            manufacturerTelecomRepository.save(organizationTelecomEntity);
        }

        //给OrganizationCertificateEntity的属性赋值并保存
        List<ManufacturerCertificateOutputDTO> certificateList = dto.getCertificateList();
        for (ManufacturerCertificateOutputDTO certificate : certificateList) {
            Long certificateId= duid.getUID();
            OrganizationCertificateEntity organizationCertificateEntity = new OrganizationCertificateEntity();
            organizationCertificateEntity.setCertificateId(certificateId);
            organizationCertificateEntity.setOrgId(orgId);
            organizationCertificateEntity.setCertificateTypeCode(certificate.getCertificateTypeCode());
            organizationCertificateEntity.setHospitalSOID(dto.getSoid()[0]);
            manufacturerCertificateRepository.save(organizationCertificateEntity);
        }

        //给OrganizationCertificateEntity的属性赋值并保存
        Long addressId= duid.getUID();
        OrganizationAddressEntity organizationAddressEntity = new OrganizationAddressEntity();
        organizationAddressEntity.setOrgAddressId(addressId);
        organizationAddressEntity.setOrgId(orgId);
        organizationAddressEntity.setAddrDetail(dto.getAddrDetail());
        organizationAddressEntity.setAddrCountryCode(dto.getAddrCountryCode());
        organizationAddressEntity.setAddrProvinceCode(dto.getAddrProvinceCode());
        organizationAddressEntity.setAddrCityCode(dto.getAddrCityCode());
        organizationAddressEntity.setAddrCountyCode(dto.getAddrCountyCode());
        organizationAddressEntity.setAddrTownCode(dto.getAddrTownCode());
        organizationAddressEntity.setAddrVillage(dto.getAddrVillage());
        organizationAddressEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerAddressRepository.save(organizationAddressEntity);

        //操作成功返回true
        return true;
    }

    /**
     * 修改厂家信息service
     * @param dto
     * @return 返回成功/失败信息
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean modifyManufacturerDetailInfo(ManufacturerDetailInfoModifyInputDTO dto) {
        // 取OrganizationEntity并保存
        Long orgId = dto.getOrgId();
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setOrgId(orgId);
        organizationEntity.setOrgName(dto.getOrgName());
        organizationEntity.setOrgNo(dto.getOrgNo());
        organizationEntity.setPinyin(dto.getPinyin());
        organizationEntity.setWubi(dto.getWubi());
        organizationEntity.setShortCut(dto.getShortCut());
        organizationEntity.setOrgStatus(dto.getOrgStatus());
        organizationEntity.setOrgDesc(dto.getOrgDesc());
        organizationEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerRepository.save(organizationEntity);

        // 给OrganizationAliasEntity的属性赋值并保存
        Long orgAliasId= dto.getOrgAliasId();
        OrganizationAliasEntity organizationAliasEntity = new OrganizationAliasEntity();
        organizationAliasEntity.setOrgAliasId(orgAliasId);
        organizationAliasEntity.setOrgId(orgId);
        organizationAliasEntity.setOrgAlias(dto.getOrgAlias());
        organizationAliasEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerAliasRepository.save(organizationAliasEntity);

        // 给OrganizationContactEntity和OrganizationContactNameSetEntity的属性赋值并保存
        Long orgContactId= dto.getOrgContactId();
        OrganizationContactEntity organizationContactEntity = new OrganizationContactEntity();
        organizationContactEntity.setOrgContactId(orgContactId);
        organizationContactEntity.setOrgId(orgId);
        organizationContactEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerContactRepository.save(organizationContactEntity);
        Long purposeNameSetId= dto.getPurposeNameSetId();
        OrganizationContactNameSetEntity organizationContactNameSetEntity = new OrganizationContactNameSetEntity();
        organizationContactNameSetEntity.setPurposeNameId(purposeNameSetId);
        organizationContactNameSetEntity.setOrgContactId(orgContactId);
        organizationContactNameSetEntity.setFullName(dto.getFullName());
        organizationContactNameSetEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerContactNameSetRepository.save(organizationContactNameSetEntity);

        //给OrganizationTelecomEntity的属性赋值并保存
        List<ManufacturerTelecomOutputDTO> contactList = dto.getContactList();
        for (ManufacturerTelecomOutputDTO contact : contactList) {
            Long orgTelecomId= contact.getOrgTelecomId();
            OrganizationTelecomEntity organizationTelecomEntity = new OrganizationTelecomEntity();
            organizationTelecomEntity.setOrgTelecomId(orgTelecomId);
            organizationTelecomEntity.setOrgId(orgId);
            organizationTelecomEntity.setContactUsageCode(contact.getContactUsageCode());
            organizationTelecomEntity.setContactCode(contact.getContactCode());
            organizationTelecomEntity.setContactNo(contact.getContactNo());
            organizationTelecomEntity.setHospitalSOID(dto.getSoid()[0]);
            manufacturerTelecomRepository.save(organizationTelecomEntity);
        }

        //给OrganizationCertificateEntity的属性赋值并保存
        List<ManufacturerCertificateOutputDTO> certificateList = dto.getCertificateList();
        for (ManufacturerCertificateOutputDTO certificate : certificateList) {
            Long certificateId= certificate.getCertificateId();
            OrganizationCertificateEntity organizationCertificateEntity = new OrganizationCertificateEntity();
            organizationCertificateEntity.setCertificateId(certificateId);
            organizationCertificateEntity.setOrgId(orgId);
            organizationCertificateEntity.setCertificateTypeCode(certificate.getCertificateTypeCode());
            organizationCertificateEntity.setHospitalSOID(dto.getSoid()[0]);
            manufacturerCertificateRepository.save(organizationCertificateEntity);
        }

        //给OrganizationCertificateEntity的属性赋值并保存
        Long addressId= dto.getAddressId();
        OrganizationAddressEntity organizationAddressEntity = new OrganizationAddressEntity();
        organizationAddressEntity.setOrgAddressId(addressId);
        organizationAddressEntity.setOrgId(orgId);
        organizationAddressEntity.setAddrDetail(dto.getAddrDetail());
        organizationAddressEntity.setAddrCountryCode(dto.getAddrCountryCode());
        organizationAddressEntity.setAddrProvinceCode(dto.getAddrProvinceCode());
        organizationAddressEntity.setAddrCityCode(dto.getAddrCityCode());
        organizationAddressEntity.setAddrCountyCode(dto.getAddrCountyCode());
        organizationAddressEntity.setAddrTownCode(dto.getAddrTownCode());
        organizationAddressEntity.setAddrVillage(dto.getAddrVillage());
        organizationAddressEntity.setHospitalSOID(dto.getSoid()[0]);
        manufacturerAddressRepository.save(organizationAddressEntity);
        //操作成功返回true
        return true;
    }
}
