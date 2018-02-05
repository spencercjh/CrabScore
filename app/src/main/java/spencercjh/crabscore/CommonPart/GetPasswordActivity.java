package spencercjh.crabscore.CommonPart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class GetPasswordActivity extends AppCompatActivity implements OnClickListener {
    private EditText Euser_name;
    private EditText Edisplay_name;
    private EditText Eemail;
    private Spinner spinner;
    private Button check;
    private EditText Epassword;
    private Button update;
    private UserOBJ userOBJ = new UserOBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_password);
        Euser_name = (EditText) findViewById(R.id.text_input_user_name);
        Edisplay_name = (EditText) findViewById(R.id.text_input_display_name);
        Eemail = (EditText) findViewById(R.id.text_input_email);
        Epassword = (EditText) findViewById(R.id.text_input_password);
        check = (Button) findViewById(R.id.button);
        check.setOnClickListener(this);
        update = (Button) findViewById(R.id.button1);
        update.setOnClickListener(this);
        spinner=(Spinner)findViewById(R.id.spinner);
        String[] Items = getResources().getStringArray(R.array.roles);
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
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            checkinfo();
        } else if (view.getId() == R.id.button1) {
            updatepassword();
        }
    }

    private void checkinfo() {

    }

    private void updatepassword() {

    }
}
