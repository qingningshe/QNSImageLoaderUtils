package top.qingningshe.qnsimageloaderutils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shihao <1406504841@qq.com> on 2016/5/6.16:18
 */
public class ContentRecAdapter extends BaseRecyclerViewAdapter<ImageLoaderType, ContentRecAdapter.RecViewHolder> {


    public ContentRecAdapter(Context context, List<ImageLoaderType> data) {
        super(context, data);
    }

    public ContentRecAdapter(Context context) {
        super(context);
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rec_content, parent, false));
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, final int position) {
        holder.tvName.setText(getItem(position).name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(position);
            }
        });
    }

    class RecViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public RecViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

}
