package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdateUserCompetitionID;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_UpdateUserCompetitionID {
    public static boolean http_UpdateUserCompetitionID(UserOBJ userOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateUserCompetitionID;
        UpdateUserCompetitionID thread = new UpdateUserCompetitionID(url, userOBJ.getUser_name(), userOBJ.getCompetition_id(), update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
