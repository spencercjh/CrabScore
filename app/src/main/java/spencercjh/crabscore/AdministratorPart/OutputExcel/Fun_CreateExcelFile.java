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

public class Fun_CreateExcelFile {
    public static boolean http_CreateExcelFile(Context context, ArrayList<GroupOBJ> AllGroup, ArrayList<CrabOBJ> AllCrab,
                                               ArrayList<QualityScoreOBJ> AllQualityScore, ArrayList<TasteScoreOBJ> AllTasteScore,
                                               String competition_year) throws InterruptedException {
        Thread_CreateExcelFile thread = new Thread_CreateExcelFile(context, AllGroup, AllCrab, AllQualityScore, AllTasteScore, competition_year);
        thread.start();
        thread.join();
        return thread.isFlag();
    }
}
