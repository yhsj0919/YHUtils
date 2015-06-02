package com.yh.yhui.app.fragment.weight_demo;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yh.yhui.app.R;
import com.yh.yhui.app.fragment.weight_demo.adapter.Adapter_RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Weight_Demo_Recyclerview extends Fragment {


    @ViewInject(R.id.recycler_view)
    private RecyclerView recyclerView;

    @ViewInject(R.id.swipe_container)
    private SwipeRefreshLayout swipeRefreshLayout;

    private Adapter_RecyclerView adapter;

    public Weight_Demo_Recyclerview() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weight_demo_recyclerview, container, false);
        ViewUtils.inject(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        //设置动画颜色
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);


        //创建默认的线性LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        adapter = new Adapter_RecyclerView(getDummyDatas());
        recyclerView.setAdapter(adapter);

        //添加条目监听
        adapter.setOnItemClickListener(new Adapter_RecyclerView.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            }
        });
        //添加菜单监听
        adapter.setOnMenuClickListener(new Adapter_RecyclerView.OnRecyclerViewMenuClickListener() {
            @Override
            public void onMenuClick(View view, int position) {
                if (view.getId() == R.id.add) {
                    Toast.makeText(getActivity(), "添加" + position, Toast.LENGTH_SHORT).show();
                }
                if (view.getId() == R.id.del) {
                    Toast.makeText(getActivity(), "删除" + position, Toast.LENGTH_SHORT).show();
                }
                if (view.getId() == R.id.chenge) {
                    Toast.makeText(getActivity(), "修改" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 6000);
            }
        });


    }

    //模拟数据
    private ArrayList<String> getDummyDatas() {

        ArrayList<String> datas = new ArrayList();

        for (int i = 0; i < 20; i++) {
            datas.add("这是测试的条目" + i);
        }
        return datas;
    }


}
