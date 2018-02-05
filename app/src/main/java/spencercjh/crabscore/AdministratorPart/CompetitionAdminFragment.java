package spencercjh.crabscore.AdministratorPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class CompetitionAdminFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BestGermplasmPrizeFragment";
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private SwipeRefreshLayout srl_simple;
    private TextView Tyear_note;
    private EditText Tcompetition_year;
    private EditText Tvar_fatness_m;
    private EditText Tvar_fatness_f;
    private EditText Tvar_weight_m;
    private EditText Tvar_mfatness_sd;
    private EditText Tvar_mweight_sd;
    private EditText Tvar_weight_f;
    private EditText Tvar_ffatness_sd;
    private EditText Tvar_fweight_sd;
    private RelativeLayout Ryear_note;
    private RelativeLayout Rmore_setting;
    private Button button;
    private CompetitionOBJ competition_OBJ = new CompetitionOBJ();

    CompetitionAdminFragment() {
    }

    CompetitionAdminFragment(UserOBJ userOBJ, int choice) {
        this.userOBJ = userOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        Intent intent = getActivity().getIntent();
        try {    //接收从修改年份备注活动传回的年份备注信息 接收从更多设置传回的数据
            competition_OBJ = (CompetitionOBJ) intent.getSerializableExtra("COMPETITIONOBJ");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        mView = inflater.inflate(R.layout.fragment_competition_admin, container, false);
        Ryear_note = mView.findViewById(R.id.re_year_note);
        Ryear_note.setOnClickListener(this);
        Rmore_setting = mView.findViewById(R.id.re_more_setting);
        Rmore_setting.setOnClickListener(this);
        Tyear_note = mView.findViewById(R.id.text_year_note);
        Tvar_fatness_m = mView.findViewById(R.id.text_var_fatness_m);
        Tvar_weight_m = mView.findViewById(R.id.text_var_weight_m);
        Tvar_mfatness_sd = mView.findViewById(R.id.text_var_mfatness_sd);
        Tvar_mweight_sd = mView.findViewById(R.id.text_var_mweight_sd);
        Tvar_fatness_f = mView.findViewById(R.id.text_var_fatness_f);
        Tvar_weight_f = mView.findViewById(R.id.text_var_weight_f);
        Tvar_ffatness_sd = mView.findViewById(R.id.text_var_ffatness_sd);
        Tvar_fweight_sd = mView.findViewById(R.id.text_var_fweight_sd);
        button = mView.findViewById(R.id.button);
        button.setOnClickListener(this);
        srl_simple = mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                InitialInfo();
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

    }

    private void InitialInfo() {
        /**
         * 数据初始化 网络线程
         */
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.re_year_note:
                intent = new Intent(getContext(), UpdateYear_NoteActivity.class);
                getActivity().startActivity(intent);
                intent.putExtra("COMPETITIONOBJ", competition_OBJ);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                break;
            case R.id.re_more_setting:
                intent = new Intent(getContext(), UpdateMoreSettingActivity.class);
                getActivity().startActivity(intent);
                intent.putExtra("COMPETITIONOBJ", competition_OBJ);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                break;
            case R.id.button:
                updateCompetition_info();
                break;
            default:
                break;
        }
    }


    private void updateCompetition_info() {
        String year = competition_OBJ.getCompetition_year();
        String note = competition_OBJ.getNote();
        double var_fatness_m = Double.parseDouble(Tvar_fatness_m.getText().toString().trim());
        double var_weight_m = Double.parseDouble(Tvar_weight_m.getText().toString().trim());
        double var_mfatness_sd = Double.parseDouble(Tvar_mfatness_sd.getText().toString().trim());
        double var_mweight_sd = Double.parseDouble(Tvar_mweight_sd.getText().toString().trim());
        double var_fatness_f = Double.parseDouble(Tvar_fatness_f.getText().toString().trim());
        double var_weight_f = Double.parseDouble(Tvar_weight_f.getText().toString().trim());
        double var_ffatness_sd = Double.parseDouble(Tvar_ffatness_sd.getText().toString().trim());
        double var_fweight_sd = Double.parseDouble(Tvar_fweight_sd.getText().toString().trim());
        int result_fatness = competition_OBJ.getResult_fatness();
        int result_quality = competition_OBJ.getResult_quality();
        int result_taste = competition_OBJ.getResult_taste();
        competition_OBJ = new CompetitionOBJ(year, note, var_fatness_m, var_fatness_f,
                var_weight_m, var_mfatness_sd, var_mweight_sd, var_weight_f, var_ffatness_sd,
                var_fweight_sd, result_fatness, result_quality, result_taste);
        /**
         * 更新数据网络线程
         */

    }
}