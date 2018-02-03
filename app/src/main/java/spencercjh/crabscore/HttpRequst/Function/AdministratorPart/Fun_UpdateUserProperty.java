package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdateUserProperty;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_UpdateUserProperty {
    public static boolean http_UpdateUserProperty(UserOBJ userobj) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateUserProperty;
        UpdateUserProperty thread = new UpdateUserProperty(url, userobj.getUser_name(), userobj.getDisplay_name(), userobj.getEmail(), userobj.getRole_id());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
