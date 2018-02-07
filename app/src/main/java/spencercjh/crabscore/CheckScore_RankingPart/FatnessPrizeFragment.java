package spencercjh.crabscore.CheckScore_RankingPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryPresentCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part.Fun_QueryCompanyName;
import spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part.Fun_QueryFatnessScore;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class FatnessPrizeFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private SwipeRefreshLayout srl_simple;

    public FatnessPrizeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_fatness_prize, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_FatnessPrizeList();
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
            Fill_FatnessPrizeList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Fill_FatnessPrizeList() throws InterruptedException {
        ListView lv = mView.findViewById(R.id.fatness_score_list);
        int competition_id = Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID();
        final ArrayList<GroupOBJ> ScoreList = JsonConvert.convert_fatness_score(Fun_QueryFatnessScore.http_QueryHighQualityScore(competition_id));
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return ScoreList.size();
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
                    view = View.inflate(getActivity(), R.layout.item_score_ranking, null);
                } else {
                    view = convertView;
                }
                GroupOBJ groupOBJ = ScoreList.get(position);
                TextView Tindex = view.findViewById(R.id.tv_index);
                Tindex.setText(position);
                TextView Tgroup_id = view.findViewById(R.id.tv_group_id);
                Tgroup_id.setText(groupOBJ.getGroup_id());
                TextView Tcompany_name = view.findViewById(R.id.tv_company_name);
                try {
                    Tcompany_name.setText(Fun_QueryCompanyName.http_QueryCompanyName(groupOBJ.getCompany_id()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TextView Tscore = view.findViewById(R.id.tv_score);
                /*
                  计算不完善！
                 */
                Tscore.setText(String.valueOf((groupOBJ.getFatness_score_f() + groupOBJ.getFatness_score_m()) / 2.0));
                return view;
            }
        });
    }
}