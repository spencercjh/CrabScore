package spencercjh.crabscore.AdministratorPart;

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

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class UserAdminFragment extends Fragment {
    private static final String TAG = "BestGermplasmPrizeFragment";
    protected View mView;
    protected Context mContext;
    private ListView lv;
    private UserOBJ admin = new UserOBJ();
    private int choice;
    private SwipeRefreshLayout srl_simple;


    UserAdminFragment() {
    }

    UserAdminFragment(UserOBJ userOBJ, int choice) {
        this.admin = userOBJ;
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_user_admin, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fill_AllUserList();
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
        Fill_AllUserList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void Fill_AllUserList() {
        lv = mView.findViewById(R.id.all_user_list);
        /**
         * 涉及多表多数据的计算 此处网络线程后面再完善
         */
        final ArrayList<UserOBJ> UserList = new ArrayList<>();
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return UserList.size();
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
                    view = View.inflate(getActivity(), R.layout.item_user_admin, null);
                } else {
                    view = convertView;
                }
                UserOBJ userOBJ = UserList.get(position);
                TextView Tuser_name = view.findViewById(R.id.tv_user_name);
                TextView Tdisplay_name = view.findViewById(R.id.tv_display_name);
                TextView Trole = view.findViewById(R.id.tv_role);
                TextView Tconpetition_status = view.findViewById(R.id.tv_conpetition_status);
                Tuser_name.setText(userOBJ.getUser_name());
                Tdisplay_name.setText(userOBJ.getDisplay_name());
                if (userOBJ.getCompetition_id() == 0) {
                    Tconpetition_status.setText("所有赛事有效");
                } else if (userOBJ.getStatus() != 0) {
                    Tconpetition_status.setText("仅当前赛事有效");
                }
                if (userOBJ.getRole_id() == 1) {
                    Trole.setText("管理员");
                } else if (userOBJ.getRole_id() == 2) {
                    Trole.setText("工作人员");
                } else if (userOBJ.getRole_id() == 3) {
                    Trole.setText("评委");
                } else if (userOBJ.getRole_id() == 4) {
                    Trole.setText("参选单位");
                }
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final UserOBJ userOBJ = UserList.get(position);
                if (userOBJ.getUser_name() != null) {
                    PopupMenu popup = new PopupMenu(getContext(), view);
                    final MenuInflater inflater = popup.getMenuInflater();
                    if (userOBJ.getCompetition_id() != 0) {
                        inflater.inflate(R.menu.pop_menu_user_admin, popup.getMenu());
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            Intent intent;

                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu_edit_info:
                                        intent = new Intent(getContext(), UpdateUserInfoActivity.class);
                                        intent.putExtra("ADMIN", admin);
                                        intent.putExtra("USEROBJ", userOBJ);
                                        intent.putExtra("USER", choice);
                                        startActivity(intent);
                                        break;
                                    case R.id.menu_ban:
//修改用户status并刷新
                                        Fill_AllUserList();
                                        break;
                                    case R.id.menu_all_competition:
//修改用户competition_id
                                        break;
                                    case R.id.menu_only_present_competition:
//修改用户competition_id
                                        break;
                                    default:
                                        break;
                                }
                                return false;
                            }
                        });
                    }
                    popup.show();
                }
            }
        });
    }
}