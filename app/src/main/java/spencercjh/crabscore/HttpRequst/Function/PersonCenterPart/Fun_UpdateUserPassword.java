package spencercjh.crabscore.HttpRequst.Function.PersonCenterPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.PersonCenterPart.UpdateUserPassword;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_UpdateUserPassword {
    public static boolean http_UpdateUserPassword(UserOBJ userOBJ, String new_password) throws InterruptedException {
        String url = ServerURL.sever_url + "UpdateUserPassword";
        UpdateUserPassword thread = new UpdateUserPassword(url, userOBJ.getUser_name(), userOBJ.getPassword(), new_password, userOBJ.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
