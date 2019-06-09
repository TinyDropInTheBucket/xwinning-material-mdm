package com.winning.material.mdm.service.repository;

import com.winning.material.mdm.domain.entity.ManufacturerContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author f_zl
 * @version v1.0
 * @className ManufacturerContactRepository
 * @description
 * @date 2019/6/9
 */
public interface ManufacturerContactRepository extends JpaRepository<ManufacturerContactEntity, Integer> {
}
