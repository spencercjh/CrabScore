package spencercjh.crabscore.AdministratorPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateYear_NoteActivity extends AppCompatActivity implements View.OnClickListener {
    private CompetitionOBJ competitionOBJ = new CompetitionOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private EditText Eyear;
    private EditText Enote;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_year__note);
        Intent intent = getIntent();
        competitionOBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        Eyear = (EditText) findViewById(R.id.text_year);
        Enote = (EditText) findViewById(R.id.text_note);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        InitialInfo();
    }

    private void InitialInfo() {
        /**
         * 数据初始化 网络线程
         */
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updateCompetition_info();
        }
    }

    private void updateCompetition_info() {
        String year = Eyear.getText().toString().trim();
        competitionOBJ.setCompetition_year(year);
        String note = Enote.getText().toString();
        competitionOBJ.setNote(note);
        finish();
    }
}
