package spencercjh.crabscore.AdministratorPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryAllCompetition;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_QueryPresentCompetitionID;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdateCompetitionStatus;
import spencercjh.crabscore.HttpRequst.Function.AdministratorPart.Fun_UpdatePresentCompetition;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class UpdatePresentCompetitionActivity extends AppCompatActivity {
    private ListView lv;
    private SwipeRefreshLayout srl_simple;
    private UserOBJ userOBJ = new UserOBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_present_competition);
        Intent intent = getIntent();
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        lv = (ListView) findViewById(R.id.all_competition_list);
        srl_simple = (SwipeRefreshLayout) findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_CompetitionList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                srl_simple.setRefreshing(false);
            }
        });
        srl_simple.setColorSchemeResources(R.color.red, R.color.orange, R.color.green, R.color.blue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("大赛列表");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
    }

    private void goback() {
        finish();
    }

    private void Fill_CompetitionList() throws InterruptedException {
        final ArrayList<CompetitionOBJ> CompetitionList = JsonConvert.convert_Competition_List(Fun_QueryAllCompetition.http_QueryAllCompetition());
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return CompetitionList.size();
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
                    view = View.inflate(UpdatePresentCompetitionActivity.this, R.layout.item_competition, null);
                } else {
                    view = convertView;
                }
                CompetitionOBJ competitionOBJ = CompetitionList.get(position);
                TextView Tyear = view.findViewById(R.id.tv_year);
                TextView Tnote = view.findViewById(R.id.tv_note);
                Tyear.setText(competitionOBJ.getCompetition_year());
                Tnote.setText(competitionOBJ.getNote().substring(0, 10));
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final CompetitionOBJ competitionOBJ = CompetitionList.get(position);
                PopupMenu popup = new PopupMenu(UpdatePresentCompetitionActivity.this, view);
                final MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.pop_menu_competition, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    Intent intent;

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit_info:
                                intent = new Intent(UpdatePresentCompetitionActivity.this, UpdateCompetitionPropertyActivity.class);
                                intent.putExtra("COMPETITIONOBJ", competitionOBJ);
                                intent.putExtra("USEROBJ", userOBJ);
                                startActivity(intent);
                                break;
                            case R.id.menu_set_present:
                                dialog_update(competitionOBJ);
                                break;
                            case R.id.menu_delete:
                                dialog_delete(competitionOBJ);
                                break;
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

    private void dialog_update(final CompetitionOBJ competitionOBJ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePresentCompetitionActivity.this);
        builder.setMessage("确认将赛事：" + competitionOBJ.getCompetition_year() + " 修改为当前赛事？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            if (Fun_UpdatePresentCompetition.http_UpdatePresentCompetition(competitionOBJ, userOBJ,
                                    Fun_QueryPresentCompetitionID.http_QueryPresentCompetitionID())) {
                                dialog_update_success();
                            } else {
                                dialog_update_fail();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();////显示对话框
    }

    private void dialog_update_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePresentCompetitionActivity.this);
        builder.setMessage("修改成功！");
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

    private void dialog_update_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePresentCompetitionActivity.this);
        builder.setMessage("修改失败！");
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

    @Override
    public void onBackPressed() {
        goback();
    }

    private void dialog_delete(final CompetitionOBJ competitionOBJ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePresentCompetitionActivity.this);
        builder.setMessage("确认删除？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                        try {
                            competitionOBJ.setStatus(0);
                            if (Fun_UpdateCompetitionStatus.http_UpdateCompetitionStatus(competitionOBJ, userOBJ)) {
                                dialog_delete_success();
                            } else {
                                dialog_delete_fail();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();////显示对话框
    }

    private void dialog_delete_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePresentCompetitionActivity.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePresentCompetitionActivity.this);
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
