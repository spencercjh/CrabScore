package spencercjh.crabscore.PersonCenterPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import spencercjh.crabscore.HttpRequst.Function.PersonCenterPart.Fun_UpdateUserDisplayName;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateUserDisplayNameActivity extends AppCompatActivity implements View.OnClickListener {
    private UserOBJ userOBJ = new UserOBJ();
    private EditText text_display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_display_name);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        text_display_name = (EditText) findViewById(R.id.text_display_name);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("修改显示名");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        Initialinfo();
    }

    private void goback() {
        Intent intent = new Intent(UpdateUserDisplayNameActivity.this, PersonCenterActivity.class);
        intent.putExtra("USEROBJ", userOBJ);
        intent.putExtra("BACK", 1);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        goback();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updateuserinfo();
        }
    }

    private void Initialinfo() {
        text_display_name.setText(userOBJ.getDisplay_name().trim());
    }

    private void updateuserinfo() {
        dialog();
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserDisplayNameActivity.this);
        builder.setMessage("确认修改？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            userOBJ.setDisplay_name(text_display_name.getText().toString().trim());
                            if (Fun_UpdateUserDisplayName.http_UpdateUserDisplayName(userOBJ)) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserDisplayNameActivity.this);
        builder.setMessage("修改成功！");
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

    private void dialog_update_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserDisplayNameActivity.this);
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

}
