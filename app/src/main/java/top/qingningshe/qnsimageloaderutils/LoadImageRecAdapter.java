package top.qingningshe.qnsimageloaderutils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

import top.qingningshe.imageloaderutilslibrary.GlideUtils;
import top.qingningshe.imageloaderutilslibrary.PicassoUtils;

/**
 * Created by shihao <1406504841@qq.com> on 2016/5/9.9:50
 */
public class LoadImageRecAdapter extends BaseRecyclerViewAdapter<String, LoadImageRecAdapter.RecViewHolder> {


    private ImageLoaderType imageLoader;
    private int spanCount;

    public LoadImageRecAdapter(Context context, ImageLoaderType imageLoader, int spanCount) {
        super(context);
        this.imageLoader = imageLoader;
        this.spanCount = spanCount;
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rec_image, parent, false));
    }

    private int drawable[] = {R.drawable.drawable_test1, R.drawable.drawable_test2};
    private String assets[] = {"assets_test1.jpg", "image/assets_test2.jpg"};
    private String files[] = {"", ""};
    private String net[] = {"http://www.qqya.com/qqyaimg/allimg/140227/1KI36229-3.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3808366342,577428906&fm=206&gp=0.jpg",
            "http://img04.tooopen.com/images/20130424/tooopen_08241741.jpg",
            "http://pic17.nipic.com/20111020/8241408_040700262000_2.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1567950977,573273456&fm=206&gp=0.jpg",
            "http://imga1.pic21.com/bizhi/140212/07423/s10.jpg"};


    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        Log.d("adpter", "height:" + params.height + "  width:" + params.width);
        params.height = params.width = getScreenWidth(context) / spanCount;
        holder.itemView.setLayoutParams(params);
        Log.d("adpter", "height:" + params.height + "  width:" + params.width);
        switch (imageLoader) {
            case Picasso:
                if (position < 2)
                    PicassoUtils.loadImageFromResources(context, drawable[position], holder.imageView);
                else if (position < 4)
                    PicassoUtils.loadImageFromAsset(context, assets[position - 2], holder.imageView);
                else if (position < 6)
                    PicassoUtils.loadImageFromFile(context, new File(files[position - 4]), holder.imageView);
                else
                    PicassoUtils.loadImageFromNet(context, net[position - 6], holder.imageView);
                break;
            case Glide:
                if (position < 2)
                    GlideUtils.loadImageFromResources(context, drawable[position], holder.imageView);
                else if (position < 4)
                    GlideUtils.loadImageFromAsset(context, assets[position - 2], holder.imageView);
                else if (position < 6)
                    GlideUtils.loadImageFromFile(context, new File(files[position - 4]), holder.imageView);
                else
                    GlideUtils.loadImageFromNet(context, net[position - 6], holder.imageView);
                break;
            case Ion:
                break;
            case Fresco:
                break;
            case UIL:
                break;
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return 屏幕宽度，单位px
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        CardView cardView;

        public RecViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            cardView = (CardView) itemView.findViewById(R.id.cardview_image);
        }
    }
}
