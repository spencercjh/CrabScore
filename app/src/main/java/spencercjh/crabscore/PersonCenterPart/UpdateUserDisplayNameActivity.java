package spencercjh.crabscore.PersonCenterPart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateUserDisplayNameActivity extends AppCompatActivity implements View.OnClickListener {
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private EditText text_display_name;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_display_name);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        text_display_name = (EditText) findViewById(R.id.text_display_name);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Initialinfo();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            updateuserinfo();
        }
    }

    private void Initialinfo() {

    }

    private void updateuserinfo() {

    }
}
