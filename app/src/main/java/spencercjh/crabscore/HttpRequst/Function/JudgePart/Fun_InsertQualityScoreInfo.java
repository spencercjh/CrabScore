package spencercjh.crabscore.HttpRequst.Function.JudgePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.JudgePart.InsertQualityScoreInfo;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/12.
 * iClass
 */

public class Fun_InsertQualityScoreInfo {
    public static boolean http_InsertQualityScoreInfo(QualityScoreOBJ qualityScoreOBJ, UserOBJ create_user) throws InterruptedException {
        String url = ServerURL.sever_url + "InsertQualityScoreInfo";
        InsertQualityScoreInfo thread = new InsertQualityScoreInfo(url, create_user.getUser_name(), qualityScoreOBJ);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
