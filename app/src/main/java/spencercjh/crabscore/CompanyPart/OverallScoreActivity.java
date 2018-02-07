package spencercjh.crabscore.CompanyPart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.lang.reflect.Method;

import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.R;

public class OverallScoreActivity extends AppCompatActivity {
    private GroupOBJ groupOBJ = new GroupOBJ();
    private TextView text_fatness_score_m;
    private TextView text_quality_score_m;
    private TextView text_taste_score_m;
    private TextView text_fatness_score_f;
    private TextView text_quality_score_f;
    private TextView text_taste_score_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_group_detail_score);
        Intent intent = getIntent();
        groupOBJ = (GroupOBJ) intent.getSerializableExtra("GROUPOBJ");
        text_fatness_score_m = (TextView) findViewById(R.id.text_fatness_score_m);
        text_quality_score_m = (TextView) findViewById(R.id.text_quality_score_m);
        text_taste_score_m = (TextView) findViewById(R.id.text_taste_score_m);
        text_fatness_score_f = (TextView) findViewById(R.id.text_fatness_score_f);
        text_quality_score_f = (TextView) findViewById(R.id.text_quality_score_f);
        text_taste_score_f = (TextView) findViewById(R.id.text_taste_score_f);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("整体得分情况");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        InitialInfo();
    }

    private void InitialInfo() {
        text_fatness_score_m.setText(String.valueOf(groupOBJ.getFatness_score_m()));
        text_quality_score_m.setText(String.valueOf(groupOBJ.getQuality_score_m()));
        text_taste_score_m.setText(String.valueOf(groupOBJ.getTaste_score_m()));
        text_fatness_score_f.setText(String.valueOf(groupOBJ.getFatness_score_f()));
        text_fatness_score_f.setText(String.valueOf(groupOBJ.getFatness_score_f()));
        text_quality_score_f.setText(String.valueOf(groupOBJ.getQuality_score_f()));
        text_taste_score_f.setText(String.valueOf(groupOBJ.getTaste_score_f()));
    }

    private void goback() {
        finish();
    }

    public void onBackPressed() {
        goback();
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        // ActionBar的featureId是8，Toolbar的featureId是108
        if (featureId % 100 == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
