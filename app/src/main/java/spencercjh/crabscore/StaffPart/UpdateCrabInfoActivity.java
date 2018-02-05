package spencercjh.crabscore.StaffPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateCrabInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text_index;
    private EditText text_crab_label;
    private EditText text_crab_weight;
    private EditText text_crab_length;
    private CrabOBJ crabOBJ = new CrabOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_crab_info);
        Intent intent = getIntent();
        crabOBJ = (CrabOBJ) intent.getSerializableExtra("CRABOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        text_index = (TextView) findViewById(R.id.text_index);
        text_crab_label = (EditText) findViewById(R.id.text_crab_label);
        text_crab_weight = (EditText) findViewById(R.id.text_crab_weight);
        text_crab_length = (EditText) findViewById(R.id.text_crab_length);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        InitialInfo();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updatecrabinfo();
        }
    }

    private void InitialInfo() {

    }

    private void updatecrabinfo() {

    }
}
