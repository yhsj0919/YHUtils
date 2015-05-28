package com.yh.yhutilsdemo;


import android.content.Context;
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
import xyz.yhsj.yhutils.tools.io.ImageUtils;



/**
 * A simple {@link Fragment} subclass.
 */
public class ImageUtilFragment extends Fragment {
    private Context context;

    @ViewInject(R.id.img)
    private ImageView img;

    public ImageUtilFragment(Context context) {
        this.context = context;
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

        ImageUtils.setImageBitmap(context, img, bitmap);
    }


}
