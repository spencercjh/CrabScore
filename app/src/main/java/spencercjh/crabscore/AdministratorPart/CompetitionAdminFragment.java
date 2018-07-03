package spencercjh.crabscore.AdministratorPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private TextView Tyear_note;
    private EditText Tvar_fatness_m;
    private EditText Tvar_fatness_f;
    private EditText Tvar_weight_m;
    private EditText Tvar_mfatness_sd;
    private EditText Tvar_mweight_sd;
    private EditText Tvar_weight_f;
    private EditText Tvar_ffatness_sd;
    private EditText Tvar_fweight_sd;
    private CompetitionOBJ competition_OBJ = new CompetitionOBJ();

    public CompetitionAdminFragment() {
    }

    public CompetitionAdminFragment(UserOBJ userOBJ) {
        this.userOBJ = userOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_competition_admin, container, false);
        RelativeLayout ryear_note = mView.findViewById(R.id.re_year_note);
        ryear_note.setOnClickListener(this);
        RelativeLayout rmore_setting = mView.findViewById(R.id.re_more_setting);
        rmore_setting.setOnClickListener(this);
        Tyear_note = mView.findViewById(R.id.text_year_note);
        Tvar_fatness_m = mView.findViewById(R.id.text_var_fatness_m);
        Tvar_weight_m = mView.findViewById(R.id.text_var_weight_m);
        Tvar_mfatness_sd = mView.findViewById(R.id.text_var_mfatness_sd);
        Tvar_mweight_sd = mView.findViewById(R.id.text_var_mweight_sd);
        Tvar_fatness_f = mView.findViewById(R.id.text_var_fatness_f);
        Tvar_weight_f = mView.findViewById(R.id.text_var_weight_f);
        Tvar_ffatness_sd = mView.findViewById(R.id.text_var_ffatness_sd);
        Tvar_fweight_sd = mView.findViewById(R.id.text_var_fweight_sd);
        Button button = mView.findViewById(R.id.button);
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
            InitialInfo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void InitialInfo() throws InterruptedException {
        int competition_id = Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID();
        competition_OBJ.setCompetition_id(competition_id);
        competition_OBJ.setCompetition_year(Fun_QueryCompetitionYear.http_QueryCompetitionYear(competition_id));
        competition_OBJ = JsonConvert.convert_CompetitionOBJ(Fun_QueryCompetitionProperty.http_QueryCompetitionProperty(competition_OBJ.getCompetition_year()));
        competition_OBJ.setCompetition_id(competition_id);
        Tyear_note.setText(competition_OBJ.getCompetition_year() + " " + competition_OBJ.getNote());
        Tvar_fatness_m.setText(String.valueOf(competition_OBJ.getVar_fatness_m()));
        Tvar_weight_m.setText(String.valueOf(competition_OBJ.getVar_weight_m()));
        Tvar_mfatness_sd.setText(String.valueOf(competition_OBJ.getVar_mfatness_sd()));
        Tvar_mweight_sd.setText(String.valueOf(competition_OBJ.getVar_mweight_sd()));
        Tvar_fatness_f.setText(String.valueOf(competition_OBJ.getVar_fatness_f()));
        Tvar_weight_f.setText(String.valueOf(competition_OBJ.getVar_weight_f()));
        Tvar_ffatness_sd.setText(String.valueOf(competition_OBJ.getVar_ffatness_sd()));
        Tvar_fweight_sd.setText(String.valueOf(competition_OBJ.getVar_fweight_sd()));
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.re_year_note:
                intent = new Intent(getContext(), UpdateYear_NoteActivity.class);
                intent.putExtra("COMPETITIONOBJ", competition_OBJ);
                intent.putExtra("USEROBJ", userOBJ);
                startActivity(intent);
                break;
            case R.id.re_more_setting:
                intent = new Intent(getContext(), UpdateMoreSettingActivity.class);
                intent.putExtra("COMPETITIONOBJ", competition_OBJ);
                intent.putExtra("USEROBJ", userOBJ);
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
        int competition_id = competition_OBJ.getCompetition_id();
        String year = competition_OBJ.getCompetition_year();
        String note = competition_OBJ.getNote();
        float var_fatness_m = Float.parseFloat(Tvar_fatness_m.getText().toString().trim());
        float var_weight_m = Float.parseFloat(Tvar_weight_m.getText().toString().trim());
        float var_mfatness_sd = Float.parseFloat(Tvar_mfatness_sd.getText().toString().trim());
        float var_mweight_sd = Float.parseFloat(Tvar_mweight_sd.getText().toString().trim());
        float var_fatness_f = Float.parseFloat(Tvar_fatness_f.getText().toString().trim());
        float var_weight_f = Float.parseFloat(Tvar_weight_f.getText().toString().trim());
        float var_ffatness_sd = Float.parseFloat(Tvar_ffatness_sd.getText().toString().trim());
        float var_fweight_sd = Float.parseFloat(Tvar_fweight_sd.getText().toString().trim());
        int result_fatness = competition_OBJ.getResult_fatness();
        int result_quality = competition_OBJ.getResult_quality();
        int result_taste = competition_OBJ.getResult_taste();
        competition_OBJ = new CompetitionOBJ(competition_id, year, note, var_fatness_m,
                var_weight_m, var_mfatness_sd, var_mweight_sd, var_fatness_f, var_weight_f, var_ffatness_sd,
                var_fweight_sd, result_fatness, result_quality, result_taste);
        if (Fun_UpdateCompetitionProperty.http_UpdateCompetitionProperty(competition_OBJ, userOBJ)) {
            dialog_update_success();
//            InitialInfo();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pop_menu_competition_function, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.menu_calculate_score) {
            intent = new Intent(getContext(), GenerateScoreActivity.class);
            intent.putExtra("USEROBJ", userOBJ);
            intent.putExtra("COMPETITIONOBJ", competition_OBJ);
            startActivity(intent);
        } else if (id == R.id.menu_excel_generate) {
            intent = new Intent(getContext(), GenerateExcelActivity.class);
            intent.putExtra("COMPETITIONOBJ", competition_OBJ);
            startActivity(intent);
        } else if (id == R.id.menu_change_competition) {
            intent = new Intent(getContext(), UpdatePresentCompetitionActivity.class);
            intent.putExtra("USEROBJ", userOBJ);
            intent.putExtra("COMPETITIONOBJ", competition_OBJ);
            startActivity(intent);
        } else if (id == R.id.menu_fresh) {
            try {
                InitialInfo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
