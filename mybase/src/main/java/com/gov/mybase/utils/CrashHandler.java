package com.gov.mybase.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;

/**
 * ===================================
 *
 * @PackageName ：com.gov.mybase.utils
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2021/11/11 18:22
 * @Version ：1.0
 * ===================================
 */
public class CrashHandler implements UncaughtExceptionHandler {
    private static CrashHandler INSTANCE = null;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (CrashHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    private Context mContext;
    private UncaughtExceptionHandler mDefautHandler;

    public void init(Context context) {
        mContext = context;
        mDefautHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (!handlerCrash(ex) && mDefautHandler != null) {
            // 没有处理还交给系统默认的处理器
            mDefautHandler.uncaughtException(thread, ex);
        } else {
            // 已经处理，结束进程
            Process.killProcess(Process.myPid());
            System.exit(1);
        }


    }

    /**
     * 自定义处理策略
     *
     * @return true：已处理
     */
    private boolean handlerCrash(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 收集设备信息、版本信息、异常信息
        String info = collectDeviceInfo(mContext, ex);
        // 本地固化存储
        saveInfo(info);

        // 上传服务器（该功能可以独立到外边，定时上传或者进入应用时检测上传）

        return true;
    }

    /**
     * 收集设备信息
     *
     * @param c
     * @param ex
     */
    private String collectDeviceInfo(Context c, Throwable ex) {
        Map<String, String> infos = new HashMap<String, String>();
        // 收集版本信息
        try {
            PackageManager pm = c.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(c.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionCode = pi.versionCode + "";
                String versionName = TextUtils.isEmpty(pi.versionName) ? "没有版本名称" : pi.versionName;
                infos.put("versionCode", versionCode);
                infos.put("versionName", versionName);
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        // 收集设备信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
            }
        }

        // 收集异常信息
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();

        // 转化为字符串
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        sb.append(result);

        return sb.toString();
    }


    /**
     * 保存异常信息到本地
     *
     * @param infos
     */
    private void saveInfo(String infos) {
        // 把采集到的信息写入到本地文件
        LogUtils.e("错误信息：" + infos);
        writeData(infos);
    }

    private void writeData(String strInfo) {
        String filePath = AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath();
        String fileName = "data.txt";
        writeTxtToFile(strInfo, filePath, fileName);
    }

    // 将字符串写入到文本文件中
    private void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                LogUtils.d("TestFile===="+"Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            LogUtils.e("TestFile===="+"Error on write File:" + e);
        }
    }

//生成文件

    private File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

//生成文件夹

    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            LogUtils.i("error:"+ e + "");
        }
    }
}
