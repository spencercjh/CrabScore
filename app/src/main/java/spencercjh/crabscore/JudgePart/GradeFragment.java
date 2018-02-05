package spencercjh.crabscore.JudgePart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class GradeFragment extends Fragment {
    private static final String TAG = "FatnessPrizeFragment";
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private ListView lv;
    private SwipeRefreshLayout srl_simple;

    GradeFragment() {
    }


    GradeFragment(UserOBJ userOBJ, int choice) {
        this.userOBJ = userOBJ;
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_grade, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fill_GroupList();
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
        Fill_GroupList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void Fill_GroupList() {
        lv = mView.findViewById(R.id.all_user_list);
        /**
         * 涉及多表多数据的计算 此处网络线程后面再完善
         */
        final ArrayList<QualityScoreOBJ> QualityScoreList = new ArrayList<>();
        final ArrayList<TasteScoreOBJ> TasteScoreList = new ArrayList<>();
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return QualityScoreList.size();
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
                    view = View.inflate(getActivity(), R.layout.item_grade, null);
                } else {
                    view = convertView;
                }
                QualityScoreOBJ qualityScoreOBJ = QualityScoreList.get(position);
                TasteScoreOBJ tasteScoreOBJ = TasteScoreList.get(position);
                TextView Tgroup_id = view.findViewById(R.id.text_group_id);
                Tgroup_id.setText("第 " + qualityScoreOBJ.getGroup_id() + " 组");
                TextView Tgrade_status = view.findViewById(R.id.text_grade_status);
                if (qualityScoreOBJ.getScore_fin() == 0 && tasteScoreOBJ.getScore_fin() == 0) {
                    Tgrade_status.setText("未打分");
                } else {
                    Tgrade_status.setText("已给分");
                }
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final QualityScoreOBJ qualityScoreOBJ = QualityScoreList.get(position);
                final TasteScoreOBJ tasteScoreOBJ = TasteScoreList.get(position);
                PopupMenu popup = new PopupMenu(getContext(), view);
                final MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.pop_menu_grade, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    Intent intent;

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_quality_grade:
                                intent = new Intent(getContext(), QualityGradeActivity.class);
                                intent.putExtra("USEROBJ", userOBJ);
                                intent.putExtra("USER", choice);
                                intent.putExtra("QUALITYSCOREOBJ", qualityScoreOBJ);
                                startActivity(intent);
                                break;
                            case R.id.menu_taste_grade:
                                intent = new Intent(getContext(), TasteGradeActivity.class);
                                intent.putExtra("USEROBJ", userOBJ);
                                intent.putExtra("USER", choice);
                                intent.putExtra("TASTESCOREOBJ", tasteScoreOBJ);
                                startActivity(intent);
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }
}