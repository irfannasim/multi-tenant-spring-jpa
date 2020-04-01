package com.mt.rnd.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;
/*
 * @author    : Irfan Nasim
 * @Date      : 16-Apr-18
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
 * @Package   : com.sd.ap.util
 * @FileName  : MTCoreUtil
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */

public class MTCoreUtil {

    private final Logger logger = LogManager.getLogger(MTCoreUtil.class);

    public static boolean isNull(String checkString) {
        if (null == checkString || checkString.trim().length() == 0 || checkString.trim().equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static boolean isValidObject(Object object) {
        if (null != object) {
            return true;
        }
        return false;
    }

    public static boolean isListEmpty(List<?> dataList) {
        if (null == dataList || dataList.isEmpty()) {
            return true;
        }
        return false;
    }

    /***
     * Valid mean , not null and not empty the list
     *
     *
     */
    public static boolean isListValid(List<?> dataList) {
        if (null == dataList || dataList.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isMapEmpty(Map<?, ?> dataMap) {
        if (null == dataMap || dataMap.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isSetEmpty(Set<?> dataSet) {
        if (null == dataSet || dataSet.isEmpty()) {
            return true;
        }
        return false;
    }
}