package spencercjh.crabscore.HttpRequst.Function.StaffPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.StaffPart.QueryGroupID;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_QueryGroupID {
    public static String http_QueryGroupID(String crab_label) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryGroupID;
        QueryGroupID thread = new QueryGroupID(url, crab_label);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
