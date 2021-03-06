package spencercjh.crabscore.StaffPart;

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

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryPresentCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.JudgePart.Fun_QueryAllGroup;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class DataEntryFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private SwipeRefreshLayout srl_simple;


    public DataEntryFragment() {
    }

    public DataEntryFragment(UserOBJ userOBJ) {
        this.userOBJ = userOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_data_entry, container, false);
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
        ListView lv = mView.findViewById(R.id.data_entry_list);
        final int competition_id = Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID();
        final ArrayList<GroupOBJ> GroupList = JsonConvert.convert_Group_List1(Fun_QueryAllGroup.http_QueryAllGroup(competition_id));
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
                TextView Tindex = view.findViewById(R.id.tv_index);
                Tindex.setText(String.valueOf(position));
                TextView Tgroup_id = view.findViewById(R.id.tv_group_id);
                Tgroup_id.setText("第 " + groupOBJ.getGroup_id() + " 组");
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final GroupOBJ groupOBJ = GroupList.get(position);
                if (userOBJ.getUser_name() != null) {
                    PopupMenu popup = new PopupMenu(getContext(), view);
                    final MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.pop_menu_data_entry, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        Intent intent;

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_crab_info_f:
                                    intent = new Intent(getContext(), CrabList_F_Activity.class);
                                    intent.putExtra("GROUPOBJ", groupOBJ);
                                    intent.putExtra("USEROBJ", userOBJ);
                                    intent.putExtra("COMPETITIONID", competition_id);
                                    startActivity(intent);
                                    break;
                                case R.id.menu_crab_info_m:
                                    intent = new Intent(getContext(), CrabList_M_Activity.class);
                                    intent.putExtra("GROUPOBJ", groupOBJ);
                                    intent.putExtra("USEROBJ", userOBJ);
                                    intent.putExtra("COMPETITIONID", competition_id);
                                    startActivity(intent);
                                    break;
                                case R.id.menu_add_crab:
                                    intent = new Intent(getContext(), AddCrabActivity.class);
                                    intent.putExtra("GROUPOBJ", groupOBJ);
                                    intent.putExtra("USEROBJ", userOBJ);
                                    intent.putExtra("COMPETITIONID", competition_id);
                                    startActivity(intent);
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            }
        });
    }

}