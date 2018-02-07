package spencercjh.crabscore.CompanyPart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.QualityScoreOBJ;

class QualityScorePageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ();

    QualityScorePageAdapter(FragmentManager fm, ArrayList<String> titleArray, QualityScoreOBJ qualityScoreOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.qualityScoreOBJ = qualityScoreOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new QualityScore_F_Fragment(qualityScoreOBJ);
        } else if (position == 1) {
            return new QualityScore_M_Fragment(qualityScoreOBJ);
        }
        return new QualityScore_F_Fragment(qualityScoreOBJ);
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
