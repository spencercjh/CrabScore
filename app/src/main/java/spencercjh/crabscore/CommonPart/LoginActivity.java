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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import spencercjh.crabscore.HttpRequst.Function.CommonPart.Fun_Login;
import spencercjh.crabscore.HttpRequst.Function.CommonPart.Fun_QueryUserStatus;
import spencercjh.crabscore.HttpRequst.Function.CommonPart.Fun_isNetworkAvailable;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

/**
 * 用户组1 管理员 administrator
 * 用户组2 工作人员 staff
 * 用户组3 评委 judge
 * 用户组4 参选单位 company
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private long lastPressTime = 0;
    private TextView forgetkey;
    private TextView register;
    private EditText id;
    private EditText pwd;
    private CheckBox rem_pw, auto_login;
    private SharedPreferences sp;
    private int choice = 0;

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
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] Items = getResources().getStringArray(R.array.roles);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                choice = pos;
                if (pos == 1) {
                    sp.edit().putBoolean("ADMINISTRATOR", true).apply();
                } else if (pos == 2) {
                    sp.edit().putBoolean("STAFF", true).apply();
                } else if (pos == 3) {
                    sp.edit().putBoolean("JUDGE", true).apply();
                } else if (pos == 4) {
                    sp.edit().putBoolean("COMPANY", true).apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sp.edit().putBoolean("ADMINISTRATOR", false).apply();
                sp.edit().putBoolean("STAFF", false).apply();
                sp.edit().putBoolean("JUDGE", false).apply();
                sp.edit().putBoolean("COMPANY", false).apply();
            }
        });
        Button bt_login = (Button) findViewById(R.id.button_search);
        bt_login.setOnClickListener(this);
        setFullScreen();
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
                sp.edit().putBoolean("ADMINISTRATOR", false).apply();
                sp.edit().putBoolean("STAFF", false).apply();
                sp.edit().putBoolean("JUDGE", false).apply();
                sp.edit().putBoolean("COMPANY", false).apply();
                spinner.setSelection(0);    //spinner恢复默认第0项
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
            spinner.setSelection(1);
            choice = 1;
        } else if (sp.getBoolean("STAFF", false)) {     //设置默认是用户组2状态
            spinner.setSelection(2);
            choice = 2;
        } else if (sp.getBoolean("JUDGE", false)) { //设置默认是用户组3状态
            spinner.setSelection(3);
            choice = 3;
        } else if (sp.getBoolean("COMPANY", false)) {  //设置默认是用户组4状态
            spinner.setSelection(4);
            choice = 4;
        }
        if (sp.getBoolean("ISCHECK", false)) {  //设置默认是记录密码状态
            rem_pw.setChecked(true);
            id.setText(sp.getString("USER_ID", ""));
            pwd.setText(sp.getString("PASSWORD", ""));
            if (sp.getBoolean("AUTO_ISCHECK", false)) { //判断自动登陆多选框状态
                auto_login.setChecked(true);          //设置默认是自动登录状态
                /*
                  自动登陆
                 */
                UserOBJ userOBJ = new UserOBJ(id.getText().toString().trim(), MD5andKL.MD5(pwd.getText().toString().trim()), choice);
                int user_status;
                try {
                    user_status = Fun_QueryUserStatus.http_QueryUserStatus(userOBJ);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    user_status = -1;
                }
                if (user_status == 1) {
                    int login_status;
                    try {
                        login_status = Fun_Login.http_login(userOBJ);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        login_status = -1;
                    }
                    if (login_status == 1) {
                        Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("USEROBJ", userOBJ);
                        intent.putExtra("USER", choice);
                        LoginActivity.this.startActivity(intent);
                        finish();
                    } else if (login_status == 0) {
                        Toast.makeText(LoginActivity.this, "账号或密码错误或用户组选择不正确！", Toast.LENGTH_SHORT).show();
                    } else if (login_status == -1) {
                        Toast.makeText(LoginActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                } else if (user_status == 0) {
                    Toast.makeText(LoginActivity.this, "账号被禁用！", Toast.LENGTH_SHORT).show();
                } else if (user_status == -1) {
                    Toast.makeText(LoginActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(LoginActivity.this, UpdatePasswordActivity.class);
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
        String user_name = id.getText().toString().trim();
        String passowrd = pwd.getText().toString().trim();
        if (user_name.length() == 0 || passowrd.length() == 0) {
            new AlertDialog.Builder(this).setMessage("帐号或密码不可为空！").setCancelable(false).
                    setIcon(android.R.drawable.ic_dialog_alert).setTitle("注意").setPositiveButton("关闭", null).show();
        } else {
            if (choice == 0) {
                new AlertDialog.Builder(this)
                        .setMessage("请选择你的用户组")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("注意")
                        .setPositiveButton("关闭", null)
                        .show();
            }
            /*
              登陆
             */
            UserOBJ userOBJ = new UserOBJ(user_name, MD5andKL.MD5(passowrd), choice);
            int user_status = Fun_QueryUserStatus.http_QueryUserStatus(userOBJ);
            if (user_status == 1) {
                int login_status = Fun_Login.http_login(userOBJ);
                if (login_status == 1) {
                    Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                    if (rem_pw.isChecked()) {
                        //记住用户名、密码、
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_ID", userOBJ.getUser_name());
                        editor.putString("PASSWORD", userOBJ.getPassword());
                        editor.apply();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("USEROBJ", userOBJ);
                    LoginActivity.this.startActivity(intent);
                    finish();
                } else if (login_status == 0) {
                    Toast.makeText(LoginActivity.this, "账号或密码错误或用户组选择不正确！", Toast.LENGTH_SHORT).show();
                } else if (login_status == -1) {
                    Toast.makeText(LoginActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                }
            } else if (user_status == 0) {
                Toast.makeText(LoginActivity.this, "账号被禁用！", Toast.LENGTH_SHORT).show();
            } else if (user_status == -1) {
                Toast.makeText(LoginActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }
            /*
              测试登陆 直接进入
             */
         /*   UserOBJ userOBJ_test = new UserOBJ("123", "123", choice);
            if (rem_pw.isChecked()) {
                //记住用户名、密码、
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_ID", userOBJ_test.getUser_name());
                editor.putString("PASSWORD", userOBJ_test.getPassword());
                editor.apply();
            }
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("USEROBJ", userOBJ_test);
            LoginActivity.this.startActivity(intent);
            finish();*/
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