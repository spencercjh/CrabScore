package spencercjh.crabscore.CommonPart;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import spencercjh.crabscore.HttpRequst.Function.CommonPart.Fun_QueryUserProperty_Login;
import spencercjh.crabscore.HttpRequst.Function.CommonPart.Fun_UpdatePassword;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdatePasswordActivity extends AppCompatActivity implements OnClickListener {
    private EditText Euser_name;
    private EditText Edisplay_name;
    private EditText Eemail;
    private Spinner spinner;
    private Button check;
    private EditText Epassword;
    private Button update;
    private int choice = 0;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        Euser_name = (EditText) findViewById(R.id.text_input_user_name);
        Edisplay_name = (EditText) findViewById(R.id.text_input_display_name);
        Eemail = (EditText) findViewById(R.id.text_input_email);
        Epassword = (EditText) findViewById(R.id.text_input_password);
        check = (Button) findViewById(R.id.button);
        check.setOnClickListener(this);
        update = (Button) findViewById(R.id.button1);
        update.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] Items = getResources().getStringArray(R.array.roles);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choice = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            try {
                checkinfo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (view.getId() == R.id.button1) {
            if (flag) {
                updatepassword();
            } else {
                dialog_check_fail();
            }
        }
    }

    private void checkinfo() throws InterruptedException {
        String user_name = Euser_name.getText().toString().trim();
        String display_name = Edisplay_name.getText().toString().trim();
        String email = Eemail.getText().toString().trim();
        UserOBJ userOBJ = new UserOBJ(user_name, display_name, email, choice);
        UserOBJ check = JsonConvert.convert_UserOBJ(Fun_QueryUserProperty_Login.http_QueryUserProperty(userOBJ));
        if (user_name.equals(check.getUser_name()) && display_name.equals(check.getDisplay_name()) && email.equals(check.getEmail()) && choice == check.getRole_id()) {
            flag = true;
            dialog_check_success();
        } else {
            flag = false;
            dialog_check_fail();
        }
    }

    private void updatepassword() {
        String user_name = Euser_name.getText().toString().trim();
        String display_name = Edisplay_name.getText().toString().trim();
        String email = Eemail.getText().toString().trim();
        String password = Epassword.getText().toString().trim();
        UserOBJ userOBJ = new UserOBJ(user_name, MD5andKL.MD5(password), display_name, choice, email);
        dialog(userOBJ);
    }

    private void dialog(final UserOBJ userOBJ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePasswordActivity.this);
        builder.setMessage("确认修改密码？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            if (Fun_UpdatePassword.http_UpdatePassword(userOBJ)) {
                                dialog_update_success();
                            } else {
                                dialog_update_fail();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();////显示对话框
    }

    private void dialog_update_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePasswordActivity.this);
        builder.setMessage("修改成功！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        finish();
                    }
                }
        );
        builder.create().show();////显示对话框
    }

    private void dialog_update_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePasswordActivity.this);
        builder.setMessage("修改失败！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                }
        );
        builder.create().show();////显示对话框
    }

    private void dialog_check_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePasswordActivity.this);
        builder.setMessage("校验成功！你可以修改密码了！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                }
        );
        builder.create().show();////显示对话框
    }

    private void dialog_check_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePasswordActivity.this);
        builder.setMessage("校验失败！你选择不能修改密码！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                }
        );
        builder.create().show();////显示对话框
    }
}
