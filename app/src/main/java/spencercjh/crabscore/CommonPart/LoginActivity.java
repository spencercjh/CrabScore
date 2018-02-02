package spencercjh.crabscore.CommonPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import spencercjh.crabscore.HttpRequest.Function.Fun_isNetworkAvailable;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

/**
 * 用户组1 管理员 administrator
 * 用户组2 工作人员 staff
 * 用户组3 评委 judge
 * 用户组4 参选单位 unit
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private long lastPressTime = 0;
    private TextView forgetkey;
    private TextView register;
    private EditText id;
    private EditText pwd;
    private CheckBox rem_pw, auto_login;
    private SharedPreferences sp;
    private RadioButton choice_user1;
    private RadioButton choice_user2;
    private RadioButton choice_user3;
    private RadioButton choice_user4;
    private int choice;

    @SuppressLint("WorldReadableFiles")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgetkey = (TextView) findViewById(R.id.button_forget_password);
        register = (TextView) findViewById(R.id.button_register);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        id = (EditText) findViewById(R.id.edit_id);
        pwd = (EditText) findViewById(R.id.edit_password);
        rem_pw = (CheckBox) findViewById(R.id.remember_password);
        auto_login = (CheckBox) findViewById(R.id.auto_login);
        choice_user1 = (RadioButton) findViewById(R.id.user1_administrator);
        choice_user2 = (RadioButton) findViewById(R.id.user2_staff);
        choice_user3 = (RadioButton) findViewById(R.id.user3_judge);
        choice_user4 = (RadioButton) findViewById(R.id.user4_unit);
        Button bt_login = (Button) findViewById(R.id.button_search);
        bt_login.setOnClickListener(this);
        setFullScreen();
        //退出登录后回到login界面清空输入过的id和pwd
        try {
            Intent it_exit = getIntent();
            String exit = (String) it_exit.getSerializableExtra("EXIT");
            if (exit != null) {
                id.setText("");
                pwd.setText("");
                auto_login.setChecked(false);
                sp.edit().putBoolean("AUTO_ISCHECK", false).apply();
                rem_pw.setChecked(false);
                sp.edit().putBoolean("ISCHECK", false).apply();
                choice_user1.setChecked(false);
                sp.edit().putBoolean("ADMINISTRATOR", false).apply();
                choice_user2.setChecked(false);
                sp.edit().putBoolean("STAFF", false).apply();
                choice_user3.setChecked(false);
                sp.edit().putBoolean("JUDGE", false).apply();
                choice_user4.setChecked(false);
                sp.edit().putBoolean("UNIT", false).apply();
            }
        } catch (RuntimeException ignored) {
            ignored.printStackTrace();
        }
        forget_password();
        register();
        if (!Fun_isNetworkAvailable.isNetworkAvailable(LoginActivity.this)) {        //检测用户联网状态
            new AlertDialog.Builder(LoginActivity.this).setMessage("你是不是忘记打开数据连接了？").setCancelable(true).
                    setIcon(android.R.drawable.ic_dialog_alert).setTitle("警告").setPositiveButton("确认", null).show();
        }
        if (sp.getBoolean("ADMINISTRATOR", false)) {  //设置默认是用户组1状态
            choice_user1.setChecked(true);
            choice = 1;
        } else if (sp.getBoolean("JUDGE", false)) { //设置默认是用户组3状态
            choice_user3.setChecked(true);
            choice = 3;
        } else if (sp.getBoolean("UNIT", false)) {  //设置默认是用户组4状态
            choice_user4.setChecked(true);
            choice = 4;
        } else if (sp.getBoolean("STAFF", false)) {     //设置默认是用户组2状态
            choice_user2.setChecked(true);
            choice = 2;
        }
        if (sp.getBoolean("ISCHECK", false)) {  //设置默认是记录密码状态
            rem_pw.setChecked(true);
            id.setText(sp.getString("USER_ID", ""));
            pwd.setText(sp.getString("PASSWORD", ""));
            if (sp.getBoolean("AUTO_ISCHECK", false)) { //判断自动登陆多选框状态
                auto_login.setChecked(true);          //设置默认是自动登录状态
                /**
                 * 自动登陆
                 */
                UserOBJ userOBJ = new UserOBJ(id.getText().toString().trim(), pwd.getText().toString().trim(), choice);
                int result = -1;
                try {
                    result = Fun_TeacherLogin.http_LoginTeacher(teacherOBJ.getTeacher_id(), teacherOBJ.getTeacher_password());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (result == 1) {  //登陆成功
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);    //跳转界面
                    intent.putExtra("USEROBJ", userOBJ);
                    intent.putExtra("USER", choice);
                    startActivity(intent);
                    finish();
                } else if (result == 0) {   //登陆失败
                    Toast.makeText(getApplicationContext(), "用户名不存在或密码错误", Toast.LENGTH_SHORT).show();
                } else if (result == -1) {  //连接服务器失败
                    Toast.makeText(getApplicationContext(), "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //监听自动登录多选框按钮事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (auto_login.isChecked()) {
                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).apply();
                } else {
                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).apply();
                }
            }
        });
        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rem_pw.isChecked()) {
                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).apply();
                } else {
                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).apply();
                }
            }
        });
        //监听用户组1多选框按钮事件
        choice_user1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (choice_user1.isChecked()) {
                    System.out.println("用户组1已选中");
                    sp.edit().putBoolean("ADMINISTRATOR", true).apply();
                } else {
                    System.out.println("用户组1没有选中");
                    sp.edit().putBoolean("ADMINISTRATOR", false).apply();
                }
            }
        });
        //监听用户组2多选框按钮事件
        choice_user2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (choice_user2.isChecked()) {
                    System.out.println("用户组2已选中");
                    sp.edit().putBoolean("STAFF", true).apply();
                } else {
                    System.out.println("用户组2没有选中");
                    sp.edit().putBoolean("STAFF", false).apply();
                }
            }
        });
        //监听用户组3多选框按钮事件
        choice_user3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (choice_user3.isChecked()) {
                    System.out.println("用户组3已选中");
                    sp.edit().putBoolean("JUDGE", true).apply();
                } else {
                    System.out.println("用户组3没有选中");
                    sp.edit().putBoolean("JUDGE", false).apply();
                }
            }
        });
        //监听用户组4多选框按钮事件
        choice_user4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (choice_user4.isChecked()) {
                    System.out.println("用户组4已选中");
                    sp.edit().putBoolean("UNIT", true).apply();
                } else {
                    System.out.println("用户组4没有选中");
                    sp.edit().putBoolean("UNIT", false).apply();
                }
            }
        });
    }

    //忘记密码按钮
    private void forget_password() {
        String text2 = "忘记密码?";
        SpannableString spannableString2 = new SpannableString(text2);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#000000"));
        spannableString2.setSpan(colorSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, GetPasswordActivity.class);
                startActivity(intent);
            }
        }, 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        forgetkey.setText(spannableString2);
        forgetkey.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //注册按钮
    private void register() {
        String text2 = "新用户注册点我！";
        SpannableString spannableString2 = new SpannableString(text2);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#000000"));
        spannableString2.setSpan(colorSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        }, 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(spannableString2);
        register.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //登陆按钮
    public void Login(View view) throws InterruptedException {
        String Eid = id.getText().toString().trim();
        String Epassword = pwd.getText().toString().trim();
        if (Eid.length() == 0 || Epassword.length() == 0) {
            new AlertDialog.Builder(this).setMessage("帐号或密码不可为空！").setCancelable(false).
                    setIcon(android.R.drawable.ic_dialog_alert).setTitle("注意").setPositiveButton("关闭", null).show();
        } else {
            if (!choice_user1.isChecked() && !choice_user2.isChecked() && !choice_user3.isChecked() && !choice_user4.isChecked()) {
                new AlertDialog.Builder(this)
                        .setMessage("请选择你的用户组")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("注意")
                        .setPositiveButton("关闭", null)
                        .show();
            } else if (choice_user1.isChecked()) {//用户组1登录
                choice = 1;
            } else if (choice_user2.isChecked()) {//用户组2登录
                choice = 2;
            } else if (choice_user3.isChecked()) {//用户组3登陆
                choice = 3;
            } else if (choice_user4.isChecked()) {//用户组4登陆
                choice = 4;
            }
            /**
             * 登陆
             */
            UserOBJ userOBJ = new UserOBJ(Eid, Epassword, choice);
            int result = ;//网络登录请求
            if (result == 1) {
                if (rem_pw.isChecked()) {
                    //记住用户名、密码、
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USER_ID", userOBJ.getUser_name());
                    editor.putString("PASSWORD", userOBJ.getPassword());
                    editor.apply();
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                LoginActivity.this.startActivity(intent);
                finish();
            } else if (result == 0) {
                Toast.makeText(getApplicationContext(), "用户名不存在或密码错误", Toast.LENGTH_SHORT).show();
            } else if (result == -1) {
                Toast.makeText(getApplicationContext(), "连接服务器失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //重写返回键
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
        if (view.getId() == R.id.button_search) {
            try {
                Login(view);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}