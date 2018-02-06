package spencercjh.crabscore.JudgePart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

public class TasteGradePageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();

    public TasteGradePageAdapter(FragmentManager fm, ArrayList<String> titleArray, UserOBJ userOBJ, int choice, TasteScoreOBJ tasteScoreOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.userOBJ = userOBJ;
        this.choice = choice;
        this.tasteScoreOBJ = tasteScoreOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TasteGrade_F_Fragment(userOBJ, choice, tasteScoreOBJ);
        } else if (position == 1) {
            return new TasteGrade_M_Fragment(userOBJ, choice, tasteScoreOBJ);
        }
        return new TasteGrade_F_Fragment(userOBJ, choice, tasteScoreOBJ);
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