package spencercjh.crabscore.CompanyPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class OverallScoreActivity extends AppCompatActivity {
    private GroupOBJ groupOBJ=new GroupOBJ();
    private UserOBJ userOBJ=new UserOBJ();
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_group_detail_score);
        Intent intent=getIntent();
        userOBJ=(UserOBJ)intent.getSerializableExtra("USEROBJ");
        groupOBJ=(GroupOBJ)intent.getSerializableExtra("GROUPOBJ");
        choice=(int)intent.getSerializableExtra("USER");
    }
}
