package top.qingningshe.qnsimageloaderutils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;

import top.qingningshe.imageloaderutilslibrary.PicassoUtils;

public class LoadImageActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "extra_type";
    private ImageLoaderType loaderType = ImageLoaderType.Picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        Intent intent = getIntent();
        loaderType = (ImageLoaderType) intent.getSerializableExtra(EXTRA_TYPE);
        if (loaderType != null) {
            final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
            scrollView.setVisibility(View.GONE);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_image);
            LoadImageRecAdapter adapter = new LoadImageRecAdapter(this, loaderType, 2);
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    scrollView.setVisibility(View.VISIBLE);
                }
            }, 100);
        }
    }

    @Override
    protected void onDestroy() {
        switch (loaderType) {
            case Picasso:
                PicassoUtils.cancel(this);
                break;
            case Glide:

                break;
            case Ion:
                break;
            case Fresco:
                break;
            case UIL:
                break;
        }
        super.onDestroy();
    }
}
