package com.yh.yhui.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import com.yh.yhui.app.mode.Event_Test;
import de.greenrobot.event.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Main extends Fragment {

	private String title;

	@ViewInject(R.id.text)
	private TextView textView;

	public Fragment_Main(String title) {
		this.title = title;
	}

	public Fragment_Main() {

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		ViewUtils.inject(this, rootView);


		textView.setText(title);

		return rootView;
	}

	@OnClick(R.id.send)
	public void btnListener(View v) {
		Event_Test my = new Event_Test();
		my.setType("0");
		my.setContent(title);
		EventBus.getDefault().post(my);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}
}
