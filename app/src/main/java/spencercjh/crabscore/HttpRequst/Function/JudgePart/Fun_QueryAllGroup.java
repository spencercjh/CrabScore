package spencercjh.crabscore.HttpRequst.Function.JudgePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.JudgePart.QueryAllGroup;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_QueryAllGroup {
    public static String http_QueryAllGroup(int competition_id) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryAllGroup";
        QueryAllGroup thread = new QueryAllGroup(url, competition_id);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
