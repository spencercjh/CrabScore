package spencercjh.crabscore.AdministratorPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateUserProperty;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateUserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Tuser_name;
    private EditText Tdisplay_name;
    private EditText Temail;
    private Spinner spinner;
    private UserOBJ admin = new UserOBJ();
    private UserOBJ userOBJ = new UserOBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        Intent intent = getIntent();
        admin = (UserOBJ) intent.getSerializableExtra("ADMIN");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        Tuser_name = (TextView) findViewById(R.id.text_user_name);
        Tdisplay_name = (EditText) findViewById(R.id.text_display_name);
        Temail = (EditText) findViewById(R.id.text_email);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] Items = getResources().getStringArray(R.array.roles2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                userOBJ.setRole_id(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("修改用户信息");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        InitialInfo();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updateCompetition_info();
        }
    }

    private void InitialInfo() {
        Tuser_name.setText(userOBJ.getUser_name());
        Tdisplay_name.setText(userOBJ.getDisplay_name());
        Temail.setText(userOBJ.getEmail());
        spinner.setSelection(userOBJ.getRole_id());
    }

    private void updateCompetition_info() {
        String display_name = Tdisplay_name.getText().toString().trim();
        String email = Temail.getText().toString().trim();
        userOBJ.setDisplay_name(display_name);
        userOBJ.setEmail(email);
        dialog();
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserInfoActivity.this);
        builder.setMessage("确认修改？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            if (Fun_UpdateUserProperty.http_UpdateUserProperty(userOBJ, admin)) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserInfoActivity.this);
        builder.setMessage("修改成功！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        goback();
                    }
                }
        );
        builder.create().show();////显示对话框
    }

    private void dialog_update_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserInfoActivity.this);
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

    private void goback() {
        finish();
    }
}

