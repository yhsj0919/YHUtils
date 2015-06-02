package com.yh.yhui.app.fragment.weight_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.yh.yhui.app.R;

import java.util.ArrayList;

/**
 * Created by LOVE on 2015/5/30.
 */
public class Adapter_RecyclerView extends RecyclerView.Adapter<Adapter_RecyclerView.ViewHolder> {
    public ArrayList<String> datas = null;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnRecyclerViewMenuClickListener monMenuClickListener = null;

    public Adapter_RecyclerView(ArrayList<String> datas) {
        this.datas = datas;
    }

    /**
     * 创建新View，被LayoutManager所调用
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weight_demo_recyclerview_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        vh.mTextView.setOnClickListener(new itemClick());
        vh.del.setOnClickListener(new menuClick());
        vh.add.setOnClickListener(new menuClick());
        vh.chenge.setOnClickListener(new menuClick());

        return vh;
    }

    /**
     * 将数据与界面进行绑定的操作
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(datas.get(position));
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(datas.get(position));

        holder.add.setTag(position);
        holder.del.setTag(position);
        holder.chenge.setTag(position);
    }

    /**
     * 获取数据的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 设置条目监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 设置菜单监听
     *
     * @param listener
     */
    public void setOnMenuClickListener(OnRecyclerViewMenuClickListener listener) {
        this.monMenuClickListener = listener;
    }


    /**
     * 条目点击事件接口
     */
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    /**
     * 菜单点击事件接口
     */
    public interface OnRecyclerViewMenuClickListener {
        void onMenuClick(View view, int position);
    }

    /**
     * 自定义的ViewHolder，持有每个Item的的所有界面元素
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private ImageView del;
        private ImageView add;
        private ImageView chenge;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            del = (ImageView) view.findViewById(R.id.del);
            add = (ImageView) view.findViewById(R.id.add);
            chenge = (ImageView) view.findViewById(R.id.chenge);
        }
    }

    /**
     * 条目监听
     */
    class itemClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v, ((TextView) v).getText().toString());
            }
        }
    }

    /**
     * 菜单监听
     */
    class menuClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (monMenuClickListener != null) {
                //注意这里使用getTag方法获取数据
                monMenuClickListener.onMenuClick(v, (Integer) v.getTag());
            }
        }
    }

}