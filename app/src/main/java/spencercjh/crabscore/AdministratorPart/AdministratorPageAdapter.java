package spencercjh.crabscore.AdministratorPart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.UserOBJ;

public class AdministratorPageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;

    public AdministratorPageAdapter(FragmentManager fm, ArrayList<String> titleArray, UserOBJ userOBJ, int choice) {
        super(fm);
        mTitleArray = titleArray;
        this.userOBJ = userOBJ;
        this.choice = choice;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UserAdminFragment(userOBJ, choice);
        } else if (position == 1) {
            return new RegisterAssessmentFragment(userOBJ, choice);
        } else if (position == 2) {
            return new CompanyAdminFragment(userOBJ, choice);
        }else if(position==3){
            return new CompetitionAdminFragment(userOBJ,choice);
        }
        return new UserAdminFragment(userOBJ, choice);
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
