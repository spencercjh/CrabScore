package spencercjh.crabscore.PersonCenterPart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateUserPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private EditText text_old_password;
    private EditText text_new_password;
    private Button check;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_password);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        text_old_password = (EditText) findViewById(R.id.text_input_old_password);
        text_new_password = (EditText) findViewById(R.id.text_input_new_password);
        check = (Button) findViewById(R.id.button);
        check.setOnClickListener(this);
        update = (Button) findViewById(R.id.button1);
        update.setOnClickListener(this);
        Initialinfo();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            checkpassword();
        } else if (view.getId() == R.id.button1) {
            updateuserinfo();
        }
    }

    private void Initialinfo() {

    }

    private void updateuserinfo() {

    }

    private void checkpassword() {

    }
}
