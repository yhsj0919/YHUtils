package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import xyz.yhsj.yhutils.ViewUtils;
import xyz.yhsj.yhutils.tools.sdcard.SDCardUtils;
import xyz.yhsj.yhutils.util.LogUtils;
import xyz.yhsj.yhutils.view.annotation.ViewInject;
import xyz.yhsj.yhutils.view.annotation.event.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SDCardUtilsFragment extends Fragment {

    private Context context;

    @ViewInject(R.id.info)
    private TextView info;

    public SDCardUtilsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sdcard_utils, container, false);
        ViewUtils.inject(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void btnListener(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                info.setText(SDCardUtils.isSDCardEnable() ? "当前内存卡有效" : "当前内存卡无效");
                break;
            case R.id.btn2:
                info.setText(SDCardUtils.getSDCardPath());
                break;
            case R.id.btn3:
                info.setText(SDCardUtils.getSDCardAvailSize(context) + "/" + SDCardUtils.getSDCardTotalSize(context));
                break;
            case R.id.btn4:
                info.setText(SDCardUtils.getRomSpaceAvailSize(context) + "/" + SDCardUtils.getRomSpaceTotalSize(context));
                break;
            case R.id.btn5:
                info.setText(SDCardUtils.getRootDirectoryPath());
                break;
            case R.id.btn6:
                info.setText(SDCardUtils.isSDCardSizeOverflow() ? "当前内存卡已满" : "当前内存卡未满");
                break;
        }


    }



}
