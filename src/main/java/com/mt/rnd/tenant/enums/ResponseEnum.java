package com.mt.rnd.tenant.enums;

/*
 * @author    : Irfan Nasim
 * @Date      : 18-Apr-17
 * @version   : ver. 1.0.0
 *
 * ________________________________________________________________________________________________
 *
 *  Developer				Date		     Version		Operation		Description
 * ________________________________________________________________________________________________
 *
 *
 * ________________________________________________________________________________________________
 *
 * @Project   : HIS
 * @Package   : com.sd.his.enums
 * @FileName  : ResponseEnum
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public enum ResponseEnum {

    DATA("DATA"),
    STATUS("STATUS"),
    REASON("REASON"),
    ERROR("ERROR"),
    SUCCESS("SUCCESS"),
    WARN("WARN"),
    INFO("INFO"),
    NOT_FOUND("NOT_FOUND"),
    INSUFFICIENT_PARAMETERS("SYS_ERR_02"),
    EXCEPTION("SYS_ERR_01"),
    USR_ALL_FOUND_SUCCESS("USR_SUC_01");

    private String value;

    ResponseEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
