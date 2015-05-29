package com.yh.yhui.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.io.ImageUtils;
import xyz.yhsj.yhutils.tools.phone.ScreenUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_Screen extends Fragment {

    @ViewInject(R.id.info)
    private TextView info;

    @ViewInject(R.id.img)
    private ImageView img;

    public Fragment_Utils_Screen() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_screen_utils, container, false);
        ViewUtils.inject(this, rootView);

        return rootView;
    }


    @OnClick({R.id.getScreenWidth, R.id.getScreenHeight, R.id.getStatusHeight, R.id.snapShotWithStatusBar,
            R.id.snapShotWithoutStatusBar, R.id.getScreenPhysicalSize, R.id.isTablet})
    public void btnListener(View v) {
        switch (v.getId()) {
            case R.id.getScreenWidth:
                info.setText(ScreenUtils.getScreenWidth(getActivity()) + "");
                break;
            case R.id.getScreenHeight:
                info.setText(ScreenUtils.getScreenHeight(getActivity()) + "");
                break;
            case R.id.getStatusHeight:
                info.setText(ScreenUtils.getStatusHeight(getActivity()) + "");
                break;
            case R.id.snapShotWithStatusBar:
                ImageUtils.setImageBitmap(getActivity(), img, ScreenUtils.snapShotWithStatusBar(getActivity()));
                break;
            case R.id.snapShotWithoutStatusBar:
                ImageUtils.setImageBitmap(getActivity(), img, ScreenUtils.snapShotWithoutStatusBar(getActivity()));
                break;
            case R.id.getScreenPhysicalSize:
                info.setText(ScreenUtils.getScreenPhysicalSize(getActivity()) + "");

                float scale = getActivity().getResources().getDisplayMetrics().densityDpi;

                LogUtils.i(scale + "");

                break;
            case R.id.isTablet:
                info.setText(ScreenUtils.isTablet(getActivity()) ? "是平板" : "不是平板");
                break;
            default:
                break;

        }
    }


}
