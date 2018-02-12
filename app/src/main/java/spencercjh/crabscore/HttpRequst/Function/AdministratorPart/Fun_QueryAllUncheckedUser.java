package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryAllUncheckedUser;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryAllUncheckedUser {
    public static String http_QueryAllUncheckedUser() throws InterruptedException {
        String url = ServerURL.sever_url + "QueryAllUncheckedUser";
        QueryAllUncheckedUser thread = new QueryAllUncheckedUser(url);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
