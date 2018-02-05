package spencercjh.crabscore.StaffPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;
import spencercjh.crabscore.R;

public class CrabList_M_Activity extends AppCompatActivity {
    ListView lv;
    private GroupOBJ groupOBJ = new GroupOBJ();
    private UserOBJ userOBJ = new UserOBJ();
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crablist__m);
        Intent intent = getIntent();
        groupOBJ = (GroupOBJ) intent.getSerializableExtra("GROUPOBJ");
        userOBJ = (UserOBJ) intent.getSerializableExtra("USEROBJ");
        choice = (int) intent.getSerializableExtra("USER");
        lv = (ListView) findViewById(R.id.crablist);
        Fill_CrabList();
    }

    private void Fill_CrabList() {
        /**
         * 涉及多表多数据的计算 此处网络线程后面再完善
         */
        final ArrayList<CrabOBJ> CrabList = new ArrayList<>();
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return CrabList.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = View.inflate(CrabList_M_Activity.this, R.layout.item_crab, null);
                } else {
                    view = convertView;
                }
                TextView text_carb_id = view.findViewById(R.id.text_crab_id);
                text_carb_id.setText("  " + position);
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CrabOBJ crabOBJ = CrabList.get(i);
                Intent intent = new Intent(CrabList_M_Activity.this, UpdateCrabInfoActivity.class);
                intent.putExtra("CRABOBJ", crabOBJ);
                intent.putExtra("GROUPOBJ", groupOBJ);
                intent.putExtra("USEROBJ", userOBJ);
                intent.putExtra("USER", choice);
                startActivity(intent);
                finish();
            }
        });
    }
}