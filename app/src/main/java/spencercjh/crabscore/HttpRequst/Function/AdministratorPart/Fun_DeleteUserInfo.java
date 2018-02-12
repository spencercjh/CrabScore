package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.DeleteUserInfo;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_DeleteUserInfo {
    public static boolean http_DeleteUserInfo(UserOBJ userOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + "DeleteUserInfo";
        DeleteUserInfo thread = new DeleteUserInfo(url, userOBJ.getUser_name(), userOBJ.getDisplay_name(), update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
