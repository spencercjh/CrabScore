package spencercjh.crabscore.JudgePart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import spencercjh.crabscore.HttpRequst.Function.CompanyPart.Fun_QueryQualityScoreInfo;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.JudgePart.Fun_UpdateQualityScoreInfo;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

/**
 * Created by spencercjh on 2018/2/5.
 * iClass
 */
@SuppressLint("ValidFragment")
public class QualityGrade_F_Fragment extends Fragment {
    protected View mView;
    protected Context mContext;
    QualityScoreOBJ qualityScoreOBJ_F = new QualityScoreOBJ();
    private TextView text_crab_sex;
    private EditText text_score_fin;
    private EditText text_score_bts;
    private EditText text_score_fts;
    private EditText text_score_ec;
    private EditText text_score_dscc;
    private EditText text_score_bbyzt;
    private RadioButton radio_enable1;
    private RadioButton radio_unable1;
    private UserOBJ userOBJ = new UserOBJ();
    private QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ();

    public QualityGrade_F_Fragment() {
    }

    public QualityGrade_F_Fragment(UserOBJ userOBJ, QualityScoreOBJ qualityScoreOBJ) {
        this.userOBJ = userOBJ;
        this.qualityScoreOBJ = qualityScoreOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_quality_grade_f, container, false);
        text_crab_sex = mView.findViewById(R.id.text_crab_sex);
        text_score_fin = mView.findViewById(R.id.text_score_fin);
        text_score_bts = mView.findViewById(R.id.text_score_bts);
        text_score_fts = mView.findViewById(R.id.text_score_fts);
        text_score_ec = mView.findViewById(R.id.text_score_ec);
        text_score_dscc = mView.findViewById(R.id.text_score_dscc);
        text_score_bbyzt = mView.findViewById(R.id.text_score_bbyzt);
        radio_enable1 = mView.findViewById(R.id.radio_enable1);
        radio_enable1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (radio_enable1.isChecked()) { //填写总分
                    text_score_fin.setEnabled(true);
                    text_score_bts.setEnabled(false);
                    text_score_fts.setEnabled(false);
                    text_score_ec.setEnabled(false);
                    text_score_dscc.setEnabled(false);
                    text_score_bbyzt.setEnabled(false);
                } else { //填写小分
                    text_score_fin.setEnabled(false);
                    text_score_bts.setEnabled(true);
                    text_score_fts.setEnabled(true);
                    text_score_ec.setEnabled(true);
                    text_score_dscc.setEnabled(true);
                    text_score_bbyzt.setEnabled(true);
                }
            }
        });
        radio_unable1 = mView.findViewById(R.id.radio_unable1);
        radio_unable1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (radio_unable1.isChecked()) {//填写小分
                    text_score_fin.setEnabled(false);
                    text_score_bts.setEnabled(true);
                    text_score_fts.setEnabled(true);
                    text_score_ec.setEnabled(true);
                    text_score_dscc.setEnabled(true);
                    text_score_bbyzt.setEnabled(true);
                } else { //填写总分
                    text_score_fin.setEnabled(true);
                    text_score_bts.setEnabled(false);
                    text_score_fts.setEnabled(false);
                    text_score_ec.setEnabled(false);
                    text_score_dscc.setEnabled(false);
                    text_score_bbyzt.setEnabled(false);
                }
            }
        });
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            InitialInfo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void InitialInfo() throws InterruptedException {
        qualityScoreOBJ_F = qualityScoreOBJ;
        qualityScoreOBJ_F.setCrab_sex(0);
        qualityScoreOBJ_F = JsonConvert.convert_QualityScoreOBJ(Fun_QueryQualityScoreInfo.http_QueryQualityScoreInfo(qualityScoreOBJ_F));
        text_crab_sex.setText("雌蟹");
        text_score_fin.setText(String.valueOf(qualityScoreOBJ_F.getScore_fin()));
        text_score_bts.setText(String.valueOf(qualityScoreOBJ_F.getScore_bts()));
        text_score_fts.setText(String.valueOf(qualityScoreOBJ_F.getScore_fts()));
        text_score_ec.setText(String.valueOf(qualityScoreOBJ_F.getScore_ec()));
        text_score_dscc.setText(String.valueOf(qualityScoreOBJ_F.getScore_dscc()));
        text_score_bbyzt.setText(String.valueOf(qualityScoreOBJ_F.getScore_bbyzt()));
    }

    private boolean updateInfo() {
        try {
            if (radio_enable1.isChecked()) {  //填写总分
                float score_fin = Float.parseFloat(text_score_fin.getText().toString().trim());
                qualityScoreOBJ_F.setScore_fin(score_fin);
            } else if (radio_unable1.isChecked()) {    //填写小分
                float score_bts = Float.parseFloat(text_score_bts.getText().toString().trim());
                float score_fts = Float.parseFloat(text_score_fts.getText().toString().trim());
                float score_ec = Float.parseFloat(text_score_ec.getText().toString().trim());
                float score_dscc = Float.parseFloat(text_score_dscc.getText().toString().trim());
                float score_bbyzt = Float.parseFloat(text_score_bbyzt.getText().toString().trim());
                qualityScoreOBJ_F.setScore_fin(score_bts + score_fts + score_ec + score_dscc + score_bbyzt);
                qualityScoreOBJ_F.setScore_bts(score_bts);
                qualityScoreOBJ_F.setScore_fts(score_fts);
                qualityScoreOBJ_F.setScore_ec(score_ec);
                qualityScoreOBJ_F.setScore_dscc(score_dscc);
                qualityScoreOBJ_F.setScore_bbyzt(score_bbyzt);
            }
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("确认修改？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            if (Fun_UpdateQualityScoreInfo.http_UpdateQualityScoreInfo(qualityScoreOBJ_F, userOBJ)) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_only_update, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_update) {
            if (updateInfo()) {
                dialog();
            } else {
                dialog_input_fail();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}