package com.winning.material.mdm.domain.constant;

/**
 * 程序逻辑删除标志
 * @author cuibx
 * @date 2019/05/14
 */
public enum DelFlagEmu {
    /**
     * 未删除
     */
    IS_NOT_DEL(0,"未删除"),

    /**
     * 删除
     */
    IS_DEL(1,"删除");

    private Integer value;
    private String  name;

   DelFlagEmu(Integer value, String name)
   {
     this.value=value;
     this.name=name;
   }

    public Integer getValue()
    {
        return this.value;
    }

    public String getName()
    {
        return this.name;
    }

}
