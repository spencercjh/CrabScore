package spencercjh.crabscore.AdministratorPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateCompetitionProperty;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateMoreSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private UserOBJ userOBJ = new UserOBJ();
    private CompetitionOBJ competitionOBJ = new CompetitionOBJ();
    private RadioButton Renable1;
    private RadioButton Runable1;
    private RadioButton Renable2;
    private RadioButton Runable2;
    private RadioButton Renable3;
    private RadioButton Runable3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_more_setting);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        competitionOBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
        Renable1 = (RadioButton) findViewById(R.id.radio_enable1);
        Runable1 = (RadioButton) findViewById(R.id.radio_unable1);
        Renable2 = (RadioButton) findViewById(R.id.radio_enable2);
        Runable2 = (RadioButton) findViewById(R.id.radio_unable2);
        Renable3 = (RadioButton) findViewById(R.id.radio_enable3);
        Runable3 = (RadioButton) findViewById(R.id.radio_unable3);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("修改其他比赛设置");
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

    private void goback() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updateCompetition_info();
        }
    }

    private void InitialInfo() {
        try {
            if (competitionOBJ.getResult_fatness() == 1) {
                Renable1.setChecked(true);
            } else {
                Runable1.setChecked(true);
            }
            if (competitionOBJ.getResult_quality() == 1) {
                Renable2.setChecked(true);
            } else {
                Runable2.setChecked(true);
            }
            if (competitionOBJ.getResult_taste() == 1) {
                Renable3.setChecked(true);
            } else {
                Runable3.setChecked(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCompetition_info() {
        if (Renable1.isChecked()) {
            competitionOBJ.setResult_fatness(1);
        }
        if (Runable1.isChecked()) {
            competitionOBJ.setResult_fatness(0);
        }
        if (Renable2.isChecked()) {
            competitionOBJ.setResult_quality(1);
        }
        if (Runable2.isChecked()) {
            competitionOBJ.setResult_quality(0);
        }
        if (Renable3.isChecked()) {
            competitionOBJ.setResult_taste(1);
        }
        if (Runable3.isChecked()) {
            competitionOBJ.setResult_taste(0);
        }
        dialog();
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateMoreSettingActivity.this);
        builder.setMessage("确认修改？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            if (Fun_UpdateCompetitionProperty.http_UpdateCompetitionProperty(competitionOBJ, userOBJ)) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateMoreSettingActivity.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateMoreSettingActivity.this);
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

    public void onBackPressed() {
        goback();
    }
}
