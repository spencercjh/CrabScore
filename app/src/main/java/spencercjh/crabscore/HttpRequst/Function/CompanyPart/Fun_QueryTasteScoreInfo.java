package spencercjh.crabscore.HttpRequst.Function.CompanyPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CompanyPart.QueryTasteScoreInfo;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_QueryTasteScoreInfo {
    public static String http_QueryTasteScoreInfo(TasteScoreOBJ tasteScoreOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryTasteScoreInfo";
        QueryTasteScoreInfo thread = new QueryTasteScoreInfo(url, tasteScoreOBJ.getGroup_id(), tasteScoreOBJ.getCompetition_id(), tasteScoreOBJ.getCrab_sex());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
