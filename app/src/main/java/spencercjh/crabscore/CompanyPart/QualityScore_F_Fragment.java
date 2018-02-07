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

import spencercjh.crabscore.HttpRequst.Function.CompanyPart.Fun_QueryQualityScoreInfo;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.R;

/**
 * Created by spencercjh on 2018/2/5.
 * iClass
 */
@SuppressLint("ValidFragment")
public class QualityScore_F_Fragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private TextView text_crab_sex;
    private TextView text_score_fin;
    private TextView text_score_bts;
    private TextView text_score_fts;
    private TextView text_score_ec;
    private TextView text_score_dscc;
    private TextView text_score_bbyzt;
    private QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ();

    public QualityScore_F_Fragment() {
    }

    public QualityScore_F_Fragment(QualityScoreOBJ qualityScoreOBJ) {
        this.qualityScoreOBJ = qualityScoreOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_quality_score_f, container, false);
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            InitialInfo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void InitialInfo() throws InterruptedException {
        QualityScoreOBJ qualityScoreOBJ_F = qualityScoreOBJ;
        qualityScoreOBJ_F.setCrab_sex(0);
        qualityScoreOBJ_F = JsonConvert.convert_QualityScoreOBJ(Fun_QueryQualityScoreInfo.http_QueryQualityScoreInfo(qualityScoreOBJ_F));
        text_crab_sex.setText("雌蟹");
        text_score_fin.setText(String.valueOf(qualityScoreOBJ_F.getScore_fin()));
        text_score_bts.setText(String.valueOf(qualityScoreOBJ_F.getScore_bts()));
        text_score_fts.setText(String.valueOf(qualityScoreOBJ_F.getScore_fts()));
        text_score_ec.setText(String.valueOf(qualityScoreOBJ_F.getScore_ec()));
        text_score_dscc.setText(String.valueOf(qualityScoreOBJ_F.getScore_dscc()));
        text_score_bbyzt.setText(String.valueOf(qualityScoreOBJ_F.getScore_bbyzt()));
    }
}
