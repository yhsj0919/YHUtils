package com.yh.yhui.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yh.yhui.app.R;
import com.yh.yhui.app.fragment.utils_demo.*;
import com.yh.yhui.app.mode.Event_Fragment_Chenge;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment_Menu_List extends Fragment {

	private Event_Fragment_Chenge event_fragment_chenge;

	@ViewInject(R.id.list)
	private ListView list;

	private ArrayList<String> list_data;

	private ArrayAdapter<String> adapter;

	public Fragment_Menu_List() {


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);


		ViewUtils.inject(this, rootView);

		init();

		return rootView;
	}

	public void init() {

		list_data = new ArrayList<String>();
		list_data.add("DBUtils");
		list_data.add("HttpUtils");
		list_data.add("BitmapUtils");
		list_data.add("AppUtils");
		list_data.add("AssetUtils");
		list_data.add("DateUtils");
		list_data.add("DensityUtils");
		list_data.add("DeviceUtils");
		list_data.add("ImageUtils");
		list_data.add("IoUtils");
		list_data.add("KeyBoardUtils");
		list_data.add("NotificationUtils");
		list_data.add("NetWorkUtils");
		list_data.add("ScreenUtils");
		list_data.add("SdcardUtils");
		//list_data.add("SecarityUtils");
		//list_data.add("SpUtils");

		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_data);

		list.setAdapter(adapter);

		event_fragment_chenge = new Event_Fragment_Chenge();


	}

	@OnItemClick(R.id.list)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


		switch ((String) parent.getAdapter().getItem(position)) {


			case "DBUtils":

				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_DB());
				EventBus.getDefault().post(event_fragment_chenge);

				break;
			case "HttpUtils":

				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Http());
				EventBus.getDefault().post(event_fragment_chenge);

				break;
			case "BitmapUtils":

				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Bitmap());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "AppUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_App());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "AssetUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Asset());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "DateUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Date());
				EventBus.getDefault().post(event_fragment_chenge);

				break;
			case "DensityUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Density());
				EventBus.getDefault().post(event_fragment_chenge);

				break;
			case "DeviceUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Device());
				EventBus.getDefault().post(event_fragment_chenge);
				break;

			case "ImageUtils":

				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Image());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "IoUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_IO());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "KeyBoardUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_KeyBoard());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "NetWorkUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_NetWork());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "NotificationUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Notification());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "ScreenUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_Screen());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			case "SdcardUtils":
				event_fragment_chenge.setId(1);
				event_fragment_chenge.setColor(R.color.colorPrimary);
				event_fragment_chenge.setFragment(new Fragment_Utils_SDCard());
				EventBus.getDefault().post(event_fragment_chenge);
				break;
			//            case "SecarityUtils":
			//                break;
			//            case "SpUtils":
			//                break;

		}


	}


}
