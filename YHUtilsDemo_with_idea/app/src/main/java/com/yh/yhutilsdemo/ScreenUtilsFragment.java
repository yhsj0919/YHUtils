package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import xyz.yhsj.yhutils.ViewUtils;
import xyz.yhsj.yhutils.tools.io.ImageUtils;
import xyz.yhsj.yhutils.tools.phone.ScreenUtils;
import xyz.yhsj.yhutils.util.LogUtils;
import xyz.yhsj.yhutils.view.annotation.ViewInject;
import xyz.yhsj.yhutils.view.annotation.event.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenUtilsFragment extends Fragment {

    private Context context;

    @ViewInject(R.id.info)
    private TextView info;

    @ViewInject(R.id.img)
    private ImageView img;

    public ScreenUtilsFragment(Context context) {
        this.context = context;
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
                info.setText(ScreenUtils.getScreenWidth(context) + "");
                break;
            case R.id.getScreenHeight:
                info.setText(ScreenUtils.getScreenHeight(context) + "");
                break;
            case R.id.getStatusHeight:
                info.setText(ScreenUtils.getStatusHeight(context) + "");
                break;
            case R.id.snapShotWithStatusBar:
                ImageUtils.setImageBitmap(context, img, ScreenUtils.snapShotWithStatusBar(getActivity()));
                break;
            case R.id.snapShotWithoutStatusBar:
                ImageUtils.setImageBitmap(context,img,ScreenUtils.snapShotWithoutStatusBar(getActivity()));
                break;
            case R.id.getScreenPhysicalSize:
                info.setText(ScreenUtils.getScreenPhysicalSize(getActivity()) + "");

                float scale = context.getResources().getDisplayMetrics().densityDpi;


                LogUtils.i(scale+"");

                break;
            case R.id.isTablet:
                info.setText(ScreenUtils.isTablet(context)?"是平板":"不是平板");
                break;
            default:
                break;

        }
    }


}
