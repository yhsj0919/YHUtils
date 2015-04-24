package xyz.yhsj.yhutilsdemo;

import xyz.yhsj.yhutils.ViewUtils;
import xyz.yhsj.yhutils.util.LogUtils;
import xyz.yhsj.yhutils.view.annotation.event.OnClick;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
	}

	@OnClick(R.id.btn)
	public void BtnListener(View v) {
		LogUtils.i("测试");
	}

}
