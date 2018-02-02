package spencercjh.crabscore.CommonPart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import spencercjh.crabscore.R;
/**
 * 用户组1 管理员 administrator
 * 用户组2 工作人员 staff
 * 用户组3 评委 judge
 * 用户组4 参选单位 unit
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText Euser_name;
    private EditText Epassword;
    private EditText Eemail;
    private EditText Edisplay_name;
    private RadioButton user2_staff;
    private RadioButton user3_judge;
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Euser_name=(EditText)findViewById(R.id.edit_id);
        Epassword=(EditText)findViewById(R.id.edit_password);
        Eemail=(EditText)findViewById(R.id.edit_email);
        Edisplay_name=(EditText)findViewById(R.id.edit_name);
        user2_staff=(RadioButton)findViewById(R.id.user2_staff);
        user3_judge=(RadioButton)findViewById(R.id.user3_judge);
    }
}
