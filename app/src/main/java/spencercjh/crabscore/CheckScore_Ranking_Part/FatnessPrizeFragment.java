package spencercjh.crabscore.CheckScore_Ranking_Part;

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

import spencercjh.crabscore.OBJ.Score_RankingOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class FatnessPrizeFragment extends Fragment {
    private static final String TAG = "FatnessPrizeFragment";
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private ListView lv;
    private SwipeRefreshLayout srl_simple;

    FatnessPrizeFragment() {
    }


    FatnessPrizeFragment(UserOBJ userOBJ, int choice) {
        this.userOBJ = userOBJ;
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_fatness_prize, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fill_FatnessPrizeList();
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
        Fill_FatnessPrizeList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void Fill_FatnessPrizeList() {
        lv = mView.findViewById(R.id.fatness_score_list);
        /**
         * 涉及多表多数据的计算 此处网络线程后面再完善
         */
        final ArrayList<Score_RankingOBJ> ScoreList = new ArrayList<>();
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
                Score_RankingOBJ score_rankingOBJ = ScoreList.get(position);
                TextView Torder = view.findViewById(R.id.tv_order);
                TextView Tgroup_id = view.findViewById(R.id.tv_group_id);
                TextView Tcompany = view.findViewById(R.id.tv_company);
                TextView Tscore = view.findViewById(R.id.tv_score);
                Torder.setText(score_rankingOBJ.getOrder());
                Tgroup_id.setText(score_rankingOBJ.getGroup_id());
                Tcompany.setText(score_rankingOBJ.getCompany_name().trim());
                Tscore.setText(String.valueOf(score_rankingOBJ.getScore()));
                return view;
            }
        });
    }
}