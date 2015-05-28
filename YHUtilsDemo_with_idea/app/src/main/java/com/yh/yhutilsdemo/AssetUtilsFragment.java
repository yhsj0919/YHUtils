package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import xyz.yhsj.yhutils.tools.io.AssetUtils;



/**
 * A simple {@link Fragment} subclass.
 */
public class AssetUtilsFragment extends Fragment {

    private Context context;

    @ViewInject(R.id.info)
    private TextView info;

    @ViewInject(R.id.img)
    private ImageView img;

    public AssetUtilsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_asset_utils, container, false);
        ViewUtils.inject(this, rootView);
        info.setText(AssetUtils.getTextFromAssets(context, "info.txt"));
        img.setImageDrawable(AssetUtils.loadImageFromAsserts(context,"1.jpg"));
        return rootView;

    }


}
