package com.yh.yhui.app.fragment.utils_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import xyz.yhsj.yhutils.tools.phone.AppUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_App extends Fragment {


	@ViewInject(R.id.info)
	private TextView info;


	public Fragment_Utils_App() {

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_app_utils, container, false);
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	@OnClick({R.id.getappname, R.id.getpackagename, R.id.getversionname, R.id.getlanguage,
			R.id.getcountry, R.id.startApkActivity, R.id.getActivities, R.id.getAllInstelAppUrl})
	public void btnListener(View v) {

		switch (v.getId()) {
			case R.id.getappname:
				info.setText(AppUtils.getAppName(getActivity()));
				break;
			case R.id.getpackagename:
				info.setText(AppUtils.getPackageName(getActivity()));
				break;
			case R.id.getversionname:
				info.setText(AppUtils.getVersionName(getActivity()));
				break;
			case R.id.getlanguage:
				info.setText(AppUtils.getLanguage());
				break;
			case R.id.getcountry:
				info.setText(AppUtils.getCountry());
				break;
			case R.id.startApkActivity:
				try {
					AppUtils.startApkActivity(getActivity(), "com.lianxiangnet");
				} catch (Exception e) {
					LogUtils.e("包名不存在", e);
				}
				break;

			case R.id.getActivities:

				ArrayList<String> Activities = AppUtils.getActivities(getActivity());

				StringBuffer sb = new StringBuffer();

				for (String name : Activities) {

					sb.append(name).append("\n");

				}
				info.setText(sb);

				break;
			case R.id.getAllInstelAppUrl:

				ArrayList<String> apps = AppUtils.getAllCanStartApp(getActivity());

				StringBuffer sb1 = new StringBuffer();

				for (String name : apps) {

					sb1.append(name).append("\n");

				}
				info.setText(sb1);


				break;
			default:
				break;

		}

	}


}
