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

import spencercjh.crabscore.HttpRequst.Function.CompanyPart.Fun_QueryTasteScoreInfo;
import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.R;

/**
 * Created by spencercjh on 2018/2/5.
 * iClass
 */
@SuppressLint("ValidFragment")
public class TasteScore_F_Fragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private TextView text_crab_sex;
    private TextView text_score_fin;
    private TextView text_score_ygys;
    private TextView text_score_sys;
    private TextView text_score_ghys;
    private TextView text_score_xwxw;
    private TextView text_score_gh;
    private TextView text_score_fbjr;
    private TextView text_score_bzjr;
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();

    public TasteScore_F_Fragment() {
    }

    public TasteScore_F_Fragment(TasteScoreOBJ tasteScoreOBJ) {
        this.tasteScoreOBJ = tasteScoreOBJ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_taste_score_f, container, false);
        text_crab_sex = mView.findViewById(R.id.text_crab_sex);
        text_score_fin = mView.findViewById(R.id.text_score_fin);
        text_score_ygys = mView.findViewById(R.id.text_score_ygys);
        text_score_sys = mView.findViewById(R.id.text_score_sys);
        text_score_ghys = mView.findViewById(R.id.text_score_ghys);
        text_score_xwxw = mView.findViewById(R.id.text_score_xwxw);
        text_score_gh = mView.findViewById(R.id.text_score_gh);
        text_score_fbjr = mView.findViewById(R.id.text_score_fbjr);
        text_score_bzjr = mView.findViewById(R.id.text_score_bzjr);
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
        TasteScoreOBJ tasteScoreOBJ_F = tasteScoreOBJ;
        tasteScoreOBJ_F.setCrab_sex(0);
        tasteScoreOBJ_F = JsonConvert.convert_TasteScoreOBJ(Fun_QueryTasteScoreInfo.http_QueryTasteScoreInfo(tasteScoreOBJ_F));
        text_crab_sex.setText("雌蟹");
        text_score_fin.setText(String.valueOf(tasteScoreOBJ_F.getScore_fin()));
        text_score_ygys.setText(String.valueOf(tasteScoreOBJ_F.getScore_ygys()));
        text_score_sys.setText(String.valueOf(tasteScoreOBJ_F.getScore_sys()));
        text_score_ghys.setText(String.valueOf(tasteScoreOBJ_F.getScore_ghys()));
        text_score_xwxw.setText(String.valueOf(tasteScoreOBJ_F.getScore_xwxw()));
        text_score_gh.setText(String.valueOf(tasteScoreOBJ_F.getScore_gh()));
        text_score_fbjr.setText(String.valueOf(tasteScoreOBJ_F.getScore_fbjr()));
        text_score_bzjr.setText(String.valueOf(tasteScoreOBJ_F.getScore_bzjr()));
    }
}
