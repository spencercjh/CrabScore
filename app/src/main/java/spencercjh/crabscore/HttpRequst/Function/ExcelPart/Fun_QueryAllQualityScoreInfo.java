package spencercjh.crabscore.HttpRequst.Function.ExcelPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.ExcelPart.QueryAllQualityScoreInfo;

/**
 * Created by spencercjh on 2018/2/11.
 * iClass
 */

public class Fun_QueryAllQualityScoreInfo {
    public static String http_QueryAllQualityScoreInfo(int competition_id) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryAllQualityScoreInfo";
        QueryAllQualityScoreInfo thread = new QueryAllQualityScoreInfo(url, competition_id);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
