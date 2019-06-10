package com.winning.material.mdm.service;

import com.winning.base.akso.common.page.WinPagedList;
import com.winning.material.mdm.domain.request.ManufacturerInfoListQueryInputDTO;
import com.winning.material.mdm.domain.response.ManufacturerInfoListQueryOutputDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerServiceTest
 * @description 药品组织生产厂家服务测试
 * @date 2019/6/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManufacturerServiceTest {
    @Autowired
    private MdmManufacturerService mdmManufacturerService;

    @Test
    public void queryManufacturerInfoList(){
        ManufacturerInfoListQueryInputDTO dto = new ManufacturerInfoListQueryInputDTO();
        dto.setOrgId(4300000022L);
        //dto.setOrgStatus(152442L);
        //dto.setKeyword("yy");
        //dto.setKeyword("sh");
        WinPagedList<ManufacturerInfoListQueryOutputDTO> resultList = mdmManufacturerService.queryManufacturerInfoList(dto);
        System.out.println("查询到数据：" + resultList.getCount() + "条");
        System.out.println("详细数据是：" + resultList.getData());
    }
}
