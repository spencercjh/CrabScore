package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryCompetitionProperty;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryCompetitionProperty {
    public static String http_QueryCompetitionProperty(String competition_year) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryCompetitionProperty";
        QueryCompetitionProperty thread = new QueryCompetitionProperty(url,competition_year);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
