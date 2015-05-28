package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class BitmapUtilsFragment extends Fragment {
    private Context context;

    @ViewInject(R.id.img)
    private ImageView img;

    public BitmapUtilsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bitmap_utils, container, false);
        ViewUtils.inject(this, rootView);
        return rootView;
    }

    @OnClick(R.id.getimg)
    public void btnListener(View v) {

        BitmapUtils bitmapUtils = new BitmapUtils(context);

        // 加载网络图片
        bitmapUtils.display(img, "http://bbs.lidroid.com/static/image/common/logo.png");

        // 加载本地图片(路径以/开头， 绝对路径)
        // bitmapUtils.display(img, "/sdcard/test.png");

        // 加载assets中的图片(路径以assets开头)
        // bitmapUtils.display(img, "assets/img/wallpaper.jpg");

        // 使用ListView等容器展示图片时可通过PauseOnScrollListener控制滑动和快速滑动过程中时候暂停加载图片
        // listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        // listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true, customListener));
    }


}
