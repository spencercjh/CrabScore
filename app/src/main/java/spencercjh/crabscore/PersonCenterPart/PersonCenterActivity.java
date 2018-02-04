package spencercjh.crabscore.PersonCenterPart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class PersonCenterActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ViewPager vp_content;
    private TabLayout tab_title;
    private ArrayList<String> mTitleArray = new ArrayList<String>();
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private long lastPressTime = 0;
    private RelativeLayout Rpassword;
    private RelativeLayout Ruser_name;
    private RelativeLayout Rdisplay_name;
    private RelativeLayout Remail;
    private TextView Tuser_name;
    private TextView Tdisplay_name;
    private TextView Temail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        Tuser_name = (TextView) findViewById(R.id.text_user_name);
        Tdisplay_name = (TextView) findViewById(R.id.text_display_name);
        Temail = (TextView) findViewById(R.id.text_email);
        Rpassword = (RelativeLayout) findViewById(R.id.re_password);
        Rpassword.setOnClickListener(this);
        Ruser_name = (RelativeLayout) findViewById(R.id.re_user_name);
        Ruser_name.setOnClickListener(this);
        Rdisplay_name = (RelativeLayout) findViewById(R.id.re_display_name);
        Rdisplay_name.setOnClickListener(this);
        Remail = (RelativeLayout) findViewById(R.id.re_email);
        Remail.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("       个人中心");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        InitialInfo();
    }

    public void onBackPressed() {
        if (new Date().getTime() - lastPressTime < 1000) {
            finish();
            Runtime.getRuntime().exit(0);//结束程序
        } else {
            lastPressTime = new Date().getTime();//重置lastPressTime
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.re_password:
                intent = new Intent(this, UpdateUserPasswordActivity.class);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                finish();
                break;
            case R.id.re_user_name:
                intent = new Intent(this, UpdateUserUserNameActivity.class);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                finish();
                break;
            case R.id.re_display_name:
                intent = new Intent(this, UpdateUserDisplayNameActivity.class);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                finish();
                break;
            case R.id.re_email:
                intent = new Intent(this, UpdateUserEmailActivity.class);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void InitialInfo() {
        /**
         * 数据初始化 网络线程
         */

    }
}
