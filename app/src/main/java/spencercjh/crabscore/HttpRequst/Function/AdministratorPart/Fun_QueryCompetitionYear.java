package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryCompetitionYear;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_QueryCompetitionYear {
    public static String http_QueryCompetitionYear(int competition_id) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryCompetitionYear;
        QueryCompetitionYear thread = new QueryCompetitionYear(url, competition_id);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getCompetition_year();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
