package com.yh.yhui.app.fragment.utils_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.io.SDCardUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_SDCard extends Fragment {

    @ViewInject(R.id.info)
    private TextView info;

    public Fragment_Utils_SDCard() {

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
                info.setText(SDCardUtils.getSDCardAvailSize(getActivity()) + "/" + SDCardUtils.getSDCardTotalSize(getActivity()));
                break;
            case R.id.btn4:
                info.setText(SDCardUtils.getRomSpaceAvailSize(getActivity()) + "/" + SDCardUtils.getRomSpaceTotalSize(getActivity()));
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
