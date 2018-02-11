package spencercjh.crabscore.AdministratorPart.OutputExcel;

import android.content.Context;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;

/**
 * Created by spencercjh on 2018/1/15.
 * iClass
 */

class Thread_CreateExcelFile extends Thread {
    ArrayList<GroupOBJ> AllGroup = new ArrayList<>();
    ArrayList<CrabOBJ> AllCrab = new ArrayList<>();
    ArrayList<QualityScoreOBJ> AllQualityScore = new ArrayList<>();
    ArrayList<TasteScoreOBJ> AllTasteScore = new ArrayList<>();
    private Context context;
    private boolean flag;
    private String competition_year;

    Thread_CreateExcelFile() {

    }

    Thread_CreateExcelFile(Context context, ArrayList<GroupOBJ> AllGroup, ArrayList<CrabOBJ> AllCrab,
                           ArrayList<QualityScoreOBJ> AllQualityScore, ArrayList<TasteScoreOBJ> AllTasteScore, String competition_year) {
        this.context = context;
        this.AllCrab = AllCrab;
        this.AllGroup = AllGroup;
        this.competition_year = competition_year;
    }

    /*在run中调用doGet*/
    @Override
    public void run() {
        try {
            doOutputExcelFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doOutputExcelFile() throws Exception {
        flag = CreateExcelFile.createExcelFile(context, AllGroup, AllCrab, AllQualityScore, AllTasteScore, competition_year);
    }

    public boolean isFlag() {
        return flag;
    }

}

