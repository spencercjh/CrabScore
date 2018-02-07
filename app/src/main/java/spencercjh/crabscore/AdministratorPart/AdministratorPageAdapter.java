package spencercjh.crabscore.AdministratorPart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.UserOBJ;

class AdministratorPageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitleArray;
    private UserOBJ userOBJ = new UserOBJ();

    AdministratorPageAdapter(FragmentManager fm, ArrayList<String> titleArray, UserOBJ userOBJ) {
        super(fm);
        mTitleArray = titleArray;
        this.userOBJ = userOBJ;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UserAdminFragment(userOBJ);
        } else if (position == 1) {
            return new RegisterAssessmentFragment(userOBJ);
        } else if (position == 2) {
            return new CompanyAdminFragment(userOBJ);
        }else if(position==3){
            return new CompetitionAdminFragment(userOBJ);
        }
        return new UserAdminFragment(userOBJ);
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
