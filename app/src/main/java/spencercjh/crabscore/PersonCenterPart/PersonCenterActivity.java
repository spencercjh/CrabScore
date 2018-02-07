package spencercjh.crabscore.PersonCenterPart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.PersonCenterPart.Fun_QueryUserProperty_Common;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class PersonCenterActivity extends AppCompatActivity implements View.OnClickListener {
    private UserOBJ userOBJ = new UserOBJ();
    private long lastPressTime = 0;
    private TextView Tuser_name;
    private TextView Tdisplay_name;
    private TextView Temail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        Tuser_name = (TextView) findViewById(R.id.text_user_name);
        Tdisplay_name = (TextView) findViewById(R.id.text_display_name);
        Temail = (TextView) findViewById(R.id.text_email);
        RelativeLayout rpassword = (RelativeLayout) findViewById(R.id.re_password);
        rpassword.setOnClickListener(this);
        RelativeLayout rdisplay_name = (RelativeLayout) findViewById(R.id.re_display_name);
        rdisplay_name.setOnClickListener(this);
        RelativeLayout Remail = (RelativeLayout) findViewById(R.id.re_email);
        Remail.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("个人中心");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        try {
            InitialInfo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                startActivity(intent);
                finish();
                break;
            case R.id.re_display_name:
                intent = new Intent(this, UpdateUserDisplayNameActivity.class);
                intent.putExtra("USEROBJ", userOBJ);
                startActivity(intent);
                finish();
                break;
            case R.id.re_email:
                intent = new Intent(this, UpdateUserEmailActivity.class);
                intent.putExtra("USEROBJ", userOBJ);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void InitialInfo() throws InterruptedException {
        userOBJ = JsonConvert.convert_UserOBJ(Fun_QueryUserProperty_Common.http_QueryUserProperty(userOBJ));
        Tuser_name.setText(userOBJ.getUser_name().trim());
        Tdisplay_name.setText(userOBJ.getDisplay_name().trim());
        Temail.setText(userOBJ.getEmail().trim());
    }
}
