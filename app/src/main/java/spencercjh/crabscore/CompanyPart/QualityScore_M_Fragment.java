package spencercjh.crabscore.CompanyPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

/**
 * Created by spencercjh on 2018/2/5.
 * iClass
 */
@SuppressLint("ValidFragment")
public class QualityScore_M_Fragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private TextView text_crab_sex;
    private TextView text_score_fin;
    private TextView text_score_bts;
    private TextView text_score_fts;
    private TextView text_score_ec;
    private TextView text_score_dscc;
    private TextView text_score_bbyzt;
    private UserOBJ userOBJ = new UserOBJ();
    private QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ();
    private int choice;

    QualityScore_M_Fragment() {
    }

    QualityScore_M_Fragment(UserOBJ userOBJ, int choice, QualityScoreOBJ qualityScoreOBJ) {
        this.userOBJ = userOBJ;
        this.choice = choice;
        this.qualityScoreOBJ = qualityScoreOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_quality_score_m, container, false);
        text_crab_sex = mView.findViewById(R.id.text_crab_sex);
        text_score_fin = mView.findViewById(R.id.text_score_fin);
        text_score_bts = mView.findViewById(R.id.text_score_bts);
        text_score_fts = mView.findViewById(R.id.text_score_fts);
        text_score_ec = mView.findViewById(R.id.text_score_ec);
        text_score_dscc = mView.findViewById(R.id.text_score_dscc);
        text_score_bbyzt = mView.findViewById(R.id.text_score_bbyzt);
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        InitialInfo();
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
}