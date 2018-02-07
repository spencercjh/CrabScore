package spencercjh.crabscore.AdministratorPart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import spencercjh.crabscore.CompanyPart.OverallScoreActivity;
import spencercjh.crabscore.CompanyPart.QualityScoreActivity;
import spencercjh.crabscore.CompanyPart.TasteScoreActivity;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryAllGroup;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class CheckCompanyScoreActivity extends AppCompatActivity {
    private UserOBJ userOBJ = new UserOBJ();
    private CompanyOBJ companyOBJ = new CompanyOBJ();
    private int choice;
    private ListView lv;
    private SwipeRefreshLayout srl_simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_company_score);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        companyOBJ = (CompanyOBJ) intent.getSerializableExtra("COMPANYOBJ");
        choice = (int) intent.getSerializableExtra("USER");
        lv = (ListView) findViewById(R.id.group_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("选择小组");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        srl_simple = (SwipeRefreshLayout) findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_GroupList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                srl_simple.setRefreshing(false);
            }
        });
        srl_simple.setColorSchemeResources(R.color.red, R.color.orange, R.color.green, R.color.blue);
        try {
            Fill_GroupList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goback() {
        finish();
    }

    private void Fill_GroupList() throws InterruptedException {
        final ArrayList<GroupOBJ> GroupList = JsonConvert.convert_group_id(Fun_QueryAllGroup.http_QueryAllGroup(companyOBJ));
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return GroupList.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = View.inflate(CheckCompanyScoreActivity.this, R.layout.item_group, null);
                } else {
                    view = convertView;
                }
                GroupOBJ groupOBJ = GroupList.get(position);
                TextView Tindex = view.findViewById(R.id.tv_index);
                Tindex.setText(position);
                TextView Tgroup_id = view.findViewById(R.id.text_group_id);
                Tgroup_id.setText("第 " + groupOBJ.getGroup_id() + " 组");
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final GroupOBJ groupOBJ = GroupList.get(position);
                PopupMenu popup = new PopupMenu(CheckCompanyScoreActivity.this, view);
                final MenuInflater inflater = popup.getMenuInflater();
                if (userOBJ.getCompetition_id() != 0) {
                    inflater.inflate(R.menu.pop_menu_company_group_score, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        Intent intent;

                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()) {
                                case R.id.menu_overall_score:
                                    intent = new Intent(CheckCompanyScoreActivity.this, OverallScoreActivity.class);
                                    intent.putExtra("GROUPOBJ", groupOBJ);
                                    startActivity(intent);
                                    break;
                                case R.id.menu_detail_quality_score:
                                    QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ(groupOBJ.getGroup_id(),companyOBJ.getCompetition_id());
                                    intent = new Intent(CheckCompanyScoreActivity.this, QualityScoreActivity.class);
                                    intent.putExtra("QUALITYSCOREOBJ", qualityScoreOBJ);
                                    startActivity(intent);
                                    break;
                                case R.id.menu_detail_taste_score:
                                    TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ(groupOBJ.getGroup_id(),companyOBJ.getCompetition_id());
                                    intent = new Intent(CheckCompanyScoreActivity.this, TasteScoreActivity.class);
                                    intent.putExtra("TASTESCOREACTIVITY", tasteScoreOBJ);
                                    startActivity(intent);
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                }
            }
        });
    }

    public void onBackPressed() {
        goback();
    }
}