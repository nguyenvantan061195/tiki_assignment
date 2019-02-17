package com.lacy.example.keywork.common.util;

import com.lacy.example.keywork.common.GlobalInfo;

/**
 * @author: Created by tannv@imt-soft.com on 9/19/18.
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String aString) {
        return (aString == null) || ("".equals(aString.trim()));
    }

    /**
     * keim tra chuoi null hoac empty
     *
     * @param object
     * @return
     * @author: Created by tannv@imt-soft.com on 9/19/18.
     */
    public static boolean isNullOrEmpty(Object object) {
        boolean isNull = (object == null);
        if (!isNull) {
            if (object instanceof String) {
                isNull = isNullOrEmpty((String) object);
            }
        }
        return isNull;
    }

    /**
     * get string from resource id
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return GlobalInfo.getInstance().getResources().getString(id);
    }

    /**
     * rut ngon tin nhan mau
     *
     * @param val
     * @return
     */
    public static String shortenSMS(String val) {
        if (val.length() > 30) {
            val = String.format("%s...", val.substring(0, 30));

        } else {
            return val;
        }
        return val;
    }
}
