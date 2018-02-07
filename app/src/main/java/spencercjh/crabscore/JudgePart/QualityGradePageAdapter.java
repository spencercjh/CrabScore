package spencercjh.crabscore.JudgePart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

class QualityGradePageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private UserOBJ userOBJ = new UserOBJ();
    private QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ();

    QualityGradePageAdapter(FragmentManager fm, ArrayList<String> titleArray, UserOBJ userOBJ, QualityScoreOBJ qualityScoreOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.userOBJ = userOBJ;
        this.qualityScoreOBJ = qualityScoreOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new QualityGrade_F_Fragment(userOBJ, qualityScoreOBJ);
        } else if (position == 1) {
            return new QualityGrade_M_Fragment(userOBJ, qualityScoreOBJ);
        }
        return new QualityGrade_F_Fragment(userOBJ, qualityScoreOBJ);
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
