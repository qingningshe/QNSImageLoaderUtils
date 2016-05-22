package top.qingningshe.qnsimageloaderutils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义的数据适配器基类，适用于RecyclerView
 * Created by shihao <1406504841@qq.com> on 2016/2/22.15:14
 */
public abstract class BaseRecyclerViewAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    protected List<T> data = new ArrayList<T>();
    protected Context context;
    protected RecOnClickListener listener;


    public void setRecOnClickListener(RecOnClickListener listener) {
        if (listener != null)
            this.listener = listener;
    }

    public BaseRecyclerViewAdapter(Context context, List<T> data) {
        if (data != null)
            this.data.addAll(data);
        this.context = context;
    }

    public BaseRecyclerViewAdapter(Context context) {
        this(context, null);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    /**
     * 通过下标获取指定的数据
     *
     * @param position
     * @return 对应的数据
     */
    public T getItem(int position) {
        return data.get(position);
    }

    /**
     * 获取数据源大小
     *
     * @return 数据源大小
     */
    public int getDataSize() {
        return data == null ? 0 : data.size();
    }

    /**
     * 设置数据源
     *
     * @param data 数据源
     */
    public void setData(List<T> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
        } else
            this.data.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置数据源
     *
     * @param data 数据源
     */
    public void setData(T[] data) {
        if (data != null) {
            setData(Arrays.asList(data));
        }
    }

    /**
     * 添加数据
     *
     * @param data 待添加数据源
     */
    public void addData(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据
     *
     * @param data 待添加数据源
     */
    public void addData(T[] data) {
        if (data != null && data.length > 0) {
            addData(Arrays.asList(data));
        }
    }

    /**
     * 清空数据
     */
    public void clearData() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单条数据
     *
     * @param element 待添加数据
     */
    public void addElement(T element) {
        if (element != null) {
            this.data.add(element);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除某条元素
     *
     * @param position 待移除元素所在位置
     */
    public void removeElement(int position) {
        if (position >= 0 && position < data.size()) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 更新某条元素
     *
     * @param element  新的数据
     * @param position 所在位置
     */
    public void updateElement(T element, int position) {
        if (element != null && position >= 0 && position < data.size()) {
            data.set(position, element);
            notifyItemChanged(position);
        }
    }

}
