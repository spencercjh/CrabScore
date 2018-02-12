package spencercjh.crabscore.HttpRequst.Function.CommonPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CommonPart.QueryUserProperty_Login;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryUserProperty_Login {
    public static String http_QueryUserProperty(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryUserProperty_Login";
        QueryUserProperty_Login thread = new QueryUserProperty_Login(url, userOBJ.getUser_name(), userOBJ.getDisplay_name());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }

}
