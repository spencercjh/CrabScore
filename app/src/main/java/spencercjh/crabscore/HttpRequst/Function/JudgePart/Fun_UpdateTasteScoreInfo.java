package spencercjh.crabscore.HttpRequst.Function.JudgePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.JudgePart.UpdateTasteScoreInfo;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_UpdateTasteScoreInfo {
    public static boolean http_UpdateTasteScoreInfo(TasteScoreOBJ tasteScoreOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + "UpdateTasteScoreInfo";
        UpdateTasteScoreInfo thread = new UpdateTasteScoreInfo(url, tasteScoreOBJ, update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
