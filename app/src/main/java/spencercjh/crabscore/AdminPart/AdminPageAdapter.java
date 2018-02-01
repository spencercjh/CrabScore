package spencercjh.crabscore.AdminPart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hp.iclass.OBJ.SubjectOBJ;
import com.example.hp.iclass.OBJ.TeacherOBJ;

import java.util.ArrayList;

public class AdminPageAdapter extends FragmentPagerAdapter {
	private ArrayList<String> mTitleArray;
	private TeacherOBJ teacherOBJ = new TeacherOBJ();
	private SubjectOBJ subjectOBJ = new SubjectOBJ();
	public AdminPageAdapter(FragmentManager fm, ArrayList<String> titleArray, SubjectOBJ subjectOBJ, TeacherOBJ teacherOBJ) {
		super(fm);
		mTitleArray = titleArray;
		this.teacherOBJ=teacherOBJ;
		this.subjectOBJ=subjectOBJ;
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			return new SignInAssessmentFragment(subjectOBJ,teacherOBJ);
		} else if (position == 1) {
			return new EntryUnitAdminFragment(subjectOBJ,teacherOBJ);
		}else if(position== 2){
            return new CompetitionAdminFragment(subjectOBJ,teacherOBJ);
        }
		return new SignInAssessmentFragment(subjectOBJ,teacherOBJ);
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
