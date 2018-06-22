package spencercjh.crabscore.AdministratorPart;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import spencercjh.crabscore.AdministratorPart.OutputExcel.Fun_CreateExcelFile;
import spencercjh.crabscore.HttpRequst.Function.ExcelPart.Fun_QueryAllCrabList;
import spencercjh.crabscore.HttpRequst.Function.ExcelPart.Fun_QueryAllQualityScoreInfo;
import spencercjh.crabscore.HttpRequst.Function.ExcelPart.Fun_QueryAllTasteScoreInfo;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.JudgePart.Fun_QueryAllGroup;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.R;

@SuppressWarnings("deprecation")
public class GenerateExcelActivity extends AppCompatActivity implements View.OnClickListener {
    private CompetitionOBJ competitionOBJ = new CompetitionOBJ();
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_excel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("大赛成绩生成");
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
        Intent intent = getIntent();
        competitionOBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
    }

    private void goback() {
        finish();
    }

    @Override
    public void onBackPressed() {
        goback();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            if (competitionOBJ != null) {
                if (check_null()) {
                    final ProgressDialog dialog = new ProgressDialog(this);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setIcon(android.R.drawable.ic_dialog_alert);
                    dialog.setTitle("警告");
                    dialog.setMessage("Excel文件生成中……");
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            // TODO Auto-generated method stub

                        }
                    });
                    dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

                        @Override
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            return false;
                        }
                    });
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            // TODO Auto-generated method stub
                        }
                    });
                    dialog.show();
                    try {
                        int competition_id = competitionOBJ.getCompetition_id();
                        ArrayList<GroupOBJ> AllGroup = JsonConvert.convert_Group_List2(Fun_QueryAllGroup.http_QueryAllGroup(competition_id));
                        ArrayList<CrabOBJ> AllCrab = JsonConvert.convert_Crab_List(Fun_QueryAllCrabList.http_QueryAllCrabList(competition_id));
                        ArrayList<QualityScoreOBJ> AllQualityScore = JsonConvert.convert_QualityScore_List(Fun_QueryAllQualityScoreInfo.http_QueryAllQualityScoreInfo(competition_id));
                        ArrayList<TasteScoreOBJ> AllTasteScore = JsonConvert.convert_TasteScore_List(Fun_QueryAllTasteScoreInfo.http_QueryAllTasteScoreInfo(competition_id));
                        if (Fun_CreateExcelFile.http_CreateExcelFile(this, AllGroup, AllCrab, AllQualityScore, AllTasteScore, competitionOBJ.getCompetition_year())) {
                            dialog.dismiss();
                            flag = true;
                        } else {
                            dialog.dismiss();
                            flag = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    dialog_null();
                }
            }
            if (flag) {
                dialog_generate_success();
            } else {
                dialog_generate_failed();
            }
        }
    }

    private void dialog_generate_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GenerateExcelActivity.this);
        builder.setMessage("Excel文件生成完毕！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        Intent intent = new Intent(GenerateExcelActivity.this, CheckExcelActivity.class);
                        intent.putExtra("COMPETITIONOBJ", competitionOBJ);
                        startActivity(intent);
                    }
                }
        );
        builder.create().show();////显示对话框
    }

    private void dialog_generate_failed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GenerateExcelActivity.this);
        builder.setMessage("Excel文件生成失败！");
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

    private void dialog_null() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GenerateExcelActivity.this);
        builder.setMessage("有参数没有填写完整！");
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

    private boolean check_null() {
        return !competitionOBJ.getCompetition_year().isEmpty() &&
                competitionOBJ.getVar_weight_m() != 0 &&
                competitionOBJ.getVar_weight_f() != 0 &&
                competitionOBJ.getVar_fatness_m() != 0 &&
                competitionOBJ.getVar_fatness_f() != 0 &&
                competitionOBJ.getVar_ffatness_sd() != 0 &&
                competitionOBJ.getVar_mfatness_sd() != 0 &&
                competitionOBJ.getVar_mweight_sd() != 0 &&
                competitionOBJ.getVar_fweight_sd() != 0;
    }


}
