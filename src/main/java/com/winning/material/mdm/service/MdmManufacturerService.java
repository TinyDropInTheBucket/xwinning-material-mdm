package com.winning.material.mdm.service;

import com.winning.base.akso.common.page.WinPagedList;
import com.winning.material.mdm.domain.entity.ManufacturerContactEntity;
import com.winning.material.mdm.domain.entity.ManufacturerEntity;
import com.winning.material.mdm.domain.entity.OrganizationContactNameSetEntity;
import com.winning.material.mdm.domain.request.ManufacturerInfoListQueryInputDTO;
import com.winning.material.mdm.domain.response.ManufacturerInfoListQueryOutputDTO;
import com.winning.material.mdm.service.repository.ManufacturerRepostiory;
import com.winning.material.mdm.util.CommonNativeSqlUtil;
import com.winning.material.mdm.util.ServiceCommonUtil;
import com.winning.pts.utils.collection.CollectionUtil;
import com.winning.pts.utils.mapper.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author f_zl
 * @version v1.0
 * @className MdmManufacturerService
 * @description 分页查询生产厂家信息
 * @date 2019/6/4
 */
@Service
public class MdmManufacturerService {
    @Autowired
    private ManufacturerRepostiory manufacturerRepostiory;

    /*@Autowired
    private ManufacturerContactRepository manufacturerContactRepository;*/

    private <T> Specification<T> buildSpecForManufacturerInfoQuery(ManufacturerInfoListQueryInputDTO dto){
        Map<String,Object> specMap=new HashMap<String,Object>();
        if (dto.getSoid() != null) {
            specMap.put("soid", dto.getSoid()[0]);
        }
        if (dto.getOrgId() != null) {
            specMap.put("orgId", dto.getOrgId());
        }
        if (dto.getOrgStatus() != null) {
            specMap.put("orgStatus", dto.getOrgStatus());
        }
        org.springframework.data.jpa.domain.Specification<T> spec= CommonNativeSqlUtil.initSpecification(specMap,false);
        return spec;
    }

    public WinPagedList<ManufacturerInfoListQueryOutputDTO> queryManufacturerInfoList(ManufacturerInfoListQueryInputDTO dto) {
        Pageable pageable = ServiceCommonUtil.convert(dto);
        HashMap<String, Object> equalMap = new HashMap<>();
        //HashMap<String, List<Object>> inMap = new HashMap<>();
        HashMap<String, Object> likeMap = new HashMap<>();
        String keyword = dto.getKeyword();
        if (null != keyword){
            likeMap.put("orgName", keyword);
            likeMap.put("pinyin", keyword);
            likeMap.put("wubi", keyword);
            likeMap.put("shortCut", keyword);
            likeMap.put("orgDesc", keyword);
        }
        if (null != dto.getOrgStatus()) {
            equalMap.put("orgStatus",dto.getOrgStatus());
        }
        if (null != dto.getSoid()) {
            equalMap.put("hospitalSOID",dto.getSoid()[0]);
        }
        if (null != dto.getOrgId()) {
            equalMap.put("orgId",dto.getOrgId());
        }
        if (null != dto.getOrgNO()) {
            equalMap.put("orgNo",dto.getOrgNO());
        }
        if (null != dto.getOrgTypeCode()) {
            equalMap.put("orgTypeCode",dto.getOrgTypeCode());
        }
        if (null != dto.getParentOrgId()) {
            equalMap.put("parentOrgId",dto.getParentOrgId());
        }
        if (likeMap.size() > 0 || equalMap.size() > 0) {
            equalMap.put("isDel",0);
        }
        Specification<ManufacturerEntity> spec = ServiceCommonUtil.buildSpecLikeOr(equalMap, null, likeMap);
        List<ManufacturerEntity> resultPageList = manufacturerRepostiory.findAll(spec, pageable).getContent();
        if(CollectionUtil.isEmpty(resultPageList)) {
            return  new WinPagedList<>(0L, Collections.emptyList());
        }

        //按供制造商拼接联系人名单后给输出dto

        StringBuffer stringBuffer;
        for (ManufacturerEntity entity:resultPageList) {
            stringBuffer=new StringBuffer("");
            int count =0;
            for (ManufacturerContactEntity contactEntity:entity.getContactList()) {
                for (OrganizationContactNameSetEntity nameSetEntity:contactEntity.getContactNameList()) {
                    count++;
                    if (count == 1) {
                        stringBuffer.append(nameSetEntity.getFullName());
                        continue;
                    }
                    stringBuffer.append("," + nameSetEntity.getFullName());
                }
            }
            entity.setNameSet(new String(stringBuffer));
        }

        List<ManufacturerInfoListQueryOutputDTO> resultOutputPageList = BeanMapper.mapList(resultPageList,
                ManufacturerEntity.class, ManufacturerInfoListQueryOutputDTO.class);
        if(CollectionUtil.isEmpty(resultOutputPageList)) {
            return  new WinPagedList<>(0L, Collections.emptyList());
        }


        return  new WinPagedList<>((long) resultPageList.size(), resultOutputPageList);
    }
}
