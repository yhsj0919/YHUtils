package com.yh.yhui.app.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.string.DateUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_Date extends Fragment {



    @ViewInject(R.id.info1)
    private TextView info1;

    @ViewInject(R.id.info2)
    private TextView info2;

    @ViewInject(R.id.info3)
    private TextView info3;

    @ViewInject(R.id.info4)
    private TextView info4;

    @ViewInject(R.id.info5)
    private TextView info5;

    public Fragment_Utils_Date() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_date_utils, container, false);
        ViewUtils.inject(this, rootView);


        info1.setText(DateUtils.getCurrentTime() + "");
        info2.setText(DateUtils.getThisTime(DateUtils.DF_YYYY_MM_DD_HH_MM_SS));
        info3.setText(DateUtils.getTimePoint(3, 2, 4, 0, 0, 0, null));
        info4.setText(DateUtils.format12Time(DateUtils.getCurrentTime()));
        info5.setText(DateUtils.format(DateUtils.getCurrentTime(), "dd"));

        return rootView;
    }


}
