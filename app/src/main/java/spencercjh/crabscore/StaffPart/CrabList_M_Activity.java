package spencercjh.crabscore.StaffPart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.StaffPart.Fun_DeleteCrabInfo;
import spencercjh.crabscore.HttpRequst.Function.StaffPart.Fun_QueryAllCrab;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class CrabList_M_Activity extends AppCompatActivity {
    ListView lv;
    private GroupOBJ groupOBJ = new GroupOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private int competition_id = -1;
    private SwipeRefreshLayout srl_simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crablist__m);
        srl_simple = (SwipeRefreshLayout) findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Fill_CrabList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                srl_simple.setRefreshing(false);
            }
        });
        srl_simple.setColorSchemeResources(R.color.red, R.color.orange, R.color.green, R.color.blue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("选择一只雄蟹");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        Intent intent = getIntent();
        groupOBJ = (GroupOBJ) intent.getSerializableExtra("GROUPOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        competition_id = (int) intent.getSerializableExtra("COMPETITIONID");
        lv = (ListView) findViewById(R.id.crablist);
        try {
            Fill_CrabList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Fill_CrabList() throws InterruptedException {
        final ArrayList<CrabOBJ> CrabList = JsonConvert.convert_Crab_List(Fun_QueryAllCrab.http_QueryAllCrab(competition_id, groupOBJ.getGroup_id(), 1));
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return CrabList.size();
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
                    view = View.inflate(CrabList_M_Activity.this, R.layout.item_crab, null);
                } else {
                    view = convertView;
                }
                CrabOBJ crabOBJ = CrabList.get(position);
                TextView text_index = view.findViewById(R.id.text_index);
                TextView text_carb_id = view.findViewById(R.id.text_crab_id);
                text_index.setText(String.valueOf(position));
                text_carb_id.setText(String.valueOf(crabOBJ.getCrab_id()));
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CrabOBJ crabOBJ = CrabList.get(i);
                Intent intent = new Intent(CrabList_M_Activity.this, UpdateCrabInfoActivity.class);
                intent.putExtra("INDEX", i);
                intent.putExtra("CRABOBJ", crabOBJ);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("SEX", 1);
                intent.putExtra("GROUPOBJ", groupOBJ);
                startActivity(intent);
                finish();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CrabOBJ crabOBJ = CrabList.get(position);
                int crab_id = crabOBJ.getCrab_id();
                dialog_delete(crab_id);
                return true;
            }
        });
    }

    private void goback() {
        finish();
    }

    @Override
    public void onBackPressed() {
        goback();
    }

    private void dialog_delete(final int crab_id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("要删除编号为" + crab_id + "的螃蟹吗？");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if (Fun_DeleteCrabInfo.http_DeleteCrabInfo(crab_id)) {
                                dialog_delete_success();
                            } else {
                                dialog_delete_fail();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            dialog_delete_fail();
                        }
                    }
                }
        );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
            }
        });
        builder.create().show();//显示对话框
    }

    private void dialog_delete_success() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("删除成功！");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Fill_CrabList();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();//关闭对话框
                    }
                }
        );
        builder.create().show();//显示对话框
    }

    private void dialog_delete_fail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        builder.create().show();//显示对话框
    }
}