package com.yh.yhui.app.fragment.utils_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.io.AssetUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_Asset extends Fragment {


    @ViewInject(R.id.info)
    private TextView info;

    @ViewInject(R.id.img)
    private ImageView img;

    public Fragment_Utils_Asset() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_asset_utils, container, false);
        ViewUtils.inject(this, rootView);
        info.setText(AssetUtils.getTextFromAssets(getActivity(), "info.txt"));
        img.setImageDrawable(AssetUtils.loadImageFromAsserts(getActivity(), "1.jpg"));
        return rootView;

    }


}
