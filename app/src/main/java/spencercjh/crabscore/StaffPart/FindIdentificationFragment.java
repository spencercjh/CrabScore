package spencercjh.crabscore.StaffPart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import spencercjh.crabscore.HttpRequst.Function.JsonConvert;
import spencercjh.crabscore.HttpRequst.Function.StaffPart.Fun_QueryGroupID;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class FindIdentificationFragment extends Fragment implements View.OnClickListener {
    protected View mView;
    protected Context mContext;
    private EditText Eidentification;
    private TextView text_group_id;

    public FindIdentificationFragment(UserOBJ userOBJ) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_find_identification, container, false);
        Button button = mView.findViewById(R.id.button);
        button.setOnClickListener(this);
        Eidentification = mView.findViewById(R.id.edit_identification);
        text_group_id = mView.findViewById(R.id.text_group_id);
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.err.println("#################");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            try {
                find_identification();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void find_identification() throws InterruptedException {
        String crab_label = Eidentification.getText().toString().trim();
        ArrayList<GroupOBJ> GroupList = JsonConvert.convert_Group_List1(Fun_QueryGroupID.http_QueryGroupID(crab_label));
        if (GroupList.size() == 0) {
            String past = text_group_id.getText().toString();
            text_group_id.setText(String.format("%s  该标识不存在", past));
        } else {
            for (int i = 0; i < GroupList.size(); i++) {
                String past = text_group_id.getText().toString();
                GroupOBJ groupOBJ = GroupList.get(i);
                text_group_id.setText(past + "  第" + groupOBJ.getGroup_id() + "  组");
            }
        }
    }
}