package com.gov.mybase.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ===================================
 *
 * @PackageName ：com.gov.mybase.utils
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/7/24 16:59
 * @Version ：1.0
 * ===================================
 */
public class BitmapUtils {

    static BackInfo bcinfo;
    public static void saveBitmap(Context context,Bitmap bitmap) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        String filename;//声明文件名
        //以保存时间为文件名
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        filename = sdf.format(date);
        File file = new File(extStorageDirectory, filename + ".JPEG");//创建文件，第一个参数为路径，第二个参数为文件名
        try {
            outStream = new FileOutputStream(file);//创建输入流
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.close();
            //       这三行可以实现相册更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            //这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！*/
//            Toast.makeText(this, "saved",
//                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void saveImageToGallery2(Context context, Bitmap image,BackInfo bc){
        Long mImageTime = System.currentTimeMillis();
        String imageDate = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(mImageTime));
        String SCREENSHOT_FILE_NAME_TEMPLATE = "winetalk_%s.png";//图片名称，以"winetalk"+时间戳命名
        String mImageFileName = String.format(SCREENSHOT_FILE_NAME_TEMPLATE, imageDate);

        final ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES
                + File.separator + "winetalk"); //Environment.DIRECTORY_SCREENSHOTS:截图,图库中显示的文件夹名。"dh"
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, mImageFileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        values.put(MediaStore.MediaColumns.DATE_ADDED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_EXPIRES, (mImageTime + DateUtils.DAY_IN_MILLIS) / 1000);
        values.put(MediaStore.MediaColumns.IS_PENDING, 1);

        ContentResolver resolver = context.getContentResolver();
        final Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try {
            // First, write the actual data for our screenshot
            try (OutputStream out = resolver.openOutputStream(uri)) {
                if (!image.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                    throw new IOException("Failed to compress");
                }
            }
            // Everything went well above, publish it!
            values.clear();
            values.put(MediaStore.MediaColumns.IS_PENDING, 0);
            values.putNull(MediaStore.MediaColumns.DATE_EXPIRES);
            resolver.update(uri, values, null, null);
            Toast.makeText(context,"保存成功",
                    Toast.LENGTH_SHORT).show();
            bcinfo.isOK(true);
        }catch (IOException e){
            bcinfo.isOK(false);
            resolver.delete(uri, null);
            Toast.makeText(context, "保存失败",
                    Toast.LENGTH_SHORT).show();

        }
    }

    /*
     * 将图片 bitmap保存到图库
     */
    public static void saveBitmap2(Context activity,Bitmap bitmap,BackInfo bc) {
        bcinfo = bc;
        //因为xml用的是背景，所以这里也是获得背景
//获取参数Bitmap方式一： Bitmap bitmap=((BitmapDrawable)(imageView.getBackground())).getBitmap();
//获取参数Bitmap方式二： Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        //t设置图片名称，要保存png，这里后缀就是png，要保存jpg，后缀就用jpg
        String imageName = System.currentTimeMillis() + "code.png";

        //创建文件，安卓低版本的方式
        //  File file=new File(Environment.getExternalStorageDirectory() +"/test.png");

        //Android Q  10为每个应用程序提供了一个独立的在外部存储设备的存储沙箱，没有其他应用可以直接访问您应用的沙盒文件
        File f = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(f.getPath() + "/" + imageName);//创建文件
        //        file.getParentFile().mkdirs();
        try {
            //文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //压缩图片，如果要保存png，就用Bitmap.CompressFormat.PNG，要保存jpg就用Bitmap.CompressFormat.JPEG,质量是100%，表示不压缩
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            //写入，这里会卡顿，因为图片较大
            fileOutputStream.flush();
            //记得要关闭写入流
            fileOutputStream.close();
            //成功的提示，写入成功后，请在对应目录中找保存的图片
            Log.e("写入成功！位置目录", f.getPath() + "/" + imageName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //失败的提示，这里的Toast工具类，大家用自己项目中的即可，若不需要可以删除
//            ToastUtil.showToast(e.getMessage());

        } catch (IOException e) {
            bcinfo.isOK(false);
            e.printStackTrace();
            //失败的提示
//            ToastUtil.showToast(e.getMessage());
        }

        // 下面的步骤必须有，不然在相册里找不到图片，若不需要让用户知道你保存了图片，可以不写下面的代码。
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(activity.getContentResolver(),
                    file.getAbsolutePath(), imageName, null);
//            ToastUtil.showToast("保存成功，请您到 相册/图库 中查看");
            bcinfo.isOK(true);
        } catch (FileNotFoundException e) {
//            ToastUtil.showToast("保存失败");
            bcinfo.isOK(false);
            e.printStackTrace();

        }
        // 最后通知图库更新
        activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));
    }

    public interface BackInfo {
        void isOK(boolean isok);
    }
}
