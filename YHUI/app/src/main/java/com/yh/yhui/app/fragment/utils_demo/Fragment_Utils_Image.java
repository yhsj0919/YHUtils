package com.yh.yhui.app.fragment.utils_demo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.io.ImageUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_Image extends Fragment {

    @ViewInject(R.id.img)
    private ImageView img;

    public Fragment_Utils_Image() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_util, container, false);
        ViewUtils.inject(this, rootView);

        return rootView;
    }

    @OnClick(R.id.btn)
    public void btnListener(View v) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        ImageUtils.setImageBitmap(getActivity(), img, bitmap);
    }


}
