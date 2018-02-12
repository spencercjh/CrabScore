package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryAllCompetition;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryAllCompetition {
    public static String http_QueryAllCompetition() throws InterruptedException {
        String url = ServerURL.sever_url + "QueryAllCompetition";
        QueryAllCompetition thread = new QueryAllCompetition(url);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
