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

import spencercjh.crabscore.R;

@SuppressLint("ValidFragment")
public class FindIdentificationFragment extends Fragment implements View.OnClickListener {
    protected View mView;
    protected Context mContext;
    private EditText Eidentification;
    private TextView text_group_id;

    public FindIdentificationFragment() {
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

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            find_identification();
        }
    }

    private void find_identification() {
        String crab_label = Eidentification.getText().toString().trim();

    }
}