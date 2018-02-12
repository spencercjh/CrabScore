package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdateUserProperty;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_UpdateUserProperty {
    public static boolean http_UpdateUserProperty(UserOBJ userobj, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + "UpdateUserProperty";
        UpdateUserProperty thread = new UpdateUserProperty(url, userobj.getUser_name(), userobj.getDisplay_name(), userobj.getEmail(), userobj.getRole_id(), update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
