package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryAllCompany;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryAllCompany {
    public static String http_QueryAllUnit() throws InterruptedException {
        String url = ServerURL.sever_url + "QueryAllCompany";
        QueryAllCompany thread = new QueryAllCompany(url);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
