package spencercjh.crabscore.AdministratorPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryCompetitionProperty;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryCompetitionYear;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryPresentCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateCompetitionProperty;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class CompetitionAdminFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BestGermplasmPrizeFragment";
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private SwipeRefreshLayout srl_simple;
    private TextView Tyear_note;
    private EditText Tcompetition_year;
    private EditText Tvar_fatness_m;
    private EditText Tvar_fatness_f;
    private EditText Tvar_weight_m;
    private EditText Tvar_mfatness_sd;
    private EditText Tvar_mweight_sd;
    private EditText Tvar_weight_f;
    private EditText Tvar_ffatness_sd;
    private EditText Tvar_fweight_sd;
    private RelativeLayout Ryear_note;
    private RelativeLayout Rmore_setting;
    private Button button;
    private CompetitionOBJ competition_OBJ = new CompetitionOBJ();

    CompetitionAdminFragment() {
    }

    CompetitionAdminFragment(UserOBJ userOBJ, int choice) {
        this.userOBJ = userOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_competition_admin, container, false);
        Ryear_note = mView.findViewById(R.id.re_year_note);
        Ryear_note.setOnClickListener(this);
        Rmore_setting = mView.findViewById(R.id.re_more_setting);
        Rmore_setting.setOnClickListener(this);
        Tyear_note = mView.findViewById(R.id.text_year_note);
        Tvar_fatness_m = mView.findViewById(R.id.text_var_fatness_m);
        Tvar_weight_m = mView.findViewById(R.id.text_var_weight_m);
        Tvar_mfatness_sd = mView.findViewById(R.id.text_var_mfatness_sd);
        Tvar_mweight_sd = mView.findViewById(R.id.text_var_mweight_sd);
        Tvar_fatness_f = mView.findViewById(R.id.text_var_fatness_f);
        Tvar_weight_f = mView.findViewById(R.id.text_var_weight_f);
        Tvar_ffatness_sd = mView.findViewById(R.id.text_var_ffatness_sd);
        Tvar_fweight_sd = mView.findViewById(R.id.text_var_fweight_sd);
        button = mView.findViewById(R.id.button);
        button.setOnClickListener(this);
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
            InitialInfo(competition_OBJ);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void InitialInfo(CompetitionOBJ competitionOBJ) throws InterruptedException {
        /**
         * 数据初始化 网络线程
         */
        int competition_id = Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID();
        competitionOBJ.setCompetition_year(Fun_QueryCompetitionYear.http_QueryCompetitionYear(competition_id));
        competitionOBJ = JsonConvert.convert_competition_property(Fun_QueryCompetitionProperty.http_QueryCompetitionProperty(competitionOBJ.getCompetition_year()));
        Tyear_note.setText(competitionOBJ.getCompetition_year() + " " + competition_OBJ.getNote().substring(0, 4));
        Tvar_fatness_m.setText(String.valueOf(competitionOBJ.getVar_fatness_m()));
        Tvar_weight_m.setText(String.valueOf(competitionOBJ.getVar_weight_m()));
        Tvar_mfatness_sd.setText(String.valueOf(competitionOBJ.getVar_mfatness_sd()));
        Tvar_mweight_sd.setText(String.valueOf(competitionOBJ.getVar_mweight_sd()));
        Tvar_fatness_f.setText(String.valueOf(competitionOBJ.getVar_fatness_f()));
        Tvar_weight_f.setText(String.valueOf(competitionOBJ.getVar_weight_f()));
        Tvar_ffatness_sd.setText(String.valueOf(competitionOBJ.getVar_ffatness_sd()));
        Tvar_fweight_sd.setText(String.valueOf(competitionOBJ.getVar_fweight_sd()));
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.re_year_note:
                intent = new Intent(getContext(), UpdateYear_NoteActivity.class);
                getActivity().startActivity(intent);
                intent.putExtra("COMPETITIONOBJ", competition_OBJ);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                break;
            case R.id.re_more_setting:
                intent = new Intent(getContext(), UpdateMoreSettingActivity.class);
                getActivity().startActivity(intent);
                intent.putExtra("COMPETITIONOBJ", competition_OBJ);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                break;
            case R.id.button:
                try {
                    updateCompetition_info();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }


    private void updateCompetition_info() throws InterruptedException {
        String year = competition_OBJ.getCompetition_year();
        String note = competition_OBJ.getNote();
        double var_fatness_m = Double.parseDouble(Tvar_fatness_m.getText().toString().trim());
        double var_weight_m = Double.parseDouble(Tvar_weight_m.getText().toString().trim());
        double var_mfatness_sd = Double.parseDouble(Tvar_mfatness_sd.getText().toString().trim());
        double var_mweight_sd = Double.parseDouble(Tvar_mweight_sd.getText().toString().trim());
        double var_fatness_f = Double.parseDouble(Tvar_fatness_f.getText().toString().trim());
        double var_weight_f = Double.parseDouble(Tvar_weight_f.getText().toString().trim());
        double var_ffatness_sd = Double.parseDouble(Tvar_ffatness_sd.getText().toString().trim());
        double var_fweight_sd = Double.parseDouble(Tvar_fweight_sd.getText().toString().trim());
        int result_fatness = competition_OBJ.getResult_fatness();
        int result_quality = competition_OBJ.getResult_quality();
        int result_taste = competition_OBJ.getResult_taste();
        competition_OBJ = new CompetitionOBJ(year, note, var_fatness_m, var_fatness_f,
                var_weight_m, var_mfatness_sd, var_mweight_sd, var_weight_f, var_ffatness_sd,
                var_fweight_sd, result_fatness, result_quality, result_taste);
        /**
         * 更新数据网络线程
         */
        if (Fun_UpdateCompetitionProperty.http_UpdateCompetitionProperty(competition_OBJ, userOBJ)) {
            dialog_update_success();
            InitialInfo(competition_OBJ);
        } else {
            dialog_update_fail();
        }
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
}