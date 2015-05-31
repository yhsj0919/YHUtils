package com.yh.yhui.app.fragment.utils_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.keyboard.KeyBoardUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_KeyBoard extends Fragment {



    @ViewInject(R.id.ed1)
    private EditText ed1;


    public Fragment_Utils_KeyBoard() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_keyboard_utils, container, false);
        ViewUtils.inject(this, rootView);

        return rootView;
    }

    @OnClick(R.id.screen)
    public void btnListener(View v) {
        if (v.getId() == R.id.screen) {

            KeyBoardUtils.closeKeybord(ed1, getActivity());
        }
    }


}
