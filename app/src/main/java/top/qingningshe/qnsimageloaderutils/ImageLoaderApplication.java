package top.qingningshe.qnsimageloaderutils;

import android.app.Application;

import top.qingningshe.imageloaderutilslibrary.GlideUtils;
import top.qingningshe.imageloaderutilslibrary.PicassoUtils;

/**
 * Created by shihao <1406504841@qq.com> on 2016/5/9.10:02
 */
public class ImageLoaderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PicassoUtils.init(R.drawable.ic_error, R.drawable.ic_place_holder);
        GlideUtils.init(R.drawable.ic_error, R.drawable.ic_place_holder);
    }
}
