package com.gov.mybase.utils;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;

/**
 * @date: 2021/2/9
 * @author: 冯小川
 * @package: com.gov.govmanage.utils
 * @description
 */
public class LogUtils {

    private static Printer getPrinter() {
        return Logger.t("平台日志打印");
    }

    public static void i(String message) {
        getPrinter().i(message);
    }

    public static void d(String message) {
        getPrinter().d(message);
    }

    public static void e(String message) {
        getPrinter().e(message);
    }

    public static void v(String message) {
        getPrinter().v(message);
    }

    public static void w(String message) {
        getPrinter().w(message);
    }

    public static void wtf(String message) {
        getPrinter().wtf(message);
    }


}
