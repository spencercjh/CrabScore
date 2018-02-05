package spencercjh.crabscore.AdministratorPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateMoreSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private UserOBJ userOBJ = new UserOBJ();
    private CompetitionOBJ competitionOBJ = new CompetitionOBJ();
    private int choice;
    private RadioButton Renable1;
    private RadioButton Runable1;
    private RadioButton Renable2;
    private RadioButton Runable2;
    private RadioButton Renable3;
    private RadioButton Runable3;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_more_setting);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        competitionOBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
        choice = (int) intent.getSerializableExtra("USER");
        Renable1 = (RadioButton) findViewById(R.id.radio_enable1);
        Runable1 = (RadioButton) findViewById(R.id.radio_unable1);
        Renable2 = (RadioButton) findViewById(R.id.radio_enable2);
        Runable2 = (RadioButton) findViewById(R.id.radio_unable2);
        Renable3 = (RadioButton) findViewById(R.id.radio_enable3);
        Runable3 = (RadioButton) findViewById(R.id.radio_unable3);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        InitialInfo();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updateCompetition_info();
        }
    }

    private void InitialInfo() {
        /**
         * 数据初始化 网络线程
         */
    }

    private void updateCompetition_info() {
        if (Renable1.isChecked()) {
            competitionOBJ.setResult_fatness(1);
        }
        if (Runable1.isChecked()) {
            competitionOBJ.setResult_fatness(0);
        }
        if (Renable2.isChecked()) {
            competitionOBJ.setResult_quality(1);
        }
        if (Runable2.isChecked()) {
            competitionOBJ.setResult_quality(0);
        }
        if (Renable3.isChecked()) {
            competitionOBJ.setResult_taste(1);
        }
        if (Runable3.isChecked()) {
            competitionOBJ.setResult_taste(0);
        }
        finish();
    }
}
