package spencercjh.crabscore.JudgePart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

class TasteGradePageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private UserOBJ userOBJ = new UserOBJ();
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();

    TasteGradePageAdapter(FragmentManager fm, ArrayList<String> titleArray, UserOBJ userOBJ, TasteScoreOBJ tasteScoreOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.userOBJ = userOBJ;
        this.tasteScoreOBJ = tasteScoreOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TasteGrade_F_Fragment(userOBJ, tasteScoreOBJ);
        } else if (position == 1) {
            return new TasteGrade_M_Fragment(userOBJ, tasteScoreOBJ);
        }
        return new TasteGrade_F_Fragment(userOBJ, tasteScoreOBJ);
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
