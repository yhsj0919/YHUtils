package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import xyz.yhsj.yhutils.ViewUtils;
import xyz.yhsj.yhutils.tools.date.DateUtils;
import xyz.yhsj.yhutils.view.annotation.ViewInject;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateUtilsFragment extends Fragment {

    private Context context;

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

    public DateUtilsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_date_utils, container, false);
        ViewUtils.inject(this, rootView);


        info1.setText(DateUtils.getCurrentTime() + "");
        info2.setText(DateUtils.getThisTime());
        info3.setText(DateUtils.getTimePoint(3, 2, 4, 0, 0, 0, null));
        info4.setText(DateUtils.format12Time(DateUtils.getCurrentTime()));
        info5.setText(DateUtils.format(DateUtils.getCurrentTime(),"dd"));

        return rootView;
    }


}
