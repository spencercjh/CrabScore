package spencercjh.crabscore.CheckScore_RankingPart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

class CheckScoreRankingPageAdapter extends FragmentPagerAdapter {
	private ArrayList<String> mTitleArray;
	CheckScoreRankingPageAdapter(FragmentManager fm, ArrayList<String> titleArray) {
		super(fm);
		mTitleArray = titleArray;
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			return new FatnessPrizeFragment();
		} else if (position == 1) {
			return new QualityPrizeFragment();
		}else if(position== 2){
            return new TastePrizeFragment();
        }
		return new FatnessPrizeFragment();
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
