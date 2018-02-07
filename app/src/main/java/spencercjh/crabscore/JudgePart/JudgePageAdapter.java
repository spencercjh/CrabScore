package spencercjh.crabscore.JudgePart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.UserOBJ;

class JudgePageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private UserOBJ userOBJ = new UserOBJ();

    JudgePageAdapter(FragmentManager fm, ArrayList<String> titleArray, UserOBJ userOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.userOBJ = userOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new GradeFragment(userOBJ);
        }
        return new GradeFragment(userOBJ);
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
