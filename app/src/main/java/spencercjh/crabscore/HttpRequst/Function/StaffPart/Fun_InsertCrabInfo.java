package spencercjh.crabscore.HttpRequst.Function.StaffPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.StaffPart.InsertCrabInfo;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_InsertCrabInfo {
    public static boolean http_InsertCrabInfo(CrabOBJ crabOBJ, UserOBJ create_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_InsertCrabInfo;
        InsertCrabInfo thread = new InsertCrabInfo(url, create_user.getUser_name(), crabOBJ.getCrab_sex(), crabOBJ.getGroup_id(), crabOBJ.getCompetition_id());
        thread.start();
        thread.join();
        return thread.getFlag();
    }

}
