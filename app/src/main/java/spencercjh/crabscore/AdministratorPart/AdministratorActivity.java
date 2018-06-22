package spencercjh.crabscore.AdministratorPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressWarnings({"deprecation", "ConstantConditions"})
public class AdministratorActivity extends AppCompatActivity implements OnTabSelectedListener {
    private ViewPager vp_content;
    private TabLayout tab_title;
    private ArrayList<String> mTitleArray = new ArrayList<>();
    private UserOBJ userOBJ = new UserOBJ();
    private long lastPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
        tab_title = (TabLayout) findViewById(R.id.tab_title);
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        tl_head.setTitle("查看成绩");
        tl_head.setEnabled(false);
        setSupportActionBar(tl_head);
        mTitleArray.add("用户列表");
        mTitleArray.add("注册审核");
        mTitleArray.add("参选单位");
        mTitleArray.add("大赛管理");
        initTabLayout();
        initTabViewPager();
    }

    private void initTabLayout() {
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(0)));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(1)));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(2)));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(3)));
        tab_title.setOnTabSelectedListener(this);
    }

    private void initTabViewPager() {
        AdministratorPageAdapter adapter = new AdministratorPageAdapter(getSupportFragmentManager(), mTitleArray, userOBJ);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tab_title.getTabAt(position).select();
            }
        });
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp_content.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastPressTime < 1000) {
            finish();
            Runtime.getRuntime().exit(0);//结束程序
        } else {
            lastPressTime = System.currentTimeMillis();//重置lastPressTime
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        // ActionBar的featureId是8，Toolbar的featureId是108
        if (featureId % 100 == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

}
