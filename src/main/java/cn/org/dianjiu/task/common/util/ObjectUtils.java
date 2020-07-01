package cn.org.dianjiu.task.common.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Dianjiu on 2020/2/15.
 */
public class ObjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }

        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error("ObjectUtils checkObjAllFieldsIsNull failed:Exception", e);
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 对象属性拷贝 <br>
     * 将源对象的属性拷贝到目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (BeansException e) {
            logger.error("ObjectUtils property copy  failed :BeansException", e);
        } catch (Exception e) {
            logger.error("ObjectUtils property copy failed:Exception", e);
        }
    }

    /**
     * 字符串非空判断
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }

    /**/
    /**
     * change UTF8 To GB2312
     * @param target
     * @return
     */
    public static final String UTF82GB2312(String target) {
        try {
            return new String(target.getBytes("UTF-8"), "gb2312");
        } catch (Exception localException) {
            System.err.println("UTF8 TO GB2312 change error!");
        }
        return null;
    }

    /**
     * change UTF8 To GBK
     * @param target
     * @return
     */
    public static final String UTF82GBK(String target) {
        try {
            return new String(target.getBytes("UTF-8"), "GBK");
        } catch (Exception localException) {
            System.err.println("UTF8 TO GBK change error!");
        }
        return null;
    }

    /**
     * change UTF8 To ISO8859-1
     * @param target
     * @return
     */
    public static final String UTF82ISO(String target) {
        try {
            return new String(target.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception localException) {
            System.err.println("UTF8 TO ISO8859-1 change error!");
        }
        return null;
    }

    /**
     * change Windows-1252 To UTF-8
     * @param target
     * @return
     */
    public static final String Windows1252UTF8(String target) {
        try {
            return new String(target.getBytes("Windows-1252"), "UTF-8");
        } catch (Exception localException) {
            System.err.println("Windows1252 To UTF8 chage error");
        }
        return null;
    }


    public static boolean isEmpty(Long l) {
        return ((l == null) || (l.longValue() == 0L));
    }

    public static boolean isNotEmpty(Long l){
        return !isEmpty(l);
    }

    public static boolean isEmpty(Object objs) {
        return (objs == null);
    }

    public static boolean isNotEmpty(Object objs){
        return !isEmpty(objs);
    }

    public static boolean isEmpty(Object[] objs) {
        return ((objs == null) || (objs.length == 0));
    }

    public static boolean isNotEmpty(Object[] objs){
        return !isEmpty(objs);
    }

    public static boolean isEmpty(Collection<?> objs) {
        return ((objs == null) || (objs.size() <= 0));
    }

    public static boolean isNotEmpty(Collection<?> objs){
        return !isEmpty(objs);
    }

    public static boolean isEmpty(byte[] objs) {
        return ((objs == null) || (objs.length == 0));
    }

    public static boolean isNotEmpty(byte[] objs){
        return !isEmpty(objs);
    }

    public static void main(String[] args) {
        System.out.println(ObjectUtils.isEmpty((String) null));        //true
        System.out.println(ObjectUtils.isEmpty(""));          //true
        System.out.println(ObjectUtils.isEmpty("   "));       //false
        System.out.println(ObjectUtils.isEmpty("dd"));        //false
        System.out.println("----------------------------");
        System.out.println(ObjectUtils.isBlank(null));        //true
        System.out.println(ObjectUtils.isBlank(""));          //true
        System.out.println(ObjectUtils.isBlank("   "));       //true
        System.out.println(ObjectUtils.isBlank("dd"));        //false
    }
}
