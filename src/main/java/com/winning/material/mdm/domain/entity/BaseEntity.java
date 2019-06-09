package com.winning.material.mdm.domain.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * ClassName: BaseEntity
 * Description:
 * date 2019/4/1 17:33
 *
 * @author liu_chao
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * 逻辑删除
     */
    @Basic
    @Column(name = "IS_DEL")
    private Integer isDel;

    /**
     * 最后修改日期时间
     */
    @Basic
    @Column(name = "MODIFIED_AT")
    private Date modifiedAt;

    /**
     * 创建日期时间
     */
    @Basic
    @Column(name = "CREATED_AT")
    private Date createdAt;

    /**
     * 医院SOID
     */
    @Basic
    @Column(name = "HOSPITAL_SOID")
    private Long hospitalSOID;
    
    /**
     * 返回主键ID
     * @return
     */
    public abstract Long getId();
}
