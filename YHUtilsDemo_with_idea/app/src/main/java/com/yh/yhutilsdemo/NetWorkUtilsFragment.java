package com.yh.yhutilsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import xyz.yhsj.yhutils.tools.phone.NetWorkUtils;
import xyz.yhsj.yhutils.tools.phone.NetWorkUtils_Broadcast;



/**
 * A simple {@link Fragment} subclass.
 */
public class NetWorkUtilsFragment extends Fragment {
    private Context context;

    @ViewInject(R.id.info)
    private TextView info;

    @ViewInject(R.id.btn1)
    private Button btn1;

    @ViewInject(R.id.btn2)
    private Button btn2;

    @ViewInject(R.id.btn3)
    private Button btn3;

    @ViewInject(R.id.btn4)
    private Button btn4;

    //用广播监听网络变化
    private NetWorkUtils_Broadcast netWork_broadcast;

    public NetWorkUtilsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_network_utils, container, false);

        ViewUtils.inject(this, rootView);


        netWork_broadcast = NetWorkUtils_Broadcast.getInstance(context);
        netWork_broadcast.registerNetworkReceiver();
        netWork_broadcast.setListener(new NetWorkUtils_Broadcast.OnNetChengeListener() {
            @Override
            public void OnNetChenged(String info) {
                LogUtils.d(info);
            }
        });


        return rootView;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void btnListener(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                info.setText(NetWorkUtils.isConnected(context) ? "连接" : "未连接");
                break;
            case R.id.btn2:
                info.setText(NetWorkUtils.isWifi(context) ? "wifi" : "不是wifi");
                break;
            case R.id.btn3:
                info.setText(NetWorkUtils.getNetworkTypeName(context));
                break;
            case R.id.btn4:

                NetWorkUtils.openSetting(getActivity());


                break;
            default:
                break;

        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        netWork_broadcast.unRegisterNetworkReceiver();
    }
}
