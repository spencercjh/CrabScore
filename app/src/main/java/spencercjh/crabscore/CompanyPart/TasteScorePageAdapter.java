package spencercjh.crabscore.CompanyPart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.TasteScoreOBJ;

class TasteScorePageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();

    TasteScorePageAdapter(FragmentManager fm, ArrayList<String> titleArray, TasteScoreOBJ tasteScoreOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.tasteScoreOBJ = tasteScoreOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TasteScore_F_Fragment(tasteScoreOBJ);
        } else if (position == 1) {
            return new TasteScore_M_Fragment(tasteScoreOBJ);
        }
        return new TasteScore_F_Fragment(tasteScoreOBJ);
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
