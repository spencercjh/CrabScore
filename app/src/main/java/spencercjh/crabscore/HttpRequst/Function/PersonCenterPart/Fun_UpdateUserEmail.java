package spencercjh.crabscore.HttpRequst.Function.PersonCenterPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.PersonCenterPart.UpdateUserEmail;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_UpdateUserEmail {
    public static boolean http_UpdateUserEmail(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateUserEmail;
        UpdateUserEmail thread = new UpdateUserEmail(url, userOBJ.getUser_name(), userOBJ.getPassword(), userOBJ.getEmail(), userOBJ.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
