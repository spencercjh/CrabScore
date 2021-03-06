package spencercjh.crabscore.AdministratorPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class UpdateCompetitionPropertyActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_competition_property);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        RelativeLayout ryear_note = (RelativeLayout) findViewById(R.id.re_year_note);
        ryear_note.setOnClickListener(this);
        RelativeLayout rmore_setting = (RelativeLayout) findViewById(R.id.re_more_setting);
        rmore_setting.setOnClickListener(this);
        Tyear_note = (TextView) findViewById(R.id.text_year_note);
        Tvar_fatness_m = (EditText) findViewById(R.id.text_var_fatness_m);
        Tvar_weight_m = (EditText) findViewById(R.id.text_var_weight_m);
        Tvar_mfatness_sd = (EditText) findViewById(R.id.text_var_mfatness_sd);
        Tvar_mweight_sd = (EditText) findViewById(R.id.text_var_mweight_sd);
        Tvar_fatness_f = (EditText) findViewById(R.id.text_var_fatness_f);
        Tvar_weight_f = (EditText) findViewById(R.id.text_var_weight_f);
        Tvar_ffatness_sd = (EditText) findViewById(R.id.text_var_ffatness_sd);
        Tvar_fweight_sd = (EditText) findViewById(R.id.text_var_fweight_sd);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        try {
            InitialInfo(competition_OBJ);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.re_year_note:
                intent = new Intent(UpdateCompetitionPropertyActivity.this, UpdateYear_NoteActivity.class);
                startActivity(intent);
                intent.putExtra("COMPETITIONOBJ", competition_OBJ);
                intent.putExtra("USEROBJ", userOBJ);
                startActivity(intent);
                break;
            case R.id.re_more_setting:
                intent = new Intent(UpdateCompetitionPropertyActivity.this, UpdateMoreSettingActivity.class);
                startActivity(intent);
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

    private void InitialInfo(CompetitionOBJ competitionOBJ) throws InterruptedException {
        int competition_id = Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID();
        competitionOBJ.setCompetition_year(Fun_QueryCompetitionYear.http_QueryCompetitionYear(competition_id));
        competitionOBJ = JsonConvert.convert_CompetitionOBJ(Fun_QueryCompetitionProperty.http_QueryCompetitionProperty(competitionOBJ.getCompetition_year()));
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

    private void updateCompetition_info() throws InterruptedException {
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
        competition_OBJ = new CompetitionOBJ(year, note, var_fatness_m, var_fatness_f,
                var_weight_m, var_mfatness_sd, var_mweight_sd, var_weight_f, var_ffatness_sd,
                var_fweight_sd, result_fatness, result_quality, result_taste);
        if (Fun_UpdateCompetitionProperty.http_UpdateCompetitionProperty(competition_OBJ, userOBJ)) {
            dialog_update_success();
        } else {
            dialog_update_fail();
        }
    }

    private void dialog_update_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCompetitionPropertyActivity.this);
        builder.setMessage("修改成功！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        finish();
                    }
                }
        );
        builder.create().show();////显示对话框
    }

    private void dialog_update_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCompetitionPropertyActivity.this);
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
