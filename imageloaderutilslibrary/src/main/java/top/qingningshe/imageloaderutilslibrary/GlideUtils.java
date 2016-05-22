package top.qingningshe.imageloaderutilslibrary;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 基于Glide的一个工具类,
 * 依赖：compile 'com.github.bumptech.glide:glide:3.7.0'
 * Created by shihao <1406504841@qq.com> on 2016/5/9.10:40
 */
public class GlideUtils {
    /**
     * 清空本地缓存,在子线程中调用
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 返回默认缓存目录下的缓存大小,在子线程中调用
     *
     * @param context
     * @return 占用空间 单位：Byte
     */
    public static long getDiskCacheSize(Context context) {
        File dir = Glide.getPhotoCacheDir(context);
        return getFolderSize(dir);
    }

    /**
     * 返回默认缓存目录下的缓存大小,在子线程中调用
     *
     * @param context
     * @return 占用空间 e. 0.55M  2.55M
     */
    public static String getDiskCacheSizeString(Context context) {
        File dir = Glide.getPhotoCacheDir(context);
        double d = getFolderSize(dir) / (1024 * 1024);
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(d) + "M";
    }

    /**
     * 计算文件夹大小
     *
     * @param dir
     * @return
     */
    public static long getFolderSize(File dir) {
        int size = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                size += getFolderSize(file);
            } else {
                size += file.length();
            }
        }
        return size;
    }


    /**
     * 清空内存缓存
     *
     * @param context
     */
    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @DrawableRes
    private static int errorResId;
    @DrawableRes
    private static int placeholderResId;

    /**
     * 设置默认图片
     *
     * @param error 错误图
     * @param place 占位图
     */
    public static void init(@DrawableRes int error, @DrawableRes int place) {
        errorResId = error;
        placeholderResId = place;
    }

    private static void loadImage(DrawableTypeRequest req, ImageView iv, Transformation transformation) {
        if (errorResId != 0)
            req.error(errorResId);
        if (placeholderResId != 0)
            req.placeholder(placeholderResId);
        if (transformation != null)
            req.bitmapTransform(transformation);
        req.crossFade().into(iv);
    }

    /**
     * 从网络获取图片
     *
     * @param context
     * @param url            图片地址
     * @param iv             控件
     * @param transformation 相应变换,可以依赖 compile 'jp.wasabeef:glide-transformations:2.0.1'
     */
    public static void loadImageFromNet(Context context, String url, ImageView iv, Transformation transformation) {
        loadImage(Glide.with(context).load(url), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromNet(Context, String, ImageView, Transformation)}
     */
    public static void loadImageFromNet(Context context, String url, ImageView iv) {
        loadImage(Glide.with(context).load(url), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromNet(Context, String, ImageView, Transformation)}
     */
    public static void loadImageFromNet(Activity activity, String url, ImageView iv, Transformation transformation) {
        loadImage(Glide.with(activity).load(url), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromNet(Context, String, ImageView, Transformation)}
     */
    public static void loadImageFromNet(Activity activity, String url, ImageView iv) {
        loadImage(Glide.with(activity).load(url), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromNet(Context, String, ImageView, Transformation)}
     */
    public static void loadImageFromNet(Fragment fragment, String url, ImageView iv, Transformation transformation) {
        loadImage(Glide.with(fragment).load(url), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromNet(Context, String, ImageView, Transformation)}
     */
    public static void loadImageFromNet(Fragment fragment, String url, ImageView iv) {
        loadImage(Glide.with(fragment).load(url), iv, null);
    }

    /**
     * 从Asset加载图片
     *
     * @param context
     * @param fileName       图片名称，如“test1.png”,如果没有在根目录，则需要加上路径，如“image/test1.png”
     * @param iv             图片控件
     * @param transformation 相应变换,可以依赖 compile 'jp.wasabeef:glide-transformations:2.0.1'
     */
    public static void loadImageFromAsset(Context context, String fileName, ImageView iv, Transformation
            transformation) {
        loadImage(Glide.with(context).load("file:///android_asset/" + fileName), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromAsset(Context, String, ImageView)}
     */
    public static void loadImageFromAsset(Context context, String fileName, ImageView iv) {
        loadImage(Glide.with(context).load("file:///android_asset/" + fileName), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromAsset(Context, String, ImageView)}
     */
    public static void loadImageFromAsset(Activity activity, String fileName, ImageView iv, Transformation
            transformation) {
        loadImage(Glide.with(activity).load("file:///android_asset/" + fileName), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromAsset(Context, String, ImageView)}
     */
    public static void loadImageFromAsset(Activity activity, String fileName, ImageView iv) {
        loadImage(Glide.with(activity).load("file:///android_asset/" + fileName), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromAsset(Context, String, ImageView)}
     */
    public static void loadImageFromAsset(Fragment fragment, String fileName, ImageView iv, Transformation
            transformation) {
        loadImage(Glide.with(fragment).load("file:///android_asset/" + fileName), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromAsset(Context, String, ImageView)}
     */
    public static void loadImageFromAsset(Fragment fragment, String fileName, ImageView iv) {
        loadImage(Glide.with(fragment).load("file:///android_asset/" + fileName), iv, null);
    }

    /**
     * 从drawable中加载图片
     *
     * @param context
     * @param resId          图片资源id
     * @param iv             图片控件
     * @param transformation 相应变换,可以依赖 compile 'jp.wasabeef:glide-transformations:2.0.1'
     */
    public static void loadImageFromResources(Context context, @DrawableRes int resId, ImageView iv, Transformation
            transformation) {
        loadImage(Glide.with(context).load(resId), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromResources(Context, int, ImageView, Transformation)}
     */
    public static void loadImageFromResources(Context context, @DrawableRes int resId, ImageView iv) {
        loadImage(Glide.with(context).load(resId), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromResources(Context, int, ImageView, Transformation)}
     */
    public static void loadImageFromResources(Activity activity, @DrawableRes int resId, ImageView iv, Transformation
            transformation) {
        loadImage(Glide.with(activity).load(resId), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromResources(Context, int, ImageView, Transformation)}
     */
    public static void loadImageFromResources(Activity activity, @DrawableRes int resId, ImageView iv) {
        loadImage(Glide.with(activity).load(resId), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromResources(Context, int, ImageView, Transformation)}
     */
    public static void loadImageFromResources(Fragment fragment, @DrawableRes int resId, ImageView iv, Transformation
            transformation) {
        loadImage(Glide.with(fragment).load(resId), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromResources(Context, int, ImageView, Transformation)}
     */
    public static void loadImageFromResources(Fragment fragment, @DrawableRes int resId, ImageView iv) {
        loadImage(Glide.with(fragment).load(resId), iv, null);
    }

    /**
     * 从文件中加载图片
     *
     * @param context
     * @param file           图片文件
     * @param iv             图片控件
     * @param transformation 图片变化
     */
    public static void loadImageFromFile(Context context, File file, ImageView iv, Transformation transformation) {
        loadImage(Glide.with(context).load(file), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromFile(Activity, File, ImageView, Transformation)}
     */
    public static void loadImageFromFile(Context context, File file, ImageView iv) {
        loadImage(Glide.with(context).load(file), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromFile(Activity, File, ImageView, Transformation)}
     */
    public static void loadImageFromFile(Activity activity, File file, ImageView iv, Transformation transformation) {
        loadImage(Glide.with(activity).load(file), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromFile(Activity, File, ImageView, Transformation)}
     */
    public static void loadImageFromFile(Activity activity, File file, ImageView iv) {
        loadImage(Glide.with(activity).load(file), iv, null);
    }

    /**
     * @see {@link GlideUtils#loadImageFromFile(Activity, File, ImageView, Transformation)}
     */
    public static void loadImageFromFile(Fragment fragment, File file, ImageView iv, Transformation transformation) {
        loadImage(Glide.with(fragment).load(file), iv, transformation);
    }

    /**
     * @see {@link GlideUtils#loadImageFromFile(Activity, File, ImageView, Transformation)}
     */
    public static void loadImageFromFile(Fragment fragment, File file, ImageView iv) {
        loadImage(Glide.with(fragment).load(file), iv, null);
    }
}
