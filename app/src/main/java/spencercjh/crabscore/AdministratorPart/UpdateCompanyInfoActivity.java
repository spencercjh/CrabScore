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

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateCompanyName;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateCompanyInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText text_company_name;
    private CompanyOBJ companyOBJ = new CompanyOBJ();
    private UserOBJ userOBJ = new UserOBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_company_info);
        Intent intent = getIntent();
        companyOBJ = (CompanyOBJ) intent.getSerializableExtra("COMPANYOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        text_company_name = (EditText) findViewById(R.id.text_company_name);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("修改参选单位信息");
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
            try {
                updatecompanyinfo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void InitialInfo() {
        text_company_name.setText(companyOBJ.getCompany_name());
    }

    private void updatecompanyinfo() throws InterruptedException {
        String company_name = text_company_name.getText().toString().trim();
        companyOBJ.setCompany_name(company_name);
        dialog();
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCompanyInfoActivity.this);
        builder.setMessage("确认修改？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            if (Fun_UpdateCompanyName.http_UpdateCompanyName(companyOBJ, userOBJ)) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCompanyInfoActivity.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCompanyInfoActivity.this);
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
