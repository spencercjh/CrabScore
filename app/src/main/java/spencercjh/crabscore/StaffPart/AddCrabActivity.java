package spencercjh.crabscore.StaffPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import spencercjh.crabscore.HttpRequst.Function.StaffPart.Fun_InsertCrabInfo;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class AddCrabActivity extends AppCompatActivity implements View.OnClickListener {
    private GroupOBJ groupOBJ = new GroupOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private int competition_id = -1;
    private CheckBox male;
    private CheckBox female;
    private int num;
    private EditText text_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crab_activity);
        Intent intent = getIntent();
        groupOBJ = (GroupOBJ) intent.getSerializableExtra("GROUPOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        competition_id = (int) intent.getSerializableExtra("COMPETITIONID");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("为第 " + groupOBJ.getGroup_id() + " 组添加螃蟹");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        male = (CheckBox) findViewById(R.id.checkBox_m);
        female = (CheckBox) findViewById(R.id.checkBox_f);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        text_num = (EditText) findViewById(R.id.text_num);
    }

    private void goback() {
        finish();
    }

    @Override
    public void onBackPressed() {
        goback();
    }

    private boolean checkInput() {
        try {
            num = Integer.parseInt(text_num.getText().toString().trim());
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void Choose_Add() throws InterruptedException {
        if (male.isChecked() && !female.isChecked()) {
            Add(1);
        } else if (female.isChecked() && !male.isChecked()) {
            Add(0);
        } else if (male.isChecked() && female.isChecked()) {
            Add(1);
            Add(0);
        }
    }

    private void Add(int crab_sex) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            if (!Fun_InsertCrabInfo.http_InsertCrabInfo(new CrabOBJ(groupOBJ.getGroup_id(), crab_sex, competition_id), userOBJ)) {
                dialog_insert_fail();
            }
        }
        dialog_insert_success();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            if (checkInput()) {
                dialog_insert();
            } else {
                dialog_input_fail();
            }
        }
    }

    private void dialog_insert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCrabActivity.this);
        builder.setMessage("确认添加？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            Choose_Add();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            dialog_insert_fail();
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

    private void dialog_insert_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCrabActivity.this);
        builder.setMessage("添加成功！");
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

    private void dialog_insert_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCrabActivity.this);
        builder.setMessage("添加失败！");
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

    private void dialog_input_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCrabActivity.this);
        builder.setMessage("非法输入！");
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
