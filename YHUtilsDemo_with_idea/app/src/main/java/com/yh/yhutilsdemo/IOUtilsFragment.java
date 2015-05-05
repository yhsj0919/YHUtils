package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import xyz.yhsj.yhutils.ViewUtils;
import xyz.yhsj.yhutils.tools.io.IOUtils;
import xyz.yhsj.yhutils.tools.sdcard.SDCardUtils;
import xyz.yhsj.yhutils.util.LogUtils;
import xyz.yhsj.yhutils.view.annotation.ViewInject;
import xyz.yhsj.yhutils.view.annotation.event.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class IOUtilsFragment extends Fragment {

    private Context context;

    @ViewInject(R.id.info1)
    private EditText info;


    public IOUtilsFragment(Context context) {

        this.context = context;
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
