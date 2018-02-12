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
import java.util.Collections;
import java.util.Comparator;

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryPresentCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part.Fun_QueryCompanyName;
import spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part.Fun_QueryQualityScore;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class QualityPrizeFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private SwipeRefreshLayout srl_simple;


    public QualityPrizeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_quality_prize, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_QualityPrizeList();
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
            Fill_QualityPrizeList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Fill_QualityPrizeList() throws InterruptedException {
        ListView lv = mView.findViewById(R.id.quality_score_list);
        int competition_id = Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID();
        final ArrayList<GroupOBJ> ScoreList = JsonConvert.convert_quality_score(Fun_QueryQualityScore.http_QueryQualityScore(competition_id));
        Collections.sort(ScoreList, new Comparator<GroupOBJ>() {
            @Override
            public int compare(GroupOBJ g1, GroupOBJ g2) {
                float quality_score_g1 = ((g1.getQuality_score_m() + g1.getQuality_score_f()) / (float) 2.0);
                float quality_score_g2 = (g2.getQuality_score_m() + g2.getQuality_score_f() / (float) 2.0);
                if (quality_score_g1 > quality_score_g2) {
                    return 1;
                } else if (quality_score_g1 == quality_score_g2) {
                    return 0;
                } else if (quality_score_g1 < quality_score_g2) {
                    return -1;
                }
                return 0;
            }
        });
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
                Tindex.setText(String.valueOf(position));
                TextView Tgroup_id = view.findViewById(R.id.tv_group_id);
                Tgroup_id.setText(String.valueOf(groupOBJ.getGroup_id()));
                TextView Tcompany_name = view.findViewById(R.id.tv_company_name);
                try {
                    Tcompany_name.setText(Fun_QueryCompanyName.http_QueryCompanyName(groupOBJ.getCompany_id()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TextView Tscore = view.findViewById(R.id.tv_score);
                Tscore.setText(String.valueOf((groupOBJ.getQuality_score_m() + groupOBJ.getQuality_score_f()) / 2.0));
                return view;
            }
        });
    }
}