package spencercjh.crabscore.HttpRequst.Function.JudgePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.JudgePart.InsertTasteScoreInfo;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/12.
 * iClass
 */

public class Fun_InsertTasteScoreInfo {
    public static boolean http_InsertTasteScoreInfo(TasteScoreOBJ tasteScoreOBJ, UserOBJ create_user) throws InterruptedException {
        String url = ServerURL.sever_url + "InsertTasteScoreInfo";
        InsertTasteScoreInfo thread = new InsertTasteScoreInfo(url, create_user.getUser_name(), tasteScoreOBJ);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
