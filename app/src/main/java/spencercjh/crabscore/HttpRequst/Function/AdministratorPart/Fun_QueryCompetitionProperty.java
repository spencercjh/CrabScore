package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryCompetitionProperty;
import spencercjh.crabscore.OBJ.Competition_Info_OBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryCompetitionProperty {
    public static String http_QueryCompetitionProperty(Competition_Info_OBJ competition_info_obj) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryCompetitionProperty;
        QueryCompetitionProperty thread = new QueryCompetitionProperty(url, competition_info_obj.getCompetition_year());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
