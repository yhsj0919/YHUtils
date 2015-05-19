package com.yh.yhui.app.activity;

import android.graphics.Color;
import android.os.Bundle;
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

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends AppCompatActivity {

    //save our header or result
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
                        new ProfileDrawerItem().withName("永恒瞬间").withEmail("1130402124@qq.com").withIcon(getResources().getDrawable(R.drawable.profile)),
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
                                    Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
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
                        new PrimaryDrawerItem().withName("待办").withIdentifier(1).withBadge("5").withIcon(GoogleMaterial.Icon.gmd_access_alarm),
                        new SectionDrawerItem().withName("分割线"),

                        new PrimaryDrawerItem().withName("主页").withIdentifier(2).withIcon(GoogleMaterial.Icon.gmd_home),
                        new SecondaryDrawerItem().withName("hello").withIcon(GoogleMaterial.Icon.gmd_home),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("设置").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(9)
                ).addStickyDrawerItems(
                        new SecondaryDrawerItem().withName("测试").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable) {

                            switch (drawerItem.getIdentifier()) {
                                case 1:
                                    getSupportActionBar().setTitle(((Nameable) drawerItem).getName());
                                    //getSupportActionBar().setSubtitle("fu标题");
                                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
                                    break;
                                case 2:
                                    getSupportActionBar().setTitle(((Nameable) drawerItem).getName());
                                    //getSupportActionBar().setSubtitle("fu标题2");

                                    View myView = findViewById(R.id.content_root);

                                    // get the center for the clipping circle
                                    int cx = (myView.getLeft() + myView.getRight()) / 2;
                                    int cy = (myView.getTop() + myView.getBottom()) / 2;

                                    // get the final radius for the clipping circle
                                    int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

                                    SupportAnimator animator =ViewAnimationUtils.createCircularReveal(myView, 30, cy, 0, finalRadius);

                                    animator.setInterpolator(new AccelerateInterpolator());

                                    animator.setDuration(500);

                                    findViewById(R.id.content_overlay).setBackgroundColor(Color.parseColor("#666666"));

                                    animator.start();

                                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
                                    break;
                                default:
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


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {

            case R.id.action_chenge:
                System.err.println(item.getTitle());
                return true;
            case R.id.action_add:
                System.err.println(item.getTitle());
                return true;
            case R.id.action_settings:
                System.err.println(item.getTitle());
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
