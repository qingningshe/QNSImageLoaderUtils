package top.qingningshe.qnsimageloaderutils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rec_content);
        final ContentRecAdapter adapter = new ContentRecAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setRecOnClickListener(new RecOnClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, LoadImageActivity.class);
                intent.putExtra(LoadImageActivity.EXTRA_TYPE, adapter.getItem(position));
                MainActivity.this.startActivity(intent);
            }
        });

        for (ImageLoaderType imageLoader : ImageLoaderType.values())
            adapter.addElement(imageLoader);
    }
}
