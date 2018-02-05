package spencercjh.crabscore.AdministratorPart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdateUserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Tuser_name;
    private EditText Tdisplay_name;
    private EditText Temail;
    private Spinner spinner;
    private Button button;
    private UserOBJ admin = new UserOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        Intent intent = getIntent();
        admin = (UserOBJ) intent.getSerializableExtra("ADMIN");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        Tuser_name = (TextView) findViewById(R.id.text_user_name);
        Tdisplay_name = (EditText) findViewById(R.id.text_display_name);
        Temail = (EditText) findViewById(R.id.text_email);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] Items = getResources().getStringArray(R.array.roles2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                userOBJ.setRole_id(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        String display_name = Tdisplay_name.getText().toString().trim();
        String email = Temail.getText().toString().trim();
        userOBJ.setDisplay_name(display_name);
        userOBJ.setEmail(email);
    }
}
