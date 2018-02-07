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
import android.widget.EditText;
import android.widget.TextView;

import spencercjh.crabscore.HttpRequst.Function.StaffPart.Fun_UpdateCrabInfo;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateCrabInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text_index;
    private TextView text_crab_id;
    private EditText text_crab_label;
    private EditText text_crab_weight;
    private EditText text_crab_length;
    private CrabOBJ crabOBJ = new CrabOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private GroupOBJ groupOBJ = new GroupOBJ();
    private int index = -1;
    private int sex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_crab_info);
        Intent intent = getIntent();
        crabOBJ = (CrabOBJ) intent.getSerializableExtra("CRABOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        index = (int) intent.getSerializableExtra("INDEX");
        sex = (int) intent.getSerializableExtra("SEX");
        groupOBJ = (GroupOBJ) intent.getSerializableExtra("GROUPOBJ");
        text_index = (TextView) findViewById(R.id.text_index);
        text_crab_id = (TextView) findViewById(R.id.text_crab_id);
        text_crab_label = (EditText) findViewById(R.id.text_crab_label);
        text_crab_weight = (EditText) findViewById(R.id.text_crab_weight);
        text_crab_length = (EditText) findViewById(R.id.text_crab_length);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        if (sex == 1) {
            toolbar.setTitle("第 " + groupOBJ.getGroup_id() + " 组 雄蟹");
        } else if (sex == 0) {
            toolbar.setTitle("第 " + groupOBJ.getGroup_id() + " 组 雌蟹");
        }
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
            updatecrabinfo();
        }
    }

    private void InitialInfo() {
        text_index.setText(index);
        text_crab_id.setText(crabOBJ.getCrab_id());
        text_crab_label.setText(crabOBJ.getCrab_label());
        text_crab_weight.setText(String.valueOf(crabOBJ.getCrab_weight()));
        text_crab_length.setText(String.valueOf(crabOBJ.getCrab_length()));
    }

    private boolean checkInput() {
        try {
            crabOBJ.setCrab_label(text_crab_label.getText().toString().trim());
            crabOBJ.setCrab_weight(Double.parseDouble(text_crab_weight.getText().toString().trim()));
            crabOBJ.setCrab_length(Double.parseDouble(text_crab_length.getText().toString().trim()));
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updatecrabinfo() {
        if (checkInput()) {
            dialog();
        } else {
            dialog_input_fail();
        }
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCrabInfoActivity.this);
        builder.setMessage("确认修改？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            if (Fun_UpdateCrabInfo.http_UpdateCrabInfo(crabOBJ, userOBJ)) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCrabInfoActivity.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCrabInfoActivity.this);
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

    private void dialog_input_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCrabInfoActivity.this);
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

    private void goback() {
        if (sex == 1) {
            Intent intent = new Intent(UpdateCrabInfoActivity.this, CrabList_M_Activity.class);
            intent.putExtra("GROUPOBJ", groupOBJ);
            intent.putExtra("USEROBJ", userOBJ);
            intent.putExtra("COMPETITIONID", crabOBJ.getCompetition_id());
            startActivity(intent);
            finish();
        } else if (sex == 0) {
            Intent intent = new Intent(UpdateCrabInfoActivity.this, CrabList_F_Activity.class);
            intent.putExtra("GROUPOBJ", groupOBJ);
            intent.putExtra("USEROBJ", userOBJ);
            intent.putExtra("COMPETITIONID", crabOBJ.getCompetition_id());
            startActivity(intent);
            finish();
        }
    }

    public void onBackPressed() {
        goback();
    }
}
