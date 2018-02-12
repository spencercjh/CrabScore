package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryAllUser;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryAllUser {
    public static String http_QueryAllUser() throws InterruptedException {
        String url = ServerURL.sever_url + "QueryAllUser";
        QueryAllUser thread = new QueryAllUser(url);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
