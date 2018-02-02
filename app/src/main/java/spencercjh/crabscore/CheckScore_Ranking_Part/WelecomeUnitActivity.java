package spencercjh.crabscore.CheckScore_Ranking_Part;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class WelecomeUnitActivity extends AppCompatActivity {
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welecome_unit);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
    }
}
