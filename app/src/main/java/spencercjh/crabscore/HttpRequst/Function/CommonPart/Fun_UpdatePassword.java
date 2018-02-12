package spencercjh.crabscore.HttpRequst.Function.CommonPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CommonPart.UpdatePassword;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/7.
 * iClass
 */

public class Fun_UpdatePassword {
    public static boolean http_UpdatePassword(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "UpdatePassword";
        UpdatePassword thread = new UpdatePassword(url, userOBJ.getUser_name(), userOBJ.getPassword(), userOBJ.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
