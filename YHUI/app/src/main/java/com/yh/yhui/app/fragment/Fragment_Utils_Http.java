package com.yh.yhui.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.PreferencesCookieStore;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_Http extends Fragment {


    @ViewInject(R.id.text)
    private TextView text;

    @ViewInject(R.id.getinfo)
    private Button getinfo;

    @ViewInject(R.id.cookie)
    private Button cookie;


    public Fragment_Utils_Http() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_http_utils, container, false);
        ViewUtils.inject(this, rootView);

        return rootView;
    }

    @OnClick({R.id.getinfo, R.id.cookie})
    public void btnListrner(View v) {

        switch (v.getId()) {
            case R.id.getinfo:
                postinfo();
                break;
            case R.id.cookie:
                testCookie();
                break;
        }


    }


    /*
    *
     */
    private void getinfo() {

        HttpUtils httpUtils = new HttpUtils();

        httpUtils.send(HttpRequest.HttpMethod.GET,
                "http://182.92.96.58:8005/yrt/",
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        LogUtils.i(current + "/" + total);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        text.setText(responseInfo.result);
                        LogUtils.i("请求成功");
                    }

                    @Override
                    public void onStart() {
                        LogUtils.i("开始请求");
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        LogUtils.i("请求失败");
                    }
                });


    }

    /**
     * post方法
     */
    private void postinfo() {

        HttpUtils httpUtils = new HttpUtils();


        PreferencesCookieStore cookieStore = new PreferencesCookieStore(getActivity());

        httpUtils.configCookieStore(cookieStore);

        RequestParams params = new RequestParams();
        params.addBodyParameter("account", "yph");
        params.addBodyParameter("password", "123");

        httpUtils.send(HttpRequest.HttpMethod.POST,
                "http://182.92.96.58:8005/yrt/login",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        LogUtils.i(current + "/" + total);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        text.setText(responseInfo.result);
                        LogUtils.i("请求成功");
                    }

                    @Override
                    public void onStart() {
                        LogUtils.i("开始请求");
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        LogUtils.i("请求失败");
                    }
                });
    }

    /**
     * post方法
     */
    private void testCookie() {

        HttpUtils httpUtils = new HttpUtils();

        PreferencesCookieStore cookieStore = new PreferencesCookieStore(getActivity());

        httpUtils.configCookieStore(cookieStore);

        RequestParams params = new RequestParams();
        params.addBodyParameter("action", "getMenu1");

        httpUtils.send(HttpRequest.HttpMethod.POST,
                "http://182.92.96.58:8005/yrt/servlet/schedule",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        LogUtils.i(current + "/" + total);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        text.setText(responseInfo.result);
                        LogUtils.i("请求成功");
                    }

                    @Override
                    public void onStart() {
                        LogUtils.i("开始请求");
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        LogUtils.i("请求失败");
                    }
                });
    }


}
