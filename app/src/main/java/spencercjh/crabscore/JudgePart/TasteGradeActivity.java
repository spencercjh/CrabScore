package spencercjh.crabscore.JudgePart;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import spencercjh.crabscore.CompanyPart.TasteScorePageAdapter;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class TasteGradeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();
    private Toolbar tl_head;
    private ViewPager vp_content;
    private TabLayout tab_title;
    private ArrayList<String> mTitleArray = new ArrayList<String>();
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste_grade);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        tasteScoreOBJ = (TasteScoreOBJ) intent.getSerializableExtra("TASTESCOREOBJ");
        tl_head = (Toolbar) findViewById(R.id.tl_head);
        tab_title = (TabLayout) findViewById(R.id.tab_title);
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        tl_head.setEnabled(false);
//        tl_head.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button_div));
        setSupportActionBar(tl_head);
        mTitleArray.add("雌蟹");
        mTitleArray.add("雄蟹");
        initTabLayout();
        initTabViewPager();
    }

    private void initTabLayout() {
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(0)));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(1)));
        tab_title.setOnTabSelectedListener(this);
    }

    private void initTabViewPager() {
        TasteGradePageAdapter adapter = new TasteGradePageAdapter(getSupportFragmentManager(), mTitleArray, userOBJ, choice, tasteScoreOBJ);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tab_title.getTabAt(position).select();
            }
        });
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void onBackPressed() {

    }
}
