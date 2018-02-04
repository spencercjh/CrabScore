package spencercjh.crabscore.CommonPart;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Date;

import spencercjh.crabscore.AdministratorPart.AdministratorActivity;
import spencercjh.crabscore.CheckScore_Ranking_Part.CheckScore_Ranking_Activity;
import spencercjh.crabscore.CompanyPart.CompanyActivity;
import spencercjh.crabscore.JudgePart.JudgeActivity;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.PersonCenterPart.PersonCenterActivity;
import spencercjh.crabscore.R;
import spencercjh.crabscore.StaffPart.StaffActivity;

/**
 * 用户组1 管理员 administrator
 * 用户组2 工作人员 staff
 * 用户组3 评委 judge
 * 用户组4 参选单位 unit
 */
public class MainActivity extends ActivityGroup implements View.OnClickListener {
    private LinearLayout ll_container, ll_first, ll_second, ll_third;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice = 4;
    private long lastPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        ll_container = findViewById(R.id.ll_container);
        ll_first = findViewById(R.id.ll_first);
        ll_second = findViewById(R.id.ll_second);
        ll_third = findViewById(R.id.ll_third);
        ll_first.setOnClickListener(this);
        ll_second.setOnClickListener(this);
        ll_third.setOnClickListener(this);
        changeContainerView(ll_second);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_first || v.getId() == R.id.ll_second || v.getId() == R.id.ll_third) {
            changeContainerView(v);
        }
    }

    private void changeContainerView(View v) {
        ll_first.setSelected(false);
        ll_second.setSelected(false);
        ll_third.setSelected(false);
        v.setSelected(true);
        if (v == ll_first) {
            toActivity("first", CheckScore_Ranking_Activity.class);
        } else if (v == ll_second) {
            if (choice == 1) {
                toActivity("second", AdministratorActivity.class);
            } else if (choice == 2) {
                toActivity("second", StaffActivity.class);
            } else if (choice == 3) {
                toActivity("second", JudgeActivity.class);
            } else if (choice == 4) {
                toActivity("second", CompanyActivity.class);
            }
        } else if (v == ll_third) {
            toActivity("third", PersonCenterActivity.class);
        }
    }

    private void toActivity(String label, Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("USEROBJ", userOBJ);
        intent.putExtra("USER", choice);
        ll_container.removeAllViews();
        View v = getLocalActivityManager().startActivity(label, intent).getDecorView();
        v.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ll_container.addView(v);
    }

    //重写返回键
    public void onBackPressed() {
        if (new Date().getTime() - lastPressTime < 1000) {
            finish();
            Runtime.getRuntime().exit(0);//结束程序
        } else {
            lastPressTime = new Date().getTime();//重置lastPressTime
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
        }
    }
}
