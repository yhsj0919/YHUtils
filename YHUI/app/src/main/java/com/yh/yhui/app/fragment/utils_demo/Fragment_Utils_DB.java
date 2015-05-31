package com.yh.yhui.app.fragment.utils_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yh.yhui.app.R;
import com.yh.yhui.app.mode.MyModel;
import xyz.yhsj.yhutils.tools.string.DateUtils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Utils_DB extends Fragment {


    private DbUtils dbUtils;

    @ViewInject(R.id.text)
    private TextView text;

    public Fragment_Utils_DB() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dbutils, container, false);
        dbUtils = DbUtils.create(getActivity());

        ViewUtils.inject(this, rootView);

        return rootView;
    }

    @OnClick({R.id.save, R.id.find, R.id.update, R.id.delete})
    public void btnListener(View v) {

        switch (v.getId()) {
            case R.id.save:
                text.setText("保存");
                try {

                    dbUtils.save(new MyModel("名称1", DateUtils.getThisTime(DateUtils.DF_YYYY_MM_DD_HH_MM_SS)));

                } catch (DbException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.find:
                text.setText("查找");
                try {
                    List<MyModel> myModels = dbUtils.findAll(Selector.from(MyModel.class));

                    StringBuffer sb = new StringBuffer();

                    if (myModels != null && myModels.size() > 0) {

                        for (int i = 0; i < myModels.size(); i++) {

                            sb.append(myModels.get(i).getId() + "  " + myModels.get(i).getName() + "   " + myModels.get(i).getData() + "\n");

                        }

                        text.setText(sb);
                    } else {
                        text.setText("不存在对象啊");
                    }

                } catch (DbException e) {
                    //e.printStackTrace();
                }

                break;
            case R.id.update:
                text.setText("更新");

                try {
                    List<MyModel> myModels = dbUtils.findAll(Selector.from(MyModel.class));

                    if (myModels != null && myModels.size() > 0) {

                        MyModel myModel = myModels.get(0);
                        myModel.setName("我更新啦");
                        dbUtils.update(myModel);

                    } else {
                        text.setText("不存在对象啊");
                    }


                } catch (DbException e) {
                    //e.printStackTrace();
                }


                break;
            case R.id.delete:
                text.setText("删除");
                try {
                    List<MyModel> myModels = dbUtils.findAll(Selector.from(MyModel.class));

                    if (myModels != null && myModels.size() > 0) {

                        MyModel myModel = myModels.get(0);

                        dbUtils.delete(myModel);

                    } else {
                        text.setText("不存在对象啊");
                    }


                } catch (DbException e) {
                    //e.printStackTrace();
                }
                break;


        }


    }


}
