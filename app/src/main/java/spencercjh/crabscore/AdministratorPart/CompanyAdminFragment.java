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

import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class CompanyAdminFragment extends Fragment {
    private static final String TAG = "BestGermplasmPrizeFragment";
    protected View mView;
    protected Context mContext;
    private ListView lv;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private SwipeRefreshLayout srl_simple;


    CompanyAdminFragment() {
    }

    CompanyAdminFragment(UserOBJ userOBJ, int choice) {
        this.userOBJ = userOBJ;
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_company_admin, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fill_CompanyList();
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
        Fill_CompanyList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void Fill_CompanyList() {
        lv = mView.findViewById(R.id.all_company_list);
        /**
         * 涉及多表多数据的计算 此处网络线程后面再完善
         */
        final ArrayList<CompanyOBJ> CompanyList = new ArrayList<>();
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return CompanyList.size();
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
                    view = View.inflate(getActivity(), R.layout.item_company, null);
                } else {
                    view = convertView;
                }
                CompanyOBJ companyOBJ = CompanyList.get(position);
                TextView Tgroup_id = view.findViewById(R.id.tv_group_id);
                TextView Tcompany_name = view.findViewById(R.id.tv_display_name);
                Tgroup_id.setText(companyOBJ.getGroup_id());
                Tcompany_name.setText(companyOBJ.getCompany_name());
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final CompanyOBJ companyOBJ = CompanyList.get(position);
                if (userOBJ.getUser_name() != null) {
                    PopupMenu popup = new PopupMenu(getContext(), view);
                    final MenuInflater inflater = popup.getMenuInflater();
                    if (userOBJ.getCompetition_id() != 0) {
                        inflater.inflate(R.menu.pop_menu_company, popup.getMenu());
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            Intent intent;

                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu_check_score:
                                        intent = new Intent(getContext(), CheckCompanyScoreActivity.class);
                                        intent.putExtra("USEROBJ", userOBJ);
                                        intent.putExtra("COMPANYOBJ", companyOBJ);
                                        intent.putExtra("USER", choice);
                                        startActivity(intent);
                                        break;
                                    case R.id.menu_edit_info:
//前往编辑活动
                                        Fill_CompanyList();
                                        break;
                                    case R.id.menu_delete:
//删除用户信息
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