package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import xyz.yhsj.yhutils.ViewUtils;
import xyz.yhsj.yhutils.tools.notification.NotificationUtils;
import xyz.yhsj.yhutils.view.annotation.event.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationUtilsFragment extends Fragment {
    private Context context;


    public NotificationUtilsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification_utils, container, false);

        ViewUtils.inject(this, rootView);

        return rootView;
    }

    @OnClick({R.id.show1, R.id.show2, R.id.clear})
    public void btnListener(View v) {
        switch (v.getId()) {
            case R.id.show1:
                NotificationUtils.sendNotification(context, "这是标题", "这是内容", R.mipmap.ic_launcher, MainActivity.class, null);
                break;
            case R.id.show2:
                NotificationUtils.showNotification(getActivity(), "不动的标题", "不动的内容", R.mipmap.ic_launcher);
                break;
            case R.id.clear:
                NotificationUtils.cancelNotification(context);
                break;

        }
    }


}
