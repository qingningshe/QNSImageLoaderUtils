package top.qingningshe.imageloaderutilslibrary;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * 基于Picasso的一个工具类
 * 依赖：compile 'com.squareup.picasso:picasso:2.5.2'
 * Note：推荐使用OKHttp配合一起使用，详见{@link com.squareup.picasso.Utils}中的createDefaultDownloader
 * Created by shihao <1406504841@qq.com> on 2016/5/6.11:32
 */
public class PicassoUtils {

    /**
     * Picasso并没有自己处理本地缓存，而是将其交给DownLoader处理，详见{@link com.squareup.picasso.Utils}中的createDefaultCacheDir
     * 和createDefaultDownloader
     */
    public static void clearDiskCache() {

    }

    /**
     * 清除内存缓存
     *
     * @param context
     * @param path    待清除的图片地址
     */
    public static void clearMemoryCache(Context context, String path) {
        Picasso.with(context).invalidate(path);
    }

    /**
     * @param context
     * @param uri
     * @see {@link PicassoUtils#clearMemoryCache(Context, String)}
     */
    public static void clearMemoryCache(Context context, Uri uri) {
        Picasso.with(context).invalidate(uri);
    }

    /**
     * @param context
     * @param file
     * @see {@link PicassoUtils#clearMemoryCache(Context, String)}
     */
    public static void clearMemoryCache(Context context, File file) {
        Picasso.with(context).invalidate(file);
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

    private static void loadImage(RequestCreator req, Object tag, boolean noPlaceholder, ImageView iv, Callback
            callback) {
        if (errorResId != 0)
            req.error(errorResId);
        if (placeholderResId != 0)
            req.placeholder(placeholderResId);
        if (noPlaceholder)
            req.noPlaceholder();
        req.tag(tag);
        req.into(iv, callback);
    }

    /**
     * 取消请求，推荐在onDestory中调用
     *
     * @param context
     */
    public static void cancel(Context context) {
        Picasso.with(context).cancelTag(context);
    }

    /**
     * 从网络加载图片
     *
     * @param context
     * @param url           图片地址
     * @param iv            图片控件
     * @param noPlaceholder 是否显示占位图
     * @param callback      加载回调
     */
    public static void loadImageFromNet(Context context, String url, ImageView iv, boolean noPlaceholder, Callback
            callback) {
        loadImage(Picasso.with(context).load(url), context, noPlaceholder, iv, callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromNet(Context, String, ImageView, boolean, Callback)}
     */
    public static void loadImageFromNet(Context context, String url, ImageView iv, Callback callback) {
        loadImageFromNet(context, url, iv, false, callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromNet(Context, String, ImageView, boolean, Callback)}
     */
    public static void loadImageFromNet(Context context, String url, ImageView iv, boolean noPlaceholder) {
        loadImageFromNet(context, url, iv, noPlaceholder, null);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromNet(Context, String, ImageView, boolean, Callback)}
     */
    public static void loadImageFromNet(Context context, String url, ImageView iv) {
        loadImageFromNet(context, url, iv, false);
    }

    /**
     * 从Asset加载图片
     *
     * @param context
     * @param fileName      图片名称，如“test1.png”,如果没有在根目录，则需要加上路径，如“image/test1.png”
     * @param iv            图片控件
     * @param noPlaceholder 是否显示占位图
     * @param callback      回调
     */
    public static void loadImageFromAsset(Context context, String fileName, ImageView iv, boolean noPlaceholder,
                                          Callback callback) {
        loadImage(Picasso.with(context).load("file:///android_asset/" + fileName), context, noPlaceholder, iv,
                callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromAsset(Context, String, ImageView, boolean, Callback)}
     */
    public static void loadImageFromAsset(Context context, String fileName, ImageView iv, boolean noPlaceholder) {
        loadImageFromAsset(context, fileName, iv, noPlaceholder, null);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromAsset(Context, String, ImageView, boolean, Callback)}
     */
    public static void loadImageFromAsset(Context context, String fileName, ImageView iv, Callback callback) {
        loadImageFromAsset(context, fileName, iv, false, callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromAsset(Context, String, ImageView, boolean, Callback)}
     */
    public static void loadImageFromAsset(Context context, String fileName, ImageView iv) {
        loadImageFromAsset(context, fileName, iv, false);
    }

    /**
     * 从drawable中加载图片
     *
     * @param context
     * @param resId         图片id
     * @param iv            图片控件
     * @param noPlaceholder 是否显示占位图
     * @param callback      回调
     */
    public static void loadImageFromResources(Context context, @DrawableRes int resId, ImageView iv, boolean
            noPlaceholder, Callback callback) {
        loadImage(Picasso.with(context).load(resId), context, noPlaceholder, iv, callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromResources(Context, int, ImageView, boolean, Callback)}  }
     */
    public static void loadImageFromResources(Context context, @DrawableRes int resId, ImageView iv, boolean
            noPlaceholder) {
        loadImageFromResources(context, resId, iv, noPlaceholder, null);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromResources(Context, int, ImageView, boolean, Callback)}  }
     */
    public static void loadImageFromResources(Context context, @DrawableRes int resId, ImageView iv, Callback
            callback) {
        loadImageFromResources(context, resId, iv, false, callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromResources(Context, int, ImageView, boolean, Callback)}  }
     */
    public static void loadImageFromResources(Context context, @DrawableRes int resId, ImageView iv) {
        loadImageFromResources(context, resId, iv, false, null);
    }

    /**
     * 从文件中加载图片
     *
     * @param context
     * @param file          图片文件
     * @param iv            图片控件
     * @param noPlaceholder 是否显示占位图
     * @param callback      回调
     */
    public static void loadImageFromFile(Context context, File file, ImageView iv, boolean noPlaceholder, Callback
            callback) {
        loadImage(Picasso.with(context).load(file), context, noPlaceholder, iv, callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromFile(Context, File, ImageView, boolean, Callback)}
     */
    public static void loadImageFromFile(Context context, File file, ImageView iv, boolean noPlaceholder) {
        loadImageFromFile(context, file, iv, noPlaceholder, null);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromFile(Context, File, ImageView, boolean, Callback)}
     */
    public static void loadImageFromFile(Context context, File file, ImageView iv, Callback callback) {
        loadImageFromFile(context, file, iv, false, callback);
    }

    /**
     * @see {@link PicassoUtils#loadImageFromFile(Context, File, ImageView, boolean, Callback)}
     */
    public static void loadImageFromFile(Context context, File file, ImageView iv) {
        loadImageFromFile(context, file, iv, false);
    }
}
