package spencercjh.crabscore.CompanyPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
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

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryCompanyID;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryPresentCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.CompanyPart.Fun_QueryAllGroup;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class OwnGroupScoreFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private SwipeRefreshLayout srl_simple;
    private CompanyOBJ companyOBJ = new CompanyOBJ();

    public OwnGroupScoreFragment() {
    }


    public OwnGroupScoreFragment(UserOBJ userOBJ, int choice) {
        this.userOBJ = userOBJ;
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_own_group_score, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
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
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            Fill_GroupList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Fill_GroupList() throws InterruptedException {
        ListView lv = mView.findViewById(R.id.group_score_list);
        companyOBJ.setCompetition_id(Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID());
        companyOBJ.setCompany_name(userOBJ.getDisplay_name());
        companyOBJ.setCompany_id(Fun_QueryCompanyID.http_QueryCompanyID(companyOBJ));
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
                    view = View.inflate(getActivity(), R.layout.item_group, null);
                } else {
                    view = convertView;
                }
                GroupOBJ groupOBJ = GroupList.get(position);
                TextView Tindex = view.findViewById(R.id.text_index);
                Tindex.setText(position);
                TextView Tgroup_id = view.findViewById(R.id.text_group_id);
                Tgroup_id.setText("第 " + groupOBJ.getGroup_id() + " 组");
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final GroupOBJ groupOBJ = GroupList.get(position);
                PopupMenu popup = new PopupMenu(getContext(), view);
                final MenuInflater inflater = popup.getMenuInflater();
                if (userOBJ.getCompetition_id() != 0) {
                    inflater.inflate(R.menu.pop_menu_company_group_score, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        Intent intent;

                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()) {
                                case R.id.menu_overall_score:
                                    intent = new Intent(getContext(), OverallScoreActivity.class);
                                    intent.putExtra("GROUPOBJ", groupOBJ);
                                    startActivity(intent);
                                    break;
                                case R.id.menu_detail_quality_score:
                                    QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ(groupOBJ.getGroup_id(), companyOBJ.getCompetition_id());
                                    intent = new Intent(getContext(), QualityScoreActivity.class);
                                    intent.putExtra("QUALITYSCOREOBJ", qualityScoreOBJ);
                                    startActivity(intent);
                                    break;
                                case R.id.menu_detail_taste_score:
                                    TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ(groupOBJ.getGroup_id(), companyOBJ.getCompetition_id());
                                    intent = new Intent(getContext(), TasteScoreActivity.class);
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
}