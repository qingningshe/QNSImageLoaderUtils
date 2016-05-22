package top.qingningshe.qnsimageloaderutils;

/**
 * recyclerview 点击事件接口
 * Created by shihao <1406504841@qq.com> on 2016/3/1.9:24
 */
public class RecOnClickListener {

    /**
     * 每个item的点击事件
     *
     * @param position 当前的item所在位置
     */
    public void onItemClick(int position) {

    }

    /**
     * 每个item长按事件
     */
    public boolean onItemLongClick(int postion) {
        return false;
    }

    /**
     * 选中某一条
     *
     * @param position 选中的item
     */
    public void onSelectItem(int position) {

    }

}
