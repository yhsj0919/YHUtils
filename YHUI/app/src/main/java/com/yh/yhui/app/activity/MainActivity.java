package com.yh.yhui.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.*;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.KeyboardUtil;
import com.yh.yhui.app.R;
import com.yh.yhui.app.fragment.Fragment_Main;
import com.yh.yhui.app.fragment.Fragment_Menu_List;
import com.yh.yhui.app.fragment.weight_demo.Weight_Demo_Recyclerview;
import com.yh.yhui.app.mode.Event_Fragment_Chenge;
import de.greenrobot.event.EventBus;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends AppCompatActivity {


	private Drawer result = null;
	private Toolbar toolbar;
	private AccountHeader headerResult = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Handle Toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		setDrawer(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	private void setDrawer(Bundle savedInstanceState) {

		headerResult = new AccountHeaderBuilder()
				.withActivity(this)
				.withHeaderBackground(R.drawable.header)
				.addProfiles(
						new ProfileDrawerItem().withEmail("1130402124@qq.com").withName("永恒瞬间").withIcon(getResources().getDrawable(R.drawable.logo)),
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

		result = new DrawerBuilder()
				.withActivity(this)
				.withToolbar(toolbar)
				.withActionBarDrawerToggle(true)
				.withAccountHeader(headerResult)
				.addDrawerItems(
						new PrimaryDrawerItem().withName("示例").withIdentifier(1).withBadge("5").withIcon(GoogleMaterial.Icon.gmd_notifications),
						new SectionDrawerItem().withName("一些控件"),
						new PrimaryDrawerItem().withName("Recyclerview").withIdentifier(2).withIcon(GoogleMaterial.Icon.gmd_local_phone),
						new PrimaryDrawerItem().withName("HttpUtils").withIdentifier(3).withIcon(GoogleMaterial.Icon.gmd_message),
						new PrimaryDrawerItem().withName("BitmapUtils").withIdentifier(4).withIcon(GoogleMaterial.Icon.gmd_blur_circular),
						new PrimaryDrawerItem().withName("AppUtils").withIdentifier(5).withIcon(FontAwesome.Icon.faw_edit),
						new PrimaryDrawerItem().withName("AssetUtils").withIdentifier(6).withIcon(GoogleMaterial.Icon.gmd_content_paste),
						new PrimaryDrawerItem().withName("DateUtils").withIdentifier(7).withIcon(FontAwesome.Icon.faw_camera),
						new PrimaryDrawerItem().withName("DensityUtils").withIdentifier(8).withIcon(FontAwesome.Icon.faw_wechat),
						new PrimaryDrawerItem().withName("DeviceUtils").withIdentifier(9).withIcon(FontAwesome.Icon.faw_newspaper_o),
						new PrimaryDrawerItem().withName("ImageUtils").withIdentifier(10).withIcon(GoogleMaterial.Icon.gmd_people),
						new PrimaryDrawerItem().withName("IoUtils").withIdentifier(11).withIcon(GoogleMaterial.Icon.gmd_nature_people),
						new PrimaryDrawerItem().withName("KeyBoardUtils").withIdentifier(12).withIcon(GoogleMaterial.Icon.gmd_event_available),
						new PrimaryDrawerItem().withName("NotificationUtils").withIdentifier(13).withIcon(FontAwesome.Icon.faw_truck),
						new PrimaryDrawerItem().withName("NetWorkUtils").withIdentifier(14).withIcon(FontAwesome.Icon.faw_thumbs_up),
						new PrimaryDrawerItem().withName("ScreenUtils").withIdentifier(15).withIcon(GoogleMaterial.Icon.gmd_store),
						new PrimaryDrawerItem().withName("SdcardUtils").withIdentifier(16).withIcon(FontAwesome.Icon.faw_line_chart),
						new PrimaryDrawerItem().withName("暂无1").withIdentifier(17).withIcon(FontAwesome.Icon.faw_cc_paypal),
						new PrimaryDrawerItem().withName("暂无2").withIdentifier(18).withIcon(FontAwesome.Icon.faw_money),
						new DividerDrawerItem(),
						new SecondaryDrawerItem().withName("设置").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(19)
				).addStickyDrawerItems(

						new SecondaryDrawerItem().withName("退出").withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withIdentifier(20)
				)
				.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
					@Override
					public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, final IDrawerItem drawerItem) {
						if (drawerItem != null && drawerItem instanceof Nameable) {
							switch (drawerItem.getIdentifier()) {
								case 1:
									chengeAty(((Nameable) drawerItem).getName(), new Fragment_Menu_List(), R.color.colorPrimary, false);
									toolbar.getMenu().clear();
									toolbar.inflateMenu(R.menu.menu_main);
									break;
								case 2:
									chengeAty(((Nameable) drawerItem).getName(), new Weight_Demo_Recyclerview(), R.color.colorPrimary, false);
									toolbar.getMenu().clear();
									toolbar.inflateMenu(R.menu.menu_test);
									break;
								//                                case 3:
								//                                    chengeAty(new Fragment_Utils_Http(), R.color.colorPrimary);
								//                                    break;
								//                                case 4:
								//                                    chengeAty(new Fragment_Utils_Bitmap(), R.color.colorPrimary);
								//                                    break;
								//                                case 5:
								//                                    chengeAty(new Fragment_Utils_App(), R.color.colorPrimary);
								//                                    break;
								//                                case 6:
								//                                    chengeAty(new Fragment_Utils_Asset(), R.color.colorPrimary);
								//                                    break;
								//                                case 7:
								//                                    chengeAty(new Fragment_Utils_Date(), R.color.colorPrimary);
								//                                    break;
								//                                case 8:
								//                                    chengeAty(new Fragment_Utils_Density(), R.color.colorPrimary);
								//                                    break;
								//                                case 9:
								//                                    chengeAty(new Fragment_Utils_Device(), R.color.colorPrimary);
								//                                    break;
								//                                case 10:
								//                                    chengeAty(new Fragment_Utils_Image(), R.color.colorPrimary);
								//                                    break;
								//                                case 11:
								//                                    chengeAty(new Fragment_Utils_IO(), R.color.colorPrimary);
								//                                    break;
								//                                case 12:
								//                                    chengeAty(new Fragment_Utils_KeyBoard(), R.color.colorPrimary);
								//                                    break;
								//                                case 13:
								//                                    chengeAty(new Fragment_Utils_Notification(), R.color.colorPrimary);
								//                                    break;
								//                                case 14:
								//                                    chengeAty(new Fragment_Utils_NetWork(), R.color.colorPrimary);
								//                                    break;
								//                                case 15:
								//                                    chengeAty(new Fragment_Utils_Screen(), R.color.colorPrimary);
								//                                    break;
								//                                case 16:
								//                                    chengeAty(new Fragment_Utils_SDCard(), R.color.colorPrimary);
								//                                    break;
								default:
									chengeAty(((Nameable) drawerItem).getName(), new Fragment_Main(((Nameable) drawerItem).getName()), R.color.colorPrimary, false);
									break;
							}
						}
						return false;
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
	public void chengeAty(String title, Fragment myfragment, int overlay_color, boolean addToBackStack) {

		if (title != null || !title.equals("")) {
			getSupportActionBar().setTitle(title);
		}


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

		if (addToBackStack) {
			getSupportFragmentManager()
					.beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
					.addToBackStack(null)
					.replace(R.id.content_frame, myfragment)
					.commit();
		} else {
			getSupportFragmentManager().popBackStack();

			getSupportFragmentManager()
					.beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
					.replace(R.id.content_frame, myfragment)
					.commit();
		}


	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState = result.saveInstanceState(outState);
		outState = headerResult.saveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onBackPressed() {
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


	public void onEvent(Event_Fragment_Chenge event) {
		if (event.getId() == 1) {
			chengeAty("示例", event.getFragment(), event.getColor(), true);
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
