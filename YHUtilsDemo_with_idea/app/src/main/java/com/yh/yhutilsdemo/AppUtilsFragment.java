package com.yh.yhutilsdemo;


import android.content.Context;
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
import xyz.yhsj.yhutils.tools.phone.AppUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppUtilsFragment extends Fragment {
	private Context context;

	@ViewInject(R.id.info)
	private TextView info;


	public AppUtilsFragment(Context context) {
		this.context = context;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_app_tools, container, false);
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	@OnClick({R.id.getappname, R.id.getpackagename, R.id.getversionname, R.id.getlanguage,
			R.id.getcountry, R.id.startApkActivity, R.id.getActivities, R.id.getAllInstelAppUrl})
	public void btnListener(View v) {

		switch (v.getId()) {
			case R.id.getappname:
				info.setText(AppUtils.getAppName(context));
				break;
			case R.id.getpackagename:
				info.setText(AppUtils.getPackageName(context));
				break;
			case R.id.getversionname:
				info.setText(AppUtils.getVersionName(context));
				break;
			case R.id.getlanguage:
				info.setText(AppUtils.getLanguage());
				break;
			case R.id.getcountry:
				info.setText(AppUtils.getCountry());
				break;
			case R.id.startApkActivity:
				try {
					AppUtils.startApkActivity(context, "com.lianxiangnet");
				} catch (Exception e) {
					LogUtils.e("包名不存在", e);
				}
				break;

			case R.id.getActivities:

				ArrayList<String> Activities = AppUtils.getActivities(context);

				StringBuffer sb = new StringBuffer();

				for (String name : Activities) {

					sb.append(name).append("\n");

				}
				info.setText(sb);

				break;
			case R.id.getAllInstelAppUrl:

				ArrayList<String> apps = AppUtils.getAllCanStartApp(context);

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
