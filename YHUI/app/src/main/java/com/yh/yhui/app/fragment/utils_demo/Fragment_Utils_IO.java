package com.yh.yhui.app.fragment.utils_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.io.IOUtils;
import xyz.yhsj.yhutils.tools.io.SDCardUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_IO extends Fragment {


    @ViewInject(R.id.info1)
    private EditText info;


    public Fragment_Utils_IO() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_io_utils, container, false);
        ViewUtils.inject(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_save, R.id.btn_get, R.id.btn_append})
    public void btnListener(View view) {
        switch (view.getId()) {
            case R.id.btn_get:

                info.setText(IOUtils.read(SDCardUtils.getSDCardPath() + "test.txt"));

                LogUtils.i(IOUtils.read(SDCardUtils.getSDCardPath() + "test.txt"));

                break;
            case R.id.btn_append:

                IOUtils.write(SDCardUtils.getSDCardPath() + "test.txt", info.getText().toString() + "\n", true);
                break;

            case R.id.btn_save:
                IOUtils.write(SDCardUtils.getSDCardPath() + "test.txt", info.getText().toString() + "\n", false);
                break;

        }
    }


}
