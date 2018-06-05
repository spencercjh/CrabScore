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

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryAllUser;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryPresentCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateUserCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateUserStatus;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class UserAdminFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private ListView lv;
    private UserOBJ admin = new UserOBJ();
    private SwipeRefreshLayout srl_simple;


    public UserAdminFragment() {
    }

    public UserAdminFragment(UserOBJ userOBJ) {
        this.admin = userOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_user_admin, container, false);
        lv = view.findViewById(R.id.all_user_list);
        srl_simple = view.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_AllUserList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                srl_simple.setRefreshing(false);
            }
        });
        srl_simple.setColorSchemeResources(R.color.red, R.color.orange, R.color.green, R.color.blue);
        mView = view;
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
            Fill_AllUserList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Fill_AllUserList() throws InterruptedException {
        final ArrayList<UserOBJ> UserList = JsonConvert.convert_User_List(Fun_QueryAllUser.http_QueryAllUser());
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
                TextView Tcompetition_status = view.findViewById(R.id.tv_conpetition_status);
                Tuser_name.setText(userOBJ.getUser_name());
                Tdisplay_name.setText(userOBJ.getDisplay_name());
                if (userOBJ.getCompetition_id() == 0) {
                    Tcompetition_status.setText("所有赛事有效");
                } else if (userOBJ.getStatus() != 0) {
                    Tcompetition_status.setText("仅当前赛事有效");
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
                                    startActivity(intent);
                                    break;
                                case R.id.menu_ban:
                                    userOBJ.setStatus(0);
                                    try {
                                        if (Fun_UpdateUserStatus.http_UpdateUserStatus(userOBJ, admin)) {
                                            dialog_ban_success();
                                            Fill_AllUserList();
                                        } else {
                                            dialog_fail();
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case R.id.menu_all_competition:
                                    userOBJ.setCompetition_id(0);
                                    try {
                                        if (Fun_UpdateUserCompetitionID.http_UpdateUserCompetitionID(userOBJ, admin)) {
                                            dialog_updatecompetition_success();
                                            Fill_AllUserList();
                                        } else {
                                            dialog_fail();
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case R.id.menu_only_present_competition:
                                    try {
                                        userOBJ.setCompetition_id(Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID());
                                        if (Fun_UpdateUserCompetitionID.http_UpdateUserCompetitionID(userOBJ, admin)) {
                                            dialog_updatecompetition_success();
                                            Fill_AllUserList();
                                        } else {
                                            dialog_fail();
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
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

    private void dialog_ban_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("禁用成功！");
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

    private void dialog_updatecompetition_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("修改比赛状态成功！");
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

    private void dialog_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("操作失败");
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