package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdatePresentCompetition;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/7.
 * iClass
 */

public class Fun_UpdatePresentCompetition {
    public static boolean http_UpdatePresentCompetition(CompetitionOBJ competitionOBJ, UserOBJ update_user, int old_competition_id) throws InterruptedException {
        String url = ServerURL.sever_url + "UpdatePresentCompetition";
        UpdatePresentCompetition thread = new UpdatePresentCompetition(url, old_competition_id, update_user.getUser_name(), competitionOBJ.getCompetition_id());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
