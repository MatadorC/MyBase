package com.gov.mybase.utils;

import java.util.regex.Pattern;

/**
 * ===================================
 *
 * @界面功能
 * @时 间:  2018/12/27 11:05
 * @作 者: 李想
 * @描 述: 校验器：利用正则表达式校验邮箱、手机号等
 * ===================================
 */
public class Validator {
    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 移动手机号码的正则表达式。
     */
    private static final String REGEX_CHINA_MOBILE = "1(3[4-9]|4[7]|5[012789]|8[278])\\d{8}";

    /**
     * 联通手机号码的正则表达式。
     */
    private static final String REGEX_CHINA_UNICOM = "1(3[0-2]|5[56]|8[56])\\d{8}";

    /**
     * 电信手机号码的正则表达式。
     */
    private static final String REGEX_CHINA_TELECOM = "(?!00|015|013)(0\\d{9,11})|(1(33|53|80|89)\\d{8})";

    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_PHONE_NUMBER = "^(0(10|2\\d|[3-9]\\d\\d)[- ]{0,3}\\d{7,8}|0?1[3584]\\d{9})$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    public static final String REGEX_USERNAME2 = "^[0-9a-zA-Z_]{1,}$";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_PHONE_NUMBER, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 判断一个字符串是否含有中文
     *
     * @param str
     * @return
     */
    public static boolean isHaveChinese(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray()) {
            if (isChinese(String.valueOf(c)))
                return true;// 有一个中文字符就返回
        }
        return false;
    }

    /**
     * 返回字符串中的汉字数量
     *
     * @param str
     * @return
     */
    public static int haveChineseNum(String str) {
        int nums = 0;
        if (str == null)
            return nums;
        for (char c : str.toCharArray()) {
            if (isChinese(String.valueOf(c)))
                nums++;// 有一个中文字符就+1
        }
        return nums;
    }


    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 校验用户名
     * 由数字、26个英文字母或者下划线组成的字符串
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername2(String username) {
        return Pattern.matches(REGEX_USERNAME2, username);
    }

    /**
     * 根据正则方式去掉小括号、英文、小数点、数字
     *
     * @param info
     */
    public static String replaceInfo(String info) {
//        String strValidatator = "\\d+\\.+\\d|\\d|\\(+[a-zA-Z]+\\)";
        String strValidatator = "\\d+\\.+\\d|\\d|\\(+[a-zA-Z]+\\)|\\“+[零一二三四五六七八九十]+\\”|\\(+[^x]{3}+\\)|\\（+[^x]{3}+\\）";
        return info.replaceAll(strValidatator, "");
    }

    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取最后所选中的段落内容，进行特殊字符过滤
     *
     * @param strInfo
     * @return
     */
    public static String getRegexStr(String strInfo) {
        String strBack = strInfo.startsWith("  ") ? strInfo.substring(2) : strInfo;
//        strBack = strBack.length() >= 20 ? strBack.substring(0, 20) : strBack;
        return replaceInfo(strBack.replaceAll("\\r\\n", "")
                .replaceAll("\\r", "")
                .replaceAll("\\n", "")
                .replaceAll("\\+", "")
                .replaceAll("<br>", "")
                .replaceAll(" ", "")
                .replaceAll("　", "")
                .replaceAll("&nbsp；", "")
                .replaceAll("&nbsp;", "")
                .replaceAll("&nbsp", "")
                .replaceAll("&gt;", "<")
                .replaceAll("&lt;", ">"));
    }

    /**
     * 替换特殊空格和换行
     *
     * @param strInfo
     * @return
     */
    public static String getStrReplace(String strInfo) {
        return strInfo.replaceAll("\\r\\n", "")
                .replaceAll("\\r", "")
                .replaceAll("\\n", "")
                .replaceAll("<br>", "")
                .replaceAll("\\+", "")
                .replaceAll(" ", "")
                .replaceAll("　", "")
                .replaceAll("  ", "")
                .replaceAll("&nbsp；", "")
                .replaceAll("&nbsp;", "")
                .replaceAll("&nbsp", "")
                .replaceAll("&gt;", "<")
                .replaceAll("&lt;", ">");
    }
}
