package spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CheckScore_Ranking_Part.QueryTasteScore;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryTasteScore {
    public static String http_QueryTasteScore(int competition_id) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryTasteScore;
        QueryTasteScore thread = new QueryTasteScore(url, competition_id);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
