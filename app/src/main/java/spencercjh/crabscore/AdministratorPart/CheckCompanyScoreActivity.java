package spencercjh.crabscore.AdministratorPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class CheckCompanyScoreActivity extends AppCompatActivity {
    private TextView Tfatness_score_m;
    private TextView Tquality_score_m;
    private TextView Ttaste_score_m;
    private TextView Tfatness_score_f;
    private TextView Tquality_score_f;
    private TextView Ttaste_score_f;
    private UserOBJ userOBJ = new UserOBJ();
    private CompanyOBJ companyOBJ = new CompanyOBJ();
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_company_score);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        companyOBJ = (CompanyOBJ) intent.getSerializableExtra("COMPANYOBJ");
        choice = (int) intent.getSerializableExtra("USER");
        Tfatness_score_m = (TextView) findViewById(R.id.text_fatness_score_m);
        Tquality_score_m = (TextView) findViewById(R.id.text_quality_score_m);
        Ttaste_score_m = (TextView) findViewById(R.id.text_taste_score_m);
        Tfatness_score_f = (TextView) findViewById(R.id.text_fatness_score_f);
        Tquality_score_f = (TextView) findViewById(R.id.text_quality_score_f);
        Ttaste_score_f = (TextView) findViewById(R.id.text_taste_score_f);
        InitialInfo();
    }

    private void InitialInfo() {
        /**
         * 数据初始化 网络线程
         */
    }
}
