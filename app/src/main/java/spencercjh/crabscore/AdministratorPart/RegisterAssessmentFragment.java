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

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_DeleteUserInfo;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryAllUncheckedUser;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateUserStatus;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class RegisterAssessmentFragment extends Fragment {
    private static final String TAG = "FatnessPrizeFragment";
    protected View mView;
    protected Context mContext;
    private UserOBJ admin = new UserOBJ();
    private int choice;
    private ListView lv;
    private SwipeRefreshLayout srl_simple;

    public RegisterAssessmentFragment() {
    }


    public RegisterAssessmentFragment(UserOBJ userOBJ, int choice) {
        this.admin = userOBJ;
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_register_assessment, container, false);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_RegisterList();
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
            Fill_RegisterList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Fill_RegisterList() throws InterruptedException {
        lv = mView.findViewById(R.id.all_register_list);
        /**
         * 涉及多表多数据的计算 此处网络线程后面再完善
         **/
        final ArrayList<UserOBJ> UserList = JsonConvert.convert_user_name_display_name_role_id_status(Fun_QueryAllUncheckedUser.http_QueryAllUncheckedUser());
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
                TextView Tstatus = view.findViewById(R.id.tv_conpetition_status);
                Tuser_name.setText(userOBJ.getUser_name());
                Tdisplay_name.setText(userOBJ.getDisplay_name());
                Tstatus.setText("禁用");
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
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, final long id) {
                final UserOBJ userOBJ = UserList.get(position);
                if (userOBJ.getUser_name() != null) {
                    PopupMenu popup = new PopupMenu(getContext(), view);
                    final MenuInflater inflater = popup.getMenuInflater();
                    if (userOBJ.getCompetition_id() != 0) {
                        inflater.inflate(R.menu.pop_menu_register_assessment, popup.getMenu());
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
                                    case R.id.menu_enable:
                                        userOBJ.setStatus(1);
                                        try {
                                            if (Fun_UpdateUserStatus.http_UpdateUserStatus(userOBJ, admin)) {
                                                dialog_enable_success();
                                                Fill_RegisterList();
                                            } else {
                                                dialog_fail();
                                            }
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case R.id.menu_delete:
                                        try {
                                            if (Fun_DeleteUserInfo.http_DeleteUserInfo(userOBJ, admin)) {
                                                dialog_delete_success();
                                                Fill_RegisterList();
                                            } else {
                                                dialog_fail();
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

    private void dialog_enable_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("启用成功！");
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