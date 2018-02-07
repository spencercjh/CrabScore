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
import android.widget.EditText;

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateCompetitionProperty;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateYear_NoteActivity extends AppCompatActivity implements View.OnClickListener {
    private CompetitionOBJ competitionOBJ = new CompetitionOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private EditText Eyear;
    private EditText Enote;
    private Button button;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_year__note);
        Intent intent = getIntent();
        choice = (int) intent.getSerializableExtra("USER");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        competitionOBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
        Eyear = (EditText) findViewById(R.id.text_year);
        Enote = (EditText) findViewById(R.id.text_note);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("修改年份以及备注信息");
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

    private void InitialInfo() {
        try {
            Eyear.setText(competitionOBJ.getCompetition_year());
            Enote.setText(competitionOBJ.getNote());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updateCompetition_info();
        }
    }

    private void updateCompetition_info() {
        String year = Eyear.getText().toString().trim();
        competitionOBJ.setCompetition_year(year);
        String note = Enote.getText().toString();
        competitionOBJ.setNote(note);
        dialog();
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateYear_NoteActivity.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateYear_NoteActivity.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateYear_NoteActivity.this);
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
