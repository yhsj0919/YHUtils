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
public class MainFragment extends Fragment {

    private Context context;
    @ViewInject(R.id.list)
    private ListView list;

    private ArrayList<String> list_data;

    private ArrayAdapter<String> adapter;


    public MainFragment(Context context) {

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
        list_data.add("AppTools");
        list_data.add("AssetTools");
        list_data.add("DateTools");
        list_data.add("DensityTools");
        list_data.add("DeviceTools");
        list_data.add("IoTools");
        list_data.add("KeyBoardTools");
        list_data.add("NetTools");
        list_data.add("ScreenTools");
        list_data.add("Sdcardls");
        list_data.add("SecarityTools");
        list_data.add("SpTools");


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
            case "AppTools":
                break;
            case "AssetTools":
                break;
            case "DateTools":
                break;
            case "DensityTools":
                break;
            case "DeviceTools":
                break;
            case "IoTools":
                break;
            case "KeyBoardTools":
                break;
            case "NetTools":
                break;
            case "ScreenTools":
                break;
            case "Sdcardls":
                break;
            case "SecarityTools":
                break;
            case "SpTools":
                break;

        }


    }


}
