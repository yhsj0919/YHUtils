package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import xyz.yhsj.yhutils.tools.string.DensityUtils;



/**
 * A simple {@link Fragment} subclass.
 */
public class DensityUtilsFragment extends Fragment {


    private Context context;

    @ViewInject(R.id.info1)
    private TextView info1;

    @ViewInject(R.id.info2)
    private TextView info2;

    @ViewInject(R.id.info3)
    private TextView info3;

    @ViewInject(R.id.info4)
    private TextView info4;

    public DensityUtilsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_density_utils, container, false);
        ViewUtils.inject(this, rootView);


        info1.setText("8px2dp>>" + DensityUtils.px2dp(context, 8));
        info2.setText("8dp2px>>" + DensityUtils.dp2px(context, 8));
        info3.setText("8px2sp>>" + DensityUtils.px2sp(context, 8));
        info4.setText("8sp2px>>" + DensityUtils.sp2px(context, 8));


        return rootView;
    }


}
