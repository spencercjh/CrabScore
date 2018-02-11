package spencercjh.crabscore.AdministratorPart;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.File;

import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.R;

@SuppressWarnings("ConstantConditions")
public class CheckExcelActivity extends AppCompatActivity implements View.OnClickListener {
    private CompetitionOBJ competitionOBJ = new CompetitionOBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_excel);
        Intent intent = getIntent();
        competitionOBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("文件生成成功");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    private void goback() {
        finish();
    }

    public void onBackPressed() {
        goback();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            directly_open();
        } else if (view.getId() == R.id.button1) {
            indirectly_open();
        }
    }

    private void indirectly_open() {
        dialog();
    }

    private void directly_open() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File dir = new File(this.getExternalFilesDir(null).getPath());
        File file = new File(dir, "王宝和杯" + competitionOBJ.getCompetition_year() + ".xls");
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        startActivity(intent);
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CheckExcelActivity.this);
        builder.setMessage("文件在.../Android/data/spencercjh.CrabScore/files中");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                File dir = new File(CheckExcelActivity.this.getExternalFilesDir(null).getPath());
                File file = new File(dir, "王宝和杯" + competitionOBJ.getCompetition_year() + ".xls");
                intent.setDataAndType(Uri.fromFile(file), "file/*");
                try {
                    startActivity(intent);
                    startActivity(Intent.createChooser(intent, "选择浏览工具"));
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();//显示对话框
    }
}
