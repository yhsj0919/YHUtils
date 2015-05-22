package com.yh.yhui.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Toast;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.*;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.KeyboardUtil;
import com.yh.yhui.app.R;
import com.yh.yhui.app.fragment.MainFragment;
import com.yh.yhui.app.fragment.SecondFragment;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends AppCompatActivity {


    private Drawer.Result result = null;
    private Toolbar toolbar;
    private AccountHeader.Result headerResult = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setDrawer(savedInstanceState);
    }

    private void setDrawer(Bundle savedInstanceState) {

        headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withEmail("1130402124@qq.com").withName("永恒瞬间").withIcon(getResources().getDrawable(R.drawable.profile)),
                        new ProfileSettingDrawerItem().withName("修改密码").withDescription("修改密码").withIcon(GoogleMaterial.Icon.gmd_settings_backup_restore).withIdentifier(1),
                        new ProfileSettingDrawerItem().withName("管理账户").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(2)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {

                        if (profile != null && profile instanceof IDrawerItem) {
                            switch (profile.getIdentifier()) {
                                case 1:
                                    Toast.makeText(MainActivity.this, "修改密码", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Toast.makeText(MainActivity.this, "管理账户", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        result = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("待办").withIdentifier(1).withBadge("5").withIcon(GoogleMaterial.Icon.gmd_notifications),
                        new PrimaryDrawerItem().withName("通讯录").withIdentifier(2).withIcon(GoogleMaterial.Icon.gmd_local_phone),
                        new PrimaryDrawerItem().withName("企信").withIdentifier(3).withIcon(GoogleMaterial.Icon.gmd_message),
                        new SectionDrawerItem().withName("功能模块"),
                        new PrimaryDrawerItem().withName("考勤管理").withIdentifier(4).withIcon(GoogleMaterial.Icon.gmd_blur_circular),
                        new PrimaryDrawerItem().withName("客户管理").withIdentifier(5).withIcon(GoogleMaterial.Icon.gmd_people),
                        new PrimaryDrawerItem().withName("客户拜访").withIdentifier(6).withIcon(GoogleMaterial.Icon.gmd_nature_people),
                        new PrimaryDrawerItem().withName("订单管理").withIdentifier(7).withIcon(GoogleMaterial.Icon.gmd_event_available),
                        new PrimaryDrawerItem().withName("退货管理").withIdentifier(8).withIcon(FontAwesome.Icon.faw_truck),
                        new PrimaryDrawerItem().withName("销售账务").withIdentifier(9).withIcon(FontAwesome.Icon.faw_cc_paypal),
                        new PrimaryDrawerItem().withName("日志上报").withIdentifier(10).withIcon(FontAwesome.Icon.faw_edit),
                        new PrimaryDrawerItem().withName("自定义审批").withIdentifier(11).withIcon(GoogleMaterial.Icon.gmd_content_paste),
                        new PrimaryDrawerItem().withName("财务信息").withIdentifier(12).withIcon(FontAwesome.Icon.faw_money),
                        new PrimaryDrawerItem().withName("拍照上传").withIdentifier(13).withIcon(FontAwesome.Icon.faw_camera),
                        new PrimaryDrawerItem().withName("竞品管理").withIdentifier(14).withIcon(FontAwesome.Icon.faw_thumbs_up),
                        new PrimaryDrawerItem().withName("库存查询").withIdentifier(15).withIcon(GoogleMaterial.Icon.gmd_store),
                        new PrimaryDrawerItem().withName("报表统计").withIdentifier(16).withIcon(FontAwesome.Icon.faw_line_chart),
                        new PrimaryDrawerItem().withName("论坛交流").withIdentifier(17).withIcon(FontAwesome.Icon.faw_wechat),
                        new PrimaryDrawerItem().withName("新闻公告").withIdentifier(18).withIcon(FontAwesome.Icon.faw_newspaper_o),

                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("设置").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(19)
                ).addStickyDrawerItems(

                        new SecondaryDrawerItem().withName("退出").withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withIdentifier(20)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, final IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            getSupportActionBar().setTitle(((Nameable) drawerItem).getName());
                            switch (drawerItem.getIdentifier()) {
                                case 1:
                                    chengeAty(new MainFragment(), R.color.colorPrimary);
                                    toolbar.getMenu().clear();
                                    toolbar.inflateMenu(R.menu.menu_main);
                                    break;
                                case 2:
                                    chengeAty(new SecondFragment(), R.color.colorPrimary);
                                    toolbar.getMenu().clear();
                                    toolbar.inflateMenu(R.menu.menu_test);
                                    break;
                                default:
                                    chengeAty(new MainFragment(), R.color.colorPrimary);
                                    break;
                            }
                        }
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        KeyboardUtil.hideKeyboard(MainActivity.this);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                    }
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();

        result.keyboardSupportEnabled(this, true);


    }

    /**
     * 切换fragment
     *
     * @param myfragment    要切换的界面
     * @param overlay_color 切换时的颜色
     */
    public void chengeAty(Fragment myfragment, int overlay_color) {

        View myView = findViewById(R.id.content_root);

        // 获取主容器的长宽
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        //防止界面未加载时报错
        if (cx > 0 && cy > 0) {

            // 获取最终半径
            int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

            animator.setInterpolator(new AccelerateInterpolator());

            animator.setDuration(500);

            findViewById(R.id.content_overlay).setBackgroundColor(getResources().getColor(overlay_color));

            animator.start();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, myfragment).commit();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存当前的界面状态
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {

            case R.id.action_chenge:
                System.err.println(item.getTitle());
                return true;
            case R.id.action_add:
                System.err.println(item.getTitle());
                return true;
            case R.id.action_del:
                System.err.println(item.getTitle());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
