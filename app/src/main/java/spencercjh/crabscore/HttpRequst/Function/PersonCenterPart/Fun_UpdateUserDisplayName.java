package spencercjh.crabscore.HttpRequst.Function.PersonCenterPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.PersonCenterPart.UpdateUserDisplayName;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_UpdateUserDisplayName {
    public static boolean http_UpdateUserDisplayName(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateUserDisplayName;
        UpdateUserDisplayName thread = new UpdateUserDisplayName(url, userOBJ.getUser_name(), userOBJ.getPassword(), userOBJ.getDisplay_name(), userOBJ.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
