package spencercjh.crabscore.CompanyPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class OwnGroupScoreFragment extends Fragment {
    private static final String TAG = "HighQualityPrizeFragment";
    protected View mView;
    protected Context mContext;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private ListView lv;
    private SwipeRefreshLayout srl_simple;

    OwnGroupScoreFragment() {
    }


    OwnGroupScoreFragment(UserOBJ userOBJ, int choice) {
        this.userOBJ = userOBJ;
        this.choice = choice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_own_group_score, container, false);
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

}
