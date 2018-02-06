package spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CheckScore_Ranking_Part.QueryCompanyName;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_QueryCompanyName {
    public static String http_QueryCompanyName(int company_id) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryCompanyName;
        QueryCompanyName thread = new QueryCompanyName(url, company_id);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getCompanyName();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
