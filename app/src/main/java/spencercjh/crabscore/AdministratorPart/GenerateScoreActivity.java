package spencercjh.crabscore.AdministratorPart;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import spencercjh.crabscore.HttpRequst.Function.GenerateScorePart.Fun_GenerateCrabFatness;
import spencercjh.crabscore.HttpRequst.Function.GenerateScorePart.Fun_GenerateCrabFatnessScore;
import spencercjh.crabscore.HttpRequst.Function.GenerateScorePart.Fun_GenerateCrabQualityScore;
import spencercjh.crabscore.HttpRequst.Function.GenerateScorePart.Fun_GenerateCrabTasteScore;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.JudgePart.Fun_QueryAllGroup;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressWarnings("deprecation")
public class GenerateScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private CompetitionOBJ competitionOBJ = new CompetitionOBJ();
    private UserOBJ userOBJ = new UserOBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_head);
        toolbar.setTitle("大赛成绩生成");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback();
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Intent intent = getIntent();
        competitionOBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
    }

    private void goback() {
        finish();
    }

    @Override
    public void onBackPressed() {
        goback();
    }

    private void dialog_null() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GenerateScoreActivity.this);
        builder.setMessage("有参数没有填写完整！");
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

    private boolean check_null() {
        return !competitionOBJ.getCompetition_year().isEmpty() &&
                competitionOBJ.getVar_weight_m() != 0 &&
                competitionOBJ.getVar_weight_f() != 0 &&
                competitionOBJ.getVar_fatness_m() != 0 &&
                competitionOBJ.getVar_fatness_f() != 0 &&
                competitionOBJ.getVar_ffatness_sd() != 0 &&
                competitionOBJ.getVar_mfatness_sd() != 0 &&
                competitionOBJ.getVar_mweight_sd() != 0 &&
                competitionOBJ.getVar_fweight_sd() != 0;
    }

    private boolean generate_crab_fatness(int crab_sex, float var_fatness) throws InterruptedException {
        return Fun_GenerateCrabFatness.http_GenerateCrabFatness(var_fatness, crab_sex, competitionOBJ.getCompetition_id(), userOBJ.getUser_name());
    }

    private boolean generate_crab_fatness_score(int group_id, int crab_sex, float var_weight, float var_fatness_sd) throws InterruptedException {
        return Fun_GenerateCrabFatnessScore.http_GenerateCrabFatnessScore(var_weight, group_id, var_fatness_sd, crab_sex, competitionOBJ.getCompetition_id(), userOBJ.getUser_name());
    }

    private boolean generate_crab_quality_score(int group_id, int crab_sex, int competition_id) throws InterruptedException {
        return Fun_GenerateCrabQualityScore.http_GenerateCrabQualityScore(crab_sex, group_id, competition_id, userOBJ.getUser_name());
    }

    private boolean generate_crab_taste_score(int group_id, int crab_sex, int competition_id) throws InterruptedException {
        return Fun_GenerateCrabTasteScore.http_GenerateCrabTasteScore(crab_sex, group_id, competition_id, userOBJ.getUser_name());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            if (competitionOBJ != null) {
                if (check_null()) {
                    final ProgressDialog dialog = new ProgressDialog(this);
                    final Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            try {
                                boolean all_f_crab_fatness = generate_crab_fatness(0, competitionOBJ.getVar_fatness_f());  //所有雌蟹的肥满度计算
                                boolean all_m_crab_fatness = generate_crab_fatness(1, competitionOBJ.getVar_fatness_m());  //所有雄蟹的肥满度计算
                                ArrayList<GroupOBJ> GroupList = JsonConvert.convert_Group_List1(Fun_QueryAllGroup.http_QueryAllGroup(competitionOBJ.getCompetition_id()));  //获取所有组id
                                boolean all_group_fatness_quality_taste_score = false;  //所有组的种质和口感分数计算
                                for (int i = 0; i < GroupList.size(); i++) {
                                    GroupOBJ groupOBJ = GroupList.get(i);
                                    boolean one_group_f_crab_fatness_score = generate_crab_fatness_score(groupOBJ.getGroup_id(), 0, competitionOBJ.getVar_weight_f(), competitionOBJ.getVar_ffatness_sd());   //该组雌蟹的肥满度分数计算
                                    boolean one_group_m_crab_fatness_score = generate_crab_fatness_score(groupOBJ.getGroup_id(), 1, competitionOBJ.getVar_weight_m(), competitionOBJ.getVar_mfatness_sd());   //该组雄蟹的肥满度分数计算
                                    boolean one_group_f_quality_score = generate_crab_quality_score(groupOBJ.getGroup_id(), 0, competitionOBJ.getCompetition_id()); //该组雌蟹种质分数计算
                                    boolean one_group_m_quality_score = generate_crab_quality_score(groupOBJ.getGroup_id(), 1, competitionOBJ.getCompetition_id()); //该组雄性种质分数计算
                                    boolean one_group_f_taste_score = generate_crab_taste_score(groupOBJ.getGroup_id(), 0, competitionOBJ.getCompetition_id()); //该组雌性口感分数计算
                                    boolean one_group_m_taste_score = generate_crab_taste_score(groupOBJ.getGroup_id(), 1, competitionOBJ.getCompetition_id()); //该组雄性口感分数计算
                                    all_group_fatness_quality_taste_score = one_group_f_quality_score &&
                                            one_group_m_quality_score &&
                                            one_group_f_taste_score &&
                                            one_group_m_taste_score &&
                                            one_group_f_crab_fatness_score &&
                                            one_group_m_crab_fatness_score;
                                }
                                if (all_f_crab_fatness && all_m_crab_fatness && all_group_fatness_quality_taste_score) {
                                    dialog.dismiss();
                                    Looper.prepare();
                                    Message message = new Message();
                                    Bundle bundle = new Bundle();
                                    Handler handler = new Handler() {
                                        @Override
                                        public void handleMessage(Message message) {
                                            super.handleMessage(message);
                                            Bundle bundle = message.getData();
                                            String msg = bundle.getString("msg");
                                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                        }
                                    };
                                    bundle.putString("msg", "所有成绩生成完毕！");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    Looper.loop();
                                } else {
                                    dialog.dismiss();
                                    Looper.prepare();
                                    Message message = new Message();
                                    Bundle bundle = new Bundle();
                                    Handler handler = new Handler() {
                                        @Override
                                        public void handleMessage(Message message) {
                                            super.handleMessage(message);
                                            Bundle bundle = message.getData();
                                            String msg = bundle.getString("msg");
                                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                        }
                                    };
                                    bundle.putString("msg", "成绩生成失败！");
                                    message.setData(bundle);
                                    handler.sendMessage(message);
                                    Looper.loop();
                                }
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                Looper.prepare();
                                Message message = new Message();
                                Bundle bundle = new Bundle();
                                Handler handler = new Handler() {
                                    @Override
                                    public void handleMessage(Message message) {
                                        super.handleMessage(message);
                                        Bundle bundle = message.getData();
                                        String msg = bundle.getString("msg");
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                    }
                                };
                                bundle.putString("msg", "成绩生成失败！");
                                message.setData(bundle);
                                handler.sendMessage(message);
                                Looper.loop();
                            }
                        }
                    });
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setIcon(android.R.drawable.ic_dialog_alert);
                    dialog.setTitle("警告");
                    dialog.setMessage("成绩生成中……");
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            // TODO Auto-generated method stub

                        }
                    });
                    dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

                        @Override
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            return false;
                        }
                    });
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            // TODO Auto-generated method stub
                        }
                    });
                    dialog.show();
                    thread.start();
                } else {
                    dialog_null();
                }
            } else {
                dialog_null();
            }
        }
    }
}
