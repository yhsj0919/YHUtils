package com.yh.yhutilsdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import xyz.yhsj.yhutils.ViewUtils;
import xyz.yhsj.yhutils.view.annotation.ViewInject;
import xyz.yhsj.yhutils.view.annotation.event.OnItemClick;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuListFragment extends Fragment {

    private Context context;
    @ViewInject(R.id.list)
    private ListView list;

    private ArrayList<String> list_data;

    private ArrayAdapter<String> adapter;


    public MenuListFragment(Context context) {

        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


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
        list_data.add("SecarityUtils");
        list_data.add("SpUtils");


        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_data);

        list.setAdapter(adapter);


    }

    @OnItemClick(R.id.list)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        switch ((String) parent.getAdapter().getItem(position)) {


            case "DBUtils":

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new DBUtilsFragment(context))
                        .commit();

                break;
            case "HttpUtils":

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new HttpUtilsFragment(context))
                        .commit();

                break;
            case "BitmapUtils":

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new BitmapUtilsFragment(context))
                        .commit();
                break;
            case "AppUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new AppUtilsFragment(context))
                        .commit();
                break;
            case "AssetUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new AssetUtilsFragment(context))
                        .commit();
                break;
            case "DateUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new DateUtilsFragment(context))
                        .commit();

                break;
            case "DensityUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new DensityUtilsFragment(context))
                        .commit();

                break;
            case "DeviceUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new DeviceUtilsFragment(context))
                        .commit();
                break;

            case "ImageUtils":

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new ImageUtilFragment(context))
                        .commit();
                break;
            case "IoUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new IOUtilsFragment(context))
                        .commit();
                break;
            case "KeyBoardUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new KeyBoardUtilsFragment(context))
                        .commit();
                break;
            case "NetWorkUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new NetWorkUtilsFragment(context))
                        .commit();
                break;
            case "NotificationUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new NotificationUtilsFragment(context))
                        .commit();
                break;
            case "ScreenUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new ScreenUtilsFragment(context))
                        .commit();
                break;
            case "SdcardUtils":
                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .addToBackStack(null)
                        .replace(R.id.container, new SDCardUtilsFragment(context))
                        .commit();
                break;
            case "SecarityUtils":
                break;
            case "SpUtils":
                break;

        }


    }


}
