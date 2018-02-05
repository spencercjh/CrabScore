package spencercjh.crabscore.CompanyPart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

public class QualityScorePageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ();

    public QualityScorePageAdapter(FragmentManager fm, ArrayList<String> titleArray, UserOBJ userOBJ, int choice, QualityScoreOBJ qualityScoreOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.userOBJ = userOBJ;
        this.choice = choice;
        this.qualityScoreOBJ = qualityScoreOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new QualityScore_F_Fragment(userOBJ, choice, qualityScoreOBJ);
        } else if (position == 1) {
            return new QualityScore_M_Fragment(userOBJ, choice, qualityScoreOBJ);
        }
        return new QualityScore_F_Fragment(userOBJ, choice, qualityScoreOBJ);
    }

    @Override
    public int getCount() {
        return mTitleArray.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleArray.get(position);
    }
}
