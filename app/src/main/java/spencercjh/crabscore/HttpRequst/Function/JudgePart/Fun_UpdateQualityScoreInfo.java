package spencercjh.crabscore.HttpRequst.Function.JudgePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.JudgePart.UpdateQualityScoreInfo;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_UpdateQualityScoreInfo {
    public static boolean http_UpdateQualityScoreInfo(QualityScoreOBJ qualityScoreOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateQualityScoreInfo;
        UpdateQualityScoreInfo thread = new UpdateQualityScoreInfo(url, qualityScoreOBJ, update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
