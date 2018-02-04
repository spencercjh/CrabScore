package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdateUserStatus;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_UpdateUserStatus {
    public static boolean http_UpdateUserStatus(UserOBJ userOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateUserStatus;
        UpdateUserStatus thread = new UpdateUserStatus(url, userOBJ.getUser_name(), userOBJ.getStatus(), update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
