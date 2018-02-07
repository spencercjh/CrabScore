package spencercjh.crabscore.AdministratorPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_DeleteCompanyInfo;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryAllCompany;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryCompanyID;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class CompanyAdminFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private SwipeRefreshLayout srl_simple;


    public CompanyAdminFragment() {
    }

    public CompanyAdminFragment(UserOBJ userOBJ) {
        this.userOBJ = userOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_company_admin, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_CompanyList();
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
            Fill_CompanyList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Fill_CompanyList() throws InterruptedException {
        ListView lv = mView.findViewById(R.id.all_company_list);
        final ArrayList<CompanyOBJ> CompanyList = JsonConvert.convert_company_user_name(Fun_QueryAllCompany.http_QueryAllUnit());
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
                TextView Tindex = view.findViewById(R.id.tv_index);
                TextView Tcompany_name = view.findViewById(R.id.tv_display_name);
                Tindex.setText(position);
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
                                        try {
                                            companyOBJ.setCompetition_id(Fun_QueryCompanyID.http_QueryCompanyID(companyOBJ));
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        intent = new Intent(getContext(), CheckCompanyScoreActivity.class);
                                        intent.putExtra("USEROBJ", userOBJ);
                                        intent.putExtra("COMPANYOBJ", companyOBJ);
                                        startActivity(intent);
                                        break;
                                    case R.id.menu_edit_info:
                                        try {
                                            companyOBJ.setCompetition_id(Fun_QueryCompanyID.http_QueryCompanyID(companyOBJ));
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        Intent intent = new Intent(getContext(), UpdateCompanyInfoActivity.class);
                                        intent.putExtra("COMPANYOBJ", companyOBJ);
                                        intent.putExtra("USEROBJ", userOBJ);
                                        startActivity(intent);
                                        break;
                                    case R.id.menu_delete:
                                        try {
                                            if (Fun_DeleteCompanyInfo.http_DeleteCompanyInfo(userOBJ, companyOBJ)) {
                                                dialog_delete_success();
                                                Fill_CompanyList();
                                            } else {
                                                dialog_delete_fail();
                                            }
                                            break;
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
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

    private void dialog_delete_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("删除成功！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                }
        );
        builder.create().show();////显示对话框
    }

    private void dialog_delete_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("删除失败！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                }
        );
        builder.create().show();////显示对话框
    }

}