package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdateCompetitionStatus;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_UpdateCompetitionStatus {
    public static boolean http_UpdateCompetitionStatus(CompetitionOBJ competitionOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + "UpdateCompetitionStatus";
        UpdateCompetitionStatus thread = new UpdateCompetitionStatus(url, competitionOBJ.getCompetition_id(), competitionOBJ.getStatus(), update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
