package spencercjh.crabscore.JudgePart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class JudgeActivity extends AppCompatActivity {
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
    }
}
