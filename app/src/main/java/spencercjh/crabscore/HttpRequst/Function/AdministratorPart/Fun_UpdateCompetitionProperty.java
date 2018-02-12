package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdateCompetitionProperty;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_UpdateCompetitionProperty {
    public static boolean http_UpdateCompetitionProperty(CompetitionOBJ competitionOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + "UpdateCompetitionProperty";
        UpdateCompetitionProperty thread = new UpdateCompetitionProperty(url, update_user.getUser_name(), competitionOBJ);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
