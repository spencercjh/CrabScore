package spencercjh.crabscore.HttpRequst.Function.PersonCenterPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.PersonCenterPart.QueryUserProperty_Common;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryUserProperty_Common {
    public static String http_QueryUserProperty(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryUserProperty_Common;
        QueryUserProperty_Common thread = new QueryUserProperty_Common(url, userOBJ.getUser_name(), userOBJ.getPassword());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }

}
