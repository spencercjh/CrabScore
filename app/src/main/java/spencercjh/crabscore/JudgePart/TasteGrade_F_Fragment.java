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

import spencercjh.crabscore.HttpRequst.Function.CompanyPart.Fun_QueryTasteScoreInfo;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.JudgePart.Fun_InsertTasteScoreInfo;
import spencercjh.crabscore.HttpRequst.Function.JudgePart.Fun_QueryUserID;
import spencercjh.crabscore.HttpRequst.Function.JudgePart.Fun_UpdateTasteScoreInfo;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

/**
 * Created by spencercjh on 2018/2/5.
 * iClass
 */
@SuppressLint("ValidFragment")
public class TasteGrade_F_Fragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private TextView text_crab_sex;
    private EditText text_score_fin;
    private EditText text_score_ygys;
    private EditText text_score_sys;
    private EditText text_score_ghys;
    private EditText text_score_xwxw;
    private EditText text_score_gh;
    private EditText text_score_fbjr;
    private EditText text_score_bzjr;
    private RadioButton radio_enable1;
    private RadioButton radio_unable1;
    private UserOBJ userOBJ = new UserOBJ();
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();
    private TasteScoreOBJ tasteScoreOBJ_F = new TasteScoreOBJ();
    private boolean need_insert = false;

    public TasteGrade_F_Fragment() {
    }

    public TasteGrade_F_Fragment(UserOBJ userOBJ, TasteScoreOBJ tasteScoreOBJ) {
        this.userOBJ = userOBJ;
        this.tasteScoreOBJ = tasteScoreOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_taste_grade_f, container, false);
        text_crab_sex = mView.findViewById(R.id.text_crab_sex);
        text_score_fin = mView.findViewById(R.id.text_score_fin);
        text_score_ygys = mView.findViewById(R.id.text_score_ygys);
        text_score_sys = mView.findViewById(R.id.text_score_sys);
        text_score_ghys = mView.findViewById(R.id.text_score_ghys);
        text_score_xwxw = mView.findViewById(R.id.text_score_xwxw);
        text_score_gh = mView.findViewById(R.id.text_score_gh);
        text_score_fbjr = mView.findViewById(R.id.text_score_fbjr);
        text_score_bzjr = mView.findViewById(R.id.text_score_bzjr);
        radio_enable1 = mView.findViewById(R.id.radio_enable1);
        radio_enable1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (radio_enable1.isChecked()) { //填写总分
                    text_score_fin.setEnabled(true);
                    text_score_ygys.setEnabled(false);
                    text_score_sys.setEnabled(false);
                    text_score_ghys.setEnabled(false);
                    text_score_xwxw.setEnabled(false);
                    text_score_gh.setEnabled(false);
                    text_score_fbjr.setEnabled(false);
                    text_score_bzjr.setEnabled(false);
                } else { //填写小分
                    text_score_fin.setEnabled(false);
                    text_score_ygys.setEnabled(true);
                    text_score_sys.setEnabled(true);
                    text_score_ghys.setEnabled(true);
                    text_score_xwxw.setEnabled(true);
                    text_score_gh.setEnabled(true);
                    text_score_fbjr.setEnabled(true);
                    text_score_bzjr.setEnabled(true);
                }
            }
        });
        radio_unable1 = mView.findViewById(R.id.radio_unable1);
        radio_unable1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (radio_unable1.isChecked()) {//填写小分
                    text_score_fin.setEnabled(false);
                    text_score_ygys.setEnabled(true);
                    text_score_sys.setEnabled(true);
                    text_score_ghys.setEnabled(true);
                    text_score_xwxw.setEnabled(true);
                    text_score_gh.setEnabled(true);
                    text_score_fbjr.setEnabled(true);
                    text_score_bzjr.setEnabled(true);
                } else { //填写总分
                    text_score_fin.setEnabled(true);
                    text_score_ygys.setEnabled(false);
                    text_score_sys.setEnabled(false);
                    text_score_ghys.setEnabled(false);
                    text_score_xwxw.setEnabled(false);
                    text_score_gh.setEnabled(false);
                    text_score_fbjr.setEnabled(false);
                    text_score_bzjr.setEnabled(false);
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
        text_crab_sex.setText("雌蟹");
        userOBJ.setUser_id(Fun_QueryUserID.http_QueryUserID(userOBJ));
        tasteScoreOBJ_F.setGroup_id(tasteScoreOBJ.getGroup_id());
        tasteScoreOBJ_F.setCompetition_id(tasteScoreOBJ.getCompetition_id());
        tasteScoreOBJ_F.setCrab_sex(0);
        tasteScoreOBJ_F = JsonConvert.convert_TasteScoreOBJ(Fun_QueryTasteScoreInfo.http_QueryTasteScoreInfo(tasteScoreOBJ_F));
        if (tasteScoreOBJ_F.getGroup_id() == 0) {   //原本没有scoreOBJ，后面不能update要insert
            need_insert = true;
            tasteScoreOBJ_F.setGroup_id(tasteScoreOBJ.getGroup_id());
            tasteScoreOBJ_F.setCompetition_id(tasteScoreOBJ.getCompetition_id());
            tasteScoreOBJ_F.setCrab_sex(0);
            text_score_fin.setText("");
            text_score_ygys.setText("");
            text_score_sys.setText("");
            text_score_ghys.setText("");
            text_score_xwxw.setText("");
            text_score_gh.setText("");
            text_score_fbjr.setText("");
            text_score_bzjr.setText("");
        } else {
            text_score_fin.setText(String.valueOf(tasteScoreOBJ_F.getScore_fin()));
            text_score_ygys.setText(String.valueOf(tasteScoreOBJ_F.getScore_ygys()));
            text_score_sys.setText(String.valueOf(tasteScoreOBJ_F.getScore_sys()));
            text_score_ghys.setText(String.valueOf(tasteScoreOBJ_F.getScore_ghys()));
            text_score_xwxw.setText(String.valueOf(tasteScoreOBJ_F.getScore_xwxw()));
            text_score_gh.setText(String.valueOf(tasteScoreOBJ_F.getScore_gh()));
            text_score_fbjr.setText(String.valueOf(tasteScoreOBJ_F.getScore_fbjr()));
            text_score_bzjr.setText(String.valueOf(tasteScoreOBJ_F.getScore_bzjr()));
        }

    }

    private boolean updateInfo() {
        try {
            if (radio_enable1.isChecked()) {  //填写总分
                float score_fin = Float.parseFloat(text_score_fin.getText().toString().trim());
                tasteScoreOBJ_F.setScore_fin(score_fin);
            } else if (radio_unable1.isChecked()) {    //填写小分
                float score_ygys = Float.parseFloat(text_score_ygys.getText().toString().trim());
                float score_sys = Float.parseFloat(text_score_sys.getText().toString().trim());
                float score_ghys = Float.parseFloat(text_score_ghys.getText().toString().trim());
                float score_xwxw = Float.parseFloat(text_score_xwxw.getText().toString().trim());
                float score_gh = Float.parseFloat(text_score_gh.getText().toString().trim());
                float score_fbjr = Float.parseFloat(text_score_fbjr.getText().toString().trim());
                float score_bzjr = Float.parseFloat(text_score_bzjr.getText().toString().trim());
                tasteScoreOBJ_F.setScore_fin(score_ygys + score_sys + score_ghys + score_xwxw + score_gh + score_fbjr + score_bzjr);
                tasteScoreOBJ_F.setScore_ygys(score_ygys);
                tasteScoreOBJ_F.setScore_sys(score_sys);
                tasteScoreOBJ_F.setScore_ghys(score_ghys);
                tasteScoreOBJ_F.setScore_xwxw(score_xwxw);
                tasteScoreOBJ_F.setScore_gh(score_gh);
                tasteScoreOBJ_F.setScore_fbjr(score_fbjr);
                tasteScoreOBJ_F.setScore_bzjr(score_bzjr);
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
                            if (need_insert) {
                                if (Fun_InsertTasteScoreInfo.http_InsertTasteScoreInfo(tasteScoreOBJ, userOBJ)) {
                                    dialog_update_success();
                                } else {
                                    dialog_update_fail();
                                }
                            } else {
                                if (Fun_UpdateTasteScoreInfo.http_UpdateTasteScoreInfo(tasteScoreOBJ_F, userOBJ)) {
                                    dialog_update_success();
                                } else {
                                    dialog_update_fail();
                                }
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
